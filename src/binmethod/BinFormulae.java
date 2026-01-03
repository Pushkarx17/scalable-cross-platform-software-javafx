package binmethod;

import java.util.List;

public abstract class BinFormulae{
    protected List<Double> data;
    protected int numberOfBins;
    
    public BinFormulae( List<Double> data ){
        this.data = data;
    }
    public abstract void calculateNumberOfBins();
    public int getNumberOfBins(){
        return numberOfBins;
    }
}