/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;

import java.util.List;
import java.util.Arrays;


/**
 *
 * @author eexpk16
 */
public class SquareRootChoice extends BinFormulae {
    public int n;
    public SquareRootChoice( List _data){
        n = _data.size();
    } 
    
    public void calculateNumberOfBins(){
        noOfPins = Math.pow(n,1/2);
    }
    
}