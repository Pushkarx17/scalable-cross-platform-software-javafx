/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statutils;

/**
 *
 * @author eexpk16
 */

import java.util.Arrays;

public class Histogram {

    private int numBins;
    private double min;
    private double max;
    private double binWidth;
    private double[] edges; 
    private double[] counts; 
    private int totalCount;

    
    public Histogram(double[] data, int numBins) {
        
    }
     
    
    public double[] getCounts() {
        return counts;
    }

    public double[] getEdges() {
        return edges;
    }

    public int getNumBins() {
        return numBins;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public double getBinWidth() {
        return binWidth;
    }
}

  