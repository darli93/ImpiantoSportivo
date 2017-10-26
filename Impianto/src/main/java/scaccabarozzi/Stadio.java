package scaccabarozzi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


 //DA FARE IN SEGUITO RIPORTATE LE OPERAZIONI COLLEGATE AL DATABASE IN UNA CLASSE CHIAMATA $NOMECLASSE+DAO -> DATA ACCESS OBJECT!!
public class Stadio {
	
	static Connection currentCon = null;
    static ResultSet rs = null;  
	
	private int idStadio;
	private String nomeStadio;
	private int capienza;
	private int postiRimanenti;
	private List<Partita> partite = new ArrayList<Partita>();
	private double incassoTotale;
	
	public int getIdStadio() {
		return idStadio;
	}
	public void setIdStadio(int idStadio) {
		this.idStadio = idStadio;
	}
	public String getNomeStadio() {
		return nomeStadio;
	}
	public void setNomeStadio(String nomeStadio) {
		this.nomeStadio = nomeStadio;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	public int getPostiRimanenti() {
		return postiRimanenti;
	}
	public List<Partita> getPartite() {
		return partite;
	}
	public void setPartite(List<Partita> partite) {
		this.partite = partite;
	}
	public double getIncassoTotale() {
		
		 Statement stmt = null;  
	     String searchQuery =  "select incassoTotale from stadio where id = " + idStadio + "'";    
	     // "System.out.println" prints in the console; Normally used to trace the process   
	     try 
	     {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection();
	        stmt=currentCon.createStatement();
	        rs = stmt.executeQuery(searchQuery);
		       
	        incassoTotale = rs.getDouble("incassoTotale");
	        
	        // if user does not exist set the isValid variable to false
	     } 

	     catch (Exception ex) 
	     {
	        System.out.println("Log In failed: An Exception has occurred! " + ex);
	     } 
		    
	     //some exception handling
	     finally 
	     { 
	    	 //chiudiconn(rs, stmt);
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
		return incassoTotale;
	}
	public void setIncassoTotale(double incasso) {
		
		getIncassoTotale();
		incassoTotale = incassoTotale + incasso;
		
		 Statement stmt = null;  
	     String searchQuery =  "insert into Stadio (incassoTotale) values ('"+ incassoTotale + "') where id = " + idStadio + "'";    
	     // "System.out.println" prints in the console; Normally used to trace the process   
	     try 
	     {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection();
	        stmt=currentCon.createStatement();
	        rs = stmt.executeQuery(searchQuery);
		       
	        incassoTotale = rs.getDouble("incassoTotale");
	        // if user does not exist set the isValid variable to false
	     } 

	     catch (Exception ex) 
	     {
	        System.out.println("An Exception has occurred! " + ex);
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
	
	public static ArrayList<String> getStadiDAO() {
		
		Statement stmt = null;    
		
        String searchQuery = "SELECT nomeStadio FROM impianto.stadio;"; 
        ArrayList<String> array = new ArrayList<String>();
		try 
	     {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection();
	        stmt=currentCon.createStatement();
	        rs = stmt.executeQuery(searchQuery);
	        
	        while(rs.next()) 
	        {
	        	String nome = rs.getString("nomeStadio");
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
	
	public static String realNameStadio(Object o) {
		
		String nomeStadio = null;
		
		switch (Integer.parseInt((String)o)) {
		case 1:
			nomeStadio = "Camp Nou";
		break;
		case 2:
			nomeStadio = "Bernabeu";
		break;
		case 3:
			nomeStadio = "San Siro";
		break;
		case 4:
			nomeStadio = "Juventus Stadium";
		break;
		case 5:
			nomeStadio = "Allianz Arena";
		break;
		case 6:
			nomeStadio = "Training";
		break;
		default:
			break;
		}
		return nomeStadio;
	}
		
	public static int numeroStadio(String nomeStadio) {
	
		int num = 0;	
	
		switch (nomeStadio.toLowerCase()) {
		case "camp nou":
			num  = 1;
		break;
		case "bernabeu":
			num = 2;
		break;
		case "san siro":
			num = 3;
		break;
		case "juventus stadium":
			num = 4;
		break;
		case  "allianz arena":
			num = 5;
		break;
		case "training":
			num = 6;
		break;
		default:
			break;
		}
		return num;
	} 
}
