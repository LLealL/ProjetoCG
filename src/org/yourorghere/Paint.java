/*
 * SimpleGLCanvas.java
 *
 * Created on 30. Juli 2008, 16:18
 */

package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import org.yourorghere.figuras.Poligono;
import org.yourorghere.figuras.Quadrilatero;
import org.yourorghere.figuras.Triangulo;
import org.yourorghere.figuras.Util.RGB;
import org.yourorghere.figuras.persistence.BancoFiguras;

/**
 *
 * @author cylab
 * @author mbien
 */
public class Paint extends JFrame {
    private BancoFiguras figuras;
   
        
    static {
        // When using a GLCanvas, we have to set the Popup-Menues to be HeavyWeight,
        // so they display properly on top of the GLCanvas
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    }
    
    private Animator animator;

    /** Creates new form MainFrame */
    public Paint() {
        initComponents();
        setTitle("Projeto CG");

        canvas.addGLEventListener(new GLRenderer());
        animator = new Animator(canvas);

        // This is a workaround for the GLCanvas not adjusting its size, when the frame is resized.
<<<<<<< HEAD
        canvas.setMinimumSize(new Dimension()); 
=======
        canvas.setMinimumSize(new Dimension());         
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
        
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
    }

    @Override
    public void setVisible(boolean show){
        if(!show)
            animator.stop();
        super.setVisible(show);
        if(!show)
            animator.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new GLCanvas(createGLCapabilites());
        jPanel1 = new JPanel();
        Triangulo = new JLabel();
        BotaoTriangulo = new JButton();
        jPanel2 = new JPanel();
        Poligono = new JLabel();
        BotaoPoligono = new JButton();
        Lados = new JTextField();
        jPanel3 = new JPanel();
        Quadrado = new JLabel();
        BotaoQuadrado = new JButton();
        jPanel4 = new JPanel();
        Cores = new JLabel();
        Verde = new JLabel();
        Vermelho = new JLabel();
        Azul = new JLabel();
        R = new JTextField();
        G = new JTextField();
        B = new JTextField();
        jPanel5 = new JPanel();
        ExcUm = new JLabel();
        BotaoExcUm = new JButton();
        ExcAll = new JLabel();
        BotaoExcAll = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(204, 204, 255));

        Triangulo.setFont(new Font("Yu Gothic", 0, 18)); // NOI18N
        Triangulo.setText("Triangulo");

        BotaoTriangulo.setBackground(new Color(204, 204, 255));
        BotaoTriangulo.setText("Draw");
        BotaoTriangulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BotaoTrianguloActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotaoTriangulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Triangulo)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Triangulo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(BotaoTriangulo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new Color(204, 204, 255));

        Poligono.setFont(new Font("Yu Gothic", 0, 18)); // NOI18N
        Poligono.setText("Poligono");

        BotaoPoligono.setBackground(new Color(204, 204, 255));
        BotaoPoligono.setText("Draw");
        BotaoPoligono.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BotaoPoligonoActionPerformed(evt);
            }
        });

        Lados.setText("Lados");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Lados, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Poligono))
                    .addComponent(BotaoPoligono, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Poligono, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(Lados, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(BotaoPoligono, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new Color(204, 204, 255));

        Quadrado.setFont(new Font("Yu Gothic", 0, 18)); // NOI18N
        Quadrado.setText("Quadrado");

        BotaoQuadrado.setBackground(new Color(204, 204, 255));
        BotaoQuadrado.setText("Draw");
        BotaoQuadrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BotaoQuadradoActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Quadrado)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(BotaoQuadrado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Quadrado, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(BotaoQuadrado, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new Color(204, 204, 255));

        Cores.setFont(new Font("Yu Gothic", 0, 18)); // NOI18N
        Cores.setText("Cores");

        Verde.setFont(new Font("Yu Gothic", 0, 14)); // NOI18N
        Verde.setText("Verde");

        Vermelho.setFont(new Font("Yu Gothic", 0, 14)); // NOI18N
        Vermelho.setText("Vermelho");

        Azul.setFont(new Font("Yu Gothic", 0, 14)); // NOI18N
        Azul.setText("Azul");

        R.setFont(new Font("Yu Gothic", 0, 13)); // NOI18N
        R.setText("R");
        R.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RActionPerformed(evt);
            }
        });

        G.setFont(new Font("Yu Gothic", 0, 13)); // NOI18N
        G.setText("G");

        B.setText("B");
        B.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(Alignment.TRAILING, false)
                            .addComponent(R)
                            .addComponent(Vermelho, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Verde))
                            .addGroup(Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(G, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(Azul)
                            .addComponent(B, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(Cores)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cores)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(Verde)
                    .addComponent(Vermelho)
                    .addComponent(Azul))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(R, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(G, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(B, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new Color(204, 204, 255));

        ExcUm.setFont(new Font("Yu Gothic", 0, 12)); // NOI18N
        ExcUm.setText("Excluir apenas um :");

        BotaoExcUm.setText("Press");

        ExcAll.setFont(new Font("Yu Gothic", 0, 12)); // NOI18N
        ExcAll.setText("Excluir todos:");

        BotaoExcAll.setText("Press");
<<<<<<< HEAD
        BotaoExcAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BotaoExcAllActionPerformed(evt);
            }
        });
