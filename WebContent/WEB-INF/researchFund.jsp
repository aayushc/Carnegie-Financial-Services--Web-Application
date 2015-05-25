<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<h2> Research Fund </h2>
<hr>
<p class="lead">Search The Fund </p>

<!-- change action into source page action do -->
<form class="form-inline" id="searchSource" name="search" action="researchFund.do" method="post">
	<jsp:include page="searchFundForm.jsp"></jsp:include>
		<a class="btn btn-primary" href="researchFund.do">Clear Search</a>
		<input style="visibility:hidden" type="submit" name="button" value="."/>
	</div>
</form>

		
			<table class="table table-bordered">
				<thead>
					<tr>
						<td align="center" style="font-weight:bold">Mutual Fund</td>
						<td align="center" style="font-weight:bold">Symbol</td>
						<td align="center" style="font-weight:bold">Last Closing Price($)</td>
						<td align="center" style="font-weight:bold">Daily Trends($)</td>
						<td align="center" style="font-weight:bold">Daily Growth(%)</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="researchfundlist" items="${researchFunds}">
					<tr
						<c:choose>
							<c:when test="${researchfundlist.priceGrowth > 0}">class=success</c:when>     
							<c:when test="${researchfundlist.priceGrowth == 0}">class=warning</c:when> 
							<c:otherwise>class=danger</c:otherwise>                                       
						</c:choose>
					>
						<td align="center"><a href="researchFund.do?searchId=${researchfundlist.fundId}">${researchfundlist.name}</a></td>
						<td align="center">${researchfundlist.symbol}</td>
						<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${researchfundlist.price}"/></td>
						<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${researchfundlist.priceDifference}"/></td>
						<td align="right"><fmt:formatNumber type="number" maxFractionDigits="2" value="${researchfundlist.priceGrowth}"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<hr>
			
			<p class="lead"> <c:out value="${fundChartName1}" /> Daily Price Chart Compared To Top  
			      <c:choose>
				  	<c:when test="${fundLimit == 1}">${fundLimit} Fund</c:when>
					<c:otherwise>${fundLimit} Funds</c:otherwise>                                       
				  </c:choose>
			</p>
			<div id="chartdiv" style="width:100%; height:500px;"></div>
			<!-- <div id="chartdiv1" style="width:100%; height:500px;"></div> -->

<jsp:include page="footer.jsp"></jsp:include>