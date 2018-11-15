package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import org.yourorghere.figuras.Figura;
import org.yourorghere.figuras.GLModel;
import org.yourorghere.figuras.Poligono;
import org.yourorghere.figuras.Quadrilatero;
import org.yourorghere.figuras.Triangulo;
import org.yourorghere.figuras.Util.ModelLoaderOBJ;
import org.yourorghere.figuras.Util.RGB;
import org.yourorghere.figuras.persistence.BancoFiguras;
import org.yourorghere.transforms.TransformStack;


public class GLRenderer implements GLEventListener{
    

    private Texture tex=null;
    private GLModel model=null;
    private GLModel phantom=null;
    private float rquad=0.0f;
    private static boolean XYActivated=false;
    private static boolean XZActivated=false;
    private static boolean ZYActivated=false;

    private BancoFiguras figuras;
    
    
    private static boolean transformActivated=false;
    private static float x=0f,y=0f,z=0f;
    private static TransformStack tstack;
    
    private static float xangle=0.0f,yangle=0.0f,zangle=0.0f;
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();

        System.err.println("INIT GL IS: " + gl.getClass().getName());
        
        tstack= TransformStack.getInstance();
        // Enable VSync
        gl.setSwapInterval(1);

  
        try {
            if(false==loadModels(gl,"./models/CubeGlass.obj","./models/CubeGlass.mtl")){
                System.exit(1);
            }
             setLight(gl);
        } catch (GLException ex) {
            Logger.getLogger(GLRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GLRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
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
        glu.gluPerspective(45.0f, h, 0.01f, 200f);
        gl.glMatrixMode (GL.GL_MODELVIEW);
        

    }

    public void display(GLAutoDrawable drawable) {
	
	GL gl = drawable.getGL();


        
	gl.glClear (GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0f+x, 0f+y, -4+z);

        gl.glRotatef(xangle,1,0,0);
        gl.glRotatef(yangle,0,1,0);
        gl.glRotatef(zangle,0,0,1);
        
        
        renderWorld(gl);
        

        gl.glEnable(GL.GL_LIGHTING);


          gl.glPushMatrix();
          
          applyTransforms(gl);
          model.opengldraw(gl);          
          gl.glPopMatrix();
       
        
           transformActivated=false;        
        gl.glFlush ();	   
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public static void TransformOn(boolean f){
        transformActivated=f;
    }
    
    public void applyTransforms(GL gl){
        float[] transform=new float[16];     
        float[][] m={
            {1f,0f,0f,0f},
            {0f,1f,0f,0f},
            {0f,0f,1f,0f},
            {0f,0f,0f,1f}
        };
            m = tstack.makeTransformMatrix();  

      //  System.out.println(m[0][3]);
        
      //  System.out.println(m[2][3]);
       // gl.glMatrixMode(GL.GL_MATRIX_MODE);
        
        
        
        transform[0]=m[0][0];
        transform[1]=m[0][1];
        transform[2]=m[0][2];
        transform[3]=m[0][3];

        transform[4]=m[1][0];
        transform[5]=m[1][1];
        transform[6]=m[1][2];
        transform[7]=m[1][3];

        transform[8]=m[2][0];
        transform[9]=m[2][1];
        transform[10]=m[2][2];
        transform[11]=m[2][3];

        transform[12]=m[3][0];
        transform[13]=m[3][1];
        transform[14]=m[3][2];
        transform[15]=m[3][3];        

        
     //  System.out.println(transform[11]);
      //  FloatBuffer fbuffer = FloatBuffer.wrap(transform);
        
        
        gl.glMultMatrixf(transform, 0);
        
       // gl.glPushMatrix();
      // tstack.clearStack();
      //gl.glMultMatrixf(fbuffer);

       // gl.glMatrixMode(GL.GL_MODELVIEW);
        
       
    }

    public void clear(){
        figuras=null;
    }    
    
    public static void OpenGridXY(){
        XYActivated = true;
    }
    
    public static void CloseGridXY(){
        XYActivated = false;
    }    
    public static void OpenGridXZ(){
        XZActivated = true;
    }
    
    public static void CloseGridXZ(){
        XZActivated = false;
    }    
        public static void OpenGridZY(){
        ZYActivated = true;
    }
    
    public static void CloseGridZY(){
        ZYActivated = false;
    }    

    public boolean loadModels(GL gl,String obj,String mtl) throws GLException, IOException {
            model = ModelLoaderOBJ.LoadModel(obj,
				mtl, gl);

            phantom = model;
		if (model == null) {
			return false;
		}
		return true;
    }

	private void setLight(GL gl) {
		
		gl.glEnable(GL.GL_LIGHTING);
		
		float SHINE_ALL_DIRECTIONS = 5;
		float[] lightPos = { -15, 15, 15, SHINE_ALL_DIRECTIONS };
		float[] lightColorAmbient = { 0.5f, 0.5f, 0.5f, 1f };
		float[] lightColorSpecular = { 0.9f, 0.9f, 0.9f, 1f };
                float[] espec ={1f,1f,1f,1f};
                FloatBuffer especularidade = FloatBuffer.wrap(espec);
                int specMaterial = 60;

		// Set light parameters.
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);
		gl.glLightfv(GL.GL_LIGHT1, GL.GL_DIFFUSE, lightColorSpecular, 0);
                
		gl.glEnable(GL.GL_LIGHT1);
                gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, especularidade);
                gl.glMateriali(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, specMaterial);
                
                gl.glEnable(GL.GL_COLOR_MATERIAL);
                gl.glEnable(GL.GL_NORMALIZE);
                gl.glEnable(GL.GL_DEPTH_TEST);
		
	}
    
