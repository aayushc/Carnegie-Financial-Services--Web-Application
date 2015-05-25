<?xml version="1.0" encoding="utf-8"?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% response.setHeader("Content-Type","text/xml"); %>

<results>
<c:forEach var="fund" items="${fundList}">
	<result>
		<fundId>${fund.fundId}</fundId>
		<fundName><c:out value="${fund.name}"/></fundName>
		<fundSymbol><c:out value="${fund.symbol}"/></fundSymbol>
	</result>
</c:forEach>
</results>