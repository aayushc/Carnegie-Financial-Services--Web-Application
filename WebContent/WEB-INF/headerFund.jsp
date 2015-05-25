<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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


	<!-- Copy Start>
================================================== -->

	<script src="js/jquery.min.js"></script>
	<script class="cssdeck" src="js/bootstrap.min.js"></script> 
	<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="js/typeahead.bundle.js"></script>
	<script>
		$(function() {
			$("#header").load("header.html");
			$("#footer").load("footer.html");
		});
	</script>
	<!-- Copy End>

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
	
	<!-- amcharts data -->
	
	<link rel="stylesheet" href="../amcharts/style.css"	type="text/css">

		<script src="amcharts/amcharts.js" type="text/javascript"></script>
		<script src="amcharts/serial.js" type="text/javascript"></script>
		<script src="amcharts/amstock.js" type="text/javascript"></script>

		<script type="text/javascript">
			AmCharts.makeChart("chartdiv", {

				type: "stock",

				pathToImages: "amcharts/images/",
				
				dataDateFormat: "YYYY-DD-MM",

				dataSets: [{
					color: "#b0de09",
					fieldMappings: [{fromField: "value",toField: "value"}],
					dataProvider: [
						<c:forEach var="dailyfundlist" items="${fundDaily}">
							{date: "${dailyfundlist.priceDate}",val: ${dailyfundlist.price}},
						</c:forEach>],
					fieldMappings: [{fromField: "val",toField: "value"}],
					categoryField: "date"
				}],

				panelsSettings:{
					creditsPosition: "bottom-right"
				},

				panels: [{
					showCategoryAxis: true,
					title: "",
					eraseAll: false,
					labels: [{
						x: 0,
						y: 100,
						text: "Click on the pencil icon on top-right to start drawing",
						align: "center",
						size: 16
					}],

					stockGraphs: [{
						id: "g1",
						valueField: "value",
						bullet: "round",
						bulletColor: "#FFFFFF",
						bulletBorderColor: "#00BBCC",
						bulletBorderAlpha: 1,
						bulletBorderThickness: 2,
						bulletSize: 7,
						lineThickness: 2,
						lineColor: "#00BBCC",
						useDataSetColors: false
					}],


					stockLegend: {
						valueTextRegular: " ",
						markerType: "none"
					},

					drawingIconsEnabled: true
				}],

				chartScrollbarSettings: {
					graph: "g1"
				},
				chartCursorSettings: {
					valueBalloonsEnabled: true,
					valueLineEnabled:true,
					valueLineBalloonEnabled:true
				},
				periodSelector: {
					position: "bottom",
					periods: [{
						period: "DD",
						count: 10,
						label: "10 days"
					}, {
						period: "MM",
						count: 1,
						label: "1 month"
					}, {
						period: "YYYY",
						count: 1,
						label: "1 year"
					}, {
						period: "YTD",
						label: "YTD"
					}, {
						period: "MAX",
						label: "MAX"
					}]
				}
			});
		</script>
	
	<!-- end of amcharts data -->
</head>

