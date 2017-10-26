package scaccabarozzi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Squadra {
	

	static Connection currentCon = null;
    static ResultSet rs = null; 
    
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static ArrayList<Partita> getPartite() {
		
		Statement stmt = null;    
		
        String searchQuery = "select * from partite";
	    
     // "System.out.println" prints in the console; Normally used to trace the process
    
        ArrayList<Partita> partite = new ArrayList<Partita>();
     try 
     {
        //connect to DB 
        currentCon = ConnectionManager.getConnection();
        stmt=currentCon.createStatement();
        rs = stmt.executeQuery(searchQuery);	        
       
	    
        // if user does not exist set the isValid variable to false
        while(rs.next()) 
        {
           int idPartita = rs.getInt("id");
           int squadraCasa = rs.getInt("squadraCasa");
           int squadraOspite = rs.getInt("squadraOspite");
           int stadio = rs.getInt("stadio");
           float prezzo = rs.getInt("prezzo");
           float sconto =rs.getFloat("sconto");
           String data  = rs.getString("data");
           
           Partita parza = new Partita(realName(squadraCasa), realName(squadraOspite), stadio, prezzo, data);
           partite.add(parza);
           // partite.add(new Partita(realName(squadraCasa), realName(squadraOspite), stadio, prezzo));
        }
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

		
		return partite;
	}

	public static void aggiungiPartitaDB(Partita partite) {

		Statement stmt = null;    
		
        String searchQuery =insertQuery(partite); 
	    
		try 
	     {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection();
	        stmt=currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	     
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
	
	public static String realName(Object o) {
		
		
		
		String nomeSquadra = null;
		
		switch (Integer.parseInt((String)o)) {
		case 1:
			nomeSquadra = "Juventus";
		break;
		case 2:
			nomeSquadra = "Barcellona";
		break;
		case 3:
			nomeSquadra = "Real Madrid";
		break;
		case 4:
			nomeSquadra = "Bayern Monaco";
		break;
		case 5:
			nomeSquadra = "Milan";
		break;
		case 6:
			nomeSquadra = "Chelsea";
		break;
		default:
			break;
		}
		return nomeSquadra;
	}
		
	public static int numeroSquadra(String nomeSquadra) {
	
		int num = 0;	
	
		switch (nomeSquadra.toLowerCase()) {
		case "juventus":
			num  = 1;
		break;
		case "barcellona":
			num = 2;
		break;
		case "real madrid":
			num = 3;
		break;
		case "bayern monaco":
			num = 4;
		break;
		case  "milan":
			num = 5;
		break;
		case "chelsea":
			num = 6;
		break;
		default:
			break;
		}
		return num;
	}
	
	private static String insertQuery(Partita p) {
		String query = null;
		
		query = "insert into partite (squadraCasa, squadraOspite,stadio,prezzo,sconto,data) values "
				+ "( " + numeroSquadra(p.getSquadraCasa())  + "," + numeroSquadra(p.getSquadraOspite())  + ","  + p.getStadio() + ","  + p.getPrezzo() + "," + p.getSconto() +"," + "'" + p.getData() + "'" + ")";
		
		System.out.println(query);
		return query;
	}
	
	public static ArrayList<String> getNomiSquadre() {
		
		Statement stmt = null;    
		
        String searchQuery = "SELECT nomeSquadra FROM impianto.squadre"; 
        ArrayList<String> array = new ArrayList<String>();
		try 
	     {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection();
	        stmt=currentCon.createStatement();
	        rs = stmt.executeQuery(searchQuery);
	        
	        while(rs.next()) 
	        {
	          
	        	String nome = rs.getString("nomeSquadra"); // meta.get
	        	array.add(nome);
	           // partite.add(new Partita(realName(squadraCasa), realName(squadraOspite), stadio, prezzo));
	        } 
	           
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
		return array;
	}
}
