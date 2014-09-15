package contacts.pendragon.com.pl.dbutils;

import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.DBModelException;
import contacts.pendragon.com.pl.dbutils.repo.ForeignKeyField;
import contacts.pendragon.com.pl.dbutils.repo.PrimaryKeyField;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel on 08.09.14.
 */
public abstract class DBModel {
    //    public abstract String getSQLInsertStatment();

    private String model;
    private DBFactory dbFactory = new DBFactory();
    private SQLDictFactory sqlDictFactory = new SQLDictFactory();
    private PrimaryKeyField pkField;
    private String pkFieldName;
    private List<Field> modelFields;

    public DBModel() {
        String [] split = this.getClass().getName().split("\\.");
        this.model = split[split.length-1].toLowerCase();
    }

    public String getModel () {
        return this.model;
    }

    public void setFields() throws IllegalAccessException{
        modelFields = getFields();
    }

    // this is not needed method
    public void setPkField(Integer id) throws ValueToLongException{
        this.pkField.setValue(id);
    }

    public Integer getPkField() throws DBModelException { return this.pkField.getValue();}

    private List<Field> getFields () throws IllegalAccessException{
        Class cl = this.getClass();
        Field[] fields = cl.getFields();
        List<Field> modelFields= new LinkedList<Field>();
        for (Field f: fields){
            Class fSuperclass = f.getType().getSuperclass();
            Class fClass = f.getType();

            if (fSuperclass == DBField.class && fClass != PrimaryKeyField.class){
                modelFields.add(f);
            }

            // setting primary key field for model
            if (fClass == PrimaryKeyField.class){
                pkFieldName = f.getName();
                pkField = (PrimaryKeyField) f.get(this);
            }
        }
        return modelFields;
    }

    private List<Field> getNotNullFields(List<Field> modelFields)
            throws IllegalAccessException, DBModelException{
        List<Field> fields = new LinkedList<>();
        for (Field mf: modelFields){
            DBField dbField = (DBField) mf.get(this);
            if (dbField.getValue() != null){
                fields.add(mf);
            }
        }
        return fields;
    }

    private List<DBField> getDBFields(List<Field> fields) throws IllegalAccessException{
        List<DBField> dbFields = new LinkedList<>();
        for (Field field: fields){
            //geting object of DBField for this object; casting is crucial
            //alter that we obtain instances of field for specific model
            DBField dbField = (DBField) field.get(this);
            dbFields.add(dbField);
        }
        return dbFields;
    }

    private String getInsertStatmant()
            throws IllegalAccessException, DBModelException
    {
        SQLDict sqlDict = sqlDictFactory.getSQLDict();
        String baseSqlStatment = sqlDict.insertStatment;
        String sql;
        StringBuilder sqlColumns = new StringBuilder();
        StringBuilder sqlValues = new StringBuilder();

        List<Field> fields = this.getNotNullFields(modelFields); //not null fields
        int listSize = fields.size() - 1 ;
        for (int i = 0; i <= listSize; i= i + 1 ){
            if (i < listSize ){
                sqlColumns.append(String.format(sqlDict.column,
                        fields.get(i).getName()).toUpperCase());
                sqlValues.append(sqlDict.value);
            } else {
                sqlColumns.append(String.format(sqlDict.columnLast,
                        fields.get(i).getName()).toUpperCase());
                sqlValues.append(sqlDict.valueLast);
            }
        }
        sql = String.format(baseSqlStatment, this.model.toUpperCase(),
                sqlColumns.toString(), sqlValues.toString());

        return sql;
    }

//    private String getPKFieldName(){
//        Class cl = this.getClass();
//    }

    private String getUpdateStatment()
            throws  IllegalAccessException, DBModelException
    {
        SQLDict sqlDict = sqlDictFactory.getSQLDict();
        String baseSqlStatment = sqlDict.updateStatment;
        String sql;
        StringBuilder sqlWhere = new StringBuilder();
        StringBuilder sqlSet = new StringBuilder();

        List<Field> fields = this.getNotNullFields(modelFields);
        int listSize = fields.size() - 1;
        for (int i = 0; i <= listSize; i = i +1 ){
            if (i < listSize){
                sqlSet.append(String.format(sqlDict.columnSet,
                        fields.get(i).getName()).toUpperCase());
            } else {
                sqlSet.append(String.format(sqlDict.columnSetLast,
                        fields.get(i).getName()).toUpperCase());
            }
        }

        sqlWhere.append(String.format(sqlDict.whereLast,
                this.pkFieldName.toUpperCase()));

        sql = String.format(baseSqlStatment, this.model.toUpperCase(),
                sqlSet.toString(), sqlWhere.toString());

        return sql;
    }

