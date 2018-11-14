/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.transforms;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author keyalisth
 */
public class RotateX extends Rotacao {
    
    public RotateX(float angle){
        super(angle);
    }
    
    @Override
    public float[][] doTransform() {
        matrix[1][1]= (float) cos(super.getAngle());
        matrix[1][2]=(float )-sin(super.getAngle());
        matrix[2][1]=(float) sin(super.getAngle());
        matrix[2][2]=(float) cos(super.getAngle());
        
        return matrix;
    }
}
