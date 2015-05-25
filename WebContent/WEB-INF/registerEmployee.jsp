<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

	<h2> Register New Employee </h2>
	
<hr>
<p class="help-block">
Create new employee accounts here. An email will be sent to let you set up password.</p>
<hr>
	<form role="form" action="registerEmployee.do" method="POST">
		<div class="form-group">
			<label for="userName">User Name</label>
			<input type="text" class="form-control" id="userName" name="userName" value="${form.userName}" placeholder="Employee User Name">
		</div>
		<div class="form-group">
			<label for="firstName">First Name</label>
			<input type="text" class="form-control" id="firstName" name="firstName" value="${form.firstName}" placeholder="Employee First Name">
		</div>
		<div class="form-group">
			<label for="lastName">Last Name</label>
			<input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}" placeholder="Employee Last Name">
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" id="email" name="email" value="${form.email}" placeholder="email&commat;email.com">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="password">Confirm Password</label>
			<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
		</div>
		<button type="submit" class="btn btn-primary" name="action" value="regEmp">Register New Employee</button>
	</form>

<jsp:include page="footer.jsp"></jsp:include>