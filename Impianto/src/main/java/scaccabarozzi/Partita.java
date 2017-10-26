package scaccabarozzi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@SuppressWarnings("unused")
public class Partita extends Entity {

	Connection currentCon = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	private int idPartita;
	private String squadraCasa;
	private String squadraOspite;
	private String data;
	private int stadio;
	private float sconto;
	private float prezzo;
	
	public Partita() {}
	
	public Partita(String casa, String ospite, int stadio, float prezzo, String data ) {

		this.squadraCasa = casa;
		this.squadraOspite = ospite;
		this.data = data;
		this.stadio = stadio;
		this.prezzo = prezzo;
		
	}
	
	public String getSquadraCasa() {	
		return squadraCasa;
	}
	
	public void setSquadraCasa(String squadraCasa) {
		this.squadraCasa = squadraCasa;
	}
	
	public String getSquadraOspite() {
		return squadraOspite;
	}

	public void setSquadraOspite(String squadraOspite) {
		this.squadraOspite = squadraOspite;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public int getIdPartita() {
		return idPartita;
	}

	public void setIdPartita(int idPartita) {
		this.idPartita = idPartita;
	}

	public int getStadio() {
		return stadio;
	}

	public void setStadio(int stadio) {
		this.stadio = stadio;
	}

	public float getSconto() {
		return sconto;
	}

	public void setSconto(float sconto) {
		this.sconto = sconto;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public Partita aggiungiPartita(Partita partita) {
		return partita;
	}
	
	public void rimuoviPartita(Partita partita) {
		
	}
	
	public void scontaPartita(float newSconto) {
		
		String myQuery = "insert into partite(sconto) values (" + newSconto + ")";
		prezzo = prezzo - newSconto;
		this.sconto = newSconto;
		 try 
	        {
	           //connect to DB 
	           currentCon = ConnectionManager.getConnection();
	           stmt=currentCon.createStatement();
	           rs = stmt.executeQuery(myQuery);	        
	        } 

	        catch (Exception ex) 
	        {
	           System.out.println("Log In failed: An Exception has occurred! " + ex);
	        } 
	   	    
	        //some exception handling
	        finally 
	        {
	           if (rs != null)	{
	              try {
	                 rs.close();
	              } catch (Exception e) {}
	                 rs = null;
	              }
	   	
	           if (stmt != null) {
	              try {
	                 stmt.close();
	              } catch (Exception e) {}
	                 stmt = null;
	              }
	   	
	           if (currentCon != null) {
	              try {
	                 currentCon.close();
	              } catch (Exception e) {
	              }

	              currentCon = null;
	           }
	        }
		aggiornaPrezzoDB(prezzo);
	}
	
	private void aggiornaPrezzoDB(float nuovoPrezzo){
		
		String updateQuery = "update partite set prezzo = " +  nuovoPrezzo + "where id  = " + idPartita + ";";

        try 
        {
           //connect to DB 
           currentCon = ConnectionManager.getConnection();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(updateQuery);	        
        } 

        catch (Exception ex) 
        {
           System.out.println("Log In failed: An Exception has occurred! " + ex);
        } 
   	    
        //some exception handling
        finally 
        {
           if (rs != null)	{
              try {
                 rs.close();
              } catch (Exception e) {}
                 rs = null;
              }
   	
           if (stmt != null) {
              try {
                 stmt.close();
              } catch (Exception e) {}
                 stmt = null;
              }
   	
           if (currentCon != null) {
              try {
                 currentCon.close();
              } catch (Exception e) {
              }

              currentCon = null;
           }
        }
		
	}

}
