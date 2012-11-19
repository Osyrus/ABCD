package main.mats;

import main.sys.Global;

/**
*A Gradient Refractive INdex (GRIN) lens optical element.
*Can specify the thickness, base refractive index and quadratic index factor.
*The refractive index with respect to radius is defined by the equation: n(r) = n0 - 0.5*n2*r^2
*
*@author Myles Clark
*/
public class GRINLens extends ABCD {
    private double l; //Thickness of GRIN lens in metres
    private double n1; //Base refractive index of the lens material
    private double n2; //Quadratic refractive index factor

    /**
    *Base Constructor.
    *
    *@param l The thickness of the GRIN lens in metres.
    *@param n1 The base refractive index.
    *@param n2 The quadratic refractive index factor (see main description).
    */
    public GRINLens(double l, double n1, double n2) {
        super();

        this.l = l;
        this.n1 = n1;
        this.n2 = n2;

        super.setMat(calcMat());
    }
    /**
    *Default constructor, sets the thickness of the lens to 10 mm, the index to the
    *global refractive index and the quadratic refractive index factor to zero.
    */
    public GRINLens() {
        this(0.01, Global.n, 0);
    }

    //Matrix Calculation
    private double[][] calcMat() {
        double A = Math.cos(l * Math.sqrt(n2/n1));
        double B = Math.sin(l * Math.sqrt(n2/n1)) / Math.sqrt(n1*n2);
        double C = -Math.sqrt(n1*n2) * Math.sin(l * Math.sqrt(n2/n1));
        double D = Math.cos(l * Math.sqrt(n2/n1));

        return new double[][] {{A, B},{C, D}};
    }

    /**
    *Getter for the thickness of the GRIN lens.
    *
    *@return The thickness of the GRIN lens.
    */
    public double getL() {
        return l;
    }
    /**
    *Getter for the base refractive index.
    *
    *@return The base refractive index.
    */
    public double getn1() {
        return n1;
    }
    /**
    *Getter for the quadratic refractive index factor.
    *
    *@return The quadratic refractive index factor.
    */
    public double getn2() {
        return n2;
    }

    /**
    *Setter for the thickness of the GRIN lens.
    *
    *@param l The desired thickness.
    */
    public void setL(double l) {
        this.l = l;
        super.setMat(calcMat());
    }
    /**
    *Setter for the base refractive index of the GRIN lens.
    *
    *@param n1 The desired base refractive index.
    */
    public void setn1(double n1) {
        this.n1 = n1;
        super.setMat(calcMat());
    }
    /**
    *Setter for the quadratic refractive index factor of the GRIN lens.
    *
    *@param n2 The desired quadratic refractive index factor.
    */
    public void setn2(double n2) {
        this.n2 = n2;
        super.setMat(calcMat());
    }
}