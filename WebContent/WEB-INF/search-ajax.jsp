<?xml version="1.0" encoding="utf-8"?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% response.setHeader("Content-Type","text/xml"); %>

<results>
<c:forEach var="name" items="${nameList}">
	<result>
		<id>${name.fundId}</id>
		<firstNames><c:out value="${name.name}"/></firstNames>
	</result>
</c:forEach>
</results>