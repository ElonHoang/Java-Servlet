<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class=container>
		<form class="form-horizontal" action='product' method="POST"
			enctype="multipart/form-data">
			<input type="hidden" name="action" value="delete">
			<fieldset>
				<div class="control-group">
					<div class="controls">
						<input type="hidden" id="id" name="id" class="input-xlarge"
							value="${products.getId()}">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${products.getName()}" disabled>
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="price">Price</label>
					<div class="controls">
						<input type="text" id="price" name="price" class="input-xlarge"
							value="${products.getPrice()}" disabled>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="description">Description</label>
					<div class="controls">
						<input type="text" id="description" name="description"
							placeholder="Enter Description" class="input-xlarge"
							value="${products.getDescription()}" disabled>
					</div>
				</div>
				<div class="control-group">
					Option <select name="option">
						<c:forEach var="select" items="${category}">
							<option value="${select.getId()}"
								<c:if test="${products.getCategory().getName() eq select.getName()}"> selected readonly</c:if>>${select.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<img src="uploads/${products.getImage()}" width="100px"> <br>

				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">
							<i class="fa fa-trash" aria-hidden="true"> Delete</i>
						</button>
					</div>
				</div>

			</fieldset>
		</form>
		<br>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-success">
					<a href="./product"><i class="fa fa-arrow-left"
						aria-hidden="true"> Back</i></a>
				</button>
			</div>
		</div>
	</div>
</body>
</html>