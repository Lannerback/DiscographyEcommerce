/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import domain.Album;
import domain.Artist;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import util.ClientResponse;
import util.FileManager;

@Controller
@RequestMapping(value = "admin/album")
public class AlbumController {

    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;

    @Autowired
    @Qualifier("fileManager")
    private FileManager fileManager;

    static final Logger logger = Logger.getLogger(AlbumController.class);


    @RequestMapping(value = {"", "list"})
    public ModelAndView AlbumList(ModelAndView model) {
        List<Album> albums;
        try {
            albums = albumBO.findAllAlbums();
            model.addObject("albums", albums);
            model.setViewName("admin/album/list");
        } catch (Exception e) {
            logger.error(e);
            model.setViewName("error");
        }
        return model;
    }

    @RequestMapping("add")
    public ModelAndView add(ModelAndView model) {
        model.addObject("artists", artistBO.findAllArtists());
        model.addObject("album", new Album());
        model.setViewName("admin/album/add");
        return model;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@Valid Album album, BindingResult bindingResult, ModelAndView model, @RequestParam(value = "file") MultipartFile imagefile, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addObject("response", new ClientResponse(false, "Field Errors"));
            model.addObject("artists", artistBO.findAllArtists());
            model.setViewName("admin/album/add");
            return model;
        }

            if (albumBO.existAlbum(album)) {
                model.addObject("response", new ClientResponse(false, "CD already exist \nError duplicated CD"));
                model.addObject("artists", artistBO.findAllArtists());
                model.setViewName("admin/album/add");
                return model;
            }   

        

        if (imagefile != null) {

            String encoded = null;
            try {
                encoded = Base64.getEncoder().encodeToString(imagefile.getBytes());
                album.setImagefile(imagefile.getBytes());
            } catch (IOException io) {
                logger.error(io);
                javax.swing.JOptionPane.showMessageDialog(null, io);
            }
            album.setImagebase64("data:image/jpeg;base64," + encoded);
        }

        if (imagefile != null) {
            Artist artist = artistBO.findByUid(album.getArtist().getUid());
            String subPath = "/cover/" + artist.getName() + artist.getSurname() + "/" + album.getTitle() + "/";
            try {
                fileManager.makeDir(subPath);
                Long imagePath = new Date().getTime();
                fileManager.saveFile(imagefile, subPath + imagePath);
                album.setImagepath(artist.getName() + artist.getSurname() + "/" + album.getTitle() + "/" + imagePath);
                albumBO.save(album); 
            } catch (FileNotFoundException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        
        model.addObject("albums", albumBO.findAllAlbums());
        model.setViewName("redirect:list");
        return model;

    }

    @RequestMapping(value = "remove/{removeid}")
    public ModelAndView remove(
            @PathVariable(value = "removeid") Integer removecd,ModelAndView model) {

        try {            
            Album album = albumBO.findByUid(removecd);
            albumBO.delete(removecd);
            Artist artist = artistBO.findByUid(album.getArtist().getUid());
            String filePath = "/cover/" + artist.getName() + artist.getSurname() + "/" + album.getTitle();           
            fileManager.deleteDirOrFile(filePath);
            
        }catch(FileNotFoundException e){
            logger.error(e);
            model.addObject("response",new ClientResponse(false, e.getMessage()));
        } 
        catch (Exception e) {
            logger.error(e);          
            model.addObject("response",new ClientResponse(false, e.getMessage()));
        }

        model.addObject("albums", albumBO.findAllAlbums());
        model.setViewName("admin/album/list");
        return model;
    }

    @RequestMapping(value = "update/{updateid}")
    public ModelAndView update(ModelAndView model,
            @PathVariable Integer updateid) {

        model.addObject("updateid", updateid);
        try {
            model.addObject("artists", artistBO.findAllArtists());
            model.addObject("album", albumBO.findByUid(updateid));
            model.setViewName("admin/album/update");

        } catch (Exception e) {
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "cd non trovato: errore");
            model.addObject("albums", albumBO.findAllAlbums());
            model.setViewName("admin/album/list");
        }
        return model;

    }

    @RequestMapping(value = "saveupdate")
    public ModelAndView saveupdate(@Valid @ModelAttribute("album") Album cd, ModelAndView mav,
            BindingResult bindingResult) {

        javax.swing.JOptionPane.showMessageDialog(null, cd);
        if (bindingResult.hasErrors()) {
            mav.addObject("artists", artistBO.findAllArtists());
            mav.setViewName("admin/album/add");
            return mav;
        }
        try {
            albumBO.update(cd);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            logger.error(e);
        }
        mav.setViewName("admin/album/list");
        mav.addObject("albums", albumBO.findAllAlbums());
        return mav;
    }

}
