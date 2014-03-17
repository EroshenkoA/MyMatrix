package ru.mipt.cs.matrix.sub;

import ru.mipt.cs.matrix.Matrix;

/**
 * Created by 1 on 09.03.14.
 */
public class MyVector extends Matrix {
    public MyVector(int n){
        super(n,1);
    }
    // AP: методы называются с маленькой буквы!
    //Done
    public double dotProduct(MyVector v){
        if (v.getRows()!=rows){
            System.out.println("can't take dot product of different size vectors");
            return 0;
        }
        return rMult(v.transpose()).get(1, 1);
    }
}