<body>


	
<script src="js/handlebars-v2.0.0.js"></script>
<Nav  class = "navbar navbar-default navbar-fixed-top"  role = "navigation" > 
  <div  class = "container-fluid" > 
    <!--  Brand and toggle get grouped for better mobile display --> 
    <div  class = "navbar-header " > 
      <button  type = "button"  class = "navbar-toggle collapsed"  data-toggle= "collapse"  data-target= "#bs-example-navbar-collapse-1" > 
        <span  class= "sr-only" > Toggle navigation </span> 
        <span  class= "icon-bar" ></span> 
        <span  class= "icon-bar" ></span> 
        <span  class= "icon-bar" ></span> 
      </button> 
     
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav">
      <li><a id="logo-outer"  href="index.html"><img alt="logo" id="logo" src="img/logo.png"></a></li>
        
        
      </ul>
      
       
  

     
      <div id="signinButton" class="navbar-form navbar-right">
      
					<button type="submit" data-toggle="modal" data-target="#myModal"
						class="btn btn-success">Sign in</button>
				</div>
				<div id="signout" class="navbar-form navbar-right">
       Welcome, Superman!  
       
					<button id="signoutButton"
						class="btn btn-success">Sign out</button>
				</div>
				<ul class="nav navbar-nav navbar-right">
            <li><a href="AccountInfo.html">My Account</a></li>
            
          </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Have you got an account?</h4>
				</div>
				<div class="modal-body">
					<div class="well">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#login" data-toggle="tab">Login</a></li>
							<li><a href="#create" data-toggle="tab">Create Account</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">
							<div class="tab-pane active in" id="login">
								<form id="loginForm" method="POST">
									<fieldset>
										<legend></legend>
										<div class="form-group">
											<label class="control-label">Email address</label>
											<div class="controls">
												<input type="text" class="form-control" name="email" />
											</div>
										</div>

										<div class="form-group">
											<label class="control-label">Password</label>
											<div class="controls">
												<input type="password" class="form-control" name="password" />
											</div>
										</div>


										<div class="form-group">
											<div class="control-group">
												<!-- Button -->
												<div class="controls">
													<button type="submit" class="btn btn-success">Login</button>
												</div>
											</div>
										</div>
										<a href = "#"> Forget Password </a>
									</fieldset>
								</form>
							</div>
							<div class="tab-pane fade" id="create">
								<form id="registrationForm" method="post">
									<legend></legend>
									<div class="form-group">
										<label class="control-label">Username</label>
										<div class="controls">
											<input type="text" class="form-control" name="username" />
										</div>
									</div>

									<div class="form-group">
										<label class="control-label">Email address</label>
										<div class="controls">
											<input type="text" class="form-control" name="email" />
										</div>
									</div>

									<div class="form-group">
										<label class="control-label">Password</label>
										<div class="controls">
											<input type="password" class="form-control" name="password" />
										</div>
									</div>

									<div class="form-group">
										<div class="controls">
											<!-- Do NOT use name="submit" or id="submit" for the Submit button -->
											<button type="submit" class="btn btn-success">Sign
												up</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