=======
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(ExcUm, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BotaoExcUm)))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(ExcAll)
                    .addComponent(BotaoExcAll, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(ExcUm)
                    .addComponent(ExcAll))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(BotaoExcUm)
                    .addComponent(BotaoExcAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(canvas, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
<<<<<<< HEAD
                .addContainerGap(68, Short.MAX_VALUE))
=======
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RActionPerformed(ActionEvent evt) {//GEN-FIRST:event_RActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RActionPerformed

    private void BActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BActionPerformed

    private void BotaoTrianguloActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BotaoTrianguloActionPerformed
        String text1 = R.getText();
        String text2 = G.getText();
        String text3 = B.getText();
        if ("R".equals(text1) && "G".equals(text2) && "B".equals(text3)) {
            figuras= BancoFiguras.getInstance();
            figuras.addFigura(new Triangulo(1,3,4,new RGB(1.0f,0.0f,0.0f)));

        }else
        {
<<<<<<< HEAD
            float r = Float.parseFloat(text1);
            float g = Float.parseFloat(text2);
            float b = Float.parseFloat(text3);
            figuras= BancoFiguras.getInstance();
            figuras.addFigura(new Triangulo(1,3,4,new RGB(r,g,b)));
=======
            int r = Integer.parseInt(text1);
            int g = Integer.parseInt(text2);
            int b = Integer.parseInt(text3);
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
        }
        canvas.display();
    }//GEN-LAST:event_BotaoTrianguloActionPerformed

    private void BotaoQuadradoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BotaoQuadradoActionPerformed
        String text1 = R.getText();
        String text2 = G.getText();
        String text3 = B.getText();
      
        if ("R".equals(text1) && "G".equals(text2) && "B".equals(text3)) {
            figuras= BancoFiguras.getInstance();
<<<<<<< HEAD
            figuras.addFigura(new Quadrilatero(1,3,3,new RGB(1.0f,0.0f,0.0f)));
        }else
        {
            float r = Float.parseFloat(text1);
            float g = Float.parseFloat(text2);
            float b = Float.parseFloat(text3);
            figuras= BancoFiguras.getInstance();
            figuras.addFigura(new Quadrilatero(1,3,3,new RGB(r,g,b)));
=======
            figuras.addFigura(new Quadrilatero(1,3,4,new RGB(1.0f,0.0f,0.0f)));
        }else
        {
            int r = Integer.parseInt(text1);
            int g = Integer.parseInt(text2);
            int b = Integer.parseInt(text3);
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
        }
        canvas.display();
    }//GEN-LAST:event_BotaoQuadradoActionPerformed

    private void BotaoPoligonoActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BotaoPoligonoActionPerformed
        String text1 = R.getText();
        String text2 = G.getText();
        String text3 = B.getText();
<<<<<<< HEAD
        String text4 = Lados.getText();
        
        if ("R".equals(text1) && "G".equals(text2) && "B".equals(text3)) {
            if ("Lados".equals(text4)) {
                figuras= BancoFiguras.getInstance();
                figuras.addFigura(new Poligono(1,3,4,new RGB(1.0f,0.0f,0.0f),7));
                //poligono 7 lados
            }else{
                int lados = Integer.parseInt(text4);
                figuras = BancoFiguras.getInstance();
                figuras.addFigura(new Poligono(1,3,4,new RGB(1.0f,0.0f,0.0f), lados));
            }
        }else
        {
            float r = Float.parseFloat(text1);
            float g = Float.parseFloat(text2);
            float b = Float.parseFloat(text3);
            if ("Lados".equals(text4)) {
                figuras= BancoFiguras.getInstance();
                figuras.addFigura(new Poligono(1,3,4,new RGB(r,g,b),7));
                //poligono 7 lados
            }else{
                int lados = Integer.parseInt(text4);
                figuras = BancoFiguras.getInstance();
                figuras.addFigura(new Poligono(1,3,4,new RGB(r,g,b), lados));
            }
=======
        
        if ("R".equals(text1) && "G".equals(text2) && "B".equals(text3)) {
            figuras= BancoFiguras.getInstance();
            figuras.addFigura(new Poligono(1,3,4,new RGB(1.0f,0.0f,0.0f),7));
            //poligono 7 lados
        }else
        {
            int r = Integer.parseInt(text1);
            int g = Integer.parseInt(text2);
            int b = Integer.parseInt(text3);
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
        }
        canvas.display();
    }//GEN-LAST:event_BotaoPoligonoActionPerformed

<<<<<<< HEAD
    private void BotaoExcAllActionPerformed(ActionEvent evt) {//GEN-FIRST:event_BotaoExcAllActionPerformed
        figuras= BancoFiguras.getInstance();
        figuras.clearFigures();
        canvas.display();
    }//GEN-LAST:event_BotaoExcAllActionPerformed

=======
>>>>>>> d0dfdee0523bd9d2c0b4552cced38f70b27e052f
    /**
     * Called from within initComponents().
     * hint: to customize the generated code choose 'Customize Code' in the contextmenu
     * of the selected UI Component you wish to cutomize in design mode.
     * @return Returns customized GLCapabilities.
     */
    private GLCapabilities createGLCapabilites() {
        
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setHardwareAccelerated(true);

        // try to enable 2x anti aliasing - should be supported on most hardware
        capabilities.setNumSamples(2);
        capabilities.setSampleBuffers(true);
        
        return capabilities;
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        // Run this in the AWT event thread to prevent deadlocks and race conditions
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                // switch to system l&f for native font rendering etc.
                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch(Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }

                Paint frame = new Paint();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel Azul;
    private JTextField B;
    private JButton BotaoExcAll;
    private JButton BotaoExcUm;
    private JButton BotaoPoligono;
    private JButton BotaoQuadrado;
    private JButton BotaoTriangulo;
    private JLabel Cores;
    private JLabel ExcAll;
    private JLabel ExcUm;
    private JTextField G;
    private JTextField Lados;
    private JLabel Poligono;
    private JLabel Quadrado;
    private JTextField R;
    private JLabel Triangulo;
    private JLabel Verde;
    private JLabel Vermelho;
    private GLCanvas canvas;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    // End of variables declaration//GEN-END:variables

}
