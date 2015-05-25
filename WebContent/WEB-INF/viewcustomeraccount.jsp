
<jsp:include page="message.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<h2>Information for User: ${customerInfo.userName }</h2>
<hr>

<div class="row">
	<div class="col-md-12">
		<p class="lead">Customer Information </p>
	</div>
	<div class="col-md-12">
		<form method="post" action="ViewCustomerAccount.do">
			<table class="table">
				<tr style="font-weight: bold">
					<td>Name</td>
					<td>FirstName</td>
					<td>LastName</td>
					<td>UserName</td>
					<td>Address</td>
					<td>Cash Balance($)</td>
					<td>Last trading day</td>
					
				</tr>

				<tr>
					<td>${customerInfo.userName }</td>
					<td>${customerInfo.firstName }</td>
					<td>${customerInfo.lastName }</td>
					<td>${customerInfo.addrLine1 }</td>
					<td>${customerInfo.addrLine2 }</td>
					<td style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${customerInfo.cash/100 }"></fmt:formatNumber></td>
					<td>${lastDay }</td>
				</tr>

			</table>

			<br>
			<p class="lead">Customer Funds</p>
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
						<td>${custFunds.shares }</td>
						<td style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${custFunds.price }"></fmt:formatNumber></td>
						<td style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${custFunds.position }"></fmt:formatNumber></td>
					</tr>
				</c:forEach>
			</table>

			<table>
				<tr>
					<td style="width: 300px;height: 40px;"><a href="changePassword.do?id=${customerInfo.customerId}">Change Customer Password </a></td>

				</tr>
				<tr>
					<td style="width: 300px;height: 40px;"><a href="empTransaction.do?id=${customerInfo.customerId}"> View Transaction History
					</a></td>
				</tr>
				<tr>
					<td style="width: 300px;height: 40px;"><a href="emp_depositCheck.do?id=${customerInfo.customerId}">
							Deposit check
					</a></td>
				</tr>
			</table>
		</form>
		</div>
		</div>
				<jsp:include page="footer.jsp"></jsp:include>
		