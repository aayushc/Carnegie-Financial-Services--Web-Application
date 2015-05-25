<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                <div class = "row">
                <h2>Hello, ${employee.firstName}
					${employee.lastName}!</h2>
					<hr>
					<p class ="help-block">How are you today?</p>
					
					<p class ="help-block">You can use functions on the right or start with interested users below:</p>
					</div>
					<hr>
                <div class="row">
                    <div class="col-md-12">
                        <p class="lead">List of Customers</p>
                    </div>
					
					<div class="col-md-12">
						<form method="post" action="ViewCustomer.do">
	                        <table class="table">
	                            
	                                <tr>
	                                    <td>UserName</td>
	                                    <td>Customer First Name</td>
	                                    <td>Customer Last Name</td>
	           
	                                </tr>
	                            
	                            <tbody>
	                                <c:forEach var="customers" items="${customerList}">
	                                	<tr>
	                                		<td><a href= "ViewCustomerAccount.do?id=${customers.customerId}">${customers.userName}</a></td>
	                                		<td>${customers.firstName}</td>
											<td>${customers.lastName}</td>
											                              		
	                                	</tr>
	                                </c:forEach>
	                            </tbody>
	                        </table>
                        </form>
                    </div>
                    </div>
                    		<jsp:include page="footer.jsp"></jsp:include>
                    