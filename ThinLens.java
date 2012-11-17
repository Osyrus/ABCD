
public class ThinLens extends ABCD {
    private double f; //Focal length [in metres]

    //Constructor
    //Note: should make it catch an error if f == 0
    public ThinLens(double f) {
        this.f = f;
        super(calcMat());
    }
    //Default to f = 1 m
    public ThinLens() {
        this(1);
    }

    //Calculates the matrix for the a thin lens
    private double[][] calcMat() {
        return new double[][] {{1, 0},{-1/f, 1}};
    }

    //Return the focal length
    public double getF() {
        return f;
    }
    //Set the focal length (recalculate C)
    public void setF(double f) {
        this.f = f;
        super.setMat(calcMat());
    }
}