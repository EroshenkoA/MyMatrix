package ru.mipt.cs.matrix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by 1 on 08.03.14.
 */
public class Matrix {
    protected int rows;
    protected int columns;
    protected double determinant;
    protected double elements[][];
    private class Pair{
        double x;
        double y;
        Pair(double a, double b){
            x=a;
            y=b;
        }
    }
    public Matrix(){
    }
    public void setMatrix(int rows, int columns){
        if ((rows<1)||(columns<1)) {
            System.out.println("number of rows and columns must be positive integer");
        }
        this.rows=rows;
        this.columns=columns;
        elements=new double[rows][columns];
        if (columns==rows){
            determinant=1;
        }
    }
    public Matrix(int rows,int columns){
       setMatrix(rows, columns);
    }
    public double get(int i, int j){
        if ((i>0)&&(i<=rows)&&(j>=0)&&(j<=columns)){
            return elements[i-1][j-1];
        }else{
            System.out.println("got wrong indices, or matrix was not initialized");
            return -1;
        }
    }
    public void set(int i, int j, double r){
        if ((i>0)&&(i<=rows)&&(j>=0)&&(j<=columns)){
            this.elements[i-1][j-1]=r;
        }else{
            System.out.println("wrong indices, or matrix was not initialized");
        }
    }
    public int getColumns(){
        return columns;
    }
    public int getRows(){
        return rows;
    }
    public Matrix rMult(Matrix M){
        if (columns==M.getRows()){
            int i=1; int j=1; int k=1;double s=0;
            int c=M.getColumns();
            Matrix S=new Matrix(rows,c);
            while (i<=rows){
                while (j<=c){
                    while (k<=columns){
                        s+=elements[i-1][k-1]*M.get(k, j);
                        k++;
                    }
                    k=1;
                    S.set(i, j, s);
                    s=0;
                    j++;
                }
                j=1;
                i++;
            }
            return S;
        }else{
            System.out.println("Wrong sizes, Matrices can not be multed");
            return null;
        }
    }
    public static Matrix mult(Matrix A, Matrix B){
        return A.rMult(B);
    }
    public Matrix transpose(){
        Matrix A=new Matrix(columns,rows);
        int i=1; int j=1;
        while (i<=rows){
            while (j<=columns){
                A.set(j, i, elements[i - 1][j - 1]);
                j++;
            }
            j=1;
            i++;
        }
        return A;
    }
    public void swapRows(int i, int j){
        if (i!=j){
            double s;
            for (int k=0; k<columns; k++){
                s=elements[i][k];
                elements[i][k]=elements[j][k];
                elements[j][k]=s;
            }
            determinant*=-1;
        }
    }
    private void step(int k, int m){//elements[k][m] must be nonzero
    //rationalizes below k row, makes zeros in the m column
        int i;int j;
        double s;
        if ((k+1)!=rows){
            for (i=k+1;i<rows;i++){
                s=elements[i][m]/elements[k][m];
                for (j=m;j<columns;j++){
                    elements[i][j]-=s*elements[k][j];
                }
            }

        }
    }
    private Pair findFirstNonzeroColumn(int i, int j){//in column j after i-1
        Pair p=new Pair(-1,j);
        int k=i;
        if (j>=rows){
            p.x=i;
            p.y=-2;
            return p;
        }
        while (elements[k][j]==0){
            if ((k+1)<rows) k++;
            else break;
        }
        if (k<rows) {
            swapRows(i, k);
            p.x=k;
            p.y=j;
            return p;
        }
        return p;
    }
    private Pair findFirstNonzero(int i, int j){
        Pair p=new Pair(0,0);
        if ((p= findFirstNonzeroColumn(i, j)).x==-1){
            return findFirstNonzero(i, j + 1);
        }else{
            return p;//will return -2 in case of all zeros
        }
    }
    private void descend(int i, int j){
        Pair p=new Pair(0,0);

        if ((p= findFirstNonzero(i, j)).y!=-2){
            step((int) p.x, (int) p.y);
            descend((int) p.x + 1, (int) p.y + 1);
        }
    }
    public void copy(Matrix M){
        int i=1; int j=1;
        while (i<=rows){
            while (j<=columns){
                set(i, j, M.get(i, j));
                j++;
            }
            j=1;
            i++;
        }
    }
    public Matrix gauss(){
        Matrix M=new Matrix(rows,columns);
        M.copy(this);
        M.descend(0, 0);
        for (int i=1; i<=Math.min(rows,columns); i++){
            M.determinant*=M.get(i, i);
        }
        return M;
    }
    public double det(){
        if (rows!=columns){
            System.out.println("rows!=columns, can't take determinant");
            return 0;
        }
        return gauss().determinant;
    }
    public void printConsole(){
        int i=1; int j=1;
        while (j<=rows){
            while (i<=columns){
                System.out.print(get(j, i)+" ");
                i++;
            }
            System.out.println();
            i=1;
            j++;
        }
        System.out.println();
    }



}
