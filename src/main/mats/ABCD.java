package main.mats;

/**
*The generic ABCD matrix element class.
*Can be overridden for specific types of optical elements.
*/
public class ABCD {
    private double A;
    private double B;
    private double C;
    private double D;

    /**
    *Base Constructor, sets the A, B, C and D components of the matrix.
    *
    *@param A Matrix element A.
    *@param B Matrix element B.
    *@param C Matrix element C.
    *@param D Matrix element D.
    */
    public ABCD(double A, double B, double C, double D) { //[Giggady]
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }
    /**
    *Default Constructor, sets the matrix equal to the identity matrix.
    */
    public ABCD() {
        this(1, 0, 0, 1);
    }
    /**
    *Constructor using matrix input.
    *
    *@param mat The matrix to set the ABCD elements with/
    */
    public ABCD(double[][] mat) {
        setMat(mat);
    }

    /**
    *Getter for the ABCD matrix elements.
    *
    *@return The Java array version of the ABCD matrix elements.
    */
    public double[][] getMat() {
        return new double[][] {{A, B},{C, D}};
    }
    /**
    *Sets A,B,C and D from a Java array form.
    *
    *@param mat The Java array to set the ABCD matrix to.
    */
    public void setMat(double[][] mat) {
        this.A = mat[0][0];
        this.B = mat[0][1];
        this.C = mat[1][0];
        this.D = mat[1][1];
    }

    /**
    *Converts the ABCD matrix into a String.
    *
    *@return The matrix in the form "[A, B; C, D]". This is equivalent to how a matrix would be created in Matlab.
    */
    public String toString() {
        return "["+this.A+", "+this.B+"; "+this.C+", "+this.D+"]";
    }

    /**
    *Getter for the element A
    *
    *@return The matrix element A
    */
    public double getA() {
        return A;
    }
    /**
    *Getter for the element B
    *
    *@return The matrix element B
    */
    public double getB() {
        return B;
    }
    /**
    *Getter for the element C
    *
    *@return The matrix element C
    */
    public double getC() {
        return C;
    }
    /**
    *Getter for the element D
    *
    *@return The matrix element D
    */
    public double getD() {
        return D;
    }

    /**
    *Setter for matrix element A
    *
    *@param A Sets the element A
    */
    public void setA(double A) {
        this.A = A;
    }
    /**
    *Setter for matrix element B
    *
    *@param B Sets the element B
    */
    public void setB(double B) {
        this.B = B;
    }
    /**
    *Setter for matrix element C
    *
    *@param C Sets the element C
    */
    public void setC(double C) {
        this.C = C;
    }
    /**
    *Setter for matrix element D
    *
    *@param D Sets the element D
    */
    public void setD(double D) {
        this.D = D;
    }

    /**
    *The reverse function.
    *Here it does nothing, but can be overwritten in subclasses that require it.
    *This may be due to an optical element that looks different forwards than backwards.
    */
    public void reverse();
}