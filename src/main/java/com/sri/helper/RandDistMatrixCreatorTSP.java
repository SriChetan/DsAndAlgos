package com.sri.helper;

import java.util.*;
import java.util.stream.Collectors;

public class RandDistMatrixCreatorTSP {

    private double[][] distMatrix;
    private double shortPath = 0;
    public double[][] getDistMatrix() {
        return distMatrix;
    }

    public double getShortPath() {
        return shortPath;
    }

    public void build(String reqSeq, int seqMinBound, int maxBound)
    {
        String[] seq = reqSeq.split(",");
        int size = seq.length;

        Set<String> seqSet = new HashSet<>();
        for (int i = 0; i < seq.length - 1;i++) {
            seqSet.add(seq[i] + "__" + seq[i + 1]);
        }
        seqSet.add(seq[seq.length - 1] + "__" + seq[0]);


        distMatrix = new double[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                String key = i + "__" + j;
                if(seqSet.contains(key))
                {
                    double randVal = rand.nextInt(seqMinBound);
                    distMatrix[i][j] = randVal;
                    shortPath += randVal;
                }
                else {
                    distMatrix[i][j] = rand.nextInt(maxBound - seqMinBound) + seqMinBound;
                }
            }
        }
    }

    public String getRandString(int size)
    {
        Integer[] seq = new Integer[size];

        for (int i = 0; i < size; i++) {
            seq[i] = i;
        }

        Random rad = new Random();
        for (int i = 0; i < size * 100; i++) {
            int from = rad.nextInt(size);
            int to = rad.nextInt(size);

            if(from != to)
            {
                seq[from] = seq[from] ^ seq[to];
                seq[to] = seq[from] ^ seq[to];
                seq[from] = seq[from] ^ seq[to];
            }
        }

        String out = Arrays.stream(seq).map(i -> i.toString()).collect(Collectors.joining(","));
        return out;
    }

}
