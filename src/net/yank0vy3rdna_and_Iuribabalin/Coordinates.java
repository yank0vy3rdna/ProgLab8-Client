package net.yank0vy3rdna_and_Iuribabalin;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private Double x; //Поле не может быть null
    private float y;

    public Double getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    @Override
    public int hashCode() {
        return (int) x.hashCode() *  Float.valueOf(y).hashCode();
    }
    public Coordinates(Double x, float y) throws IllegalArgumentException, NullPointerException{
        this.x = Objects.requireNonNull(x,"invalid x");
        this.y = y;
    }
}
