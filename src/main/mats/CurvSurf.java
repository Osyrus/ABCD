package main.mats;

import main.sys.Global;

/**
*A curved surface optical element. 
*Can specify the radius of curvature, angle of incidence and the initial and final refractive indices.
*
*@author Myles Clark
*/
public class CurvSurf extends ABCD {
    private double nI; //Initial index
    private double nF; //Final index
    private double R; //Radius of curvature in metres
    private double AoI; //Angle of incidence in degrees

    /**
    *Base Constructor.
    *
    *@param nI Initial refractive index (by convention, the refractive index on the left).
    *@param nF Final refractive index (by convention, the right side one).
    *@param R The radius of curvature of the surface in metres (R=INF means a flat surface).
    *@param AoI The angle of incidence in deg (by convention, from the left).
    */
    public CurvSurf(double nI, double nF, double R, double AoI) {
        super();

        this.nI = nI;
        this.nF = nF;
        this.R = R;
        this.AoI = AoI;

        super.setMat(calcMat());
    }
    /**
    *Constructor for undefined angle of incidence (defaults to 0 deg).
    *
    *@param nI Initial refractive index (by convention, the refractive index on the left).
    *@param nF Final refractive index (by convention, the right side one).
    *@param R The radius of curvature of the surface in metres (R=INF means a flat surface).
    */
    public CurvSurf(double nI, double nF, double R) {
        this(nI, nF, R, 0);
    }
    /**
    *Default constructor, has initial and final indices set to the
    *global index and an infinite R (flat surface).
    */
    public CurvSurf() {
        this(Global.n, Global.n, Double.POSITIVE_INFINITY);
    }

    //Calculate the matrix for a thick lens
    private double[][] calcMat() {
        //Intermediary calculations
        double AoIc = Math.toRadians(AoI);
        double AoRc = Math.asin((nI/nF) * Math.sin(AoIc)); //Angle of refraction
        double dn = (nF * Math.cos(AoRc) - nI * Math.cos(AoIc)) / (Math.cos(AoRc) * Math.cos(AoIc));

        return new double[][] {{Math.cos(AoRc)/Math.cos(AoIc), 0.0},{dn/R, Math.cos(AoIc)/Math.cos(AoRc)}};
    }

    /**
    *Swaps the refractive index values and reverses the radius of curvature
    *to make the matrix suitable for the return calculation.
    *Note, calling twice will return the element to the original form.
    */
    public void reverse() {
        //Reverse the refractive indices (initial to final, final to initial)
        double nTemp = nI;
        setnI(nF);
        setnF(nTemp);
        //Reverse the radius of curvature
        setR(-R);
        //Reverse the boolean flag
        super.reverse();
    }

    /**
    *Getter for the initial refractive index.
    *
    *@return The initial refractive index.
    */
    public double getnI() {
        return nI;
    }
    /**
    *Getter for the final refractive index.
    *
    *@return The final refractive index.
    */
    public double getnF() {
        return nF;
    }
    /**
    *Getter for the radius of curvature.
    *
    *@return The radius of curvature.
    */
    public double getR() {
        return R;
    }
    /**
    *Getter for the angle of incidence.
    *
    *@return The angle of incidence.
    */
    public double getAoI() {
        return AoI;
    }

    /**
    *Setter for the initial refractive index.
    *
    *@param nI The desired initial refractive index.
    */
    public void setnI(double nI) {
        this.nI = nI;
        super.setMat(calcMat());
    }
    /**
    *Setter for the final refractive index.
    *
    *@param nF The desired final refractive index.
    */
    public void setnF(double nF) {
        this.nF = nF;
        super.setMat(calcMat());
    }
    /**
    *Setter for the radius of curvature.
    *
    *@param R The desired radius of curvature.
    */
    public void setR(double R) {
        this.R = R;
        super.setMat(calcMat());
    }
    /**
    *Setter for the angle of incidence.
    *
    *@param AoI The desired angle of incidence.
    */
    public void setAoI(double AoI) {
        this.AoI = AoI;
        super.setMat(calcMat());
    }
}