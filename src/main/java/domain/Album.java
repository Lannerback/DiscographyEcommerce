/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/* autore*/

@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title","year","label","producer","artist_uid"})
})
@Entity
public class Album implements java.io.Serializable {
    
    @Lob
    private String image;
    
    @NotBlank
    private String title;
    
    private String length,label,producer;
    
    @Size(min=0,max=4)
    private String year;
    
    @Size(min=2,max=15)
    private String genre;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;
     

    @ManyToOne
    @JoinColumn(name = "artist_uid",nullable = false)
    private Artist artist;     
    
    @ManyToMany(mappedBy = "orderedAlbums")
    private List<User> users = new ArrayList<User>();
    
    public Album(String title, String length, String year, String genre,
            String label, String producer, Artist artist) {
        this.title = title;
        this.length = length;
        this.year = year;
        this.genre = genre;
        this.label = label;
        this.producer = producer;        
        this.artist = artist;
    }

    public Album(String title, String length, String label, String producer, 
            String year, String genre, Integer uid, Artist artist, List<User> users) {
        this.title = title;
        this.length = length;
        this.label = label;
        this.producer = producer;
        this.year = year;
        this.genre = genre;
        this.uid = uid;
        this.artist = artist;
        this.users = users;
    }

    public Album(String image, String title, String length, String label, String producer, String year, String genre, Integer uid, Artist artist) {
        this.image = image;
        this.title = title;
        this.length = length;
        this.label = label;
        this.producer = producer;
        this.year = year;
        this.genre = genre;
        this.uid = uid;
        this.artist = artist;
    }
              

    public Album(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }    
     
   public Integer getUid() {
        return uid;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }                

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.year);
        hash = 47 * hash + Objects.hashCode(this.label);
        hash = 47 * hash + Objects.hashCode(this.producer);
        hash = 47 * hash + Objects.hashCode(this.artist);
        return hash;
    }
   
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
    
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.producer, other.producer)) {
            return false;
        }
        
        if (!Objects.equals(this.artist.getUid(), other.artist.getUid())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "";        
        return "Album{" + "title=" + title + ", length=" + length + ", year=" + year +
                ", genre=" + genre + ", label=" + label + ", producer=" + producer + ", uid=" + uid + ", artist=" + artist.getName() + '}';
    }

    

  
    
   
    
    
    
    
}
