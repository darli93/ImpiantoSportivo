package scaccabarozzi;
   import java.text.*;
   import java.util.*;
   import java.sql.*;

@SuppressWarnings("unused")
public class UserDAO extends EntityDAO {

	
	 public UserDAO() {
		super("users");
		// TODO Auto-generated constructor stub
	 }
	 
	public User loginUser(String username, String password) {
		
		User utente = new User();
		utente.setUsername(username);
		utente.setPassword(password);
		List<Entity> userLogin = executeSelectAllQuery( "select * from users where username='" + username  + "' AND password='" + password  + "'");
		
		if(userLogin.isEmpty()) {
			utente.setValid(false);
		} else {
		
			for(Entity e : userLogin) {
				
				utente.setId(Integer.parseInt((String)e.getCampo("idUsers")));
				utente.setNome((String)e.getCampo("nome"));
				utente.setCognome((String)e.getCampo("cognome"));
				System.out.println(utente.getNome());
				utente.setValid(true);
				byte manager  = (byte)e.getCampo("manager");
				
				if(manager == 1) {
					utente.setAdmin(true);
				}
				
			}
			
		}
		
		return utente;
		
	}
	
	public void addUser(Entity e) throws SQLException {
		
		insertQuery(e);
		
	}
	
     private String customQuery() {
    	 
    	 
    	 return null;
     }
     
  }
