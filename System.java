import java.util.ArrayList;
import org.apache.commons.math3.complex.Complex;

public class System {
    private ArrayList[] system; //The resonator system array, angle bracket thing? (<Object>)
    private int dim; //Number of dimensions
    private ABCD[] rtMat; //The round trip matrix
    private Complex[] q;

    //Basic Constructor, argument sets the number of dimensions
    //Note: Overload? Perhaps default 1D? Or maybe load an existing system from file?
    public System(int dim) {
        this.dim = dim;
        system = new ArrayList[dim]; //Create an ArrayList array of desired dimension
        rtMat = new ABCD[dim];
        q = new Complex[dim];
        
        //Create the system array and round trip matrix and fills them
        //with the first element of the system (the identity matrix)
        for(i = 0; i < dim; i++) {
            rtMat[i] = new ABCD(); //Do the new keywords have to be here?
            system[i] = new ArrayList(new ABCD());
        }
    }

    //Updates the round trip matrix for the specified dimension
    public void updateRTMat(int dim) {
        private double[][] temp = new double[][] {{1,0},{0,1}};
        //Calculate the forward direction
        for(i = 0; i <= system[dim].size(); i++)
            temp = matMult(system[dim].getABCD(i, dim).getMat(), temp);
        //Calculate the reverse direction
        for(i = (system[dim].size()-1); i >= 0; i--) {
            system[dim].getABCD(i, dim).reverse(); //Reverses the matrix
            temp = matMult(system[dim].getABCD(i, dim).getMat(), temp); //Combines it
            system[dim].getABCD(i, dim).reverse(); //Reverses it back
        }
        rtMat[dim].setMat(temp);
    }
    //Updates the round trip matrix for all dimensions
    public void updateRTMat() {
        for(i = 0; i < dim; i++)
            updateRTMat(i);
    }

    public Complex getQ(int dim) {
        if (isStable()) {
            calcQ(dim);
            return q[dim];
        } else {
            return Complex.NaN; //Throw an error here or something?
        }
    }

    //Calculate the initial q value
    private void calcQ(int dim) {
        private double img;

        if (getImg() > 0)
            img = -getImg(dim);
        else
            img = getImg(dim);

        q[dim] = Complex.valueOf(getReal(dim), img);
        q[dim] = q[dim].divide(q[dim]);
    }

    //Calculate and return the real and imaginary parts
    private double getReal(int dim) { //Haha, get it?
        return (rtMat[dim].getD() - rtMat[dim].getA()) / (2.0 * rtMat[dim].getB());
    }
    private double getImg(int dim) { //Not as funny...
        return Math.sqrt(4.0 - Math.pow((rtMat[dim].getA() + rtMat[dim].getD()),2.0)) / (2.0 * rtMat[dim].getB());
    }

    //Calculate the half trace
    public double getHalfTrace(int dim) {
        return (rtMat[dim].getA() + rtMat[dim].getD()) / 2.0;
    }

    public boolean isStable(int dim) {
        if (Double.isNaN(getImg(dim)))
            return false;
        else
            return true;
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