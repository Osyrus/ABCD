package main.mats;

import main.sys.Global;

public class CurvSurf extends ABCD {
    private double nI; //Initial index
    private double nF; //Final index
    private double R; //Radius of curvature
    private double AoI; //Angle of incidence [default units of degrees]

    //Constructor
    public CurvSurf(double nI, double nF, double R, double AoI) {
        super();

        this.nI = nI;
        this.nF = nF;
        this.R = R;
        this.AoI = AoI;

        super.setMat(calcMat());
    }
    //Constructor for undefined angle of incidence (default to 0 deg)
    public CurvSurf(double nI, double nF, double R) {
        this(nI, nF, R, 0);
    }
    //Default to a flat nothing thingo
    public CurvSurf() {
        this(Global.n, Global.n, Double.POSITIVE_INFINITY);
    }

    //Calculate the matrix for a thick lens
    private double[][] calcMat() {
        //Intermediary calculations
        double AoIc = Math.toRadians(AoI);
        double AoRc = Math.asin(nI/nF * Math.sin(AoIc)); //Angle of refraction
        double dn = (nF * Math.cos(AoRc) - nI * Math.cos(AoIc)) / (Math.cos(AoRc) * Math.cos(AoIc));

        return new double[][] {{Math.cos(AoRc)/Math.cos(AoIc), 0.0},{dn/R, Math.cos(AoIc)/Math.cos(AoRc)}};
    }

    //Swap values to make the matrix suitable for the return trip
    public void reverse() {
        //Reverse the refractive indices (initial to final, final to initial)
        double nTemp = nI;
        nI = nF;
        nF = nTemp;
        //Reverse the radius of curvature
        R = -R;
        //Recalculate the matrix elements
        super.setMat(calcMat());
    }

    //All the get functions
    public double getnI() {
        return nI;
    }
    public double getnF() {
        return nF;
    }
    public double getR() {
        return R;
    }
    public double getAoI() {
        return AoI;
    }

    //All the set functions
    public void setnI(double nI) {
        this.nI = nI;
        super.setMat(calcMat());
    }
    public void setnF(double nF) {
        this.nF = nF;
        super.setMat(calcMat());
    }
    public void setR(double R) {
        this.R = R;
        super.setMat(calcMat());
    }
    public void setAoI(double AoI) {
        this.AoI = AoI;
        super.setMat(calcMat());
    }
}