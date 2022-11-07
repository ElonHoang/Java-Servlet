<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<c:if test="${orderDt == null }">
<p>No order here</p>
</c:if>
<c:if test="${orderDt != null || orderDt.size() > 0}">
<h3 class="text-danger">Order Success</h3>
<h3>Total : ${total}</h3>
<hr class="dashed">
<c:forEach var="item" items = "${orderDt}">
<h4>OrderID : ${item.getOrder().getId()}</h4>
<h4>ProductID : ${item.getProduct().getId()}</h4>
<h4>Name Product : ${item.getProduct().getName()}</h4>
<h4>Quantity :${item.getQuantity()}</h4>
<h4>Price : ${item.getPrice()}</h4>
<hr class="dashed">
</c:forEach>
</c:if>
</body>
</html>