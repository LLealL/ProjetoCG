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
public class Cisalhamento extends Transform {

    static float[] m = {
        1.0f,0.0f,0.0f,0.0f, 
        0.0f,1.0f,0.0f,0.0f, 
        0.0f,0.0f,1.0f,0.0f, 
        0.0f,0.0f,0.0f,1.0f
        };
    
    
    @Override
    public void doTransform(GL gl) {
        
	m[0] = 1.f; m[4] = 0.0f;  m[8] = -.5f;  m[12] = 0.0f;
	m[1] = 0.0f; m[5] = 1.f;  m[9] = .0f;  m[13] = 0.0f;
	m[2] = 0.0f; m[6] = 0.0f;  m[10] = 1.f;  m[14] = .0f;
	m[3] = 0.0f; m[7] = 0.0f;  m[11] = 0.0f;  m[15] = 1.0f;
    }
    
}
