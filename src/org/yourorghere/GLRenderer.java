package org.yourorghere;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import org.yourorghere.figuras.Figura;
import org.yourorghere.figuras.Poligono;
import org.yourorghere.figuras.Quadrilatero;
import org.yourorghere.figuras.Triangulo;
import org.yourorghere.figuras.Util.RGB;
import org.yourorghere.figuras.persistence.BancoFiguras;


public class GLRenderer implements GLEventListener{

    
    private static boolean gridActivated=true;
    private static float tamanhoGrid=0.1f; 
    private BancoFiguras figuras;
    
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        figuras= BancoFiguras.getInstance();
        
        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h =  550.0f /  400.0f;
        gl.glViewport(0, 0, 550, 400);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(0.0f, h, 0.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        
        figuras = BancoFiguras.getInstance();
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,0.0f,0.0f);
        figuras.drawFigures(gl);
        if(gridActivated){
            drawGrid(gl,tamanhoGrid);
        }
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public void clear(){
        figuras=null;
    }    
    
    public static void OpenGrid(float tamanho){
        gridActivated = true;
        tamanhoGrid=tamanho;
    }
    
    public static void CloseGrid(){
        gridActivated = false;
    }
    
    public void drawGrid(GL gl, float tamanho){
        
        gl.glBegin(gl.GL_LINES);
        gl.glColor3f(1.0f,1.0f,1.0f); 
        for(float x = 0.0f; x < 500.0f; x += tamanho )
        {
            gl.glVertex3f(x-250.0f, -200.0f, 0.0f);
            gl.glVertex3f(x-250.0f, 200.0f, 0.0f);
        }
        for(float y = 0; y < 400.0f; y += tamanho )
        {
            gl.glVertex3f(-250.0f, y-200.0f, 0.0f);
            gl.glVertex3f(250.0f, y-200.0f, 0.0f);
        }
        gl.glEnd();
    }
    
    
}