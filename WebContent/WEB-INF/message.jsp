<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<c:if test="${not empty errors}">
<div class="panel panel-danger">
<div class="panel-heading">
    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>  &nbsp; &nbsp;ERROR
  </div>
<div class="panel-body">
<c:forEach var="error" items="${errors}">


	<p style="font-size:medium; color:red">
		${error}</br>
	</p>
</c:forEach>  </div>
  </div>
  </c:if>
  
<c:if test="${not empty message}">
<div class="panel panel-success">

<div class="panel-heading">
    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  &nbsp; &nbsp;SUCCESS
  </div>
<div class="panel-body">
<c:forEach var="message" items="${message}">


	<p style="font-size:medium; color:green">
		${message}
	</p>
</c:forEach>  </div>
  </div>
  </c:if>
  </div>
  