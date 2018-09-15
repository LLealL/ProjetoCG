/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import javax.media.opengl.GL;
import org.yourorghere.figuras.Util.RGB;

/**
 *
 * @author keyalisth
 */
public class Triangulo extends Figura{

    public Triangulo(Integer id, int x, int y, RGB cor) {
        super(id, x, y, cor);     
    }


    @Override
    public void desenhar(GL gl) {
        gl.glTranslatef(-1.5f, -2.0f, -3.0f);
        gl.glBegin(0);
            gl.glColor3i(super.getCor().getRed(),super.getCor().getGreen(),super.getCor().getBlue());
            gl.glVertex2f(super.getX()-3,super.getY()-2);
            gl.glVertex2f(super.getX(),super.getY()+2);
            gl.glVertex2f(super.getX()+3,super.getY()-2);
        gl.glEnd();
    }


    
    
}
