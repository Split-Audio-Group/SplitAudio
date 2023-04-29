package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "USER_ID")
   private Integer users_id;

   @Column(name = "USERNAME")
   private String username;

   @Column(name = "DISPLAY_NAME")
   private String display_name;

   @Column(name = "EMAIL")
   private String email;
   
   @Column(name = "PASSWORD")
   private String password;
   
   public User() {
   }

   public User(Integer users_id, String username, String display_name, String email, String password) {
      this.users_id = users_id;
      this.username = username;
      this.display_name = display_name;
      this.email = email;
      this.password = password;
   }

   public User(String username, String display_name, String email, String password) {
      this.username = username;
      this.display_name = display_name;
      this.email = email;
      this.password = password;
      
   }

   public Integer getId() {
      return users_id;
   }

   public void setId(Integer id) {
      this.users_id = id;
   }

   public String getName() {
      return username;
   }
   
   public void setusername(String name) {
	      this.username = name;
	   }
   public String getdisplay_Name() {
	      return display_name;
	   }
   
   public void setdisplay_name(String displayname) {
      this.display_name = displayname;
   }

   public String getEmail() {
      return email;
   }

  public void setEmail(String email) {
	  this.email=email;
  }
  public String getPassword() {
	  return password;
  }
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public String toString(){
      return "Username: " + username + " - Name: " + display_name + " - Email: " + email ;
  }
}