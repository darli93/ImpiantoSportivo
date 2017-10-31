package scaccabarozzi;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@SuppressWarnings("unused")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

		try
		{	    

			User user = new User();
			UserDAO utente = new UserDAO();
			user = utente.loginUser(request.getParameter("un"), request.getParameter("pw"));
			
			if (user.isValid() || user.isAdmin())
			{
				HttpSession session = request.getSession(true);
				if(user.isAdmin()) {
						    
					session.setAttribute("currentAdminUser",user);
					response.sendRedirect("addMatch.jsp");
				} else {
					session.setAttribute("currentUser", user);
					response.sendRedirect("index.jsp"); //logged-in page
				}
			}
 
			else {
				//HttpSession session = request.getSession(true);	  
				PrintWriter out = response.getWriter();
				String info ="<br><br><label class='col-xs-12 text-center  text-danger'> Invalid username or password</label>";
				//response.sendRedirect("Login.jsp"); //error page 
				request.setAttribute("InfoLog", info);
				//session.setAttribute("InfoLog", info);
				//RequestDispatcher rd = response.set
				//response.sendRedirect("Login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
				//rd.include(request, response);
			}
		} 

		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}