        public static void moveCam(float i,float u, float p ){
            x=x+i;
            y=y+u;
            z=z+p;
                  
        }
        
        public static void resetCam(){
            x=0f;
            y=0f;
            z=0f;
            xangle=0f;
            yangle=0f;
            zangle=0f;
        }
        
        public static void rotateCam(float i,float u , float p){
            xangle=xangle+i;
            yangle=yangle+u;
            zangle=zangle+p;
        }
        
        private void renderWorld(GL gl){
        float x=0.0f,y=0.0f,z=0.0f;
            
        gl.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-45.0f, 0.0f, 1.0f, 0.0f);
        
        gl.glDisable(GL.GL_LIGHTING);
        gl.glLineWidth(1);
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        if(XZActivated){
            gl.glBegin(GL.GL_LINES);
            for(x=-1000;x<1000;x+=1F){
                if(x==0){               
                    continue;
                }
             gl.glVertex3f(x, 0.0f, -1000.0f);
             gl.glVertex3f(x, 0.0f, 1000.0f);
            }
            for(z=-1000;z<1000;z+=1F){
                if(z==0){
                    continue;
                }
             gl.glVertex3f(-1000.0f, 0.0f, z);
             gl.glVertex3f(1000.0f, 0.0f, z);
            }
        }
        if(XYActivated){
            gl.glBegin(GL.GL_LINES);
            for(x=-1000;x<1000;x+=1F){
                if(x==0){               
                    continue;
                }
             gl.glVertex3f(x, -1000.0f, 0.0f);
             gl.glVertex3f(x, 1000.0f, 0.0f);
            }
            for(y=-1000;y<1000;y+=1F){
                if(y==0){
                    continue;
                }
             gl.glVertex3f(-1000.0f, y, 0.0f);
             gl.glVertex3f(1000.0f, y, 0.0f);
            }            
        }
        
       if(ZYActivated){
            gl.glBegin(GL.GL_LINES);
            for(y=-1000;y<1000;y+=1F){
                if(y==0){               
                    continue;
                }
             gl.glVertex3f(0.0f, y, -1000.0f);
             gl.glVertex3f(0.0f, y, 1000.0f);
            }
            for(z=-1000;z<1000;z+=1F){
                if(z==0){
                    continue;
                }
             gl.glVertex3f(0.0f, -1000.0f, z);
             gl.glVertex3f(0.0f, 1000.0f, z);
            }
       }
        
        gl.glDisable(GL.GL_LIGHTING);
        gl.glLineWidth(1);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(0f, 1f, 1f);
         gl.glVertex3f(-1000.0f, 0.0f, 0.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glColor3f(1f,0f,0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);
         gl.glVertex3f(1000.0f, 0.0f, 0.0f);
         
         gl.glColor3f(1f,0f,1f);
         gl.glVertex3f(0.0f, -1000.0f, 0.0f);
         gl.glVertex3f(0f, 0f, 0f);
         gl.glColor3f(0f, 1f, 0f);
         gl.glVertex3f(0f, 0f, 0f);
         gl.glVertex3f(0.0f, 1000.0f, 0.0f);
         
         
         gl.glColor3f(1f,1f,0f);
         gl.glVertex3f(0.0f, 0.0f, -1000.0f);
         gl.glVertex3f(0.0f, 0.0f, 0.0f);   
         gl.glColor3f(0f, 0f, 1f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1000.0f);
        gl.glEnd();
        }


        public boolean loadNewModel(GL gl,String s,String m){
                try {
                    
                    model= ModelLoaderOBJ.LoadModel(s,
				m, gl);

                    if (model == null) {
                            return false;
                    }
                    System.out.println(model.numpolygons());
                    return true; 
                } catch (GLException ex) {
                    Logger.getLogger(GLRenderer.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
        }
    
}