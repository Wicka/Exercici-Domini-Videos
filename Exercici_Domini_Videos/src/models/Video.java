package models;

import java.util.ArrayList;
import java.util.List;



public class Video {
	private String nick;
	private String url;
	private String title;
	private List <String> tagList;//= new ArrayList <String>();
	
	
	public Video(String nick, String url, String title) {
		
		this.nick=nick;
		this.url=url;
		this.title=title;
		this.tagList=new ArrayList <String>();
		
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<String> getTagList() {
		return tagList;
	}


	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}


	public void addTag (String entry) {
		this.tagList.add(entry);
	}


	@Override
	public String toString() {
		return "Video [nick=" + nick + ", url=" + url + ", title=" + title + ", tagList=" + tagList + "]";
	}
	
}

/*
public class Video {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
*/