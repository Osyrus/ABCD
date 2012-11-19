package main.sys;

/**
*The Global class essentially just stores variables and methods that are to be shared from other classes.
*/
public class Global {
    /**
    *Stores the refractive index of the global system.
    */
    public static double n;

    private Global();

    /**
    *Performs matrix multiplication (equivalent to doing mat1*mat2 in Matlab)
    *
    *@param mat1 The first matrix to be multiplied
    *@param mat2 The second matrix post-multiplied to mat1
    *@return The result of mat1 times mat2
    */
    public static double[][] matMult(double[][] mat1, double[][] mat2) {
        double[][] mat = new double[2][2];

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    mat[i][j] += mat1[i][k] * mat2[k][j];

        return mat;
    }
}