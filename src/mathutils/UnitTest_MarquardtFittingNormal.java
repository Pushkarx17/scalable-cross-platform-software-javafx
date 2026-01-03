/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;

import org.apache.commons.math3.fitting.*;
import mathutils.Gaussianfitting;


/**
 *
 * @author pmzsp1
 */
public class UnitTest_MarquardtFittingNormal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /** This example is taken from the library website 
        * http://commons.apache.org/proper/commons-math/apidocs/index.html
        *   Fits points to a Gaussian function.
        *   The initial guess values must be passed in the following order:
        *   Normalization
        *   Mean
        *   Sigma
        *   The optimal values will be returned in the same order. 
        */
        
        WeightedObservedPoints obs = new WeightedObservedPoints();
        obs.add(4.0254623,  531026.0);
        obs.add(4.03128248, 984167.0);
        obs.add(4.03839603, 1887233.0);
        obs.add(4.04421621, 2687152.0);
        obs.add(4.05132976, 3461228.0);
        obs.add(4.05326982, 3580526.0);
        obs.add(4.05779662, 3439750.0);
        obs.add(4.0636168,  2877648.0);
        obs.add(4.06943698, 2175960.0);
        obs.add(4.07525716, 1447024.0);
        obs.add(4.08237071, 717104.0);
        obs.add(4.08366408, 620014.0);
        
        Gaussianfitting.calculateFitting(obs);
        
        
        // Print out result on screen 
        System.out.printf("Normalization factor(alpha) = %f\n",Gaussianfitting.alpha);
        System.out.printf("Mean(mu) = %f\n",Gaussianfitting.mu);
        System.out.printf("Sigma = %f\n",Gaussianfitting.sigma);
        
        
    }
    
}
