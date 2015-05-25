<%@page import="databean.EmployeeBean"%>
<%@page import="java.util.List"%>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

<h2>Transition Day</h2>
<hr>
<p class="help-block">
Please input new prices for each fund. The new date cannot be in the past.
</p>
<hr>

    <%@page import="databean.FundPriceHistoryBean"%>
    <%@page import= "java.text.SimpleDateFormat" %>
<%@page import= "java.text.DecimalFormat" %>
<%@page import="java.util.*" %>
<%@page import="databean.FundPriceHistoryBean"%>


		
		<% 
                
                Date lastday = (Date)request.getAttribute("lastday"); 
                ArrayList<FundPriceHistoryBean> prices = (ArrayList<FundPriceHistoryBean>)request.getAttribute("prices"); 
        		ArrayList<String> names = (ArrayList<String>)request.getAttribute("names");
        		ArrayList<String> symbols = (ArrayList<String>)request.getAttribute("symbols");
            	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                DecimalFormat dfAmount = new DecimalFormat("###,###,###,###,###,###,##0.00"); 
                
                %>
		
		
<form method="post" action="e_transitionday.do">		
		<div class="col-md-6" align="center">
									Last Transition Day<br/>
									<%if(lastday == null){ %>
									N/A
									<%}else{ %>
									<%=sdf.format(lastday) %>
									<%} %>
								</div>
								<div>Enter the New Transition Day Here</div>
								
								
			<div class="span5 col-md-5" id="sandbox-container"><input type="text" name = "odate" class="form-control" value="<%if(lastday == null){ %>
									N/A
									<%}else{ %>
									<%=sdf.format(lastday) %>
									<%} %>"></div>
<script type="text/javascript">
$('#sandbox-container input').datepicker({
	startDate: '-0d'

});
</script>


                
                

			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>
							Id
						</th>
						<th>
							Name
						</th>
						<th>
							Symbol
						</th>
						<th>
							Old Price($)
						</th>
						<th>
							New Price($)
						</th>
					</tr>
				</thead>
				<tbody>
				
				<%
			                   if(names != null && names.size()!=0){
		                       		for(int i=0; i<prices.size();i++){ 
		                       			System.out.println(prices.size());
		                       %>
				 
					<tr>
						<td>
							<%=prices.get(i).getFundId()%>
						</td>
						<td>
							<%=names.get(i)%>
						</td>
						<td>
							<%=symbols.get(i)%>
						</td>
						<td>
						<%if(dfAmount.format((double)prices.get(i).getPrice()/100).equals("-0.01")){ %>
									N/A
									<%}else{ %>
									<%=dfAmount.format((double)prices.get(i).getPrice()/100) %>
									<%} %>
						</td>
						<td>
							<input type="text" name="price_<%=prices.get(i).getFundId()%>"/>
						</td>
					</tr>
					
					 <%
		                        	}
		                       } else {
		                       %>
		                      	<tr>
                        			<td colspan="5" align="center">No funds, create some now!</td>
                    			</tr>
		                       <%}
		                    	%>
					
					
					
					
				</tbody>
			</table>
			<button type="submit" name="transbutton" class="btn btn-default">Submit</button>
		</form>
	
	


<jsp:include page="footer.jsp"></jsp:include>

  



