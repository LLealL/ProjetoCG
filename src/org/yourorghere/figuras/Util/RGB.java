/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras.Util;

import java.io.Serializable;

/**
 *
 * @author keyalisth
 */
public class RGB implements Serializable{
    private float red;
    private float green;
    private float blue;

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }
    
    public RGB(float r,float g , float b){
        this.red=r;
        this.green=g;
        this.blue=b;
    }
    
    
}
