
public class Mirror extends ABCD {
    private double R; //Radius of curvature of the mirror
    private double[][] temp = new double[][] {{1,0},{0,1}};

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

    //On reverse, it sets the matrix to the identity as mirrors are only counted once,
    //calling it again sets it back to the original matrix
    public void reverse() {
        private double[][] revTemp = this.getMat();
        this.setMat(temp);
        temp = revTemp;
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