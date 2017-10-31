package scaccabarozzi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Squadra extends Entity {
	    
	private String nome;
	private List<Entity> squadre;
	
	public Squadra(SquadraDAO s) {
		squadre = s.getSquadre();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String referenceID(int id) {
		
		String name= "";
		for(Entity e : squadre) {
			int x = Integer.parseInt((String)e.getCampo("id"));
			if(x == id) {
				name = (String)e.getCampo("nomeSquadra");
			}
		}
		return name;
	}
	
	public int referenceName(String name) {
		
		int id = 0;
		for(Entity e : squadre) {
			String x = (String)e.getCampo("nomeSquadra");
			if(x.equals(name)) {
				id =Integer.parseInt((String)e.getCampo("id"));
			}
		}
		return id;
	}
	
	public List<Entity> getSquadre() {
		return squadre;
	}

	public void setSquadre(List<Entity> squadre) {
		this.squadre = squadre;
	}
	
}
