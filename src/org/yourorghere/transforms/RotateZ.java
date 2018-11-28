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
public class RotateZ extends Rotacao{

    public RotateZ(float angle){
        super(angle);
    }
    
    @Override
    public float[][] doTransform() {
        matrix[0][0]= (float) cos(super.getAngle());
        matrix[0][1]=(float)sin(super.getAngle());
        matrix[1][0]=(float) -sin(super.getAngle());
        matrix[1][1]=(float) cos(super.getAngle());
        
        return matrix;
    }

    
}
