<%@page import="databean.CustomerBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<h2>Request Check</h2>
<hr>
<p class="help-block">Request checks here. The check will be mailed
	to you.</p>
<hr>
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


<%
	
%>
<form name="cus_requestCheck.do" method="POST" role="form"
	class="form-inline">
	<div class="form-group">
		<label for="Request Check Value">Request Check Value</label> <span><label>
				$ &nbsp&nbsp</label><input type="text" name="withdraw" value=""
			sclass="form-control" id="checkValue"></span>

	</div>
	<br></br>
	<div>
		<button type="submit" name="action" value="requestCheck"
			class="btn btn-default">Submit</button>
	</div>

</form>

</div>


</div>
</div>



</div>
<div class="col-md-2 column"></div>
</div>
</div>








<jsp:include page="footer.jsp"></jsp:include>