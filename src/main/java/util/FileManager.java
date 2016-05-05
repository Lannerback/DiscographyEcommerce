
package util;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class FileManager {
    
    static final Logger logger = Logger.getLogger(FileManager.class);

    
    private String path;
    private MultipartFile image;
    private String subPath;
    
    public FileManager(){        
    }

    public FileManager(MultipartFile image, String subPath) {
        this.image = image;
        this.subPath = subPath;
    }

    public FileManager(String path, MultipartFile image, String subPath) {
        this.path = path;
        this.image = image;
        this.subPath = subPath;
    }
    
                              
    public boolean saveFile(){
        try{
            image.transferTo(new File(path + subPath));
        }catch(IOException e){
            logger.error(e);
            return false;
        }
        return true;
    }
    
    //RIVEDERE BENE METODO
    public boolean makeDir(String path){
        File imageDir = new File(path);
        boolean success=false;
        if (!imageDir.exists()){
          success = imageDir.mkdir();
          if(success){
            logger.info("Directory created into: " + imageDir.getPath());            
          }else{
              logger.error("Cannot create dir " + imageDir.getPath());
          }
        }else{
            logger.info("Directory già esistente");
        }
        
        return success;
    }
    
    public boolean saveFile(MultipartFile image, String subPath){
        try{
            image.transferTo(new File(path + subPath));
        }catch(IOException e){
            logger.error(e);
            return false;
        }
        return true;
    }
        
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    
    
    
    
    
}
