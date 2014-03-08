package ru.mipt.cs.main;

import ru.mipt.cs.matrix.Matrix;

/**
 * Created by 1 on 09.03.14.
 */
public class Main {
    public static void main(String args[]){
        Matrix M1=new Matrix(2,3);
        M1.ConsoleSet();
        M1.PrintConsole();
        Matrix M2=new Matrix(3,3);
        M2.ConsoleSet();
        M2.PrintConsole();
        Matrix M3;
        M3=Matrix.Mult(M1,M2);
        M3.PrintConsole();
        (M3.RMult(M1.Transpose())).PrintConsole();
        M1.Gauss().PrintConsole();

    }
}
