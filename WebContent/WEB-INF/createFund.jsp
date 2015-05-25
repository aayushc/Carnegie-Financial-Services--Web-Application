<%@page import="databean.EmployeeBean"%>
<%@page import="java.util.List"%>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<h2>Create Fund</h2>
<hr>
<p class="help-block">
Create new funds here. Please input name and symbol. New funds have no price until set in transition day.
</p>
<hr>

<%-- <%
		String message = (String) request.getAttribute("message");
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (message != null) {
%>		
			<h3 style="color:red"> <%= message %> </h3>
<%		
		}
%>		 --%>
			<form action="emp_createFund.do" method="POST" role="form" class="form-inline">
				<%-- <table style="border-collapse:collapse">
						<tr>
							<td style="font-size: x-large">Fund Name: </td>
							<td>
								<input type="text" name="fundName" value="${form.fundName}" class="form-control" id="checkValue"/>
							</td>
						</tr>
					<tr>
						<td style="font-size: x-large">Symbol: </td>
						<td>
					 		 <input type="text" name="fundSymbol" value="${form.fundSymbol}" class="form-control" id="checkValue"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							 <button type="submit" name="action" value="createFund" class="btn btn-default">Submit</button>
						</td>
					</tr>
				
				</table> --%>
				
				
				<div class="form-group">
					 <label for="Fund Name">Fund Name: </label><br></br>
					 <span><input type="text" name="fundName" value="${form.fundName}" class="form-control" id="checkValue"></span>
					 
				</div><br></br>
				<div class="form-group">
					 <label for="Ticker">Symbol: </label><br></br>
					 <span><input type="text" name="fundSymbol" value="${form.fundSymbol}" class="form-control" id="checkValue"></span>
					 
				</div><br></br>
				<div>
					<button type="submit" name="action" value="createFund" class="btn btn-default">Submit</button>
				</div>
				 
				 
				 
			</form>

		<jsp:include page="footer.jsp"></jsp:include>

		
		