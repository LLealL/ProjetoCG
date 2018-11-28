/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import java.io.Serializable;
import org.yourorghere.figuras.Util.RGB;
import javax.media.opengl.GL;

/**
 *
 * @author keyalisth
 */
public abstract class Figura implements Comparable<Figura>, Serializable{
    private final Integer id;
    private float x;
    private float y;
    protected float escalaX;
    protected float escalaY;
    private RGB cor;
    
    protected Figura(Integer id,float x,float y,RGB cor){
        this.id=id;
        this.x = x;
        this.y= y;
        this.escalaX=1.0f;
        this.escalaY=1.0f;
        this.cor = cor;
    }
    
    public abstract void desenhar(GL gl);
    public void mudaCor(RGB cor){
        this.cor=cor;
    }
    public void realizarEscala(float sx ,float sy){
        escalaX=sx;
        escalaY=sy;
    }
    
    public void realizarEscalaX(float sx){
        escalaX=sx;
    }
    
        public void realizarEscalaY(float sy){
        escalaY=sy;
    }
    
    public void transform(float tx , float ty){
        this.x=tx;
        this.y=ty;
    }
    
    protected RGB getCor(){
        return this.cor;
    }
    
    protected float getX(){
        return x;
    }
    
    protected float getY(){
       return y;
    }
    public Integer getID(){
        return id;
    }
    
    protected float getEscalaX(){
        return this.escalaX;
    }
    
    protected float getEscalaY(){
        return this.escalaY;
    }
    
    
    public int compareTo(Figura f) {
       return this.getID().compareTo(f.getID());
    }
     
}