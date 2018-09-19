/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras.persistence;

import static java.time.Clock.system;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.media.opengl.GL;
import org.yourorghere.figuras.Figura;

/**
 *
 * @author keyalisth
 */
public class BancoFiguras{
    private TreeSet<Figura> set;
    private static BancoFiguras instance;
    
    public BancoFiguras(){
        set=new TreeSet<Figura>();
    }
    
    public static BancoFiguras getInstance(){
        if(instance==null){
            instance= new BancoFiguras();
        }
        return instance;
    }
    
    public void addFigura(Figura f){
        
        set.add(f);
    }
    public void removeFigura(Figura f){
        set.remove(f);
    }
    public Figura getFigura(Integer id){
        for(Figura fig: set){
            if(fig.getID()==id){
                return fig;
            }
        }
        return null;
    }
    public List<Figura> getSetFiguras(GL gl){
        LinkedList<Figura> list = new LinkedList<Figura>();
        for(Figura fig: set){
            list.add(fig);
        }
        return list;
    }
    
    public void drawFigures(GL gl){
        for(Figura fig: set){
            fig.desenhar(gl);
        }
    }
    
    public void clearFigures(){
        for(Figura fig: set){
            removeFigura(fig);
        }
    }
}