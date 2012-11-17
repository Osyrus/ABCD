
public class ThickLens extends ABCD {
    private double l; //Lens thickness
    private double n1; //Lens material refractive index
    private double R1; //Front radius of curvature
    private double R2; //Back ROC
    private CurvSurf FrontSurf;
    private CurvSurf BackSurf;
    private FreeSpace Medium;

    //Constructor
    public ThickLens(double l, double n1, double R1, double R2) {
        this.l = l;
        this.n1 = n1;
        this.R1 = R1;
        this.R2 = R2;
        this.FrontSurf = new CurvSurf(n, n1, R1);
        this.BackSurf = new CurvSurf(n1, n, R2);
        this.Medium = new FreeSpace(l, n1);

        super(calcMat())
    }
    //Default to a 10 mm thick lens with global ref ind and flat surfaces
    public ThickLens() {
        this(0.01, n, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    //Calculate the matrix for a thick lens
    private double[][] calcMat() {
        return matMult(matMult(BackSurf.getMat(),Medium.getMat()),FrontSurf.getMat());
    }

    public void reverse() {
        //Front surface is now the back surface, and vice versa
        private ABCD ABCDTemp = FrontSurf;
        FrontSurf = BackSurf;
        BackSurf = ABCDTemp;
        //Reverse the radii of curvature
        R1 = -R1;
        R2 = -R2;
        //Reset the radii of curvature (which also recalculates and resets the matrix)
        setR1(R1);
        setR2(R2);
    }

    //All the get functions
    public double getL() {
        return l;
    }
    public double getn() {
        return n1;
    }
    public double getR1() {
        return R1;
    }
    public double getR2() {
        return R2;
    }

    //All the set functions
    public void setL(double l) {
        this.l = l;
        Medium.setL(l);
        super.setMat(calcMat());
    }
    public void setn(double n1) {
        this.n1 = n1;
        FrontSurf.setnF(n1);
        BackSurf.setnI(n1);
        Medium.setn(n1);
        super.setMat(calcMat());
    }
    public void setR1(double R1) {
        this.R1 = R1;
        FrontSurf.setR(R1);
        super.setMat(calcMat());
    }
    public void setR2(double R2) {
        this.R2 = R2;
        BackSurf.setR(R2);
        super.setMat(calcMat());
    }

    //Matrix multiplication code
    //Note, this is repeated in the resonator system code, fix this somehow? Global function?
    private double[][] matMult(double[][] mat1, double[][] mat2) {
        private double[][] mat = new double[2][2];

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    mat[i][j] += mat1[i][k] * mat2[k][j];

        return mat;
    }
}