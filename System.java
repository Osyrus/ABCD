import java.util.ArrayList;

public class System {
    private ArrayList[] system; //The resonator system array, angle bracket thing? (<Object>)
    private int dim; //Number of dimensions
    private ABCD[] rtMat; //The round trip matrix

    //Basic Constructor, argument sets the number of dimensions
    //Note: Overload? Perhaps default 1D? Or maybe load an existing system from file?
    public System(int dim) {
        this.dim = dim;
        system = new ArrayList[dim]; //Create an ArrayList array of desired dimension
        rtMat = new ABCD[dim];
        
        //Create the system array and round trip matrix and fills them
        //with the first element of the system (the identity matrix)
        for(i = 0; i < 2; i++) {
            rtMat[i] = new ABCD(); //Do the new keywords have to be here?
            system[i] = new ArrayList(new ABCD());
        }
    }

    //Updates the round trip matrix for the specified dimension
    public void updateRTMat(int dim) {
        for(i = 0; i <= system[dim].size(); i++)
            rtMat[dim] = matMult(system[dim].getABCD(i, dim).getMat(), rtMat[dim]);
    }
    //Updates the round trip matrix for all dimensions
    public void updateRTMat() {
        for(i = 0; i < dim; i++)
            updateRTMat(i);
    }

    //Optical element add function
    public void addABCD(ABCD mat, int index, int dim) {
        system[dim].add(index, mat); //Add the matrix element to the ArrayList
    }
    //Optical element get function
    public ABCD getABCD(int index, int dim) {
        return system[dim].get(index); //Get the matrix element from the ArrayList
    }
    //Round trip matrix get function
    public ABCD getRTMat(int dim) {
        updateRTMat(dim);
        return rtMat[dim];
    }

    //Matrix multiplication code
    //Note: This is repeated in the ThickLens code, fix this somehow? Global function? How?
    private double[][] matMult(double[][] mat1, double[][] mat2) {
        private double[][] mat = new double[2][2];

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    mat[i][j] += mat1[i][k] * mat2[k][j];

        return mat;
    }
}