package binmethod;

import java.util.List;
import java.util.Arrays;

import binmethod.*;  



public class Unit_test_binmethod
{
    public static void main(String[] args) {

       // Create array of data 
       List<Double> exampleData = Arrays.asList(1., 1., 3., 3., 3., 2., 10., 10., 6., 6., 6.);
		
       // test SturgesFormula class
       SturgesFormula SturgesInstance = new SturgesFormula(exampleData); 
       SturgesInstance.calculateNumberOfBins();
       System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
		
       // test SquareRootChoice class
       SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(exampleData);
       SquareRootChoiceInstance.calculateNumberOfBins();
       System.out.printf("By Square Root Formula: %d \n", SquareRootChoiceInstance.getNumberOfBins());
		
       // test RiceRule class
       RiceRule RiceRuleInstance = new RiceRule(exampleData);
       RiceRuleInstance.calculateNumberOfBins();
       System.out.printf("By Rice Rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());

    }
}