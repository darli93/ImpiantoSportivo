package scaccabarozzi;

import java.util.ArrayList;
import java.util.List;

public class StadioDAO extends EntityDAO {
	
	public StadioDAO() {
		super("stadio");
		// TODO Auto-generated constructor stub
	}
	
	public List<Entity> getStadi() {
		
		List<Entity> stadi = executeSelectAllQuery("select * from stadio");
		
		return stadi;
		
	}
	 
	public int linkIdwithName(String nomeStadio) {
		
		int id = 0;
		List<Entity> stadi = new ArrayList<Entity>();
		
		stadi = executeSelectAllQuery("select * from stadio where nomeStadio = '" + nomeStadio + "'");
		System.out.println(stadi.size());
		
		for (Entity entity : stadi) {
			id = Integer.parseInt((String)entity.getCampo("id"));
			//System.out.println((String)entity.getCampo("id"));
		}
		
	
		return id;
	}
	
public String linkNameWithId(int id) {
		
		String s = "";
		
		List<Entity> squadre =  executeSelectAllQuery("select * from stadio where id = '" + id + "'");
		//System.out.println(squadre.size());
		
		for (Entity entity : squadre) {
			s = (String)entity.getCampo("nomeStadio");
		}
		
		return s;
	}

}
