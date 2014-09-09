package contacts.pendragon.com.pl.dbutils;

import contacts.pendragon.com.pl.dbutils.factory.DBFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel on 08.09.14.
 */
public abstract class DBModel {
    //    public abstract String getSQLInsertStatment();

    private String model;
    private Connection dbConn;
    private DBFactory dbFactory = new DBFactory();

    public DBModel(String table){
        this.model = table;

    }

    private List<Field> getFields (){
        Class cl = this.getClass();
        Field[] fields = cl.getFields();
        List<Field> filedsDBField= new LinkedList<Field>();
        for (Field f: fields){
            if (f.getType().getSuperclass() == DBField.class){
                filedsDBField.add(f);
            }
        }
        return filedsDBField;
    }

    private List<DBField> getNotNullFields() throws IllegalAccessException{
        List<Field> fields = this.getFields();
        List<DBField> notNullFields = new LinkedList<DBField>();
        for (Field f: fields){
            //geting object of DBField for this object; casting is crucial
            //alter that we obtain instances of field for specific model
            DBField notNullField = (DBField) f.get(this);
            if (notNullField.getValue() != null){
                notNullFields.add(notNullField);
            }
        }
        return notNullFields;
    }

    private void getInsertStatment(){

    }

    private void getUpdateStatment(){

    }

    public void save() throws SQLException{
        dbConn = dbFactory.getDBConnection();

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
