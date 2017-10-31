package scaccabarozzi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			Entity e = new Entity();
			UserDAO userDAO = new UserDAO();
			User user = new User();
			Map<String,Object> myMap = new HashMap<String,Object>();
			String username = (String)request.getParameter("username");
			String nome = (String)request.getParameter("nome");
			String cognome = (String)request.getParameter("cognome");
			String email = (String)request.getParameter("mail");
			String password = (String)request.getParameter("psw").toString();
			
			myMap.put("username", username);
			myMap.put("nome", nome);
			myMap.put("cognome",cognome);
			myMap.put("email",email);
			myMap.put("password",password);
			user.setNome(nome);
			user.setCognome(cognome);
			
			System.out.println(username + " " + nome );
			e.setCampi(myMap);
			try {
				userDAO.addUser(e);
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", user);
			response.sendRedirect("index.jsp");
			
	}

}
