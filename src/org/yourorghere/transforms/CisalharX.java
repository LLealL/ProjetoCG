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
public class CisalharX extends Cisalhamento {
    private float sy,sz;
    
    public CisalharX(float sy,float sz){
        this.sy=sy;
        this.sz=sz;
    }
    
    
    public float[][] doTransform() {
        super.matrix[1][0]=sy;
        super.matrix[2][0]=sz;
        
        return matrix;
    }
    
}
