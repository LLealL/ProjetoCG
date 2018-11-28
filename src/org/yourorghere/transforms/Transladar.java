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
        matrix[3][0]=x;
        matrix[3][1]=y;
        matrix[3][2]=z;
        
        return matrix;
    }
    
    
    
    

    
}
