<%@page import="scaccabarozzi.SquadraDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="stile/myStyle.css">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator"%>
<%@ page import="scaccabarozzi.*"%>
<% 

	User currentUser = (User)session.getAttribute("currentAdminUser"); 
	if(currentUser == null || !currentUser.isAdmin()) {
		
		response.sendRedirect("Login.jsp");
		
	}

%>
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
	  
	<div class="container-fluid ">    
		<div class="jumbotron text-center"><h3>INSERISCI PARTITA</h3></div>
			<form action="addMatch" method="post">
			<div class ="form-group col-xs-4">
				<label>Casa</label>
				<select class="form-control" name = "home">
				<% String selectedHome = null;
				SquadraDAO squad = new SquadraDAO();
				List<Entity> listaSquadre = squad.getSquadre();
				
				for(Entity e : listaSquadre) {
					String squadra = (String)e.getCampo("nomeSquadra");
				%>
				       
					<option><%=squadra%></option><% 
					 	} %>
				</select>
			</div>
			<div class ="form-group col-xs-4">
				<label>Trasferta</label>
				<select class="form-control" name = "away">
				<% 
				for(Entity e : listaSquadre) {
					String squadra = (String)e.getCampo("nomeSquadra");
				%>
				       
					<option><%=squadra%></option><% } %>
				</select>
			</div>
			<div class ="form-group col-xs-4">
				<label>Stadio</label>
				<select class="form-control" name = "stadio">
<% 				
				StadioDAO stadi = new StadioDAO();
				List<Entity> listaStadi = stadi.getStadi();
				for(Entity e : listaStadi) {
					String stadio = (String)e.getCampo("nomeStadio");
				%>
				       
					<option><%=stadio%></option><% } %>
				</select>
			</div>
			<div class ="form-group col-xs-4">
				<label>Prezzo</label>
				<input class="form-control" name = "prezzo" required="true">
			</div>
			<div class ="form-group col-xs-4">
				<label>Sconto</label>
				<input class="form-control" name = "sconto">
			</div>
			<div class ="form-group col-xs-4">
				<label>data</label>
				<input class="form-control" name = "data" required="true" placeholder="yyyy-mm-dd hr:min:sec">
			</div>
		
			<input type="submit" value ="aggiungi partita">
			</form>
			
			 <% String infoLog = (String)request.getAttribute("matchLog"); %>
						 <% if(infoLog != null) {%>
						 <%=infoLog%>
						 <%}%>
	</div>
</body>
</html>
