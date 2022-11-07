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
	<c:if test="${products.size() == 0 || products == null}">
		<h3>
			<i class="fa fa-ban" aria-hidden="true" style="color: red;"> No
				Products !</i>
		</h3>
	</c:if>
	<c:if test="${products.size() > 0}">
	<div class="row" enctype="multipart/form-data">
	<c:forEach var="pro" items="${products}" >
			<div class="col-md-4 ftco-animate fadeInUp ftco-animated">
				<div class="block-7">
					<div class="img"
						><img src="uploads/${pro.getImage()}" /></div>
					<div class="text-center p-4">
						<span class="excerpt d-block">${pro.getName()}</span> <span class="price"><sup>$</sup>
							<span class="number">${pro.getPrice()}</span> <sub>/USD</sub></span>
						
						<a href="home?action=addCart&id=${pro.getId()}" class="btn btn-primary d-block px-2 py-3">Add To Bag</a>
					</div>
				</div>
			</div>		
		</c:forEach>
	</div>
	</c:if>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${currentPage > 1}">
				<li class="page-item"><a class="page-link"
					href="home?index=${currentPage - 1}">Previous</a></li>
			</c:if>
			<c:forEach begin="1" end="${endPage}" var="i">
				<li class="${currentPage == i ? "page-item active" : "" }"><a
					class="page-link" href="home?index=${i}">${i}</a></li>
			</c:forEach>
			<c:if test="${currentPage < endPage}">
				<li class="page-item"><a class="page-link"
					href="home?index=${currentPage + 1}">Next</a></li>
			</c:if>
		</ul>
	</nav>
</body>
</html>