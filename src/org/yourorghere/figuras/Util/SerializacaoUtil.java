/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere.figuras.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Keyalisth
 */
public class SerializacaoUtil {
        private static File f;
    
    static{
        f= new File("Figuras.ser");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SerializacaoUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void serializar(TreeSet colecao, String localArquivo) throws Exception {
        FileOutputStream fos = new FileOutputStream(localArquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(colecao);
        oos.close();
        fos.close();
    }

    public static TreeSet deserializar(String localArquivo) throws Exception {
        FileInputStream fis = new FileInputStream(localArquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TreeSet colecaoContas = (TreeSet) ois.readObject();
        ois.close();
        fis.close();
        return colecaoContas;
    }
}
