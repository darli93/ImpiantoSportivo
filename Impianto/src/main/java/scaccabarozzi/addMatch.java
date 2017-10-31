package scaccabarozzi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scaccabarozzi.*;
/**
 * Servlet implementation class addMatch
 */
@SuppressWarnings("unused")
@WebServlet("/addMatch")
public class addMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try
		{	    

			Entity e = new Entity();
			Partita p = new Partita();
			Squadra squadra = new Squadra(new SquadraDAO());
			Stadio stad = new Stadio(new StadioDAO());
			PartitaDAO pa = new PartitaDAO();
			
			String prezzoString = (String)request.getParameter("sconto");
			
			String squadrCasa = (String)request.getParameter("home");
			String squadraOspite = (String)request.getParameter("away");
			String stadio = (String)request.getParameter("stadio");
			float prezzo = Float.parseFloat((String)request.getParameter("prezzo"));
			
			String data = (String)request.getParameter("data");
			
			Map<String, Object> myMap = new LinkedHashMap<String, Object>();
			myMap.put("squadraCasa", squadra.referenceName(squadrCasa));
			myMap.put("squadraOspite", squadra.referenceName(squadraOspite));
			myMap.put("stadio", stad.referenceName(stadio));
			myMap.put("prezzo", prezzo);
			if(!prezzoString.isEmpty()) {
				float sconto = Float.parseFloat(prezzoString);
				myMap.put("sconto", sconto);
			}
			myMap.put("data", data);
			e.setCampi(myMap);
			pa.setPartita(e);
			
			response.sendRedirect("admin");
	
		} 
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}

}
