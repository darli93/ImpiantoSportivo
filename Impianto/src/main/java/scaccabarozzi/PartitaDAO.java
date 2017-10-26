package scaccabarozzi;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PartitaDAO extends EntityDAO {

	public PartitaDAO() {
		super("partite");
		// TODO Auto-generated constructor stub
	}
	
	public List<Entity> getPartite() {
		
		List<Entity> partite = executeSelectAllQuery("select * from partite");
		
		return partite;
	}

}
