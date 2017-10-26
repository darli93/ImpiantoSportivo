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

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="partite.jsp">Partite</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
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
      </tr>
    </thead>
    <tbody class ="">
<%  //--------------------------------------------------------------------------------------------------------------------
	PartitaDAO partita = new PartitaDAO();
	List<Entity> partite = partita.getPartite();

	for(Entity p : partite) {

		Map<String, Object> myMap  = p.getCampi();	
	//--------------------------------------------------------------------------------------------------------------------
%>
      <tr>
        <td><%= Squadra.realName(p.getCampo("squadraCasa"))%></td>
        <td><%= Squadra.realName(p.getCampo("squadraOspite")) %></td> 
      	<td><%= Stadio.realNameStadio(p.getCampo("stadio")) %></td> 
        <td><%=((String)p.getCampo("data")).substring(0, 16)%></td> 
     </tr>
     
<%} //---------------------------------------------------------------------------------------------------------------------
	 
		partite.clear();

	//---------------------------------------------------------------------------------------------------------------------
%>

	    </tbody>
 	 </table>	
 	 </div>
</body>
</html>