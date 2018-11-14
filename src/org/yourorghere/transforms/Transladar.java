/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.transforms;

import javax.media.opengl.GL;

/**
 *
 * @author lucas
 */
public class Transladar extends Transform {
    float x,y,z;
    
    public Transladar(float X,float Y, float Z ){
        x=X;
        y=Y;
        z=Z;
    }

    public float[][] doTransform() {
        matrix[0][3]=x;
        matrix[1][3]=y;
        matrix[2][3]=z;
        
        return matrix;
    }
    
    
    
    

    
}
