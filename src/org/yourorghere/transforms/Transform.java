/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.transforms;

/**
 *
 * @author keyalisth
 */
public abstract class Transform {
    protected float[][] matrix = {
        {1f,0f,0f,0f},
        {0f,1f,0f,0f},
        {0f,0f,1f,0f},
        {0f,0f,0f,1f}
    };
    
    public abstract float[][] doTransform();
    
}
