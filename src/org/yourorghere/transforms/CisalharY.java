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
public class CisalharY extends Cisalhamento {
    private float sx,sz;
    
    public CisalharY(float sx,float sz){
        this.sx=sx;
        this.sz=sz;
    }
    
    @Override
    public float[][] doTransform() {
        matrix[0][1]=sx;
        matrix[2][1]=sz;
        
        return matrix;
    }
    
}
