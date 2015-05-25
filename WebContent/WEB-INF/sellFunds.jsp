<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

<h2>Sell Funds</h2>
<hr>
<p class="help-block">
Sell your funds here.
</p>
<hr>
<p class ="lead">Your Funds</p>
<form class="form-inline" method="post" action="sellFund.do">

<table class="table table-striped">
  <tr style="font-weight:bold">
  <td>Select Fund to Sell</td><td>Fund Name </td><td>Symbol</td><td>Current Share Price($)</td><td>Number of Shares</td>
  </tr>
  <c:forEach var="funds" items="${allCustFunds }">
  <tr>
  <td><div class="radio">
  <label>
    <input type="radio" name="fundName" id="blankRadio1" value="${funds.name }" aria-label="...">
  </label>
</div></td><td>${funds.name }</td><td>${funds.symbol }</td><td style="text-align: right;padding-right: 40px;">${funds.price }</td><td style="text-align: right;padding-right: 40px;">${funds.shares }</td>
  </tr>
 </c:forEach>
</table>
<hr>
<div class="text-center" style="display:flex"> <h4 style="width:250px">Number of Shares to Sell:</h4>

  <div class="form-group" style="margin-right: 20px;">
    <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
    <div class="input-group">
      <input type="text" class="form-control"  name="numberOfShares" id="exampleInputAmount" placeholder="Number of Shares">
     <!--  <div class="input-group-addon">.00</div> -->
    </div>
  </div>
  <button type="submit" name=" button" class="btn btn-primary">Sell Shares</button>
  </div>
</form>



<jsp:include page="footer.jsp"></jsp:include>