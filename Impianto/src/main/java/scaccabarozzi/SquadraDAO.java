package scaccabarozzi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SquadraDAO extends EntityDAO {
	
	public SquadraDAO() {
		super("squadre");
		// TODO Auto-generated constructor stub
	}
	
	public List<Entity> getSquadre() {
		
		List<Entity> partite = executeSelectAllQuery("select * from squadre");
		
		return partite;
		
	}
	 
	public int linkIdwithName(String nomeSquadra) {
		
		int id = 0;
		List<Entity> squadre = new ArrayList<Entity>();
		
		squadre = executeSelectAllQuery("select id from squadre where nomeSquadra = '" + nomeSquadra + "'");
		
		for (Entity entity : squadre) {
			id = Integer.parseInt((String)entity.getCampo("id"));
		}
		
		return id;
	}
    
}
