package statutils;

import java.util.Collections;
import java.util.List;

public class BasicStats{

    public static double mean(List<Double> data) {
        double sum = 0;
        sum = data.stream().map((d) -> d).reduce(sum, (accumulator, _item) -> accumulator + _item); //IDE automatically did this
        return sum / data.size();
    }

    public static double variance(List<Double> data) {
        double mean = mean(data);
        double sum = 0;
        sum = data.stream().map((d) -> Math.pow(d - mean, 2)).reduce(sum, (accumulator, _item) -> accumulator + _item);  //IDE automatically did this
        return sum / data.size();
    }

    public static double standardDeviation(List<Double> data) {
        return Math.sqrt(variance(data));
    }

    public static double max(List<Double> data) {
        return Collections.max(data);
    }

    public static double min(List<Double> data) {
        return Collections.min(data);
    }

    public static double median(List<Double> data) {
        Collections.sort(data);
        int n = data.size();
        if (n % 2 == 0) {
            return (data.get(n/2 - 1) + data.get(n/2)) / 2.0;
        } else {
            return data.get(n/2);
        }
    }
}