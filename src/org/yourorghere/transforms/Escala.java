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
public class Escala extends Transform {
    float x,y,z;

    public Escala(float X,float Y, float Z){
        x=X;
        y=Y;
        z=Z;
    }

    @Override
    public float[][] doTransform() {
        matrix[0][0]= x;
        matrix[1][1]=y;
        matrix[2][2]=z;
        
        return matrix;
    }
}
