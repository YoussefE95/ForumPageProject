package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.SQLForumConnector;

@WebServlet( "/DisplayTopic" )
public class DisplayTopic extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DisplayTopic() { super(); }

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// current forumID is ( forumIndex + 1 )
		int forumID = Integer.parseInt( request.getParameter( "forumIndex" ) ) + 1;
		// current forumID is ( topicIndex + 1 )
		int topicID = Integer.parseInt( request.getParameter( "topicIndex" ) ) + 1;

		// get current forum, current topic, and save current forumIndex and current topicIndex
		request.setAttribute( "currentForumName", SQLForumConnector.getForum( forumID ).getName() );
		request.setAttribute( "currentTopic", SQLForumConnector.getTopic( forumID, topicID ) );
		request.setAttribute( "forumIndex", forumID - 1 );
		request.setAttribute( "topicIndex", topicID - 1 );

		// forward to DisplayTopic
		request.getRequestDispatcher("/WEB-INF/DisplayTopic.jsp").forward(request, response);
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// current forumID is ( forumIndex + 1 )
		int forumID = Integer.parseInt( request.getParameter( "forumIndex" ) ) + 1;
		// current forumID is ( topicIndex + 1 )
		int topicID = Integer.parseInt( request.getParameter( "topicIndex" ) ) + 1;
		
		// create new post with user given author and content
		SQLForumConnector.addPost( forumID, topicID, request.getParameter( "author" ), request.getParameter( "content" ), new java.sql.Date( System.currentTimeMillis() ) );

		// forward to DisplayTopic with current forum index and topic index
		response.sendRedirect( "DisplayTopic?forumIndex=" + ( forumID - 1 ) + "&topicIndex=" + ( topicID - 1 ) );
	}
}