    private String getSelectSimpleStatment(String [] order_by, String sort_type)
            throws IllegalAccessException, DBModelException
    {
        SQLDict sqlDict = sqlDictFactory.getSQLDict();
        String baseSqlStatment;
        String sql;
        StringBuilder sqlOrder = new StringBuilder();
        StringBuilder sqlWhere = new StringBuilder();
        Integer pkValue = pkField.getValue();

        List<Field> fields = this.getNotNullFields(modelFields);
        List<DBField> dbFields = this.getDBFields(fields);
        int listSize = fields.size() - 1;

        for (int i = 0; i <= listSize; i = i +1){
            if (i == 0){
                if (dbFields.get(i).getClass() == ForeignKeyField.class){
                    sqlWhere.append(String.format(sqlDict.selectWhereIntFirst,
                            fields.get(i).getName().toUpperCase()));
                } else {
                    sqlWhere.append(String.format(sqlDict.selectWhereFirst,
                            fields.get(i).getName().toUpperCase()));
                }
            } else if (i == listSize) {
                if (dbFields.get(i).getClass() == ForeignKeyField.class){
                    sqlWhere.append(String.format(sqlDict.selectWhereIntLast,
                            fields.get(i).getName().toUpperCase()));
                } else {
                    sqlWhere.append(String.format(sqlDict.selectWhereLast,
                            fields.get(i).getName().toUpperCase()));
                }
            } else {
                if (dbFields.get(i).getClass() == ForeignKeyField.class){
                    sqlWhere.append(String.format(sqlDict.selectWhereInt,
                            fields.get(i).getName().toUpperCase()));
                } else {
                    sqlWhere.append(String.format(sqlDict.selectWhere,
                            fields.get(i).getName().toUpperCase()));
                }
            }
        }

        if (pkValue != null) {
            listSize = listSize+1;
            // primary key is set - we add it to sqlWhere
            if (listSize == 0) {
                sqlWhere.append(String.format(sqlDict.selectWhereIntFirst,
                        this.pkFieldName.toUpperCase()));
            } else {
                sqlWhere.append(String.format(sqlDict.selectWhereIntLast,
                        this.pkFieldName.toUpperCase()));
            }
        }

        // setting order by
        if (order_by != null && order_by.length > 0){
            int orderSize = order_by.length - 1 ;
            // ordered statmanet
            for (int i = 0; i <= orderSize; i = i +1){
                if (i < orderSize) {
                    sqlOrder.append(String.format(sqlDict.column, order_by[i].toUpperCase()));
                } else {
                    sqlOrder.append(String.format(sqlDict.columnLast, order_by[i].toUpperCase()));
                }
            }
            // sort type setting
            if (sort_type == null){
                sqlOrder.append(sqlDict.sortASC);
            } else {
                sqlOrder.append(sort_type);
            }

            baseSqlStatment =  sqlDict.selectSimpleStatmentOrdered;
            sql = String.format(baseSqlStatment,
                    this.model.toUpperCase(), sqlWhere.toString(), sqlOrder.toString());
        } else {
            // non ordered statment
            baseSqlStatment =  sqlDict.selectSimpleStatment;
            sql = String.format(baseSqlStatment,
                    this.model.toUpperCase(), sqlWhere.toString());
        }
        return sql;
    }

    private List<DBModel> runSelectSimple(Connection dbConn, String [] order_by, String sort_type)
        throws IllegalAccessException, SQLException, DBModelException
    {
        String sql = this.getSelectSimpleStatment(order_by, sort_type);
        List<Field> fields = this.getNotNullFields(this.modelFields);
        List<DBField> dbFields = this.getDBFields(fields);
        Class modelClass = this.getClass();
        Constructor [] ctorArray = modelClass.getConstructors();
        List<DBModel> querySet = new LinkedList<>();

        // here we close statment the connection must be close in method invoking this method
        try(PreparedStatement stmt = dbConn.prepareStatement(sql)) {

            int listSize = dbFields.size();
            for (int i = 0; i < listSize; i = i+1){
                try{ // dirty solution; better checkig the filed type
                    stmt.setString((i+1), (String) dbFields.get(i).getValue());
                } catch (ClassCastException e) {
                    // we have integer field
                    stmt.setInt((i+1), (Integer) dbFields.get(i).getValue());
                }
            }

            // settin value for pk - this will by always last position
            if (pkField.getValue() != null){
                stmt.setInt((listSize+1), pkField.getValue());
            }

            try{
                Constructor ctor = modelClass.getConstructor(String[].class);
                try(ResultSet rs = stmt.executeQuery()){
                    int columnCount = rs.getMetaData().getColumnCount();
                    while (rs.next()){
                        String [] values = new String[columnCount];
                        try {

                            for (int i = 0; i < columnCount; i++) {
                                if (rs.getString(i+1) == "null"){
                                    values[i] = null;
                                } else {
                                    values[i] = rs.getString(i+1);
                                }
                            }
                            querySet.add((DBModel) ctor.newInstance(new Object[] {values}));
                        }
                        catch (InvocationTargetException e) {throw new DBModelException(e);}
                        catch (InstantiationException e){throw new DBModelException(e);}
                    }
                }
            } catch (NoSuchMethodException e) {throw new DBModelException(e);}
        }
        return querySet;
    }


