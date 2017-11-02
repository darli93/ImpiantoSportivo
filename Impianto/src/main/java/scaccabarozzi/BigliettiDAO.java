package scaccabarozzi;

import java.util.List;

public class BigliettiDAO extends EntityDAO {
	
	public BigliettiDAO() {
		super("biglietto");
	}
	
	public List<Entity> getBiglietti(User utente) {

		List<Entity> biglietti = executeSelectAllQuery("select * from biglietto where utente = " + utente.getId());
		
		return biglietti;
	
	}
}
