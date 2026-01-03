/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;

/**
 *
 * @author eexpk16
 */

import org.apache.commons.math3.fitting.*;

public class Gaussianfitting {
    
    public static double alpha;
    public static double mu;
    public static double sigma;
    
    public static void calculateFitting(WeightedObservedPoints obs) {
        double[] p = GaussianCurveFitter.create().fit(obs.toList());
        alpha = p[0];
        mu    = p[1];
        sigma = p[2];
    }
}