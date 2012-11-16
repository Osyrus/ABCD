
public class ABCD {
    private double A;
    private double B;
    private double C;
    private double D;

    //Constructor, sets A, B, C and D.
    public ABCD(double A, double B, double C, double D) { //[Giggady]
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }
    //Default Constructor.
    public ABCDMat() {
        this(0, 0, 0, 0);
    }
    //Constructor using matrix input
    public ABCDMat(double[][] mat) {
        setMat(mat);
    }

    //Returns the ABCD matrix in a two dimensional matrix form.
    public double[][] getMat() {
        return new double[][] {{A, B},{C, D}};
    }
    //Sets A,B,C and D from a matrix
    public void setMat(double[][] mat) {
        this.A = mat[1][1];
        this.B = mat[1][2];
        this.C = mat[2][1];
        this.D = mat[2][2];
    }

    //Returns the requested matrix elements
    public double getA() {
        return A;
    }
    public double getB() {
        return B;
    }
    public double getC() {
        return C;
    }
    public double getD() {
        return D;
    }

    //Sets the requested matrix elements
    public void setA(double A) {
        this.A = A;
    }
    public void setB(double B) {
        this.B = B;
    }
    public void setC(double C) {
        this.C = C;
    }
    public void setD(double D) {
        this.D = D;
    }
}