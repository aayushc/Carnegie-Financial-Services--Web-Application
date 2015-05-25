<%@page import="databean.CustomerBean"%>
<%@page import="databean.EmployeeBean"%>
<%@page import="java.util.List"%>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<h2>Deposit Check for User: ${username.userName }</h2>
<hr>
<p class="help-block">Deposit check here.</p>
<hr>

<form action="emp_depositCheck.do?id=${username.customerId }"
	method="POST" role="form" class="form-inline">
	<div class="form-group">
		<label for="Request Check Value">Deposit Check Value: </label><br></br>

		<span> <label> $</label> <input type="text" name="deposit"
			class="form-control" id="checkValue" value="">
		</span>

	</div>
	<br></br>
	<div>
		<button type="submit" name="action" value="deposit"
			class="btn btn-default">Submit</button>
	</div>

</form>

<table>
	<tr>
		<td style="width: 300px; height: 40px;"><a
			href="ViewCustomerAccount.do?id=${username.customerId }"> Go Back
		</a></td>

	</tr>

</table>


<jsp:include page="footer.jsp"></jsp:include>