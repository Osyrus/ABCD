package main.mats;

import main.sys.Global;

/**
*A thick lens optical element.
*Can specify the thickness, refractive index of the lens as well as the
*radius of curvature of the front and back surfaces (all in metres).
*
*@author Myles Clark
*/
public class ThickLens extends ABCD {
    private double l; //Lens thickness in metres
    private double n1; //Lens material refractive index
    private double R1; //Front radius of curvature in metres
    private double R2; //Back ROC in metres
    private CurvSurf FrontSurf;
    private CurvSurf BackSurf;
    private FreeSpace Medium;

    /**
    *Base Constructor.
    *
    *@param l The thickness of the lens.
    *@param n1 The refractive index of the lens.
    *@param R1 The radius of curvature of the front face (by convention, the left side).
    *@param R2 The radius of curvature of the back face (by convention, the right side).
    */
    public ThickLens(double l, double n1, double R1, double R2) {
        super();

        this.l = l;
        this.n1 = n1;
        this.R1 = R1;
        this.R2 = R2;
        this.FrontSurf = new CurvSurf(Global.n, n1, R1);
        this.BackSurf = new CurvSurf(n1, Global.n, R2);
        this.Medium = new FreeSpace(l, n1);

        super.setMat(calcMat());
    }
    /**
    *Default constructor, creates a lens with 10 mm thickness, refractive index
    *equal to the global index and flat (R=INF) front and back surfaces.
    */
    public ThickLens() {
        this(0.01, Global.n, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    //Calculate the matrix for a thick lens
    private double[][] calcMat() {
        return Global.matMult(Global.matMult(BackSurf.getMat(),Medium.getMat()),FrontSurf.getMat());
    }

    /**
    *As the lens will look different on the return path for the round trip matrix, this can
    *be called to reverse the matrix.
    *This is done by swapping the front and back surfaces and reversing the
    *radius of curvature for each.
    */
    public void reverse() {
        //Front surface is now the back surface, and vice versa
        CurvSurf SurfTemp = FrontSurf;
        FrontSurf = BackSurf;
        BackSurf = SurfTemp;
        //Reverse the radii of curvature
        setR1(-R1);
        setR2(-R2);
    }

    /**
    *Getter for the thickness of the lens.
    *
    *@return The thickness (m).
    */
    public double getL() {
        return l;
    }
    /**
    *Getter for the refractive index of the lens.
    *
    *@return The refractive index.
    */
    public double getn() {
        return n1;
    }
    /**
    *Getter for the front surface radius of curvature of the lens.
    *
    *@return The front surface radius of curvature (m).
    */
    public double getR1() {
        return R1;
    }
    /**
    *Getter for the back surface radius of curvature of the lens.
    *
    *@return The back surface radius of curvature (m).
    */
    public double getR2() {
        return R2;
    }

    /**
    *Setter for the thickness of the lens.
    *
    *@param l The desired thickness (m).
    */
    public void setL(double l) {
        this.l = l;
        Medium.setL(l);
        super.setMat(calcMat());
    }
    /**
    *Setter for the refractive index of the lens.
    *
    *@param n1 The desired refractive index.
    */
    public void setn(double n1) {
        this.n1 = n1;
        FrontSurf.setnF(n1);
        BackSurf.setnI(n1);
        Medium.setn(n1);
        super.setMat(calcMat());
    }
    /**
    *Setter for the front surface radius of curvature of the lens.
    *
    *@param R1 The desired front surface radius of curvature (m).
    */
    public void setR1(double R1) {
        this.R1 = R1;
        FrontSurf.setR(R1);
        super.setMat(calcMat());
    }
    /**
    *Setter for the back surface radius of curvature of the lens.
    *
    *@param R2 The desired back surface radius of curvature (m).
    */
    public void setR2(double R2) {
        this.R2 = R2;
        BackSurf.setR(R2);
        super.setMat(calcMat());
    }
}