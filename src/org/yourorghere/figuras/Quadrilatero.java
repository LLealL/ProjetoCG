/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import javax.media.opengl.GL;
import org.yourorghere.figuras.Figura;
import static javax.media.opengl.GL.GL_QUADS;
import org.yourorghere.figuras.Util.RGB;

/**
 *
 * @author keyalisth
 */
public class Quadrilatero extends Figura {

    public Quadrilatero(Integer id, float x, float y, RGB cor) {
        super(id, x, y, cor);
    }

    @Override
    public void desenhar(GL gl) {
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL_QUADS);
            gl.glColor3f(super.getCor().getRed(),super.getCor().getGreen(),super.getCor().getBlue());
            gl.glVertex2f((super.getX()-100.0f)*this.escalaX,(super.getY()+100.0f)*this.escalaY);
            gl.glVertex2f((super.getX()+100.0f)*this.escalaX,(super.getY()+100.0f)*this.escalaY);
            gl.glVertex2f((super.getX()+100.0f)*this.escalaX,(super.getY()-100.0f)*this.escalaY);
            gl.glVertex2f((super.getX()-100.0f)*this.escalaX,(super.getY()-100.0f)*this.escalaY);
        gl.glEnd();
    }
    
}