    private void runInsert(Connection dbConn)
            throws IllegalAccessException, SQLException, DBModelException
    {
        String sql = this.getInsertStatmant();
        List<Field> fields = this.getNotNullFields(this.modelFields);
        List<DBField> dbFields = this.getDBFields(fields);

        // here we close statment the connection must be close in method invoking this method
        try(PreparedStatement stmt = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            for (int i=0; i < dbFields.size(); i = i + 1){
                // fk must be int type
                if (dbFields.get(i).getClass() == ForeignKeyField.class){
                    stmt.setInt((i + 1), (Integer) dbFields.get(i).getValue());
                } else {
                    stmt.setString((i+1), (String) dbFields.get(i).getValue());
                }
            }
            stmt.executeUpdate();
            ResultSet rsId = stmt.getGeneratedKeys();
            if (rsId.next()){
                try {
                    this.pkField.setValue(rsId.getInt(1)); //there will be no error
                } catch (ValueToLongException ex){}
            }
        }
    }

    private void runUpdate(Connection dbConn)
            throws IllegalAccessException, SQLException, DBModelException{
        String sql = this.getUpdateStatment();
        List<Field> fields = this.getNotNullFields(this.modelFields);
        List<DBField> dbFields = this.getDBFields(fields);

        // here we close statment the connection must be close in method invoking this method
        try(PreparedStatement stmt = dbConn.prepareStatement(sql)){
            int listSize = dbFields.size();
            for (int i = 0; i < listSize; i = i + 1){
                // fk must be int type
                if (dbFields.get(i).getClass() == ForeignKeyField.class){
                    stmt.setInt((i+1), (Integer) dbFields.get(i).getValue());
                } else {
                    stmt.setString((i+1), (String) dbFields.get(i).getValue());
                }
            }
            // setting where clause to pkField value - primary key
            stmt.setInt((listSize+1), pkField.getValue());
            stmt.executeUpdate();
            //setting the pkField is not needed, we all ready have this value
        }
    }

    public void save()
            throws SQLException, IllegalAccessException, DBModelException
    {
        try(Connection conn = dbFactory.getDBConnection()){
            if (pkField.getValue() == null){
                // primary key not set = Insert new object to db
                this.runInsert(conn);
            } else {
                // primary key has value = Update objcet in db
                this.runUpdate(conn);
            }
        }
    }

    private List<DBModel> sQuery(String [] order_by, String sort_type)
            throws IllegalAccessException, SQLException, DBModelException
    {
        List<DBModel> querySet;
        try(Connection conn = dbFactory.getDBConnection()){
            querySet = this.runSelectSimple(conn, order_by, sort_type);
        }
        return querySet;
    }

    public List<DBModel> simpleQuery()
            throws IllegalAccessException, SQLException, DBModelException
    {
        return this.sQuery(null, null);
    }

    public List<DBModel> simpleQuery(String [] order_by, String sort_type)
            throws IllegalAccessException, SQLException, DBModelException
    {
        return this.sQuery(order_by, sort_type);
    }

    public List<DBModel> simpleQuery(String order_by, String sort_type)
            throws IllegalAccessException, SQLException, DBModelException
    {
        String[] order_array = {order_by};
        return  this.sQuery(order_array, sort_type);
    }

    public List<DBModel> simpleQuery(String [] order_by)
            throws IllegalAccessException, SQLException, DBModelException
    {
        return this.sQuery(order_by, null);
    }

    public List<DBModel> simpleQuery(String order_by)
            throws IllegalAccessException, SQLException, DBModelException
    {
        String[] order_array = {order_by};
        return this.sQuery(order_array, null);
    }

    public DBModel get()
            throws IllegalAccessException, SQLException, DBModelException
    {
        List<DBModel> qs = this.sQuery(null, null);
        DBModel result;
        if (qs.size() == 1){
            result = qs.get(0);
        } else {
            throw new DBModelException("Query return multiple result");
        }
        return result;
    }

}
