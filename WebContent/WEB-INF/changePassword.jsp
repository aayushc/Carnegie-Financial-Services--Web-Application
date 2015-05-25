<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

<h2> Change Password For user: <c:out value="${username.userName }" /><!--add user attribute here--></h2>	
<hr>
<p class="help-block">
Create new password here.</p>
<hr>

			<form role="form" action="changePassword.do?id=${username.customerId }" method="POST">
				<div class="form-group">
					<label for="oldPassword">New Password</label>
					<input type="password" class="form-control" id="oldPassword" name="newPassword" placeholder="New Password">
				</div>
				<div class="form-group">
					<label for="confirmPassword">Confirm Password</label>
					<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
				</div>
				<button type="submit" class="btn btn-primary" name="action" value="changePass">Change Password</button>
			</form>
				<table>
				<tr>
					<td style="width: 300px;height: 40px;"><a href="ViewCustomerAccount.do?id=${username.customerId}">Go Back </a></td>

				</tr>
				
			</table>

<jsp:include page="footer.jsp"></jsp:include>