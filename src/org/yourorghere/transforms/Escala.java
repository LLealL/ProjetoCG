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

    @Override
    public void doTransform(GL gl) {
        gl.glScalef(x, y, z);
    }
    
}