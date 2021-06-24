package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.SQLForumConnector;

@WebServlet( "/CreateTopic" )
public class CreateTopic extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CreateTopic() { super(); }

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// current forumID is ( forumIndex + 1 )
		int forumID = Integer.parseInt( request.getParameter( "forumIndex" ) ) + 1;

		// get current forum and save current forum index
		request.setAttribute( "currentForum", SQLForumConnector.getForum( forumID ) );
		request.setAttribute( "forumIndex", forumID - 1 );

		// forward to CreateTopic
		request.getRequestDispatcher( "/WEB-INF/CreateTopic.jsp" ).forward( request, response );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// current forumID is ( forumIndex + 1 )
		int forumID = Integer.parseInt( request.getParameter( "forumIndex" ) ) + 1;

		// create new topic with user given subject, author, and content
		SQLForumConnector.addTopic( forumID, request.getParameter( "subject" ), request.getParameter( "author" ), request.getParameter( "content" ) );
		
		// forward to DisplayTopics with current forum index
		response.sendRedirect( "DisplayTopics?forumIndex=" + ( forumID - 1 ) );
	}
}