<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
#bar{
background-color: #4099ff;
  padding: 0 90px;
height: 65px;
box-shadow: 0px 2px 2px #606366;
}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carnegie Financial Services</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
<!--script src="js/less-1.3.3.min.js"></script-->
<!--append ‘#!watch’ to the browser URL, then refresh the page. -->

<link class="cssdeck" rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css"
	class="cssdeck">
<link rel="stylesheet" href="css/bootstrapValidator.min.css" />
<link href="css/carousel.css" rel="stylesheet">
<link href="css/style-index.css" rel="stylesheet">




<script src="js/jquery.min.js"></script>
<script class="cssdeck" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="js/typeahead.bundle.js"></script>
<script type="text/javascript" src="js/searchAutoComplete.js"></script>



<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="img/favicon.png">
<link href="css/style-header.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/datepicker.css" rel="stylesheet">
	
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



	<script src="js/handlebars-v2.0.0.js"></script>
	<Nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<!--  Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header ">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only"> Toggle navigation </span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
				<li><a id="logo-outer" href="login.do"><img alt="logo"
						id="logo" src="img/logo.png">Carnegie Financial Service</a></li>


			</ul>





			<div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#about">About</a>
                    </li>
                    <li><a href="#services">Services</a>
                    </li>
                    <li><a href="#contact">Contact</a>
                    </li>
                    <li><a href="emp_logout.do">Logout</a>
                   </li>
                    <li><a href="ViewCustomer.do">Hello, ${employee.firstName}
					${employee.lastName}!</a>
                   </li>
                   
                </ul>
            </div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>


	<!-- Modal -->
	
	

	


	<!-- Header ends -->
	<div id="bar">
	haha
	</div>
	<br>
		<div class="container">
			
	<div class="row clearfix">
					<div class="col-md-2 column">
					<br> <br> <br> <br>
          <ul id="sidebar" class="nav nav-stacked affix">

						<div class="list-group">
            <a class="list-group-item" href="ViewCustomer.do" > <span class="glyphicon glyphicon-list"></span>&nbsp;Customer List</a> 
            <a class="list-group-item" href="emp_createFund.do" ><span class="glyphicon glyphicon-plus"></span>&nbsp;Create Fund</a>
            <a class="list-group-item" href="e_transitionday.do" > <span class="glyphicon glyphicon-pencil"></span>&nbsp;Transition	Day</a> 
            <a class="list-group-item" href="registerCustomer.do" > <span class="glyphicon glyphicon-user"></span>&nbsp;Create Customer</a>
            <a class="list-group-item" href="registerEmployee.do" > <span class="glyphicon glyphicon-briefcase"></span>&nbsp;Create Employee</a>
            <a class="list-group-item" href="emp_changePassword.do" > <span class="glyphicon glyphicon-lock"></span>&nbsp;Change Password</a>
            <a class="list-group-item" href="emp_logout.do" > <span class="glyphicon glyphicon-off"></span>&nbsp;Log Out</a>
				
			</div>
						</ul>



					</div>
					<div class="col-md-10 column">
					<div class = " jumbotron well">