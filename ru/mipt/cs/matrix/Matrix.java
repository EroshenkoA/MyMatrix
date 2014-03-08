package ru.mipt.cs.matrix;

import java.util.Scanner;

/**
 * Created by 1 on 08.03.14.
 */
public class Matrix {
    protected int rows;
    protected int columns;
    protected double elements[][];
    private class Pair{
        int x;
        int y;
        Pair(int a, int b){
            x=a;
            y=b;
        }
    }
    public Matrix(int rows,int columns){
        if ((rows<1)||(columns<1)) {
            System.out.println("number of rows and columns must be positive integer");
        }
        this.rows=rows;
        this.columns=columns;
        elements=new double[rows][columns];
    }
    public double Get(int i, int j){
        if ((i>0)&&(i<=rows)&&(j>=0)&&(j<=columns)){
            return elements[i-1][j-1];
        }else{
            System.out.println("got wrong indices, or matrix was not initialized");
            return -1;
        }
    }
    public void Set(int i, int j, double r){
        if ((i>0)&&(i<=rows)&&(j>=0)&&(j<=columns)){
            this.elements[i-1][j-1]=r;
        }else{
            System.out.println("wrong indices, or matrix was not initialized");
        }
    }
    public int GetColumns(){
        return columns;
    }
    public int GetRows(){
        return rows;
    }
    public Matrix RMult(Matrix M){
        if (columns==M.GetRows()){
            int i=1; int j=1; int k=1;double s=0;
            int c=M.GetColumns();
            Matrix S=new Matrix(rows,c);
            while (i<=rows){
                while (j<=c){
                    while (k<=columns){
                        s+=elements[i-1][k-1]*M.Get(k,j);
                        k++;
                    }
                    k=1;
                    S.Set(i,j,s);
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
    public static Matrix Mult(Matrix A, Matrix B){
        return A.RMult(B);
    }
    public Matrix Transpose(){
        Matrix A=new Matrix(columns,rows);
        int i=1; int j=1;
        while (i<=rows){
            while (j<=columns){
                A.Set(j,i,elements[i-1][j-1]);
                j++;
            }
            j=1;
            i++;
        }
        return A;
    }
    public void SwapRows(int i, int j){
        if (i!=j){
            double s;
            for (int k=0; k<columns; k++){
                s=elements[i][k];
                elements[i][k]=elements[j][k];
                elements[j][k]=s;
            }
        }
    }
    private void Step(int k,int m){//elements[k][m] must be nonzero
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
    private Pair FindFirstNonzeroColumn(int i,int j){//in column j after i-1
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
            SwapRows(i,k);
            p.x=k;
            p.y=j;
            return p;
        }
        return p;
    }
    private Pair FindFirstNonzero(int i,int j){
        Pair p=new Pair(0,0);
        if ((p=FindFirstNonzeroColumn(i,j)).x==-1){
            return FindFirstNonzero(i,j+1);
        }else{
            return p;//will return -2 in case of all zeros
        }
    }
    private void Descend(int i,int j){
        Pair p=new Pair(0,0);

        if ((p=FindFirstNonzero(i,j)).y!=-2){
            Step(p.x,p.y);
            Descend(p.x+1,p.y+1);
        }
    }
    public void Copy(Matrix M){
        int i=1; int j=1;
        while (i<=rows){
            while (j<=columns){
                Set(i, j, M.Get(i, j));
                j++;
            }
            j=1;
            i++;
        }
    }
    public Matrix Gauss(){
        Double c;
        Matrix M=new Matrix(rows,columns);
        M.Copy(this);
        M.Descend(0, 0);
        return M;
    }
    public void PrintConsole(){
        int i=1; int j=1;
        while (j<=rows){
            while (i<=columns){
                System.out.print(Get(j,i)+" ");
                i++;
            }
            System.out.println();
            i=1;
            j++;
        }
        System.out.println();
    }
    public void ConsoleSet(){
        int i=1;int j=1;
        Scanner s=new Scanner(System.in);
        while (i<=rows){
            while (j<=columns){
                Set(i,j,s.nextDouble());
                j++;
            }
            j=1;
            i++;
        }
    }
}