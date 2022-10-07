<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<table class="table">
		<a class="btn btn-primary" href="<c:url value ='addCate'></c:url>"><i
			class="fa fa-plus" aria-hidden="true"> Add</i></a>
		<c:if test="${categories.size() == 0 || categories == null}">
			<h3>
				<i class="fa fa-ban" aria-hidden="true" style="color: red;"> No
					Category !</i>
			</h3>
		</c:if>
		<c:if test="${categories.size() > 0}">
			<thead>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
			</thead>
			<tbody>
				<c:forEach var="cate" items="${categories}" varStatus="loop">
					<tr>
						<td>${cate.getId()}</td>
						<td>${cate.getName()}</td>
						<td>${cate.getDescription()}</td>
						<td><a href="editCate?id=${cate.getId()}">Edit</a></td>
						<td><a href="deleteCate?id=${cate.getId()}">Delete</a></td>
					</tr>
				</c:forEach>
		</c:if>
		</tbody>
	</table>
</body>
</html>