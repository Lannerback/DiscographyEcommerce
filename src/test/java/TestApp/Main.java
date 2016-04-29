/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestApp;

import domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adriano
 */
public class Main {

    
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    public static void main(String args[]){
        String username = "admin";
        String queryString =
        "select u from User u join u.userRoles r where u.username = :username ";     
        //"select u.username, r.role from User u join u.userRoles r where u.username = :username "
        try{
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(queryString);
            query.setParameter("username", username);
            List<User> users = (List<User>) query.list();
            User user = (User) query.uniqueResult();
            System.out.println(user.getUsername());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
  
    
    
}
