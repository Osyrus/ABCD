package main;

import main.mats.*;
import main.sys.*;
import org.apache.commons.math3.complex.Complex;

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
        Complex q;
        double hT;

        //My laser configuration!! For complex testing
        //The HR mirror
        resonator.addABCD(new Mirror(), 0, 0);
        resonator.addABCD(new Mirror(), 0, 1);
        System.out.println("HR Mirror: "+resonator.getABCD(0, 0).toString());
        //The following free space
        resonator.addABCD(new FreeSpace(0.053), 1, 0);
        resonator.addABCD(new FreeSpace(0.053), 1, 1);
        System.out.println("L1: "+resonator.getABCD(1, 0).toString());
        //Then the negative thick lens
        resonator.addABCD(new ThickLens(0.004, nL, 0.039, Double.POSITIVE_INFINITY), 2, 0);
        resonator.addABCD(new ThickLens(0.004, nL, 0.039, Double.POSITIVE_INFINITY), 2, 1);
        System.out.println("Lens1: "+resonator.getABCD(2, 0).toString());
        //More free space
        resonator.addABCD(new FreeSpace(0.145), 3, 0);
        resonator.addABCD(new FreeSpace(0.145), 3, 1);
        System.out.println("Lsep: "+resonator.getABCD(3, 0).toString());
        //The positive thick lens
        resonator.addABCD(new ThickLens(0.002, nL, Double.POSITIVE_INFINITY, 0.104), 4, 0);
        resonator.addABCD(new ThickLens(0.002, nL, Double.POSITIVE_INFINITY, 0.104), 4, 1);
        System.out.println("Lens2: "+resonator.getABCD(4, 0).toString());
        //More free space
        resonator.addABCD(new FreeSpace(0.03), 5, 0);
        resonator.addABCD(new FreeSpace(0.03), 5, 1);
        System.out.println("L2: "+resonator.getABCD(5, 0).toString());
        //The first Brewster face
        resonator.addABCD(new CurvSurf(Global.n, n2, Double.POSITIVE_INFINITY, 0), 6, 0);
        resonator.addABCD(new CurvSurf(Global.n, n2, Double.POSITIVE_INFINITY, bAng), 6, 1);
        System.out.println("B1: "+resonator.getABCD(6, 1).toString());
        //The gain medium (for now just free space, test GRIN later)
        resonator.addABCD(new FreeSpace(0.0633, n2), 7, 0);
        resonator.addABCD(new FreeSpace(0.0633, n2), 7, 1);
        System.out.println("GM: "+resonator.getABCD(7, 0).toString());
        //The second Brewster face
        resonator.addABCD(new CurvSurf(n2, Global.n, Double.POSITIVE_INFINITY, 0), 8, 0);
        resonator.addABCD(new CurvSurf(n2, Global.n, Double.POSITIVE_INFINITY, 90.0-bAng), 8, 1);
        System.out.println("B2: "+resonator.getABCD(8, 1).toString());
        //More free space
        resonator.addABCD(new FreeSpace(0.05), 9, 0);
        resonator.addABCD(new FreeSpace(0.05), 9, 1);
        System.out.println("L3: "+resonator.getABCD(9, 0).toString());
        //The output coupler
        resonator.addABCD(new Mirror(), 10, 0);
        resonator.addABCD(new Mirror(), 10, 1);
        System.out.println("OC: "+resonator.getABCD(10, 0).toString());

        for (int i = 0; i < dimN; i++) {
            System.out.println("In dimension "+(i+1)+":");
            if (resonator.isStable(i)) {
                q = resonator.getQ(i);
                hT = resonator.getHalfTrace(i);
                System.out.println("This resonator is stable, and has an initial q of: "+q.toString());
                System.out.println("With a half trace of: "+hT);
            } else {
                System.out.println("This resonator is unstable!");
            }
            System.out.println("Round trip matrix: "+resonator.getRTMat(i).toString());
        }
    }
}