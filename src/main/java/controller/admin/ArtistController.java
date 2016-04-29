/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import domain.Artist;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author adriano
 */
@Controller
@RequestMapping(value = "admin/artist")
public class ArtistController {
    
    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;
 
    static final Logger logger = Logger.getLogger(AlbumController.class);

    @RequestMapping(value ={"list",""}, method = RequestMethod.GET)
    public ModelAndView ArtistList() {                      
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/artist/list");
        model.addObject("artists",artistBO.findAllArtists());
        return model;
    }        
    
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model) {        
        model.addObject("artistBean",new Artist());
        model.setViewName("admin/artist/add");
        return model;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("artistBean") Artist artist) {
                        
        try{
           if(artistBO.existArtist(artist)){            
            javax.swing.JOptionPane.showMessageDialog(null,"Artist already exist","Error duplicated artist",
                   javax.swing.JOptionPane.ERROR_MESSAGE);           
                return new ModelAndView("admin/artist/add");           
            }
            artistBO.save(artist);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "errore inserimento: " + e.getMessage());
            logger.error(e);
        }
        return new ModelAndView("admin/artist/list").addObject("artists",artistBO.findAllArtists());

    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public ModelAndView remove(
            @RequestParam(value = "removeartist",required=true) Long removeartist) {
        
        try{
            artistBO.delete(removeartist);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
                   
        return new ModelAndView("admin/artist/list").addObject("artists",artistBO.findAllArtists());
    }
   
    
    @RequestMapping(value = "update/{updateid}")
    public ModelAndView update(ModelAndView model,
        @PathVariable Long updateid){
        
        model.addObject("updateid", updateid);        
        try{
            model.addObject("artistBean",artistBO.findByUid(updateid));
            model.setViewName("admin/artist/update");

        }catch(Exception e){
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "artista non trovato: errore");
            model.addObject("artists",artistBO.findAllArtists());
            model.setViewName("admin/artist/list");            
        }
        
        return model;  
    }
  
    
    @RequestMapping(value = "saveupdate")
    public ModelAndView saveupdate(@ModelAttribute("artistBean") Artist artist){                                                    
            
        javax.swing.JOptionPane.showMessageDialog(null, artist);
        artistBO.update(artist);
        return new ModelAndView("admin/artist/list").addObject("artists",artistBO.findAllArtists());
    }
       
}
