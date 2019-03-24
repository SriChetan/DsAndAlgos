package com.sri.alogs;


import java.util.Random;

public class TravellingSalesmanProblemSA {

    private double[][] distMatrix;
    private int noOfPoints;
    private double initTemp;
    private double coolingRate;
    private double finalTemp;
    private int[] seq;
    private double bestCost;

    public void init(double[][] distMatrix, int noOfPoints, double initTemp, double coolingRate, double finalTemp)
    {
        this.distMatrix = distMatrix;
        this.noOfPoints = noOfPoints;
        this.initTemp = initTemp;
        this.coolingRate = coolingRate;
        this.finalTemp = finalTemp;
    }

    public int[] getSeq() {
        return seq;
    }

    public double getBestCost() {
        return bestCost;
    }

    public void executeSimulatedAnnealing()
    {
        seq = new int[noOfPoints];
        for (int i = 0; i < noOfPoints; i++) {
            seq[i] = i;
        }

        Random rad = new Random();
        bestCost = getTotalCost(seq);
        long itrs = 0;
        double temp = initTemp;
        while (temp > finalTemp)
        {
            itrs++;
            int from = rad.nextInt(noOfPoints);
            int to = rad.nextInt(noOfPoints);

            if(from != to)
            {
                seq[from] = seq[from] ^ seq[to];
                seq[to] = seq[from] ^ seq[to];
                seq[from] = seq[from] ^ seq[to];

                double cost = getTotalCost(seq);
                double delta = bestCost - cost;

                if(delta > 0 || (Math.exp(delta/temp) > rad.nextDouble()))
                {
                    bestCost = cost;
                    System.out.println("accepted " + Math.exp(delta/temp));
                }
                else {
                    System.out.println("Ignored ");
                    seq[from] = seq[from] ^ seq[to];
                    seq[to] = seq[from] ^ seq[to];
                    seq[from] = seq[from] ^ seq[to];
                }
            }
            temp *= coolingRate;
        }

        System.out.println("Iternations " + itrs);
    }

    public double getTotalCost(int[] seq)
    {
        double cost = 0;

        for (int i = 0; i < seq.length - 1; i++) {

            cost += distMatrix[seq[i]][seq[i + 1]];
        }
        cost += distMatrix[seq[seq.length - 1]][seq[0]];
        return cost;
    }
}
