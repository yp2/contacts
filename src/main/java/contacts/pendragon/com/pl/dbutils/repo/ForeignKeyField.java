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

    public ForeignKeyField(DBModel model) {
        super();
        foreignKey = model;
    }

    public void setValue(Integer value) {
        try {
            this.foreignKey.setPkField(value); // this will not hapend
        } catch (ValueToLongException e) {
        }
    }

    public void setValue(String value) {
        try {
            this.foreignKey.setPkField(Integer.parseInt(value));
        } catch (ValueToLongException e) {
        }
    }

    public Integer getValue() throws DBModelException {
        try {
            return this.foreignKey.get().getPkField();
        } catch (IllegalAccessException | SQLException e) {
            throw new DBModelException(e.toString());
        }
    }

    public void setValue(DBModel model) {
        this.foreignKey = model;
    }

    public DBModel getForeignModel() throws DBModelException {
        try {
            return this.foreignKey.get();
        } catch (IllegalAccessException | SQLException e) {
            throw new DBModelException(e.toString());
        }
    }
}
