package com.sri.alogs;

import com.sri.helper.RandDistMatrixCreatorTSP;
import org.junit.Test;

import java.util.Arrays;

public class TravellingSalesmanProblemSATest {


    @Test
    public void tspTest()
    {
        TravellingSalesmanProblemSA tsp = new TravellingSalesmanProblemSA();

        double[][] distMatrix = {
               //0 1 2 3 4
                {0,1,2,4,4},
                {4,0,3,2,1},
                {2,3,0,1,4},
                {1,2,4,0,5},
                {4,4,1,5,0}
        };

        RandDistMatrixCreatorTSP helper = new RandDistMatrixCreatorTSP();
        String seq = helper.getRandString(15);
        helper.build(seq, 4, 20);
        distMatrix = helper.getDistMatrix();

        /*for (int i = 0; i < distMatrix.length; i++) {
            System.out.println(Arrays.toString(distMatrix[i]));
        }*/


        tsp.init(distMatrix, distMatrix.length, 1000000, 0.9999, 0.00000001);
        long start = System.currentTimeMillis();
        tsp.executeSimulatedAnnealing();

        System.out.println("Expected sequence " + Arrays.toString(seq.split(",")));
        System.out.println("Actual sequence" + Arrays.toString(tsp.getSeq()));

        System.out.println("Expected cost " + helper.getShortPath());
        System.out.println("Actual cost " + tsp.getBestCost());
    }
}
