<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<style>
body {
margin: 0;
     background: url('img/background.jpg');         
     background-attachment: fixed;

     margin: 0;
     background-size: 1440px 800px;
     background-repeat:no-repeat;
    display: compact;
     font: 13px/18px "Helvetica Neue", Helvetica, Arial, sans-serif;
}

#nav.affix {
    position: fixed;
    top: 0;
  
}


</style>


  <meta charset="utf-8">
  <title>Carnegie Financial Services</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/datepicker.css" rel="stylesheet">

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="img/favicon.png">
  
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
	
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h1>
Carnegie Financial Service			</h1>
		</div>
	</div>
	<hr>
	<div class="row clearfix">
		
	
	
		<div class="col-md-6 column">
				<jsp:include page="message.jsp"></jsp:include>
		<div class="jumbotron well">
		
<form class="form-horizontal" method="post" action="login.do">
				<div class="form-group">
					 <label for="exampleInputEmail1">Username</label>
					 
					 <div class="input-group">
  <span class="input-group-addon" id="sizing-addon2">@</span>
  <input type="text" class="form-control" name= "userName" placeholder="Username" aria-describedby="sizing-addon2">
</div>

				</div>
				
				<div class="form-group">
					 <label for="exampleInputPassword1">Password</label><input type="password" name = "password" class="form-control" id="exampleInputPassword1" />
				</div>
				<!-- <div class="form-group">
					 <label for="exampleInputFile">Login As</label>
					 <div class="btn-group">
  <button class="btn btn-default btn-sm dropdown-toggle" name="typeButton" type="button" data-toggle="dropdown" aria-expanded="false">
    Choose Your Role <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
   <li><a href="#">Customer</a></li>
    <li><a href="#">Employee</a></li>
  </ul>
</div> -->
					<p class="help-block">
						One entry for both customers and employees
					</p>
				 <button type="submit" name="button" class="btn btn-default">Login</button>
			</form>
			</div>
		</div>
		
	</div>
</div>

			
</body>
</html>