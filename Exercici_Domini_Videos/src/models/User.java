package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {			
	
	private String name;
	private String surname;
	private String nick;
	private String pwd;
	private String date;	
	private List <Video> videos;//= new ArrayList <Video>();
	
	public User(String nameEntry, String surnameEntry, String nickEntry, String pwdEntry, String registry) {
	
		this.name=nameEntry;
		this.surname=surnameEntry;
		this.nick=nickEntry;
		this.pwd=pwdEntry;
		this.date=registry;	
	
		this.videos= new ArrayList <Video>();
		

	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((videos == null) ? 0 : videos.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
	//	if (date == null) {
	//		if (other.date != null)
	//			return false;
	//	} else if (!date.equals(other.date))
	//		return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (videos == null) {
			if (other.videos != null)
				return false;
		} else if (!videos.equals(other.videos))
			return false;
		return true;
	}


	public User(String nameEnt, String snameEnt, String nickEnt, String pwdEnt) {
		// TODO Auto-generated constructor stub
		
		this.name=nameEnt;
		this.surname=snameEnt;
		this.nick=nickEnt;
		this.pwd=pwdEnt;
	//	this.date=registry;
	
		
	
	//	List <Video> videos= new ArrayList <Video>();
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", nick=" + nick + ", pwd=" + pwd + ", date=" + date
				+ ", videos=" + videos + "]";
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public List<Video> getVideos() {
		return videos;
	}


	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean verifyPwd(String nickEnt,String pwdEnt) {
		
		boolean correct=false;
		
		if(this.nick.equals(nickEnt)&& this.pwd.equals(pwdEnt)) {
			correct=true;
		}
		return correct;}
	
	
	
	public void addVideo(Video video) {
		
		this.videos.add(video)	;	
	
	}
	public void deleteVideo(int index) {
		
		this.videos.remove(index)	;	
	
	}


	
}

	
	/*
	 * Crea un programa en Java amb l’estructura de domini de les classes:



- Usuari: esta format per un nom, cognom, password i una data de registre.

Un usuari pot crear nous vídeos i veure un llistat dels seus videos.

*Un tag es un text amb una paraula, tenir en compte que un video pot tenir varis tags.

La estructura no ha de permetre afegir camps buits, en cas de que n’hi hagi ha de retornar una excepció. 
	 * */
	
	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}

//}
