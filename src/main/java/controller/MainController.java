/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.AlbumBO;
import business.ArtistBO;
import business.RoleBO;
import business.UserBO;
import domain.Album;
import domain.Artist;
import domain.User;
import domain.Role;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@Controller
public class MainController {

    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;

    @Autowired
    @Qualifier("userBO")
    private UserBO userBO;
    
    @Autowired
    @Qualifier("roleBO")
    private RoleBO roleBO;
    
    static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    @Qualifier(value = "FirstCD")
    Album firstcd;
    
    @Autowired
    @Qualifier(value = "FirstArtist")
    Artist artist;
    
    
 
    @RequestMapping(value = "prova")
    public String prova(){
        logger.debug("prova");
        String UserName = "admin";
        User user = userBO.getUserByUserName(UserName);
        List<Role> roles = userBO.getRolesByUserName(UserName);
        javax.swing.JOptionPane.showMessageDialog(null, user.getUsername());
        javax.swing.JOptionPane.showMessageDialog(null, roles.get(0) );
        return "index";
    }
    @RequestMapping(value = "initalbumartist")
    public String init(){
        logger.debug("init() invoked");        
        List<Album> albums= new ArrayList<Album>();
        albums.add(firstcd);
        try{
            artist.setAlbums(albums);
            artistBO.save(artist);            
        }catch(Exception e){
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore inserimento: " + e.getMessage());
        }
        
        return "index";
    }
    @RequestMapping(value = "initrole" )
    public String init2(){
        try{
            Role role=new Role("ROLE_ADMIN");
            roleBO.save(role);                          
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "errore save role: " +
                   e.getMessage());
            logger.error(e);
        }
        
        return "index";
    }
    @RequestMapping(value = "initadmin")
    public String init3(){
        logger.debug("init2() invoked");        
       try{                           
            User user= new User("admin","password",true);              
            userBO.save(user);            
        }catch(Exception e){
           logger.error(e);
           javax.swing.JOptionPane.showMessageDialog(null, "errore save user: " +
                   e.getMessage());
        }        
        return "index";
    }       
    
    @RequestMapping(value = "addorder/{userid}")
    public String init3(@PathVariable Integer userid){
        try{
            User user = userBO.findUserById(userid);
            List<Album> albums = user.getOrderedAlbums();
            albums.add(albumBO.findByUid(Integer.parseInt("1")));
            user.setOrderedAlbums(albums);
            userBO.update(user);            
        }catch(Exception e){
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore save order: " + e.getMessage());
        }
        return "index";
    }
    
    @RequestMapping(value = "addrole/{userid}")
    public String init4(@PathVariable Integer userid){
        try{
            User user = userBO.findUserById(userid);
            List<Role> roles = user.getUserRoles();
            roles.add(roleBO.findByUid(1));
            user.setUserRoles(roles);            
            
            userBO.update(user);
            
        }catch(Exception e){
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore save order: " + e.getMessage());
        }
        return "index";
    }
        
    
    @RequestMapping(value = {"/","index", "/home"})
    public ModelAndView home(ModelAndView model) {       
        logger.debug("home() invoked"); 
        List<Album> albums;
        List<Artist> artists;
        try {  
            artists = artistBO.findAllArtists();
            albums = albumBO.findAllAlbums();            
            model.addObject("albums", albums);
            model.addObject("artists",artists);            
        } catch (Exception e) {
            logger.error(e);  
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "errore: " + e.getMessage());
        }
        model.setViewName("index");
        return model;
    }

    //_____________________________________________________________________________//
    
    @RequestMapping(value = "/login")
    public String login(){
        
        return "login";
    }        
     
    @RequestMapping(value = "showalbum/{id}")
    public ModelAndView show_album_detail(@PathVariable Integer id,ModelAndView model){
        logger.debug("show album detail() invoked");               

        model.addObject("cd",albumBO.findByUid(id));
        model.setViewName("user/show/showalbumdetail");
        return model;
    }
    
    @RequestMapping(value = "showartist/{id}")
    public ModelAndView show_artist_detail(@PathVariable Long id,ModelAndView model){
        logger.debug("show artist detail() invoked");               
        
        model.addObject("artist",artistBO.findByUid(id));
        model.setViewName("user/show/showartistdetail");
        return model;
    }
    
   
    @RequestMapping(value = "ecommercelayout")
    public String layout(){
       return "ecommercelayout";
    }   
    @RequestMapping(value = "ecommercelayoutsidemenu")
    public String layout1(){
       return "ecommercelayoutsidemenu";
    } 
    @RequestMapping(value = "tiles")
    public String tiles(){
        return "tiles";
    }
}
