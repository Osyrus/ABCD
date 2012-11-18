
public class GRINLens extends ABCD {
    private double l; //Length of GRIN lens
    private double n1; //Base refractive index of the lens material
    private double n2; //Quadratic refractive index factor

    //Constructor
    public GRINLens(double l, double n1, double n2) {
        super();

        this.l = l;
        this.n1 = n1;
        this.n2 = n2;

        super.setMat(calcMat());
    }
    //Default to 10 mm thickness with global ref ind and no quad ref ind factor
    public GRINLens() {
        this(0.01, Global.n, 0);
    }

    //Matrix Calculation
    private double[][] calcMat() {
        double A = Math.cos(l * Math.sqrt(n2/n1));
        double B = Math.sin(l * Math.sqrt(n2/n1)) / Math.sqrt(n1*n2);
        double C = -Math.sqrt(n1*n2) * Math.sin(l * Math.sqrt(n2/n1));
        double D = Math.cos(l * Math.sqrt(n2/n1));

        return new double[][] {{A, B},{C, D}};
    }

    //Get Functions
    public double getL() {
        return l;
    }
    public double getn1() {
        return n1;
    }
    public double getn2() {
        return n2;
    }

    //Set Functions
    public void setL(double l) {
        this.l = l;
        super.setMat(calcMat());
    }
    public void setn1(double n1) {
        this.n1 = n1;
        super.setMat(calcMat());
    }
    public void setn2(double n2) {
        this.n2 = n2;
        super.setMat(calcMat());
    }
}