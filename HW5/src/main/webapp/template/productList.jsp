<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<form action="product" method = "POST"> 
	<div class="input-group">
		<input type="text" class="form-control" id="txtSearch"
			name="txtSearch" placeholder="Search this blog" value="${txt}">
		<div class="input-group-append">
			<button class="btn btn-secondary" type="submit">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</div>
	</form>
	<br>
	<table class="table" enctype="multipart/form-data">
		<a class="btn btn-primary" href="<c:url value ='add'></c:url>"><i
			class="fa fa-plus" aria-hidden="true"> Add</i></a>
		<c:if test="${products.size() == 0 || products == null}">
			<h3>
				<i class="fa fa-ban" aria-hidden="true" style="color: red;"> No
					Products !</i>
			</h3>
		</c:if>
		<c:if test="${products.size() > 0}">

			<thead>
				<th>Id</th>
				<th>Name</th>
				<th>CategoryId</th>
				<th>Price</th>
				<th>Image</th>
				<th>Description</th>
			</thead>
			<tbody>
				<c:forEach var="pro" items="${products}" varStatus="loop">
					<tr>
						<td scope="row">${pro.getId()}</td>
						<td>${pro.getName()}</td>
						<td>${pro.getCategory().getName()}</td>
						<td>${pro.getPrice()}</td>
						<c:if
							test="${pro.getImage().length() == 0 || pro.getImage() == null }">
							<td>NULL</td>
						</c:if>
						<c:if test="${pro.getImage().length() > 0}">
							<td><img src="uploads/${pro.getImage()}"
								width="150" height="186" /></td>
						</c:if>
						<td>${pro.getDescription()}</td>
						<td><a href="edit?id=${pro.getId()}">Edit</a></td>
						<td><a href="delete?id=${pro.getId()}">Delete</a></td>
					</tr>
				</c:forEach>
		</c:if>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${currentPage > 1}">
				<li class="page-item"><a class="page-link"
					href="product?index=${currentPage - 1}">Previous</a></li>
			</c:if>
			<c:forEach begin="1" end="${endPage}" var="i">
				<li class="${currentPage == i ? " page-item active" : "" }"><a
					class="page-link" href="product?index=${i}">${i}</a></li>
			</c:forEach>
			<c:if test="${currentPage < endPage}">
				<li class="page-item"><a class="page-link"
					href="product?index=${currentPage + 1}">Next</a></li>
			</c:if>
		</ul>
	</nav>
	<script>
		function searchByName(param){
			var search = param.value;
			$.ajax({
				url: "/HW5/product",
				type="GET",
				data: {
					txtSearch: search
				},
				success: function (data){
					
				}
				error
			})
			
		}
	</script>
</body>
</html>
