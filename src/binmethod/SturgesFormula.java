package binmethod;

import java.util.List;

public class SturgesFormula extends BinFormulae {

    public SturgesFormula(List<Double> data) {
        super(data);
    }

    @Override
    public void calculateNumberOfBins() {
        int n = data.size();
        numberOfBins = (int) Math.round(3.3 * Math.log10(n) + 1);
    }
}