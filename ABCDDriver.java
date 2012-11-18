import org.apache.commons.math3.complex.Complex;

class ABCDDriver {
    public static void main(String[] args) {
        Resonator resonator = new Resonator(1);
        Global.n = 1;
        Complex q = Complex.ZERO;
        double hT = 0;

        resonator.addABCD(new Mirror(), 1, 0);
        resonator.addABCD(new FreeSpace(0.1), 2, 0);
        resonator.addABCD(new FreeSpace(0.1), 3, 0);
        resonator.addABCD(new Mirror(), 4, 0);
        resonator.addABCD(new ThinLens(0.2), 3, 0);

        if (resonator.isStable(0)) {
            q = resonator.getQ(0);
            hT = resonator.getHalfTrace(0);
            System.out.println("The resonator is stable, and has an initial q of: "+q.toString());
        } else {
            System.out.println("The resonator is unstable!");
        }
        System.out.println("A = "+resonator.getRTMat(0).getA());
        System.out.println("B = "+resonator.getRTMat(0).getB());
        System.out.println("C = "+resonator.getRTMat(0).getC());
        System.out.println("D = "+resonator.getRTMat(0).getD());
    }
}