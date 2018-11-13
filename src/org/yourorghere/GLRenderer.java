package org.yourorghere;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import org.yourorghere.figuras.Figura;
import org.yourorghere.figuras.GLModel;
import org.yourorghere.figuras.Poligono;
import org.yourorghere.figuras.Quadrilatero;
import org.yourorghere.figuras.Triangulo;
import org.yourorghere.figuras.Util.ModelLoaderOBJ;
import org.yourorghere.figuras.Util.RGB;
import org.yourorghere.figuras.persistence.BancoFiguras;


public class GLRenderer implements GLEventListener,MouseListener,KeyListener{
    
    private GLModel model=null;
    
    private static boolean gridActivated=false;
    private static float tamanhoGrid=0.1f; 
    private BancoFiguras figuras;
    
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        
        // Enable VSync
        gl.setSwapInterval(1);

        if(false==loadModels(gl)){
            System.exit(1);
        }
       setLight(gl);
        
        
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
        
        
        figuras= BancoFiguras.getInstance();


    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        
        System.out.println("reshape()");
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        if(height<=0){
            height=1;
        }
        final float h= (float) width/(float)height;
    	gl.glViewport(0, 0, width, height); 
        
        gl.glMatrixMode (GL.GL_PROJECTION);
        gl.glLoadIdentity ();
        glu.gluPerspective(45.0f, h, 0, 200);
        gl.glMatrixMode (GL.GL_MODELVIEW);
        

    }

    public void display(GLAutoDrawable drawable) {
	
	GL gl = drawable.getGL();

	gl.glClear (GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
	gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -1);
        gl.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-45.0f, 0.0f, 1.0f, 0.0f);

        gl.glLineWidth(1);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(1000.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 1000.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 1000.0f);   
        gl.glColor3f(0.1f, 0.1f, 0.1f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(-1000.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, -1000.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, -1000.0f);
        gl.glEnd();

        model.opengldraw(gl);
        
        gl.glFlush ();	   
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
        for(float x = 0.0f; x < 275.0f; x += tamanho )
        {
            gl.glVertex3f(x, -200.0f, 0.0f);
            gl.glVertex3f(x, 200.0f, 0.0f);
            gl.glVertex3f(-1*x,-200.0f,0.0f );
            gl.glVertex3f(-1*x,200.0f,0.0f );
        }
        for(float y = 0; y < 200.0f; y += tamanho )
        {
            gl.glVertex3f(-275.0f, y, 0.0f);
            gl.glVertex3f(275.0f, y, 0.0f);
            gl.glVertex3f(-275.0f, -1*y, 0.0f);
            gl.glVertex3f(275.0f, -1*y, 0.0f);
        }
        gl.glEnd();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("clicou");
        System.out.println(me.getPoint().x);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean loadModels(GL gl) {
		model = ModelLoaderOBJ.LoadModel("./models/peixe.obj",
				"./models/peixe.mtl", gl);
		if (model == null) {
			return false;
		}
		return true;
    }

	private void setLight(GL gl) {
		
		gl.glEnable(GL.GL_LIGHTING);
		
		float SHINE_ALL_DIRECTIONS = 1;
		float[] lightPos = { -30, 30, 30, SHINE_ALL_DIRECTIONS };
		float[] lightColorAmbient = { 0.02f, 0.02f, 0.02f, 1f };
		float[] lightColorSpecular = { 0.9f, 0.9f, 0.9f, 1f };

		// Set light parameters.
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, lightColorSpecular, 0);
		gl.glEnable(GL.GL_LIGHT1);
		
	}
    
    
}