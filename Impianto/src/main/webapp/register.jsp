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
        <li><a href="index.jsp">Home</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Projects</a></li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid">    
	<div style="padding : 10px;">
		<form action="register" method="post">
			<div class ="form-group col-xs-7">
				<label>Username</label>
				<input class="form-control" name = "username" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<label>Nome</label>
				<input class="form-control" name = "nome" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<label>Cognome</label>
				<input class="form-control" name = "cognome" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<label>Email</label>
				<input class="form-control" type="email" name = "mail" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<label>Password</label>
				<input class="form-control" type="password" name = "psw" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<label>Confirm Password</label>
				<input type="password" class="form-control" name = "psw2" required="true" autocomplete = "off">
			</div>
			<div class ="form-group col-xs-7">
				<input type="submit" class="btn btn-default" value ="REGISTER">
			</div>
		</form>
	</div>
</div>
</body>
</html>