<br><br><br>

	<script>
	function createCookie(name,value,days) {
	    if (days) {
	        var date = new Date();
	        date.setTime(date.getTime()+(days*24*60*60*1000));
	        var expires = "; expires="+date.toGMTString();
	    }
	    else var expires = "";
	    document.cookie = name+"="+value+expires+"; path=/";
	}

	function readCookie(name) {
	    var nameEQ = name + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0;i < ca.length;i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1,c.length);
	        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	    }
	    return null;
	}

	function eraseCookie(name) {
	    createCookie(name,"",-1);
	}
	var substringMatcher = function(strs) {
		  return function findMatches(q, cb) {
		    var matches, substrRegex;
		 
		    // an array that will be populated with substring matches
		    matches = [];
		 
		    // regex used to determine if a string contains the substring `q`
		    substrRegex = new RegExp(q, 'i');
		 
		    // iterate through the pool of strings and for any string that
		    // contains the substring `q`, add it to the `matches` array
		    $.each(strs, function(i, str) {
		      if (substrRegex.test(str)) {
		        // the typeahead jQuery plugin expects suggestions to a
		        // JavaScript object, refer to typeahead docs for more info
		        matches.push({ value: str });
		        matches.push({value: '&nbsp;&nbsp;&nbsp;&nbsp;in Diet & Fitness'});
		        matches.push({value: '&nbsp;&nbsp;&nbsp;&nbsp;in Household'});
		        matches.push({value: '&nbsp;&nbsp;&nbsp;&nbsp;in Medicines & Treatments'});
		        matches.push({value: '&nbsp;&nbsp;&nbsp;&nbsp;in Personal Care'});
		        
		      }
		    });
		 
		    cb(matches);
		  };
		};
		 
		var states = ['Aspirin', 'SleepCaps'
		];
		 
		$('#scrollable-dropdown-menu .typeahead').typeahead({
		  hint: false,
		  highlight: false,
		  minLength: 1,
		
		},
		
		
		{
		  name: 'states',
		  displayKey: 'value',
		  source: substringMatcher(states),
		 
		});
		$('input.typeahead').on('typeahead:cursorchanged', function(event, selection) {
			$('#typeaheadinput').val('Aspirin');
			 
			});
		
		$('input.typeahead').on('typeahead:selected', function(event, selection) {
			$('#typeaheadinput').val('Aspirin');
			  // the second argument has the info you want
			  window.location = 'Searchresults.html';
			  
			  // clearing the selection requires a typeahead method
			  $(this).typeahead('setQuery', '');
			});
		
		$('input.typeahead').on('keyup', this, function (event) {
	        if (event.keyCode == 13) {
	        	$('#typeaheadinput').val('Aspirin');
	        	 window.location = 'Searchresults.html';
	        }
	    });
		$('#searchButton').click(function (e) {
			$('#typeaheadinput').val('Aspirin');
			 window.location = 'Searchresults.html';
		});
		$(document)
				.ready(
						
						function() {
							
							var x = readCookie('userName')
				            if (x) {
				            	$('#signinButton').hide();
								
				            }
				            else {
				            	$('#signout').hide();
							
				            }
							$('#signoutButton').click(function(e) {
					            $('#signinButton').show();
					            $('#signout').hide();
					            eraseCookie('userName');
					        });
							
							$('#registrationForm')
									.bootstrapValidator(
											{
												// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													username : {
														message : 'The username is not valid',
														validators : {
															notEmpty : {
																message : 'The username is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The username must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The username can only consist of alphabetical and number'
															},
															different : {
																field : 'password',
																message : 'The username and password cannot be the same as each other'
															}
														}
													},
													email : {
														validators : {
															notEmpty : {
																message : 'The email address is required and cannot be empty'
															},
															emailAddress : {
																message : 'The email address is not a valid'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															different : {
																field : 'username',
																message : 'The password cannot be the same as username'
															},
															stringLength : {
																min : 8,
																message : 'The password must have at least 8 characters'
															}
														}
													}

												}
											})
											.on('success.form.bv', function(e) {
									            // Prevent form submission
									            e.preventDefault();
									            $('#myModal').modal('hide');
									            $('#signinButton').hide();
									            $('#signout').show();
									            createCookie('userName','Superman',7);
											 });
									            
							$('#loginForm')
									.bootstrapValidator(
											{
												// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													username : {
														message : 'The username is not valid',
														validators : {
															notEmpty : {
																message : 'The username is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'The username must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'The username can only consist of alphabetical and number'
															},
															different : {
																field : 'password',
																message : 'The username and password cannot be the same as each other'
															}
														}
													},
													email : {
														validators : {
															notEmpty : {
																message : 'The email address is required and cannot be empty'
															},
															emailAddress : {
																message : 'The email address is not a valid'
															}
														}
													},
													password : {
														validators : {
															notEmpty : {
																message : 'The password is required and cannot be empty'
															},
															different : {
																field : 'username',
																message : 'The password cannot be the same as username'
															},
															stringLength : {
																min : 8,
																message : 'The password must have at least 8 characters'
															}
														}
													}

												}
											})
											.on('success.form.bv', function(e) {
									            // Prevent form submission
									            e.preventDefault();
									            $('#myModal').modal('hide');
									            $('#signinButton').hide();
									            $('#signout').show();
									            createCookie('userName','Superman',7);

									            
									        });
							
							
						
							
						});
	</script>



	<!-- Header ends -->
	<div class="container">
		<div class="row clearfix">
			<br> <br> <br> <br>
			<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">

			<div class="btn-group btn-group-vertical">
				 <button class="btn btn-default" type="button" onclick="location.href='buyFund.do'"> Buy Share</button> 
				 <button class="btn btn-default" type="button" onclick="location.href='SellFund.html'">Sell Share</button>
				 <button class="btn btn-default" type="button" onclick="location.href='transaction.do'">Transaction History</button> 
				 <button class="btn btn-default" type="button" onclick="location.href='RequestCheck.html'">Request Check</button> 
				 <button class="btn btn-default" type="button" onclick="location.href='researchFund.do'">Research Fund</button> 
				 <button class="btn btn-default" type="button" onclick="location.href='AccountInfo.html'">Account Information</button>
			</div> 


			
		</div>
		<div class="col-md-8 column">
