package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.SQLForumConnector;

@WebServlet( "/DisplayForums" )
public class DisplayForums extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DisplayForums() { super(); }

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		// get all forums
		request.setAttribute( "forums", SQLForumConnector.getForums() );

		// forward to DisplayForums
		request.getRequestDispatcher( "/WEB-INF/DisplayForums.jsp" ).forward( request, response );
	}
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
		throws ServletException, IOException
	{
		doGet( request, response );
	}
}