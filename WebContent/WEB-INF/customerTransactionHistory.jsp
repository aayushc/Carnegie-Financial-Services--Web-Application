<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

<h2> Transaction History</h2>
<hr>
<p class="help-block">
View your transaction history here.
</p>
<hr>

<p class="lead"> Buy And Sell History</p>
<table class="table table-striped">
  <tr style="font-weight:bold">
  <td>Date</td><td>Operation</td><td>Symbol</td><td>Fund Name</td><td>Number of Shares</td><td>Buying price($)</td><td>Total Amount($)</td><td>Status</td>
  </tr>
  <c:forEach var="buyhistory" items="${buySellList }">
  <tr>
  <td>${buyhistory.executeDate }</td>
  <td>${buyhistory.transactionType }</td>
  <td>${buyhistory.symbol }</td>
  <td>${buyhistory.fundname }</td>
  <td  style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="3" maxFractionDigits="3" value="${buyhistory.numberOfShares }"></fmt:formatNumber></td>
  <td style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${buyhistory.buyingPrice }"></fmt:formatNumber></td>
  <td style="text-align: right;padding-right: 40px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${buyhistory.amount }"></fmt:formatNumber></td>
  <td>${buyhistory.status }</td>
  </tr>
  </c:forEach>
</table>
<br>
<br>
<p class="lead"> Deposit And Check History</p>
<table class="table table-striped">
	<tr style="font-weight:bold">
  <td>Date</td><td>Operation</td><td>Total Amount($)</td><td>Status</td>
  </tr>
  <c:forEach var="custFunds" items="${depositList }">
  <tr>
  <td>${custFunds.executeDate }</td><td>${custFunds.transactionType }</td>
  <td style="text-align: right;padding-right: 30px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${custFunds.amount }"></fmt:formatNumber></td>
  <td>${custFunds.status }</td>
  </tr>
  </c:forEach>
</table>

<jsp:include page="footer.jsp"></jsp:include>