package scaccabarozzi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			Partita p = new Partita();
			p.setSquadraCasa(request.getParameter("home"));
			p.setSquadraOspite(request.getParameter("away"));
			String nomeStadio = (request.getParameter("stadio"));
			p.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
			p.setSconto(Integer.parseInt(request.getParameter("sconto")));
			p.setData(request.getParameter("data"));
			p.setStadio(Stadio.numeroStadio(nomeStadio));
			if(p.getSquadraCasa().equals(p.getSquadraOspite())) {
				
				String info ="<br><br><label class='col-xs-12 text-center  text-danger'> Una squadra non può giocare contro se stessa!</label>";
				request.setAttribute("matchLog", info);
				RequestDispatcher rd = request.getRequestDispatcher("addMatch.jsp");
				rd.forward(request, response);
				//rd.include(request, response);
			} else {
				Squadra.aggiungiPartitaDB(p);
			}
	
		} 


		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}

}
