/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.transforms;


import java.util.Stack;
import javax.media.opengl.GL;


/**
 *
 * @author lucas
 */
public class TransformStack {

    
    private Stack<Transform> pilha;
    
    public TransformStack(){
        pilha= new Stack<Transform> ();
    }
    

}
