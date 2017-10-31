<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="stile/myStyle.css">
<%@ page import="java.util.List,java.util.*,java.util.Iterator"%>
<%@ page import="scaccabarozzi.*"%>
</head>
<body>
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
        <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
    </div>
    <div class="col-sm-8 text-center bg"> 
      <h1>Welcome  <% User currentUser = (User)session.getAttribute("currentUser"); 
			if(currentUser != null) { %> 
		
			<%= currentUser.getNome()%> <%
		
	}%>  
	</h1>
      <p>
      <hr>
      <h4>Next Match</h4>
      <h2> 
<% //------------------------------------------------------------------------------------------------------------------------
    
	PartitaDAO partita = new PartitaDAO();
	//List<Entity> partite = partita.getPartite();
	Squadra s  = new Squadra(new SquadraDAO());
	Stadio stadio = new Stadio(new StadioDAO());
	List<Entity> listaPartite = partita.getPartiteWithCondition();
	Entity e = listaPartite.get(0);
	String home = s.referenceID((Integer.parseInt((String)e.getCampo("squadraCasa"))));
	String away = s.referenceID((Integer.parseInt((String)e.getCampo("squadraOspite"))));
	String currentStadio = stadio.referenceID((Integer.parseInt((String)e.getCampo("stadio"))));
    
	//------------------------------------------------------------------------------------------------------------------------
%>    
      <%=home %> vs <%=away%> 
      </h2>
      <h4><%=currentStadio %></h4>
    </div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>

</body>

</html>
