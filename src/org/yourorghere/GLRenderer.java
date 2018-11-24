package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static javax.media.opengl.GL.GL_SRC_ALPHA;
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
    
    private static boolean modelLoading=true;
    private float[] transformedMatrix={1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1}; 
    private static float[] IdMatrix={1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1};
    private Texture tex=null;
    private GLModel model=null;
    private GLModel phantom=null;
    private float rquad=0.0f;
    private static boolean XYActivated=false;
    private static boolean XZActivated=false;
    private static boolean ZYActivated=false;
    private static FloatBuffer f;
    private int modelState=0;
    private BancoFiguras figuras;
    private ArrayList<GLModel> models=null;
    private boolean modelLoaded=false;
    
    private static String absObjPath;
    private static String absMtlPath;
    
    private static boolean fantasma = false;
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
        f = FloatBuffer.wrap(transformedMatrix);
  
        models=new ArrayList<GLModel>();
        
        try {
            if(false==loadModels(gl)){
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
    
        if(!modelLoading){

            if(modelLoaded){
                gl.glPushMatrix();
                gl.glDisable(GL.GL_BLEND);
                gl.glGetFloatv(GL.GL_MODELVIEW_MATRIX, f);
                gl.glLoadMatrixf(f);
                applyTransforms(gl,modelState);
                model.opengldraw(gl);
               // drawLastModel(gl);
                gl.glPopMatrix();

                //phantom.opengldraw(gl);


                gl.glPushMatrix();  
                if(fantasma){
                    applyTransforms(gl,tstack.stackSize());
                    gl.glEnable(GL.GL_BLEND);
                    gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    model.opengldraw(gl);
                  //  drawLastModel(gl);
                }
                gl.glPopMatrix();


            }

        }else{
            modelLoaded=false;
            if(ChangeModel(gl)){
                modelLoaded=true;
            }
            modelLoading=false;
        }
        transformActivated=false;
        gl.glFlush ();	   
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public static void TransformOn(boolean f){
        transformActivated=f;
    }
   
    public static void criarFantasma(){
        fantasma = true;
    }
    
      public static void desativarFantasma(){
        fantasma = false;
    }
      
    public void drawLastModel(GL gl){
        models.get(models.size()-1).opengldraw(gl);
    }
    
    public void applyTransforms(GL gl, int i){
        float[] transform=new float[16];     
        float[][] m={
            {1f,0f,0f,0f},
            {0f,1f,0f,0f},
            {0f,0f,1f,0f},
            {0f,0f,0f,1f}
        };
            m = tstack.makeTransformMatrix(gl,i);  

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

    public boolean loadModels(GL gl) throws GLException, IOException {
            model = ModelLoaderOBJ.LoadModel("./models/CubeGlass.obj","./models/CubeGlass.mtl", gl);
           // phantom = ModelLoaderOBJ.LoadModel("./models/Cilindro.obj","./models/Cilindro.mtl", gl);
           // models.add(model);
		if (model == null) {
			return false;
		}
		return true;
    }

	private void setLight(GL gl) {
		
		gl.glEnable(GL.GL_LIGHTING);
		
		float SHINE_ALL_DIRECTIONS = 5;
		float[] lightPos = { -15, 0, 0, SHINE_ALL_DIRECTIONS };
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
        
        public  void resetCam(){
            x=0f;
            y=0f;
            z=0f;
            xangle=0f;
            yangle=0f;
            zangle=0f;
            
            f=FloatBuffer.wrap(IdMatrix);
            modelState=0;
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
                    GLModel modelo;
                    modelo= ModelLoaderOBJ.LoadModel(s,m, gl);
                                  //  modelo = ModelLoaderOBJ.LoadModel("./models/peixe.obj","./models/peixe.mtl", gl);
                    models.add(modelo);
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
        
        public static void changeModel(File objFile){
            absObjPath =objFile.getAbsolutePath();
            absMtlPath = FindMtl(objFile.getAbsolutePath());
            modelLoading=true;
        }
        
        public void newTransformState(){
            modelState=tstack.stackSize();
        }
    
        public boolean ChangeModel(GL gl){
          try {
                    GLModel modelo;
                    model= ModelLoaderOBJ.LoadModel(absObjPath,absMtlPath, gl);
                                  //  modelo = ModelLoaderOBJ.LoadModel("./models/peixe.obj","./models/peixe.mtl", gl);
                   //models.add(modelo);
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
        
        public static String FindMtl(String absoluteOBJPath){
           return absoluteOBJPath.replace("obj", "mtl");
            
        }
        
        
}