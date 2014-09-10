package contacts.pendragon.com.pl.dbutils;

import contacts.pendragon.com.pl.dbutils.factory.DBFactory;
import contacts.pendragon.com.pl.dbutils.factory.SQLDictFactory;
import contacts.pendragon.com.pl.dbutils.repo.PrimaryKeyField;
import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

import java.sql.Statement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by daniel on 08.09.14.
 */
public abstract class DBModel {
    //    public abstract String getSQLInsertStatment();

    private String model;
    private DBFactory dbFactory = new DBFactory();
    private SQLDictFactory sqlDictFactory = new SQLDictFactory();
    private PrimaryKeyField pkField;
    private List<Field> modelFields;

    public DBModel(String table) throws IllegalAccessException {
        // TODO: moze uzyc this.model = this.getClass().getName().upper()
        this.model = table;

    }

    public Integer getId(){
        return this.pkField.getValue();
    }
    public void setPkField(Integer id) throws ValueToLongException{
        this.pkField.setValue(id);
    }

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
                pkField = (PrimaryKeyField) f.get(this);
            }
        }
        return modelFields;
    }

//    private List<Field> getNotNullFields() throws IllegalAccessException{
//        List<Field> fields = new LinkedList<>();
//        for (Field f : this.modelFields){
//            DBField dbField = (DBField) f.get(this);
//            if (dbField.getValue() != null){
//                fields.add(f);
//            }
//        }
//        return fields;
//    }

    private List<Field> getNotNullFields() throws IllegalAccessException{
        List<Field> fields = this.getFields();
        for(int i = 0; i< fields.size(); i=i+1){
            DBField dbField = (DBField) fields.get(i).get(this);
            if (dbField.getValue() == null){
                fields.remove(i);
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
//        List<Field> fields = this.getNotNullFields();
//        List<DBField> notNullDBFields = new LinkedList<DBField>();
//        for (Field f: fields){
//            //geting object of DBField for this object; casting is crucial
//            //alter that we obtain instances of field for specific model
//            DBField notNullDBField = (DBField) f.get(this);
//            if (notNullDBField.getValue() != null){
//                notNullDBFields.add(notNullDBField);
//                System.out.println(f.getName());
//            }
//        }
//        return notNullDBFields;
    }

    /**
     *
     */
    private String getInsertStatmant() throws IllegalAccessException{
        SQLDict sqlDict = sqlDictFactory.getSQLDict();
        String baseSqlStatment = sqlDict.insertStatment;
        String sql;
        StringBuilder sqlColumns = new StringBuilder();
        StringBuilder sqlValues = new StringBuilder();

        List<Field> fields = this.getNotNullFields(); //not null fields
        int listSize = fields.size() - 1 ;
        for (int i = 0; i <= listSize; i= i + 1 ){
            if (i != listSize ){
                sqlColumns.append(String.format(sqlDict.column, fields.get(i).getName()).toUpperCase());
                sqlValues.append(sqlDict.value);
            } else {
                sqlColumns.append(String.format(sqlDict.columnLast, fields.get(i).getName()).toUpperCase());
                sqlValues.append(sqlDict.valueLast);
            }
        }
        sql = String.format(baseSqlStatment, this.model.toUpperCase(),
                sqlColumns.toString(), sqlValues.toString());

//        stat = dbConn.prepareStatement(sql);
//
//        List<DBField> dbFields = this.getDBFields(fields);
//
//        for (int i = 0; i<=listSize; i = i + 1){
//            // incrementing i by 1 for PreparedStatment
//            stat.setString((i+1), (String) dbFields.get(i).getValue());
//        }

        return sql;
    }

    private void runInsert(Connection dbConn) throws IllegalAccessException, SQLException{
        String sql = this.getInsertStatmant();
        List<Field> fields = this.getFields();
        List<DBField> dbFields = this.getDBFields(fields);
        // tu zamykamy statment a w save zamykamy connection
        try(PreparedStatement stat = dbConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            // i = 1 for prepareStatmant usage
            for (int i=1; i <= dbFields.size(); i = i + 1){
                stat.setString(i, (String) dbFields.get(i).getValue());
            }
        }


        // try with resource to create and close prepared statment
    }

    private void getUpdateStatment(){

    }

    /**
     * Metoda save zarządza connection !!!
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public void save() throws SQLException, IllegalAccessException{
        //try with resources to create and close connection
//        try (Connection dbConn = )
//        this.setDB();
        System.out.println(getInsertStatmant());
//        if (this.pkField.getValue() != null){
//            System.out.println(this.pkField.getValue());
//        }
    }

//    public void getSQLInsertStatment() throws ClassNotFoundException, IllegalAccessException {
//
//        List<Field> fields = this.getFields();
//        StringBuilder sql = new StringBuilder(this.insert);
//        StringBuilder sqlColumns = new StringBuilder();
//        StringBuilder sqlValues = new StringBuilder();
//
//        sql.append(this.model);
//        for (Field field : fields){
//           DBField f = (DBField) field.get(this); //casting important this object to get value
//           if (f.getValue() != null) {
//               sqlColumns.append(field.getName());
//               sqlColumns.append(",");
//               sqlValues.append("?,");
//           }
//        }
//
//        sql.append("(");
//        sql.append(sqlColumns);
//        sql.append(")");
//        sql.append(" VALUES ");
//        sql.append("(");
//        sql.append(sqlValues);
//        sql.append(")");
//        System.out.println(sql.toString());
//
//
//    }

    public void insertToDB() throws SQLException {
        DBFactory factory = new DBFactory();

        try (Connection conn = factory.getDBConnection()) {

        }
    }
}
