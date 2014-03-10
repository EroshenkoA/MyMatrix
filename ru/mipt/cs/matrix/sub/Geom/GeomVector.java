package ru.mipt.cs.matrix.sub.Geom;

import ru.mipt.cs.matrix.sub.MyVector;

/**
 * Created by 1 on 10.03.14.
 */
public class GeomVector extends MyVector {
    public GeomVector(){
        super(3);
    }
    public GeomVector(double x,double y,double z){
        super(3);
        elements[0][0]=x;
        elements[1][0]=y;
        elements[2][0]=z;
    }
    public void Setx(double x){
        elements[0][0]=x;
    }
    public void Sety(double x){
        elements[1][0]=x;
    }
    public void Setz(double x){
        elements[2][0]=x;
    }
    public double x(){
        return elements[0][0];
    }
    public double y(){
        return elements[1][0];
    }
    public double z(){
        return elements[2][0];
    }
    public GeomVector CrossProduct(GeomVector v){
        GeomVector vector=new GeomVector(elements[1][0]*v.z()-elements[2][0]*v.y(),
                v.x()*elements[2][0]-v.z()*elements[0][0], elements[0][0]*v.y()-elements[1][0]*v.x());
        return vector;
    }

}
