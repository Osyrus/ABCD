package main.mats;

import main.sys.Global;

/**
*A free space optical element.
*Can define the length as well as the refractive index of the free space.
*/
public class FreeSpace extends ABCD {
    private double l; //Length of free space in metres
    private double n1; //Refractive index of the free space

    /**
    *Base constructor.
    *
    *@param l The length of the free space in metres.
    *@param n1 The refractive index of the free space.
    */
    public FreeSpace(double l, double n1) {
        super();

        this.l = l;
        this.n1 = n1;

        super.setMat(calcMat());
    }
    /**
    *Constructor refractive index of the free space is the global index.
    *
    *@param l The length of the free space in metres.
    */
    public FreeSpace(double l) {
        this(l, Global.n);
    }
    /**
    *Default constructor where the length of the free space is set to 10 cm,
    *and the refractive index is the global index.
    */
    public FreeSpace() {
        this(0.1);
    }

    //Matrix calculator for free space.
    private double[][] calcMat() {
        return new double[][] {{1, l/n1},{0, 1}};
    }

    /**
    *Getter for the length of the free space.
    *
    *@return The length of the free space.
    */
    public double getL() {
        return l;
    }
    /**
    *Getter for the refractive index of the free space.
    *
    *@return The refractive index of the free space.
    */
    public double getn() {
        return n1;
    }

    /**
    *Setter for the length of the free space.
    *
    *@param l The desired length.
    */
    public void setL(double l) {
        this.l = l;
        super.setMat(calcMat());
    }
    /**
    *Setter for the refractive index of the free space.
    *
    *@param n1 The desired refractive index.
    */
    public void setn(double n1) {
        this.n1 = n1;
        super.setMat(calcMat());
    } 
}