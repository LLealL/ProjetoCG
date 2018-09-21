/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import static java.lang.Math.sqrt;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_TRIANGLES;
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
        gl.glTranslatef(0.0f, 0.0f, -1.0f);
        gl.glBegin(GL_TRIANGLES);
            gl.glColor3f(super.getCor().getRed(),super.getCor().getGreen(),super.getCor().getBlue());
            gl.glVertex2f(super.getX(), (float) (super.getY() + sqrt(3.0f)/1.0f));
            gl.glVertex2f(super.getX() +2.0f, (float) (super.getY() - sqrt(3.0f)/1.0f));
            gl.glVertex2f(super.getX()-2.0f, (float) (super.getY() - sqrt(3.0f)/1.0f));
        gl.glEnd();
    }


    
    
}