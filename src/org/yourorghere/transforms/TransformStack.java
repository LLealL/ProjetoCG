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
    private static TransformStack instance=null;
    
    
    private TransformStack(){
        pilha= new Stack<Transform> ();
    }
    
    public static TransformStack getInstance(){
        if(instance==null){
            instance= new TransformStack();
        }
        return instance;
    }
    
    
    public void addTransform(Transform t){
        pilha.add(t);
        System.out.println("adicionou");
    }
    
    public float[][] makeTransformMatrix(){
        float[][] matrix={
            {1f,0f,0f,0f},
            {0f,1f,0f,0f},
            {0f,0f,1f,0f},
            {0f,0f,0f,1f}
        };
        float[][] m;
        
        for(Transform t: pilha){
                     
            m=t.doTransform();
            
            matrix[0][0]= matrix[0][0]*m[0][0]+matrix[0][1]*m[1][0]+matrix[0][2]*m[2][0]+matrix[0][3]*m[3][0];
            matrix[0][1]=matrix[0][0]*m[0][1]+matrix[0][1]*m[1][1]+matrix[0][2]*m[2][1]+matrix[0][3]*m[3][1];
            matrix[0][2]=matrix[0][0]*m[0][2]+matrix[0][1]*m[1][2]+matrix[0][2]*m[2][2]+matrix[0][3]*m[3][2];
            matrix[0][3]=matrix[0][0]*m[0][3]+matrix[0][1]*m[1][3]+matrix[0][2]*m[2][3]+matrix[0][3]*m[3][3];
            
            matrix[1][0]= matrix[1][0]*m[0][0]+matrix[1][1]*m[1][0]+matrix[1][2]*m[2][0]+matrix[1][3]*m[3][0];
            matrix[1][1]=matrix[1][0]*m[0][1]+matrix[1][1]*m[1][1]+matrix[1][2]*m[2][1]+matrix[1][3]*m[3][1];
            matrix[1][2]=matrix[1][0]*m[0][2]+matrix[1][1]*m[1][2]+matrix[1][2]*m[2][2]+matrix[1][3]*m[3][2];
            matrix[1][3]=matrix[1][0]*m[0][3]+matrix[1][1]*m[1][3]+matrix[1][2]*m[2][3]+matrix[1][3]*m[3][3];
            
            matrix[2][0]= matrix[2][0]*m[0][0]+matrix[2][1]*m[1][0]+matrix[2][2]*m[2][0]+matrix[2][3]*m[3][0];
            matrix[2][1]=matrix[2][0]*m[0][1]+matrix[2][1]*m[1][1]+matrix[2][2]*m[2][1]+matrix[2][3]*m[3][1];
            matrix[2][2]=matrix[2][0]*m[0][2]+matrix[2][1]*m[1][2]+matrix[2][2]*m[2][2]+matrix[2][3]*m[3][2];
            matrix[2][3]=matrix[2][0]*m[0][3]+matrix[2][1]*m[1][3]+matrix[2][2]*m[2][3]+matrix[2][3]*m[3][3];
            
            matrix[3][0]= matrix[3][0]*m[0][0]+matrix[3][1]*m[1][0]+matrix[3][2]*m[2][0]+matrix[3][3]*m[3][0];
            matrix[3][1]=matrix[3][0]*m[0][1]+matrix[3][1]*m[1][1]+matrix[3][2]*m[2][1]+matrix[3][3]*m[3][1];
            matrix[3][2]=matrix[3][0]*m[0][2]+matrix[3][1]*m[1][2]+matrix[3][2]*m[2][2]+matrix[3][3]*m[3][2];
            matrix[3][3]=matrix[3][0]*m[0][3]+matrix[3][1]*m[1][3]+matrix[3][2]*m[2][3]+matrix[3][3]*m[3][3];
          
        }       
        System.out.println(matrix[0][0]+" "+ matrix[0][1]+ " "+ matrix[0][2]+" "+matrix[0][3]);
        System.out.println(matrix[1][0]+" "+ matrix[1][1]+ " "+ matrix[1][2]+" "+matrix[1][3]);
        System.out.println(matrix[2][0]+" "+ matrix[2][1]+ " "+ matrix[2][2]+" "+matrix[2][3]);
        System.out.println(matrix[3][0]+" "+ matrix[3][1]+ " "+ matrix[3][2]+" "+matrix[3][3]);
        return matrix;
    }
    
    public void clearStack(){
        pilha.clear();
    }
    

}
