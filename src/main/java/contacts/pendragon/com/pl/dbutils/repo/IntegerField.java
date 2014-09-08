package contacts.pendragon.com.pl.dbutils.repo;

import contacts.pendragon.com.pl.dbutils.DBField;

/**
 * Created by daniel on 08.09.14.
 */

public class IntegerField extends DBField<Integer>{
    public IntegerField(Integer value){
        super(value, 6);
    }
    public IntegerField(Integer value, Integer size){
        super(value, size);
    }
}
