<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail History</title>
</head>
<body>
<c:if test="${detail == null || detail.size() == 0}">
<h3>Detail Empty !</h3>
</c:if>

<c:if test="${detail != null}">
<c:forEach var="item" items = "${detail}">
<h3>ID :${item.getOrder().getId()}</h3>
<h4>Name :${item.getProduct().getName()}</h4>
<h4>Price : ${item.getPrice()}</h4>
<h4>Quantity : ${item.getQuantity()}</h4>
</c:forEach>
</c:if>
</body>
</html>