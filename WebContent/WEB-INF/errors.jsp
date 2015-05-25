<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="error-list.jsp"></jsp:include>

<c:forEach var="error" items="${errors }">
	<p style="font-size:medium; color:red">
		${error }</br>
	</p>
</c:forEach>



<jsp:include page="footer.jsp"></jsp:include>
