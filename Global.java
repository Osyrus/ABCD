public class Global {
    public static double n;

    public static double[][] matMult(double[][] mat1, double[][] mat2) {
        private double[][] mat = new double[2][2];

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    mat[i][j] += mat1[i][k] * mat2[k][j];

        return mat;
    }
}