package main;

import org.apache.commons.math3.complex.Complex;
import main.mats.*;
import main.sys.*;

/**
*Driver class file
*
*@author Myles Clark
*/
public class ABCDDriver {
    public static void main(String[] args) {
        int dimN = 2;
        Resonator resonator = new Resonator(dimN);

        //Refractive indices
        Global.n = 1; //Air
        double n2 = 1.82; //YAG
        double nL = 1.52; //Lenses

        double bAng = Math.toDegrees(Math.atan(n2/Global.n));
        Complex[] q = new Complex[dimN];
        double[] hT = new double[dimN];

        //My laser configuration!! For complex testing
        //The HR mirror
        resonator.addABCD(new Mirror(), 0, 0);
        resonator.addABCD(new Mirror(), 0, 1);
        //The following free space
        resonator.addABCD(new FreeSpace(0.053), 1, 0);
        resonator.addABCD(new FreeSpace(0.053), 1, 1);
        //Then the negative thick lens
        resonator.addABCD(new ThickLens(0.004, nL, 0.039, Double.POSITIVE_INFINITY), 2, 0);
        resonator.addABCD(new ThickLens(0.004, nL, 0.039, Double.POSITIVE_INFINITY), 2, 1);
        //More free space
        resonator.addABCD(new FreeSpace(0.145), 3, 0);
        resonator.addABCD(new FreeSpace(0.145), 3, 1);
        //The positive thick lens
        resonator.addABCD(new ThickLens(0.002, nL, Double.POSITIVE_INFINITY, 0.104), 4, 0);
        resonator.addABCD(new ThickLens(0.002, nL, Double.POSITIVE_INFINITY, 0.104), 4, 1);
        //More free space
        resonator.addABCD(new FreeSpace(0.03), 5, 0);
        resonator.addABCD(new FreeSpace(0.03), 5, 1);
        //The first Brewster face
        resonator.addABCD(new CurvSurf(Global.n, n2, Double.POSITIVE_INFINITY, 0), 6, 0);
        resonator.addABCD(new CurvSurf(Global.n, n2, Double.POSITIVE_INFINITY, bAng), 6, 1);
        //The gain medium (for now just free space, test GRIN later)
        resonator.addABCD(new FreeSpace(0.0633, n2), 7, 0);
        resonator.addABCD(new FreeSpace(0.0633, n2), 7, 1);
        //More free space
        resonator.addABCD(new FreeSpace(0.05), 8, 0);
        resonator.addABCD(new FreeSpace(0.05), 8, 1);
        //The output coupler
        resonator.addABCD(new Mirror(), 9, 0);
        resonator.addABCD(new Mirror(), 9, 1);

        for (int i = 0; i < dimN; i++) {
            System.out.println("In dimension "+(i+1)+":");
            if (resonator.isStable(i)) {
                q[i] = resonator.getQ(i);
                hT[i] = resonator.getHalfTrace(i);
                System.out.println("This resonator is stable, and has an initial q of: "+q.toString());
                System.out.println("With a half trace of: "+hT[i]);
            } else {
                System.out.println("This resonator is unstable!");
            }
            System.out.println("Round trip matrix: "+resonator.getRTMat(i).toString());
        }
    }
}