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
import java.io.File;
import java.io.UnsupportedEncodingException;
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
import java.util.Base64;
import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;



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
    
    
    @RequestMapping(value = "upload")
    public String upload(){
        return "upload";
    }
        
    
    @RequestMapping(value = "initall")
    public String initall(){
        init();
        init2();
        init3();
        init4(1);
        init5(1);
        init6();
        
        return "index";
    }
 
    
    @RequestMapping(value={"initusererole"})
    public String init6(){
        logger.debug("iniusererole");
        User user= new User("user","password",true);        
        try{
            Role role=new Role("ROLE_USER");
            roleBO.save(role);   
            
            userBO.save(user);
            
            user = userBO.getUserByUserName(user.getUsername());
            List<Role> roles = user.getUserRoles();
            roles.add(roleBO.findByUid(2));
            user.setUserRoles(roles);            
            
            userBO.update(user);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "errore save role: " +
                   e.getMessage());
            logger.error(e);
        }
        
        return "index";
    }
    
    @RequestMapping(value = "prova")
    public String prova(){
        userBO.delete(userBO.findUserById(2));
        return "index";
    }
    @RequestMapping(value = "prova2")
    public String prova2() throws UnsupportedEncodingException{
        byte[] bytes = "Hello, World!".getBytes("UTF-8");
        String encoded = Base64.getEncoder().encodeToString(bytes);
        byte[] decoded = Base64.getDecoder().decode(encoded);
        javax.swing.JOptionPane.showMessageDialog(null, encoded + ":" + new String(decoded));
        
        return "index";
    }
    @RequestMapping("provaupload")
	public ModelAndView onSubmit(
                @RequestParam("file") MultipartFile imageUpload)
		throws Exception {
            
                //javax.swing.JOptionPane.showMessageDialog(null, imageUpload==null);
		MultipartFile multipartFile = imageUpload;
		
		String fileName="";

		if(multipartFile!=null){
			fileName = multipartFile.getOriginalFilename();
                        javax.swing.JOptionPane.showMessageDialog(null, fileName);
			//do whatever you want
		}
                firstcd.setTitle("altrotitolo");
                String encoded = Base64.getEncoder().encodeToString(multipartFile.getBytes());
                byte[] decoded = Base64.getDecoder().decode(encoded);
                logger.warn(encoded + ":" + new String(decoded));
                firstcd.setImagefile(multipartFile.getBytes());
                firstcd.setImagebase64("data:image/jpeg;base64," + encoded);
                firstcd.setArtist(artistBO.findByUid(1L));
                try{
                    albumBO.save(firstcd);
                }catch(Exception e){
                    javax.swing.JOptionPane.showMessageDialog(null, e);
                    
                }
		
		return new ModelAndView("upload");
	}
        
    /*
    @RequestMapping(method = RequestMethod.GET, value = "/uploadimg")
	public String provideUploadInfo(Model model) {
		File rootFolder = new File(Application.ROOT);
		List<String> fileNames = Arrays.stream(rootFolder.listFiles())
			.map(f -> f.getName())
			.collect(Collectors.toList());

		model.addAttribute("files",
			Arrays.stream(rootFolder.listFiles())
					.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
					.map(f -> f.getName())
					.collect(Collectors.toList())
		);

		return "uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadimg")
	public String handleFileUpload(@RequestParam("name") String name,
								   @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
			return "redirect:/";
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
			return "redirect:/";
		}

		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(Application.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + name + "!");
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to upload " + name + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to upload " + name + " because the file was empty");
		}

		return "redirect:/";
	}*/
    
    
    @RequestMapping(value = "admin/album/upload")
    public String uplo(){
        return "admin/album/upload";
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
    public String init4(@PathVariable Integer userid){
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
    public String init5(@PathVariable Integer userid){
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
