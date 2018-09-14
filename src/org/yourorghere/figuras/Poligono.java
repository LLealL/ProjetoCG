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

    
    public Poligono(int n,int x, int y,RGB cor,Integer id){
        super(id,x,y,cor);
        lados=n;
    }
        
    
    @Override
    public void desenhar(GL gl){
        double angulo;
        gl.glTranslatef(-1.5f, -2.0f, -3.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3i(super.getCor().getRed(), super.getCor().getGreen(), super.getCor().getBlue());
        for(int i=0;i<lados;++i){
            angulo= (2*Math.PI*i/lados);
            gl.glVertex2d(Math.cos(angulo)*(super.getX()+5),Math.sin(angulo)*(super.getY()+5));
        }
        gl.glEnd();
    }



}

