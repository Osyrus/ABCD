package main.sys;

import java.util.ArrayList;
import main.mats.*;
import org.apache.commons.math3.complex.Complex;

/**
*The Resonator class contains an ArrayList on optical elements and has methods for working on those elements.
*The round trip matrix can be calculated for the resonator from the contained objects and their order.
*In addition, the number of dimensions (or directions) is arbitrary.
*/
public class Resonator {
    private ArrayList<ArrayList> system; //The resonator system array
    private int dim; //Number of dimensions
    private ABCD[] rtMat; //The round trip matrix
    private Complex[] q;

    //Note: Overload? Perhaps default 1D? Or maybe load an existing system from file?
    /**
    *Basic Constructor.
    *
    *@param dim Sets the number of dimensions of the resonator.
    */
    public Resonator(int dim) {
        this.dim = dim;
        system = new ArrayList<ArrayList>(dim); //Create an ArrayList array of desired dimension
        //A warning is appearing here for the ArrayList "generic array creation", fix somehow?
        rtMat = new ABCD[dim];
        q = new Complex[dim];
        
        //Create the system array and round trip matrix and fills them
        //with the first element of the system (the identity matrix)
        for(int i = 0; i < dim; i++) {
            rtMat[i] = new ABCD();
            system.add(i, new ArrayList<ABCD>());
        }
    }

    /**
    *Updates the round trip matrix for the specified dimension.
    *
    *@param dim The dimension to be calculated.
    */
    public void updateRTMat(int dim) {
        double[][] temp = new double[][] {{1,0},{0,1}};
        //Calculate the forward direction
        for(int i = 0; i < system.get(dim).size(); i++) {
            temp = Global.matMult(getABCD(i, dim).getMat(), temp);
        }
        //Calculate the reverse direction
        for(int i = (system.get(dim).size()-1); i >= 0; i--) {
            getABCD(i, dim).reverse(); //Reverses the matrix
            temp = Global.matMult(getABCD(i, dim).getMat(), temp); //Combines it
            getABCD(i, dim).reverse(); //Reverses it back
        }
        rtMat[dim].setMat(temp);
    }
    /**
    *Updates the round trip matrix for all dimensions.
    */
    public void updateRTMat() {
        for(int i = 0; i < dim; i++) {
            updateRTMat(i);
        }
    }

    /**
    *Getter for the initial q variable.
    *
    *@param dim Dimension to retrieve q from.
    *@return The initial q value calculated from the round trip matrix.
    */
    public Complex getQ(int dim) {
        updateRTMat(dim);
        if (isStable(dim)) {
            calcQ(dim);
            return q[dim];
        } else {
            return Complex.NaN; //Throw an error here or something?
        }
    }

    //Calculate the initial q value
    private void calcQ(int dim) {
        double img;

        if (getImg(dim) > 0) {
            img = -getImg(dim);
        }
        else {
            img = getImg(dim);
        }

        q[dim] = Complex.valueOf(getReal(dim), img);
        q[dim] = Complex.ONE.divide(q[dim]);
    }

    //Calculate and return the real and imaginary parts
    private double getReal(int dim) { //Haha, get it?
        return (rtMat[dim].getD() - rtMat[dim].getA()) / (2.0 * rtMat[dim].getB());
    }
    private double getImg(int dim) { //Not as funny...
        return Math.sqrt(4.0 - Math.pow((rtMat[dim].getA() + rtMat[dim].getD()),2.0)) / (2.0 * rtMat[dim].getB());
    }

    /**
    *Getter for the half trace of the round trip matrix.
    *
    *@param dim Dimension from which to retrieve the half trace.
    *@return The half trace.
    */
    public double getHalfTrace(int dim) {
        updateRTMat(dim);
        return (rtMat[dim].getA() + rtMat[dim].getD()) / 2.0;
    }

    /**
    *Calculates the imaginary part of the round trip matrix to find if it is stable.
    *
    *@param dim Dimension so find stability in.
    *@return If the resonator is stable in the specified dimension.
    */
    public boolean isStable(int dim) {
        updateRTMat(dim);
        if (Double.isNaN(getImg(dim))) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
    *Method to as optical elements to the resonator.
    *
    *@param mat The ABCD matrix object to add.
    *@param index The index location to add the matrix too. If a matrix exists in that index location it will be moved back to accommodate the new one.
    *@param dim The dimension to add the matrix to.
    */
    public void addABCD(ABCD mat, int index, int dim) {
        system.get(dim).add(index, mat); //Add the matrix element to the ArrayList
    }
    /**
    *Optical element getter.
    *
    *@param index The index location to retrieve the ABCD matrix from.
    *@param dim The dimension to retrieve from.
    *@return The ABCD matrix object for the specified index and dimension.
    */
    public ABCD getABCD(int index, int dim) {
        return (ABCD) system.get(dim).get(index); //Get the matrix element from the ArrayList
    }
    /**
    *Round trip matrix getter, recalculates the matrix for the resonator first.
    *
    *@param dim The dimension to retrieve the round trip matrix from.
    *@return The round trip matrix of the resonator for the specified direction.
    */
    public ABCD getRTMat(int dim) {
        updateRTMat(dim);
        return rtMat[dim];
    }
    public int getSize() {
        return system.get(0).size();
    }
}