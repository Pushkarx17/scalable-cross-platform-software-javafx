/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;

import java.util.List;

/**
 *
 * @author eexpk16
 */
public class RiceRule extends BinFormulae{
    
    public RiceRule( List _data){
        n = _data.size();
    } 
    
    public void calculateNumberOfBins(){
        noOfPins = 2*(Math.pow(n,1/3));
    }
}