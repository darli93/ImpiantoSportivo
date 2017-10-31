package scaccabarozzi;
import java.util.*;
import java.util.Map.Entry;
import java.sql.*;

@SuppressWarnings("unused")
public abstract class EntityDAO {
	
	private String tableName = "";
	private int maxColumns;
	
	public EntityDAO(String tableName) {
		this.tableName = tableName;
	}
    
    public List<Entity> executeSelectAllQuery(String query) {
    	
    	List<Entity> lista = new ArrayList<Entity>();
    	List<String> chiavi = new ArrayList<String>();
    	List<String> columns = new ArrayList<String>();
    	System.out.println("*********** " + query);
    	Connection currentCon = null;
        ResultSet rs = null;
        Statement stmt = null;
		
		try 
	      {
	        //connect to DB 
	        currentCon = ConnectionManager.getConnection(); 
	        stmt=currentCon.createStatement();
	        DatabaseMetaData meta = currentCon.getMetaData();
	        
	        chiavi = getPrimaryKey(currentCon);
	        columns = getAllColumns(currentCon);
	        int numeroColonne = columns.size();
	        rs = stmt.executeQuery(query);

	        while(rs.next()) {
	        	
	        	Map<String,Object> myMap = new LinkedHashMap<String, Object>();
	        	Entity newEntity = new Entity();
	        	newEntity.setNome(tableName);
	        	newEntity.setKeys(chiavi);
	        	
	        	for(int i = 1; i <=numeroColonne; i++) {
	        		Object obj = getTypedValue(rs, i);
	        		myMap.put(columns.get(i-1), obj);
	        	}
	        	
	        	newEntity.setCampi(myMap);
	        	//System.out.println(myMap);
	        	lista.add(newEntity);
	        	
	        }

         } 

	     catch (Exception ex) 
	     {
	        System.out.println("An Exception has occurred! " + ex);
	     } 
	     finally 
	     {
	    	closeConn(rs, currentCon, stmt);
	     }

		return lista;
    }
    
    public void insertQuery(Entity e) throws SQLException {
    	
        ResultSet rs = null;
        Connection currentCon = ConnectionManager.getConnection();        
        PreparedStatement stmt = generateInsertStatement(e, currentCon);
        stmt.execute();
        
        currentCon.close();
        
	}

    public void updateQuery(Entity e, String condition) throws SQLException {
    	
    	List<Object> values = new ArrayList<>();
    	ResultSet rs = null;
        Connection con = ConnectionManager.getConnection();
    	String query = generateUpdateStatemente(e, con, values);
    	
    	if(!(condition.isEmpty() || condition == null)) {
    		query += " where " + condition;
    	}
    	
    	PreparedStatement stmt = con.prepareStatement(query);
    	
    	for(int i = 0; i < values.size(); i++) {
    		stmt.setObject(i+1, values.get(i));
    	}
    	System.out.println("Executing query: " + query);
    	stmt.execute();
    	
    	con.close();
	
	}
    
    private List<String> getPrimaryKey(Connection currentCon) throws SQLException {
    	
        ResultSet rs = null;
        
    	currentCon = ConnectionManager.getConnection(); 
        DatabaseMetaData meta = currentCon.getMetaData();
    	List<String> keys = new ArrayList<String>();
        rs = meta.getPrimaryKeys(null, null, tableName);
        while(rs.next()) {
        	keys.add(rs.getString("COLUMN_NAME"));
        	
        }
        rs.close();
        return keys;
    }
    
    protected List<String> getAllColumns(Connection currentCon) throws SQLException {
     	 
    	ResultSet rs = null;
    	 
    	currentCon = ConnectionManager.getConnection(); 
        DatabaseMetaData meta = currentCon.getMetaData();
    	List<String> colonne = new ArrayList<String>();
    	rs =  meta.getColumns(null, null, tableName, null);
	        
	    while(rs.next()) {
	    	colonne.add(rs.getString("COLUMN_NAME"));
	    }
    	
	    rs.close();
    	return colonne;
    }
    
    private void closeConn(ResultSet rs, Connection currentCon, Statement stmt) {
    	
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
    
    private Object getTypedValue(ResultSet rs, int col) throws SQLException {

        Object res;
        ResultSetMetaData metadata = rs.getMetaData();

        switch (metadata.getColumnType(col)) {
        case Types.NUMERIC:
          res = rs.getInt(col);
          break;
        case Types.DATE:
          res = rs.getDate(col);
          break;
        case Types.FLOAT:
        	res = rs.getFloat(col);
        	break;
        case Types.TINYINT:
        	res = rs.getByte(col);
        	break;
        default:
          res = rs.getString(col);
          break;
        }
        

        return res;
      }
    
    private PreparedStatement generateInsertStatement(Entity entity, Connection con) throws SQLException {
    	
    	List<Object> values = new ArrayList<>();
    	String query = "insert into " +  tableName  + " (";
    	String valuesQuery = ") " + " values ( ";
    	 
    	Iterator<Entry<String, Object>> iterator = entity.getCampi().entrySet().iterator();
    	while(iterator.hasNext()) {
    		Entry<String, Object> entry = iterator.next();
    		query += entry.getKey();
    		valuesQuery += "?";
    		if(iterator.hasNext()) {
    			query += ", ";
    			valuesQuery += ", ";
    		}
    		 	 
    		values.add(entry.getValue());
    	}
    	 
    	query += valuesQuery + " )";
    	
    	PreparedStatement stmt = con.prepareStatement(query);
    	
    	for(int i = 0; i < values.size(); i++) {
    		stmt.setObject(i+1, values.get(i));
    	}
    	System.out.println("Executing query: " + query);
    	return stmt;
    	
    }
    
    private String generateUpdateStatemente(Entity entity, Connection con, List<Object> values) throws SQLException {
    	
    	String query = "update " +  tableName  + " set(";
    	
    	Iterator<Entry<String, Object>> iterator = entity.getCampi().entrySet().iterator();
    	while(iterator.hasNext()) {
    		Entry<String, Object> entry = iterator.next();
    		query += entry.getKey() + " = ?";
    		
    		if(iterator.hasNext()) {
    			query += ", ";
    			
    		}
    		 	 
    		values.add(entry.getValue());
    	}
    	
    	query += ")";
    	
    	System.out.println("Executing query: " + query);
    	
    	return query;
    	
    }

}
