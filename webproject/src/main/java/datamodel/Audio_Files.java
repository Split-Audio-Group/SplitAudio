package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIO_FILES")
public class Audio_Files {

	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "AUDIO_ID")
	   private Integer id;

	   @Column(name = "USER_ID")
	   private Integer uid;

	   @Column(name = "NAME")
	   private String name;

	   @Column(name = "FILE_PATH")
	   private String path;
	   
	   @Column(name = "SIZE")
	   private Double size;
	   
	   @Column(name = "PUBLIC")
	   private Boolean pub;
	   
	   public Audio_Files() {}
	   
	   public Audio_Files(Integer id, Integer uid, String name, String path, Double size, Boolean pub) {
		   this.id= id;
		   this.uid =uid;
		   this.name= name;
		   this.path=path;
		   this.size=size;
		   this.pub=pub;
	   }
	   
	   public Audio_Files(Integer uid, String name, String path, Double size, Boolean pub) {
		   this.uid =uid;
		   this.name= name;
		   this.path=path;
		   this.size=size;
		   this.pub=pub;
	   }
	   
	   public Audio_Files(String name, String path, Double size) {
		   this.name= name;
		   this.path=path;
		   this.size=size;
	   }
	   
	   public Integer getId() {
		      return id;
		   }

	   public void setId(Integer id) {
		      this.id = id;
		   }

	   public Integer getuid() {
			      return uid;
			   }

		public void setuid(Integer uid) {
			      this.uid = uid;
			   }
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name= name;
		}
		
		public String getFilepath() {
			return this.path;
		}
		
		public void setFilepath(String path) {
			this.path=path;
		}
		
		public Double getSize() {
			return this.size;
		}
		
		public void setSize(Double size) {
			this.size= size;
		}
		
		public Boolean getpub() {
			return this.pub;
		}
		
		public void setpub(Boolean pub) {
			this.pub = pub;
		}
		
		public String toString(){
		      return "UserId: " + uid + " - Name: " + name + " - Path: " + path + " - Size: " + size + " - Public: " + pub;
		 }
		
		
		
	   
	   
}
