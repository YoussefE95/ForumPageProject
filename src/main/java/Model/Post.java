package Model;

import java.sql.Date;

public class Post
{
	private String author;
	private String content;
	private Date date;
	
	public Post( String author, String content, Date date )
	{
		this.author = author;
		this.content = content;
		this.date = date;
	}

	public String getAuthor()
	{
		return author;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public Date getDate()
	{
		return date;
	}
}