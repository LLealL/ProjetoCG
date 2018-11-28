/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.transforms;

/**
 *
 * @author lucas
 */
public class CisalharZ extends Cisalhamento{
    private float sx,sy;
    
    public CisalharZ(float sx,float sy){
        this.sx=sx;
        this.sy=sy;
    }
    
    @Override
    public float[][] doTransform() {
        matrix[0][2]=sx;
        matrix[1][2]=sy;
        
        return matrix;
    }
}
