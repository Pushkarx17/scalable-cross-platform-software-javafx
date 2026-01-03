package binmethod;

import java.util.List;

public class SquareRootChoice extends BinFormulae {

    public SquareRootChoice(List<Double> data) {
        super(data);
    }

    @Override
    public void calculateNumberOfBins() {
        int n = data.size();
        numberOfBins = (int) Math.round(Math.sqrt(n));
    }
}