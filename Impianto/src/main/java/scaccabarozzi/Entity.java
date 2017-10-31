package scaccabarozzi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class Entity {
	
	private String nome;
    private List<String> keys;
    protected Map<String,Object> campi;
    
    public Object getCampo(String nomeCampo) {
    	return campi.get(nomeCampo);
    }
    
    public List<String> getColumns() {
    	List<String> res = new ArrayList<String>();
    	res.addAll(campi.keySet());
    	return res;
    }
        
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public Map<String, Object> getCampi() {
		return campi;
	}
	public void setCampi(Map<String, Object> campi) {
		this.campi = campi;
	}
	
    //metodo che recupera lista valori delle chiavi 
	//
	
	//metodo che recupera la sottomappa delle chiavi
	// public Map<String,Object> 
	
}
