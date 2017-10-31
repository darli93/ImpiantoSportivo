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
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="jumbotron">
					<form action="Login" method="post">
						<div class="form-group">
							<label>Please enter your username</label> 		
							<input type="text" class="form-control" name="un">
						</div>
						<div class="form-group">
							<label>Please enter your password</label>
							<input type="password" class="form-control" name="pw">
						</div>
							<div class="col-xs-12 text-center">
								<input type="submit" class="btn btn-default" value="Login">
								
							</div>	
					</form>
						 <% String infoLog = (String)request.getAttribute("InfoLog"); %>
						 <% if(infoLog != null) {%>
						 <%=infoLog%>
						 <%}%>
						 
						 <a href="register.jsp"><button class="btn btn-default">Register</button></a>
				</div>
			</div>
					
		</div>
		
	</div>
</body>
</html>