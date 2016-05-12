
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import business.UserBO;
import dao.impl.DaoBase;
import domain.Role;
import domain.User;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@Controller
@RequestMapping("/admin**")
public class AdminMainController {
private final static Logger logger = Logger.getLogger(AdminMainController.class);   
    
      
    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;
    
    @Autowired
    @Qualifier("userBO")
    private UserBO userBO;
    
    @RequestMapping(value = {"", "home"})
    public String adminmanagment(
            @AuthenticationPrincipal User user) {          

        DaoBase toDto = new DaoBase();
        javax.swing.JOptionPane.showMessageDialog(null, toDto.getDTO(user));
        
        return "admin/management";
    }
    
    @RequestMapping(value = "user/{id}")
    public ModelAndView getUser(@PathVariable Integer id){
        try{
            User user = userBO.findUserById(id);
            javax.swing.JOptionPane.showMessageDialog(null, user);
        }catch(Exception e){
            e.printStackTrace();            
        }
        
        return new ModelAndView("admin/management");
    }
        
    
    
    
    
   
    
}


