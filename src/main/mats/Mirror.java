package main.mats;

/**
*A mirror optical element.
*Can specify the radius of curvature of the mirror in meters.
*Note that these will usually go on either the front of back of the resonator.
*
*@author Myles Clark
*/
public class Mirror extends ABCD {
    private double R; //Radius of curvature of the mirror in metres.
    private double[][] temp = new double[][] {{1,0},{0,1}};

    /**
    *Base Constructor.
    *
    *@param R The radius of curvature of the mirror, R=INF is for a flat mirror
    */
    public Mirror(double R) {
        super();

        this.R = R;

        super.setMat(calcMat());
    }
    /**
    *Default constructor, creates a flat mirror (R=INF).
    */
    public Mirror() {
        this(Double.POSITIVE_INFINITY);
    }

    //Matrix calculator
    private double[][] calcMat() {
        return new double[][] {{1, 0},{-2.0/R, 1}};
    }

    /**
    *When called, sets the matrix to the identity as mirrors are only counted once
    *in the round trip calculation.
    *Calling this again sets the element back to the original matrix.
    */
    public void reverse() {
        double[][] revTemp = this.getMat();
        this.setMat(temp);
        temp = revTemp;
    }

    /**
    *Getter of the radius of curvature of the mirror.
    *
    *@return The radius of curvature.
    */
    public double getR() {
        return R;
    }

    /**
    *Setter for the radius of curvature of the mirror.
    *
    *@param R The desired radius of curvature.
    */
    public void setR(double R) {
        this.R = R;
        super.setMat(calcMat());
    }
}