/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import org.yourorghere.figuras.Util.RGB;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.media.opengl.GL;

/**
 *
 * @author keyalisth
 */
public class Poligono extends Figura {
    
    private int lados;

    
    public Poligono(Integer id,float x, float y,RGB cor,int n){
        super(id,x,y,cor);
        lados=n;
    }
        
    
    @Override
    public void desenhar(GL gl){
        double angulo;
        gl.glTranslatef(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(super.getCor().getRed(), super.getCor().getGreen(), super.getCor().getBlue());
        for(int i=0;i<lados;++i){
            angulo= (2*Math.PI*i/lados);
            gl.glVertex2d(super.getX() + Math.cos(angulo)*(+50.0f)*this.escalaX,super.getY()+ Math.sin(angulo)*(+50.0f)*this.escalaY);
        }
        gl.glEnd();
    }
}