package net.yank0vy3rdna_and_Iuribabalin;

import java.io.Serializable;

public class Location implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private double x;
    private float y;

    public double getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public Location(double x, float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode()*Float.valueOf(y).hashCode()*z.hashCode()* name.hashCode();
    }
    private Long z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 817, Поле не может быть null
}
