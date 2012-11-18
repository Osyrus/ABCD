import org.apache.commons.math3.complex.Complex;

class ABCDDriver {
    public static void main(String[] args) {
        Resonator resonator = new Resonator(1);
        Global.n = 1;
        Complex q;
        double hT = 0;

        resonator.addABCD(new Mirror(), 0, 0);
        resonator.addABCD(new FreeSpace(0.1), 1, 0);
        resonator.addABCD(new FreeSpace(0.1), 2, 0);
        resonator.addABCD(new Mirror(), 3, 0);
        resonator.addABCD(new ThinLens(0.2), 2, 0);

        if (resonator.isStable(0)) {
            q = resonator.getQ(0);
            hT = resonator.getHalfTrace(0);
            System.out.println("The resonator is stable, and has an initial q of: "+q.toString());
        } else {
            System.out.println("The resonator is unstable!");
        }

        System.out.println("Round trip matrix: "+resonator.getRTMat(0).toString());
    }
}