/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras.persistence;

import java.io.FileNotFoundException;
import static java.time.Clock.system;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import org.yourorghere.figuras.Figura;
import org.yourorghere.figuras.Util.SerializacaoUtil;

/**
 *
 * @author keyalisth
 */
public class BancoFiguras{
    private TreeSet<Figura> set;
    private static BancoFiguras instance;
    
    private final String file = "Figuras.ser";
    
    public BancoFiguras(){
        set=new TreeSet<Figura>();
    }
    
    public void salvarBanco(){
        try {
            SerializacaoUtil.serializar(set, file);
        } catch (Exception ex) {
            Logger.getLogger(BancoFiguras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarBanco(){

        try {
            set= SerializacaoUtil.deserializar(file);
        } catch (Exception ex) {
            Logger.getLogger(BancoFiguras.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    
    public void UpdateFigura(Figura f){
        set.remove(f);
        set.add(f);
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
    
    public TreeSet<Figura> getFiguras(){
        return this.set;
    }
    
    public void drawFigures(GL gl){
        for(Figura fig: set){
            fig.desenhar(gl);
        }
    }
    
    public void clearFigures(){
        instance = null;
    }
    
    public Integer getNewID(){
        return set.size();
    }
}