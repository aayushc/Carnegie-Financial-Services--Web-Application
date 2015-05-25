<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<%@page import="java.text.DecimalFormat"%>
<jsp:include page="message.jsp"></jsp:include>
<h2>Buy Funds</h2>
<hr>
<p class="help-block">Click on the checkbox to select the fund you
	want to buy. You can select from all existing funds and your existing
	funds. Input the amount at the bottom.</p>
<hr>

<p class="lead">Search The Fund</p>
<!-- change action into source page action do -->
<form class="form-inline" id="searchSource" name="search"
	action="buyFund.do" method="post">
	<jsp:include page="searchFundForm.jsp"></jsp:include>
	<a class="btn btn-primary" href="buyFund.do">Clear Search</a> <input
		style="visibility: hidden" type="submit" name="button" value="." />
	</div>
</form>

<hr>
<p class="lead">All Funds</p>

<form method="post" action="buyFund.do">
	<table class="table table-striped">
		<tr style="font-weight: bold">
			<td>Select Fund to Buy</td>
			<td>Symbol</td>
			<td>Fund Name</td>
			<td>Share Price($)</td>
		</tr>
		<c:forEach var="funds" items="${allFunds }">
			<tr>
				<td><div class="radio">
						<label> <input type="radio" name="fund" id="blankRadio1"
							value="${funds.name }" aria-label="...">
						</label>
					</div></td>
				<td>${funds.symbol }</td>
				<td>${funds.name }</td>
				<td style="text-align: right; padding-right: 100px">${funds.price }</td>
			</tr>
		</c:forEach>
	</table>


	<hr>
	<p class="lead">Your Funds</p>
	<table class="table table-striped">
		<tr style="font-weight: bold">
			<td>Select to Buy more Funds</td>
			<td>Symbol</td>
			<td>Fund Name</td>
			<td>Share Price($)</td>
			<td>Number Of Shares</td>
		</tr>
		<script>
			Total('${allCustFunds }')
		</script>
		<c:forEach var="custFunds" items="${allCustFunds }">
			<tr>
				<td><div class="radio">
						<label> <input type="radio" name="fund" id="blankRadio1"
							value="${custFunds.name }" aria-label="...">
						</label>
					</div></td>
				<td>${custFunds.symbol }</td>
				<td>${custFunds.name }</td>
				<td style="text-align: right; padding-right: 40px;">${custFunds.price }</td>
				<td style="text-align: right; padding-right: 50px;">${custFunds.shares }</td>
			</tr>
		</c:forEach>
	</table>
	
	<p class="lead">
	Your Current Account Balance:


	<%
	DecimalFormat nf = new DecimalFormat("#,##0.00");
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	Double cash = (Double) request.getAttribute("cash");
%>
	$
	<%=nf.format(cash)%>
</p>
	<hr>
	<div class="text-center" style="display: flex">
		<h4 style="width: 300px; text-align: left;">Total amount you want
			to invest:</h4>
		<div class="form-group">
			<label class="sr-only" for="exampleInputAmount">Amount (in
				dollars)</label>
			<div class="input-group" style="width: 210px;">
				<div class="input-group-addon">$</div>
				<input type="text" class="form-control" name="amount"
					id="exampleInputAmount" placeholder="Amount"
					style="text-align: right;">
				<!-- <div class="input-group-addon">.00</div> -->
			</div>
		</div>
	</div>
	<button type="submit" name=" button" class="btn btn-primary">Buy
		Fund</button>
</form>


<jsp:include page="footer.jsp"></jsp:include>