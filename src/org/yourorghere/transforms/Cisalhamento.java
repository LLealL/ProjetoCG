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
public  class Cisalhamento extends Transform {
    
    protected float x,y,z;
    
    
    public Cisalhamento(float X,float Y,float Z){
        x=X;
        y=Y;
        z=Z;
    }

    @Override
    public float[][] doTransform(){
        
        return matrix;
    }
    
}
