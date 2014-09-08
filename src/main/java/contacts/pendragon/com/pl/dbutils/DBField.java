package contacts.pendragon.com.pl.dbutils;

import contacts.pendragon.com.pl.dbutils.repo.ValueToLongException;

/**
 * Created by daniel on 08.09.14.
 */
public abstract class DBField<T> {
    protected Integer size;
    protected T value;


    public DBField(){
        this.size = 6;
    }

    public DBField(T value){
        this();
        this.value = value;
    }

    public DBField(T value, Integer size){
        this.size = size;
        this.value = value;
    }

    public void setSize(Integer size){
        this.size = size;
    }

    public Integer getSize(){
        return this.size;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value) throws ValueToLongException{
        this.value = value;
    }

}
