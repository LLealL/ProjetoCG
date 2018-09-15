/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras;

import org.yourorghere.figuras.Util.RGB;
import javax.media.opengl.GL;

/**
 *
 * @author keyalisth
 */
public abstract class Figura implements Comparable<Figura>{
    private final Integer id;
    private int x;
    private int y;
    private int escalaX;
    private int escalaY;
    private RGB cor;
    
    protected Figura(Integer id,int x,int y,RGB cor){
        this.id=id;
    }
    
    public abstract void desenhar(GL gl);
    public void mudaCor(RGB cor){
        this.cor=cor;
    }
    public void realizarEscala(int sx ,int sy){
        escalaX=sx;
        escalaY=sy;
    }
    public void transform(int tx , int ty){
        this.x=this.x+tx;
        this.y=this.y+ty;
    }
    
    protected RGB getCor(){
        return this.cor;
    }
    
    protected int getX(){
        return x;
    }
    
    protected int getY(){
        return y;
    }
    public Integer getID(){
        return id;
    }
    
    public int compareTo(Figura f) {
       return this.getID().compareTo(f.getID());
    }
     
}

