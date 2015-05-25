<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	background-repeat: no-repeat;
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

<!-- nazh : to create research funds graphics from amcharts, don't forget to include it in header.jsp -->
<link rel="stylesheet" href="amcharts/style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
<script src="amcharts/amstock.js" type="text/javascript"></script>
<script src="amcharts/themes/light.js" type="text/javascript"></script>

<!-- <script type="text/javascript">
			AmCharts.makeChart("chartdiv1", {

				type: "stock",

				pathToImages: "amcharts/images/",
				
				dataDateFormat: "YYYY/MM/DD",

				dataSets: [{
					color: "#b0de09",
					fieldMappings: [{fromField: "value",toField: "value"}],
					dataProvider: [
						<c:forEach var="dailyfundlist" items="${fundDaily1}">
							{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",val: ${dailyfundlist.price}},
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
		</script> -->

<script type="text/javascript">	
			var chart = AmCharts.makeChart("chartdiv", {
				type: "stock",
			    "theme": "light",
			    pathToImages: "amcharts/images/",
			    dataDateFormat: "YYYY/MM/DD",
	
				dataSets: [{
						title: "<c:out value="${fundChartName1}" />",
						fieldMappings: [{
							fromField: "value",
							toField: "value"
						}, {
							fromField: "volume",
							toField: "volume"
						}],
						dataProvider: [<c:forEach var="dailyfundlist" items="${fundDaily1}">
											{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",
											 value: ${dailyfundlist.price/100},
											 volume:${dailyfundlist.numberOfShares/1000}},
									   </c:forEach>],
						categoryField: "date"
					}
					<c:if test='${!(empty fundChartName2)}'>
					,{
						title: "<c:out value="${fundChartName2}" />",
						fieldMappings: [{
							fromField: "value",
							toField: "value"
						}, {
							fromField: "volume",
							toField: "volume"
						}],
						dataProvider: [<c:forEach var="dailyfundlist" items="${fundDaily2}">
										{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",
							 			value: ${dailyfundlist.price/100},
							 			volume: ${dailyfundlist.numberOfShares/1000}},
									   </c:forEach>],
						categoryField: "date"
					}
					</c:if>
					<c:if test='${!(empty fundChartName3)}'>
					,{
						title: "<c:out value="${fundChartName3}" />",
						fieldMappings: [{
							fromField: "value",
							toField: "value"
						}, {
							fromField: "volume",
							toField: "volume"
						}],
						dataProvider: [<c:forEach var="dailyfundlist" items="${fundDaily3}">
										{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",
							 			 value: ${dailyfundlist.price/100},
							 			 volume: ${dailyfundlist.numberOfShares/1000}},
									   </c:forEach>],
						categoryField: "date"
					}
					</c:if>
					<c:if test='${!(empty fundChartName4)}'>				
					,{
						title: "<c:out value="${fundChartName4}" />",
						fieldMappings: [{
							fromField: "value",
							toField: "value"
						}, {
							fromField: "volume",
							toField: "volume"
						}],
						dataProvider: [<c:forEach var="dailyfundlist" items="${fundDaily4}">
										{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",
							 			 value: ${dailyfundlist.price/100},
							 			 volume: ${dailyfundlist.numberOfShares/1000}},
									   </c:forEach>],
						categoryField: "date"
					}
					</c:if>
					<c:if test='${!(empty fundChartName5)}'>						
					,{
						title: "<c:out value="${fundChartName5}" />",
						fieldMappings: [{
							fromField: "value",
							toField: "value"
						}, {
							fromField: "volume",
							toField: "volume"
						}],
						dataProvider: [<c:forEach var="dailyfundlist" items="${fundDaily5}">
										{date: "<fmt:formatDate pattern="yyyy/MM/dd" value="${dailyfundlist.priceDate}" />",
							 			 value: ${dailyfundlist.price/100},
							 			 volume: ${dailyfundlist.numberOfShares/1000}},
									   </c:forEach>],
						categoryField: "date"
					}
					</c:if>
				],
	
				panels: [{
	
						showCategoryAxis: false,
						title: "Value",
						percentHeight: 70,
	
						stockGraphs: [{
							id: "g1",
	
							valueField: "value",
							comparable: true,
							compareField: "value",
							balloonText: "[[title]]:<b>[[value]]</b>",
							compareGraphBalloonText: "[[title]]:<b>[[value]]</b>"
						}],
	
						stockLegend: {
							periodValueTextComparing: "[[percents.value.close]]%",
							periodValueTextRegular: "[[value.close]]"
						}
					} ,
	
					{
						title: "Volume",
						percentHeight: 30,
						stockGraphs: [{
							valueField: "volume",
							type: "column",
							showBalloon: false,
							fillAlphas: 1
						}],
	
	
						stockLegend: {
							periodValueTextRegular: "[[value.close]]"
						}
					} 
				],
	
				chartScrollbarSettings: {
					graph: "g1"
				},
	
				chartCursorSettings: {
					valueBalloonsEnabled: true,
			        fullWidth:true,
			        cursorAlpha:0.1,
			         valueLineBalloonEnabled:true,
			         valueLineEnabled:true,
			         valueLineAlpha:0.5
				},
	
				periodSelector: {
					position: "left",
					periods: [{
						period: "MM",
						selected: true,
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
				},
	
				dataSetSelector: {
					position: "left"
				}
			});
		</script>
<!--  nazh : end of research funds graphics from amcharts, don't forget to include it in header.jsp -->
<!-- nazh : start of javascript for search autocomplete functions, don't forget to include it in header.jsp -->
<script type="text/javascript" src="ajax.js"> </script>
<script type="text/javascript" src="searchFund.js"> </script>
<!--  nazh : end of javascript for search autocomplete functions, don't forget to include it in header.jsp -->
</head>

<body onLoad="init('${allFundnames }');">



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
		<div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#about">About</a>
                    </li>
                    <li><a href="#services">Services</a>
                    </li>
                    <li><a href="#contact">Contact</a>
                    </li>
                    <li><a href="logout.do">Logout</a>
                   </li>
                    <li><a href="Cus_ViewAccount.do">Hello, ${customer.firstName}
					${customer.lastName}!</a>
                   </li>
                   
                </ul>
            </div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
				<li><a id="logo-outer" href="login.do"><img alt="logo"
						id="logo" src="img/logo.png">Carnegie Financial Service</a></li>


			</ul>





			
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>


	

	



	<!-- Header ends -->
	<div id="bar">
	haha
	</div>
	<br>
	<div class="container">

		<div class="row clearfix">
			<div class="col-md-2" id="nav">
				<br> <br> <br> <br>

				<ul id="sidebar" class="nav nav-stacked affix">





					<div class="list-group">
            <a class="list-group-item" href="buyFund.do" > <span class="glyphicon glyphicon-import"></span>&nbsp;Buy Fund</a> 
            <a class="list-group-item" href="sellFund.do" > <span class="glyphicon glyphicon-export"></span>&nbsp;Sell Fund</a>
            <a class="list-group-item" href="transaction.do" > <span class="glyphicon glyphicon-time"></span>&nbsp;Transaction History</a> 
            <a class="list-group-item" href="cus_requestCheck.do" > <span class="glyphicon glyphicon-usd"></span>&nbsp;Request Check</a>
            <a class="list-group-item" href="researchFund.do" > <span class="glyphicon glyphicon-search"></span>&nbsp;Research Fund</a>
            <a class="list-group-item" href="Cus_ViewAccount.do" > <span class="glyphicon glyphicon-user"></span>&nbsp;Account Information</a>
            <a class="list-group-item" href="cus_changeOwnPassword.do" > <span class="glyphicon glyphicon-user"></span>&nbsp;Change Password</a>
            
            <a class="list-group-item" href="logout.do" > <span class="glyphicon glyphicon-off"></span>&nbsp;Log Out</a>
				
			</div>

				</ul>



			</div>
			<div class="col-md-10  ">
				<div class="jumbotron well">