package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.SQLForumConnector;


@WebServlet( "/CreateForum" )
public class CreateForum extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CreateForum() { super(); }

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// forward to CreateForum
		request.getRequestDispatcher( "/WEB-INF/CreateForum.jsp" ).forward( request, response );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// create new forum with user given name
		SQLForumConnector.addForum( request.getParameter( "name" ) );

		// forward to DisplayForums
		response.sendRedirect( "DisplayForums" );
	}
}