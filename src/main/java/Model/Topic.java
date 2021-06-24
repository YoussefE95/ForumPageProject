package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Topic
{
	private String subject;
	private ArrayList<Post> posts;

	public Topic( String subject, ArrayList<Post> posts )
	{
		this.subject = subject;
		this.posts = posts;
	}

	public String getSubject()
	{
		return subject;
	}
	
	public ArrayList<Post> getPosts()
	{
		return posts;
	}

	public int getNumberPosts()
	{
		return posts.size();
	}

	public int getNumberReplies()
	{
		return ( getNumberPosts() - 1 );
	}
	
	public Post getFirstPost()
	{
		if( getNumberPosts() == 1 )
			return posts.get( 0 );

		// assume first post in replies is oldest
		Post oldest = posts.get( 0 );

		for( int i = 1; i < posts.size(); i++ )
		{
			Date temp = posts.get( i ).getDate();
			
			// if current date is older, set to oldest
			if( temp.getTime() < oldest.getDate().getTime() )
				oldest = posts.get( i );
		}

		return oldest;
	}

	public Post getLastPost()
	{
		if( getNumberPosts() == 1 )
			return posts.get( 0 );
		
		// assume first post in replies is newest
		Post newest = posts.get( 0 );
		
		for( int i = 1; i < posts.size(); i++ )
		{
			Date temp = posts.get( i ).getDate();
			
			// if current date is newer, set to newest
			if( temp.getTime() > newest.getDate().getTime() )
				newest = posts.get( i );
		}
		
		return newest;
	}
}