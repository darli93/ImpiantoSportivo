package scaccabarozzi;
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
    
}
