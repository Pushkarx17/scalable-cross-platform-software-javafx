package binmethod;

import java.util.List;

public class RiceRule extends BinFormulae {

    public RiceRule(List<Double> data) {
        super(data);
    }

    @Override
    public void calculateNumberOfBins() {
        int n = data.size();
        numberOfBins = (int) Math.round(2 * Math.pow(n, 1.0 / 3.0));
    }
}