
public class Mirror extends ABCD {
    private double R; //Radius of curvature of the mirror

    //Constructor
    public Mirror(double R) {
        this.R = R;

        super(calcMat());
    }
    //Default constructor for a flat mirror
    public Mirror() {
        this(Double.POSITIVE_INFINITY);
    }

    //Matrix calculator
    private double[][] calcMat() {
        return new double[][] {{1, 0},{-2.0/R, 1}};
    }

    //Get function
    public double getR() {
        return R;
    }

    //Set function
    public void setR(double R) {
        this.R = R;
        super.setMat(calcMat());
    }
}