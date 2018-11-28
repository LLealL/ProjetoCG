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
public abstract class Rotacao extends Transform {
    float angle=0f;
    
    protected Rotacao(float a){
        angle=a;
    }
    
    protected float getAngle(){
        return angle;
    }
    
    protected void setAngle(float a){
        angle=a;
    }
    

    
    public abstract float[][] doTransform();
}
