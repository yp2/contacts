package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;

/**
 * Created by daniel on 09.09.14.
 */
public class PrimaryKeyField extends DBField<Integer> {

    public PrimaryKeyField(Integer value){
        super(value);
    }
    public PrimaryKeyField(Integer value, Integer size){
        super(value, size);
    }

}
