
public class FreeSpace extends ABCD {
    private double l; //Length of free space
    private double n1; //Refractive index of the free space

    //Constructor
    public FreeSpace(double l, double n1) {
        this.l = l;
        this.n1 = n1;

        super(calcMat());
    }
    //Constructor where default refractive index is global index
    public FreeSpace(double l) {
        this(l, n);
    }
    //Default to free space of length 10 cm
    public FreeSpace() {
        this(0.1);
    }

    //Matrix calculator for free space
    private double[][] calcMat() {
        return new double[][] {{1, 1/n1},{0, 1}};
    }

    //Get functions
    public double getL() {
        return l;
    }
    public double getn() {
        return n1;
    }

    //Set functions
    public void setL(double l) {
        this.l = l;
        super.setMat(calcMat());
    }
    public void setn(double n1) {
        this.n1 = n1;
        super.setMat(calcMat());
    } 
}