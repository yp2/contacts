package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;
import contacts.pendragon.com.pl.dbutils.DBModel;

import java.sql.SQLException;

/**
 * Created by daniel on 15.09.14.
 */
public class ForeignKeyField extends DBField<Integer> {

    //przerobic całe pole getValue musi zwracać string dla kompatyblinosci
    DBModel foreignKey;

    public ForeignKeyField(DBModel model){
        super();
        foreignKey = model;
        }

    public void setValue(Integer value){
        try {
            this.foreignKey.setPkField(value); // this will not hapend
        } catch (ValueToLongException e){}
    }

    public void setValue(String value){
        try{
            this.foreignKey.setPkField(Integer.parseInt(value));
        } catch (ValueToLongException e) {}
    }

    public void setValue(DBModel model) {
        this.foreignKey = model;
    }

    public Integer getValue() throws DBModelException{
        try {
            return this.foreignKey.get().getPkField();
        } catch (IllegalAccessException e){
            throw new DBModelException(e);
        } catch (SQLException e){
            throw  new DBModelException(e);
        }
    }

    public DBModel getForeignKey () throws DBModelException{
        try {
            return this.foreignKey.get();
        } catch (IllegalAccessException e){
            throw new DBModelException(e);
        } catch (SQLException e){
            throw  new DBModelException(e);
        }
    }

//    private DBModel foreignModel;
//
//    public ForeignKeyField(DBModel model){
//        super();
//        foreignModel = model;
//    }
//
//    public ForeignKeyField(DBModel model, Integer value){
//        super();
//        foreignModel = model;
//        this.setValue(value);
//    }
//
//    public ForeignKeyField(DBModel model, String value){
//        super();
//        foreignModel = model;
//        this.setValue(value);
//    }
//

//    @Override
//    public DBModel getValue(){
//        return foreignModel.get();
//    }
}
