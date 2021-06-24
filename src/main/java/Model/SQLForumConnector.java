package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class SQLForumConnector
{
	private SQLForumConnector() {};

	// connects to SQL database
	private static Connection SQLConnect()
		throws SQLException
	{
		String url = "";
		String username = "";
		String password = "";

		return DriverManager.getConnection( url, username, password );
	}

	// -----------------------------------------------
	// Functions to insert new entries into SQL tables
	// -----------------------------------------------
	// insert new forum entry
	public static void addForum( String name )
	{
		Connection c = null;

		try
		{	
			c = SQLConnect();

			String sql = "insert into Forums (name) values (?);";
			PreparedStatement pstmt = c.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			pstmt.setString( 1, name );
			pstmt.executeUpdate();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
	}

	// insert new topic entry along with its initial post entry
	public static void addTopic( int forumID, String subject, String author, String content )
	{
		Connection c = null;

		try
		{	
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select count(*) as count from Topics where forumID='" + forumID + "';" );
			int topicID = 1;

			if( rs.next() )
				topicID = rs.getInt( "count" ) + 1;

			String sql = "insert into Topics (forumID, topicID, subject) values (?, ?, ?);";
			PreparedStatement pstmt = c.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			pstmt.setInt( 1, forumID );
			pstmt.setInt( 2, topicID );
			pstmt.setString( 3, subject );
			pstmt.executeUpdate();

			addPost( forumID, topicID, author, content, new Date( System.currentTimeMillis() ) );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
	}

	// insert new post entry
	public static void addPost( int forumID, int topicID, String author, String content, Date date )
	{
		Connection c = null;

		try
		{	
			c = SQLConnect();

			String sql = "insert into Posts (forumID, topicID, author, content, timePosted) values (?, ?, ?, ?, ?);";
			PreparedStatement pstmt = c.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			pstmt.setInt( 1, forumID );
			pstmt.setInt( 2, topicID );
			pstmt.setString( 3, author );
			pstmt.setString( 4, content );
			pstmt.setTimestamp( 5, new java.sql.Timestamp( date.getTime() ) );
			pstmt.executeUpdate();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}
	}

	// --------------------------------------
	// Functions to pull data from SQL tables
	// --------------------------------------
	// get all forums
	public static ArrayList<Forum> getForums()
	{
		Connection c = null;
		ArrayList<Forum> forums = new ArrayList<Forum>();

		try
		{	
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select forumID, name from Forums;" );

			while( rs.next() )
			{
				forums.add( new Forum( rs.getString( "name" ), getTopics( rs.getInt( "forumID" ) ) ) );
			}
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}

		return forums;
	}

	// get specific forum
	public static Forum getForum( int forumID )
	{
		Connection c = null;
		String name = " ";

		try
		{	
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select name from Forums where forumID='" + forumID + "';" );

			if( rs.next() )
				name = rs.getString( "name" );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}

		return new Forum( name, getTopics( forumID ) );
	}

	// get all topics within a specifc forum
	public static ArrayList<Topic> getTopics( int forumID )
	{
		Connection c = null;
		ArrayList<Topic> topics = new ArrayList<Topic>();

		try
		{	
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select topicID, subject from Topics where forumID='" + forumID + "';" );

			while( rs.next() )
			{	
				topics.add( new Topic( rs.getString( "subject" ), getPosts( forumID, rs.getInt( "topicID" ) ) ) );
			}
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}

		return topics;
	}

	// get a specific topic within a specific forum
	public static Topic getTopic( int forumID, int topicID )
	{
		Connection c = null;
		String subject = " ";

		try
		{	
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select subject from Topics where forumID='" + forumID + "' and topicID='" + topicID + "';" );

			if( rs.next() )
				subject = rs.getString( "subject" );
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}

		return new Topic( subject, getPosts( forumID, topicID ) );
	}

	// get all posts for a specific topic within a specific forum
	public static ArrayList<Post> getPosts( int forumID, int topicID )
	{
		Connection c = null;
		ArrayList<Post> posts = new ArrayList<Post>();

		try
		{
			c = SQLConnect();

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select author, content, timePosted from Posts where forumID='" + forumID + "' and topicID='" + topicID + "';" );

			while( rs.next() )
			{
				posts.add( new Post( rs.getString( "author" ), rs.getString( "content" ), new Date( rs.getTimestamp( "timePosted" ).getTime() ) ) );
			}
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if( c != null )
					c.close();
			}
			catch( SQLException e )
			{
				e.printStackTrace();
			}
		}

		return posts;
	}
}