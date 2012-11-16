
public class ABCD {
    private int A;
    private int B;
    private int C;
    private int D;

    //Constructor, sets A, B, C and D.
    //Note: change this so that the default (with not A,B,C,D set) is zero.
    public ABCD(int A,int B,int C,int D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    //Returns the ABCD matrix in a two dimensional matrix form.
    //Note: Figure out the ordering of the array for later mat manips.
    public int[][] getMat() {
        private int[][] ABCDMat = new int[][] {{A, B},{C, D}};
        return ABCDMat;
    }

    //Returns the requested matrix elements
    public int getA() {
        return A;
    }
    public int getB() {
        return B;
    }
    public int getC() {
        return C;
    }
    public int getD() {
        return D;
    }

    //Sets the requested matrix elements
    public void setA(int A) {
        this.A = A;
    }
    public void setB(int B) {
        this.B = B;
    }
    public void setC(int C) {
        this.C = C;
    }
    public void setD(int D) {
        this.D = D;
    }
}














