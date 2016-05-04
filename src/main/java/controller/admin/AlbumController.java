/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import domain.Album;
import java.util.Base64;
import java.util.List;
import javax.validation.Valid;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value="admin/album")
public class AlbumController {
             
    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;
     
    static final Logger logger = Logger.getLogger(AlbumController.class);

    Validator validator;
    
    @RequestMapping(value = {"", "list"})
    public ModelAndView AlbumList(ModelAndView model) {   
        logger.debug("album AlbumList() invoked");
    List<Album> albums;
    try {
     albums = albumBO.findAllAlbums();
     model.addObject("albums",albums);
     model.setViewName("admin/album/list");
    } catch(Exception e) {
      logger.error(e);
       model.setViewName("error");
    }
    return model;       
    }
    
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model) {
        model.addObject("artists", artistBO.findAllArtists());
        model.addObject("album",new Album());       
        model.setViewName("admin/album/add");       
        return model;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Album album,BindingResult bindingResult
            ,ModelAndView model,@RequestParam("file") MultipartFile imageUpload) {         
        
        if (bindingResult.hasErrors()) {
            model.addObject("artists",artistBO.findAllArtists());
            model.setViewName("admin/album/add");
            return model;
        }
        try{
            if(albumBO.existAlbum(album)){            
            javax.swing.JOptionPane.showMessageDialog(null,"CD already exist","Error duplicated CD",
                   javax.swing.JOptionPane.ERROR_MESSAGE);  
            model.addObject("artists",artistBO.findAllArtists());
            model.setViewName("admin/album/add");
            return model;          
            }
            
            MultipartFile multipartFile = imageUpload;
		
            String fileName="";

            if(multipartFile!=null){
                fileName = multipartFile.getOriginalFilename();
                javax.swing.JOptionPane.showMessageDialog(null, fileName);
                //do whatever you want
            }
            String encoded = Base64.getEncoder().encodeToString(multipartFile.getBytes());
            album.setImagebase64(encoded);
            albumBO.save(album);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "errore inserimento: " + e.getMessage());
            logger.error(e);
        }
        
        model.addObject("albums",albumBO.findAllAlbums());
        model.setViewName("redirect:list");
        return model;

    }



    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelAndView remove(
            @RequestParam(value = "removecd",required=true) Integer removecd) {
        
        try{
            albumBO.delete(removecd);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
                   
        return new ModelAndView("admin/album/list").addObject("albums",albumBO.findAllAlbums());
    }
   
    
    @RequestMapping(value = "update/{updateid}")
    public ModelAndView update(ModelAndView model,
        @PathVariable Integer updateid){
        
        model.addObject("updateid", updateid);        
        try{
            model.addObject("artists",artistBO.findAllArtists());
            model.addObject("album",albumBO.findByUid(updateid));
            model.setViewName("admin/album/update");

        }catch(Exception e){
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "cd non trovato: errore");
            model.addObject("albums",albumBO.findAllAlbums());
            model.setViewName("admin/album/list");            
        }                
        return model;                               

        
    }
  
    
    @RequestMapping(value = "saveupdate")
    public ModelAndView saveupdate(@Valid @ModelAttribute("album") Album cd,ModelAndView mav,
            BindingResult bindingResult){                                                    
            
        javax.swing.JOptionPane.showMessageDialog(null, cd);
        if (bindingResult.hasErrors()) {
            mav.addObject("artists",artistBO.findAllArtists());
            mav.setViewName("admin/album/add");
            return mav;
        }
        try{             
            albumBO.update(cd);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            logger.error(e);
        }
        mav.setViewName("admin/album/list");
        mav.addObject("albums",albumBO.findAllAlbums());
        return mav;
    }
        
    
}
