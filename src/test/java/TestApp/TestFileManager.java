/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestApp;

import org.springframework.beans.factory.annotation.Autowired;
import util.FileManager;

/**
 *
 * @author
 */
public class TestFileManager {

    /**
     * @param args the command line arguments
     */
    
   
    
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.setPath("/home/adriano/SERVERFILES");
        try{
            fileManager.deleteDirOrFile("/cover/primaartistacognomeartist/prova");
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
}
