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
    private int escalaX;
    private int escalaY;
    private int x;
    private int y;
    private RGB cor;
    
    public Poligono(int n,int x, int y,RGB cor,long id){
        super(id);
        lados=n;
        escalaX=1;
        escalaY=1;
        this.x=x;
        this.y=y;
        this.cor=cor;
    }
        
    public void realizarEscalaProporcional(int s){
        escalaX=s;
        escalaY=s;
    }
    
    public void realizarEscala(int sx,int sy){
        escalaX=sx;
        escalaY=sy;
    }
    
    public void transform(int tx,int ty){
        this.x=this.x+tx;
        this.y=this.y+ty;
    }
    
    public void desenhar(GL gl){
        double angulo;
          gl.glTranslatef(-1.5f, -2.0f, -3.0f);
        gl.glBegin(GL.GL_TRIANGLES);
        /*gl.glColor3i(cor.getRed(), cor.getGreen(), cor.getBlue());
        for(int i=0;i<lados;++i){
            angulo= (2*Math.PI*i/lados);
            gl.glVertex2d(Math.cos(angulo),Math.sin(angulo));
        }*/
      
        gl.glVertex2f(1.0f, 1.0f);
        gl.glVertex2f(2.0f, 2.0f);
        gl.glVertex2f(3.0f, 1.0f);
        gl.glEnd();
    }


    public void mudaCor(RGB cor) {
        this.cor=cor;
   }

}

