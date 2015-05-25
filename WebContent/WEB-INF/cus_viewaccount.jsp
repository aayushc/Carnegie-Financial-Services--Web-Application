<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<%@page import="java.text.DecimalFormat"%>
<h2>Welcome, ${accountInfo.firstName} ${accountInfo.lastName} !</h2>
<hr>
<p class="help-block">Thank you for choosing Carnegie Financial
	Service. Enjoy your day!</p>
<hr>

<h3 style="padding-left: 20px;">Account Information</h3>

<form method="post" action="Cus_ViewAccount.do">
	<table class="table">
		<tr style="font-weight: bold">
			<td>Name</td>
			<td>FirstName</td>
			<td>LastName</td>
			<td>Address1</td>
			<td>Address2</td>
			<td>Cash Balance($)</td>
			<td>Last trading day</td>
		</tr>
<%
	DecimalFormat nf = new DecimalFormat("#,##0.00");
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	Double cash = (Double) request.getAttribute("cash");
%>

		<tr>
			<td>${accountInfo.userName }</td>
			<td>${accountInfo.firstName}</td>
			<td>${accountInfo.lastName }</td>
			<td>${accountInfo.addrLine1 }</td>
			<td>${accountInfo.addrLine2 }</td>
			<td style="text-align: right; padding-right: 40px;"><fmt:formatNumber
					type="number" minFractionDigits="2" maxFractionDigits="2"
					value="${accountInfo.cash/100 }"></fmt:formatNumber></td>
			<td>${lastDay }</td>
		</tr>

	</table>

	<br>
	<h3>Existing Funds</h3>
	<table class="table">
		<tr style="font-weight: bold">
			<td>FundId</td>
			<td>FundName</td>
			<td>Number of Shares</td>
			<td>Price($)</td>
			<td>Value Of Position($)</td>
		</tr>
		<c:forEach var="custFunds" items="${allCustFunds }">
			<tr>
				<td>${custFunds.fundId }</td>
				<td>${custFunds.name }</td>
				
				<td style="text-align: right; padding-right: 40px;"><fmt:formatNumber
						type="number" minFractionDigits="2" maxFractionDigits="2"
						value="${custFunds.shares}"></fmt:formatNumber></td>
				<td style="text-align: right; padding-right: 40px;"><fmt:formatNumber
						type="number" minFractionDigits="2" maxFractionDigits="2"
						value="${custFunds.price }"></fmt:formatNumber></td>
				<td style="text-align: right; padding-right: 40px;"><fmt:formatNumber
						type="number" minFractionDigits="2" maxFractionDigits="2"
						value="${custFunds.position }"></fmt:formatNumber></td>
			</tr>
		</c:forEach>
	</table>
</form>


<jsp:include page="footer.jsp"></jsp:include>