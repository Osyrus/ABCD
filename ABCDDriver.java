import org.apache.commons.math3.complex.Complex;

class ABCDDriver {
    public static void main(String[] args) {
        System resonator = new System(1);
        Global.n = 1;
        Complex q = Complex.ZERO;
        double hT = 0;

        resonator.addABCD(new Mirror(), 1, 0);
        resonator.addABCD(new FreeSpace(0.1), 2, 0);
        resonator.addABCD(new FreeSpace(0.1), 3, 0);
        resonator.addABCD(new Mirror(), 4, 0);
        resonator.addABCD(new ThinLens(0.2), 3, 0);

        if (system.isStable()) {
            q = system.getQ(0);
            hT = system.getHalfTrace();
            System.out.println("The resonator is stable, and has an initial q of: "+q.toString());
        } else {
            System.out.println("The resonator is unstable!")
        }
        System.out.println("A = "+system.getRTMat(0).getA());
        System.out.println("B = "+system.getRTMat(0).getB());
        System.out.println("C = "+system.getRTMat(0).getC());
        System.out.println("D = "+system.getRTMat(0).getD());
    }
}