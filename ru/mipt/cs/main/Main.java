package ru.mipt.cs.main;

import ru.mipt.cs.matrix.MSetter;
import ru.mipt.cs.matrix.Matrix;
import ru.mipt.cs.matrix.sub.Geom.GeomVector;

/**
 * Created by 1 on 09.03.14.
 */
public class Main {
    public static void main(String args[]){
        Matrix m1=new Matrix();
        MSetter.dialogSet(m1,5,5);
        m1.printConsole();
        Matrix m2=new Matrix();
        MSetter.consoleSet(m2,3,3);
        m2.printConsole();
       /* Matrix M1=new Matrix(3,3);
        M1.consoleSet();
        M1.printConsole();
        Matrix M2=new Matrix(3,3);
        M2.consoleSet();
        M2.printConsole();
        Matrix M3;
        M3=Matrix.mult(M1,M2);
        M3.printConsole();
        (M3.rMult(M1.Transpose())).printConsole();
        M1.gauss().printConsole();
        System.out.println("det is " + M1.det());
        GeomVector V1=new GeomVector(1,0,0);
        GeomVector V2=new GeomVector(0,1,0);
        V1.crossProduct(V2).printConsole();*/
    }
}
