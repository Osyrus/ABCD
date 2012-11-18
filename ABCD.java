
public class ABCD {
    private double A;
    private double B;
    private double C;
    private double D;
    //Maybe add storage for a reversed set of elements in case a reverse is needed?

    //Constructor, sets A, B, C and D.
    public ABCD(double A, double B, double C, double D) { //[Giggady]
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }
    //Default Constructor, defaults to the identity matrix
    public ABCD() {
        this(1, 0, 0, 1);
    }
    //Constructor using matrix input
    public ABCD(double[][] mat) {
        setMat(mat);
    }

    //Returns the ABCD matrix in a two dimensional matrix form.
    public double[][] getMat() {
        return new double[][] {{A, B},{C, D}};
    }
    //Sets A,B,C and D from a matrix
    public void setMat(double[][] mat) {
        this.A = mat[0][0];
        this.B = mat[0][1];
        this.C = mat[1][0];
        this.D = mat[1][1];
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

    //The reverse function, here it does nothing, but can be overwritten in subclasses
    //that require it due to the matrix needing to be different forward and back.
    public void reverse() {
        //Overwrite in the subclasses that need it!!
    }
}