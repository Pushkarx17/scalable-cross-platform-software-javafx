package statutils;

import java.util.Arrays;
import java.util.List;


public class Unit_test_statutils {

    public static void main(String[] args) {

        List<Double> exampleData = Arrays.asList(1., 1., 3., 3., 3., 2., 10., 10., 6., 6., 6.);

        System.out.println("Mean: " + BasicStats.mean(exampleData));
        System.out.println("Variance: " + BasicStats.variance(exampleData));
        System.out.println("Std Dev: " + BasicStats.standardDeviation(exampleData));
        System.out.println("Min: " + BasicStats.min(exampleData));
        System.out.println("Max: " + BasicStats.max(exampleData));
        System.out.println("Median: " + BasicStats.median(exampleData));

        int k = 4; // from binmeathod using any of those three
        int[] bins = HistogramUtils.countBins(exampleData, k);

        double binWidth;
        binWidth = (BasicStats.max(exampleData) - BasicStats.min(exampleData)) / k;

        double[] normalised = HistogramUtils.normaliseHistogram(bins, binWidth);

        System.out.println("Normalised histogram:");
        for (double v : normalised) {
            System.out.println(v);
        }
    }
}