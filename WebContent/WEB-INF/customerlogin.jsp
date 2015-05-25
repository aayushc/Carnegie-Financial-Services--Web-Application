<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>
<p>
	<form method="post" action="login.do">
		<table>
			<tr>
				<td> UserName: </td>
				<td><input type="text" name="username" value="${form.username}"/></td>
			</tr>
			<tr>
				<td> Password: </td>
				<td><input type="password" name="password" value=""/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="button" value="Login"/>
				</td>
			</tr>
			<tr>
	        <h4>New User?<a href="register.do">Register now!</a></h4>
		</table>
	</form>
</p>