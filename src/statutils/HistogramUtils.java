package statutils;

import java.util.List;

public class HistogramUtils{

    public static int[] countBins(List<Double> data, int k) {
        double min = BasicStats.min(data);
        double max = BasicStats.max(data);
        double width = (max - min) / k;

        int[] bins = new int[k];

        for (double d : data) {
            int index = (int) ((d - min) / width);
            if (index == k) index--; // we will put it to all bin
            bins[index]++;
        }
        return bins;
    }

    public static double[] normaliseHistogram(int[] bins, double binWidth) {
        double sum = 0;
        for (int f : bins) {
            sum += f * binWidth;
        }

        double[] normalised = new double[bins.length];
        for (int i = 0; i < bins.length; i++) {
            normalised[i] = bins[i] / sum;
        }
        return normalised;
    }
}