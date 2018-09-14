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
public abstract class Figura {
    private long id;
    
    protected Figura(long id){
        this.id=id;
    }
    
    public abstract void desenhar(GL gl);
    public abstract void mudaCor(RGB cor);
    public abstract void realizarEscala(int sx ,int sy);
    public abstract void transform(int tx , int ty);
}

