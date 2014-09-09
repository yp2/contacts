package contacts.pendragon.com.pl.dbutils.repo;

/**
 * Created by daniel on 09.09.14.
 */
public class PrimaryKeyField extends IntegerField {

    public PrimaryKeyField(Integer value){
        super(value);
    }
    public PrimaryKeyField(Integer value, Integer size){
        super(value, size);
    }

}
