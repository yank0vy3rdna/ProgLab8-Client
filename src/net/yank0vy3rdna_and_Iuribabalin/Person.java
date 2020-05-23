package net.yank0vy3rdna_and_Iuribabalin;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Date birthday; //Поле может быть null
    private long height; //Значение поля должно быть больше 0
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public long getHeight() {
        return height;
    }

    public Long getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }
    @Override
    public int hashCode() {
        return weight.hashCode()
                * Long.valueOf(height).hashCode()
                * birthday.hashCode()
                * location.hashCode();
    }
    public Person(String name, Date birthday, long height, Long weight, Location location) throws IllegalArgumentException, NullPointerException {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Invalid name");
        }
        if (height <= 0){
            throw new IllegalArgumentException("Invalid height");
        }
        if (weight == null || weight<=0){
            throw new IllegalArgumentException("Invalid weight");
        }
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }
}
