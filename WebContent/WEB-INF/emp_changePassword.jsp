<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

			
	<h2> Change Password </h2>
	
<hr>
<p class="help-block">
Change your own password here.</p>
<hr>
			<form role="form" action="emp_changePassword.do" method="POST">
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

<jsp:include page="footer.jsp"></jsp:include>