<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History</title>
</head>
<body>
<c:if test="${orders == null || orders.size() == 0}">
<h3>History Empty</h3>
</c:if>
<c:if test="${orders != null}">
<c:forEach var="item" items = "${orders}">
<h3><a href="order?action=views&id=${item.getId()}"/>ID : ${item.getId()}</h3>
<h4>UserName :${item.getUser().getUser()}</h4>
<h4>Price : ${item.getTotal_price()}</h4>
<h4>DateTime : ${item.getDatetime()}</h4>
<hr class="dashed">
</c:forEach>
</c:if>
</body>
</html>