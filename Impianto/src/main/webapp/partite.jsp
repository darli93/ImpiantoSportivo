<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="stile/myStyle.css">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.List,java.util.*,java.util.Iterator"%>
<%@ page import="scaccabarozzi.*"%>
<%User currentUser = (User)session.getAttribute("currentUser"); %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index.jsp">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="partite.jsp">Partite</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li> <% if(currentUser != null) { %> 
		
				<a href="Logout.jsp"><span class="glyphicon glyphicon-log-in"></span> Logout</a> <%
		
			} else {%>  
				
				<a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a> <%
		
		}%>
		</li>
      </ul>
    </div>
  </div>
</nav>
 
<div class="container-fluid">    
<table class="table">
    <thead class ="">
      <tr>
        <th>Squadra Casa</th>
        <th>Squadra Ospite</th>
        <th>Stadio</th>
        <th>Data</th>
        <th>Order By <select class="col-xs-2 form-control" id="select">
        	<option>Data</option>
		    <option>Stadio</option>
		    <option>Nome Squadra di casa</option>
        </select>
        </th>
 <%//--------------------------------------------------------------------------------------------------------------------                     
        
	if((currentUser != null)) {%>  
		<th></th> <%	
	}%> 
        
      </tr>
    </thead>
    <tbody class ="">
<%  //--------------------------------------------------------------------------------------------------------------------
	PartitaDAO partita = new PartitaDAO();
	List<Entity> partite = partita.getPartite();
	Squadra s  = new Squadra(new SquadraDAO());
	Stadio stadio = new Stadio(new StadioDAO());
	int i = 0;
	for(Entity p : partite) {
		
		Map<String, Object> myMap  = p.getCampi();
		String squadraCasa =  s.referenceID((Integer.parseInt((String)p.getCampo("squadraCasa"))));
		String squadraOspite = s.referenceID((Integer.parseInt((String)p.getCampo("squadraOspite"))));
		String stad = stadio.referenceID(((Integer.parseInt((String)p.getCampo("stadio")))));
		String data = ((String)p.getCampo("data")).substring(0, 16);
		String id = (String)p.getCampo("id");

	//--------------------------------------------------------------------------------------------------------------------
%>
      <tr>
        <td><%= squadraCasa%></td>
        <td><%= squadraOspite %></td> 
      	<td><%= stad %></td> 
        <td><%= data%></td>
       
<%	//--------------------------------------------------------------------------------------------------------------------    
	if(currentUser != null) {%>  
		<td><button  onclick="start(<%=id%>)" class="acq <%=id%> btn btn-info">ACQUISTA</button> <button class="acq btn btn-success">PRENOTA</button></td> <%
			
	} else {%> 
    	<td><a href="Login.jsp"><button class="btn btn-info">ACQUISTA</button> <button class="btn btn-success" >PRENOTA</button></a></td>
<% } 
		i++;
		//System.out.print( a.getCampo("squadraCasa").toString());
} //---------------------------------------------------------------------------------------------------------------------
	 
		partite.clear();

  //---------------------------------------------------------------------------------------------------------------------
%>

	    </tbody>
 	 </table>
 	 	
 	 </div>
 	 <script>
 	function start(dato){
 		console.log(dato)
 		 $.ajax({
 	        type:"POST",
 	        url:"buy",
 	        dataType:"text",
 	        data: { "data" : dato}
 		 });
 	}
 	 </script>
</body>
</html>