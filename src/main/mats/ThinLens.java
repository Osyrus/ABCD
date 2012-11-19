package main.mats;

/**
*A thin lens optical element.
*Can define the focal length of the lens in metres.
*
*@author Myles Clark
*/
public class ThinLens extends ABCD {
    private double f; //Focal length [in metres]

    //Note: should make it catch an error if f == 0
    /**
    *Base Constructor.
    *
    *@param f The focal length of the lens in metres.
    */
    public ThinLens(double f) {
        super();

        this.f = f;
        
        super.setMat(calcMat());
    }
    /**
    *Default constructor, sets the focal length to one metre.
    */
    public ThinLens() {
        this(1);
    }

    //Calculates the matrix for the a thin lens
    private double[][] calcMat() {
        return new double[][] {{1, 0},{-1/f, 1}};
    }

    /**
    *Getter for the focal length of the lens.
    *
    *@return The focal length (m).
    */
    public double getF() {
        return f;
    }
    
    /**
    *Setter for the focal length of the lens
    *
    *@param f The desired focal length (m).
    */
    public void setF(double f) {
        this.f = f;
        super.setMat(calcMat());
    }
}