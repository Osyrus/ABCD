
public class ABCD {
    private int A1;
    private int B1;
    private int C1;
    private int D1;
    private int A2;
    private int B2;
    private int C2;
    private int D2;

    //Constructor, sets A, B, C and D for both sides explicitly
    public ABCD(int A1, int B1, int C1, int D1, int A2, int B2, int C2, int D2) {
        this.A1 = A1;
        this.B1 = B1;
        this.C1 = C1;
        this.D1 = D1;
        this.A2 = A2;
        this.B2 = B2;
        this.C2 = C2;
        this.D2 = D2;
    }
    //Constructor for only one direction set
    public ABCD(int A, int B, int C, int D) {
        super(A, B, C, D, A, B, C, D)
    }
    //Constructor for no elements given
    public ABCD() {
        super(0, 0, 0, 0)
    }

    //Returns the ABCD matrix in a two dimensional matrix form dependant on which direction was asked for
    //Note: Figure out the ordering of the array for later mat manips.
    public int[][] getMat(int mat) {
        private int[][] ABCDMat;

        if mat == 1
            ABCDMat = new int[][] {{A1, B1},{C1, D1}};
        else //should this be else if?
            ABCDMat = new int[][] {{A2, B2},{C2, D2}};

        return ABCDMat;
    }

    //Sets the values of A,B,C and D from a matrix with choice to direction
    //Note: Overload for default of A1?
    public void setMat(int[][] ABCDMat, int mat) {
        if mat == 1 {
            this.A1 = ABCDMat[1][1];
            this.B1 = ABCDMat[1][2];
            this.C1 = ABCDMat[2][1];
            this.D1 = ABCDMat[2][2];
        } else {
            this.A2 = ABCDMat[1][1];
            this.B2 = ABCDMat[1][2];
            this.C2 = ABCDMat[2][1];
            this.D2 = ABCDMat[2][2];
        }
        
    }

    //Returns the requested matrix elements of a specified direction
    //Note: Overload?
    public int getA(int mat) {
        if mat == 1
            return A1;
        else
            return A2;
    }
    public int getB() {
        if mat == 1
            return B1;
        else
            return B2;
    }
    public int getC() {
        if mat == 1
            return C1;
        else
            return C2;
    }
    public int getD() {
        if mat == 1
            return D1;
        else
            return D2;
    }

    //Sets the requested matrix elements for specified direction
    //Note: Overload?
    public void setA(int A, int mat) {
        if mat == 1
            this.A1 = A;
        else
            this.A2 = A;
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














