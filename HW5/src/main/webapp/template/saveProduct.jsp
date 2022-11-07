<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class=container>
		<c:if test="${products == null }">
			<form class="form-horizontal" action='product' method="POST"
				enctype="multipart/form-data">
				<input type="hidden" name="action" value="add" />
		</c:if>
		<c:if test="${products != null }">
			<form class="form-horizontal" action='product' method="POST"
				enctype="multipart/form-data">
				<input type="hidden" name="action" value="edit" />
		</c:if>

		<fieldset>
			<div class="control-group">
				<label class="control-label" for="id">Id :</label>
				<div class="controls">
					<c:if test="${products == null }">
						<input type="text" id="id" name="id" placeholder="Enter Id"
							class="input-xlarge" value="${id }">
					</c:if>
					<c:if test="${products != null}">
						<input type="text" id="id" name="id" class="input-xlarge"
							value="${products.getId()}" readonly>
					</c:if>
				</div>
				<span class="text-danger">${errors.id}</span>
			</div>

			<div class="control-group">
				<label class="control-label" for="name">Name :</label>
				<div class="controls">
					<c:if test="${products == null }">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${name}">
					</c:if>
					<c:if test="${products != null }">
						<input type="text" id="name" name="name" class="input-xlarge"
							value="${products.getName()}">
					</c:if>
				</div>
				<span class="text-danger">${errors.name}</span>
			</div>

			<div class="control-group">
				<!-- E-mail -->
				<label class="control-label" for="price">Price</label>
				<div class="controls">
					<c:if test="${products == null }">
						<input type="text" id="price" name="price"
							placeholder="Enter Price" class="input-xlarge" value="${price}">
					</c:if>
					<c:if test="${products != null }">
						<input type="text" id="price" name="price" class="input-xlarge"
							value="${products.getPrice()}">
					</c:if>
				</div>
				<span class="text-danger">${errors.price}</span>
			</div>

			<div class="control-group">
				<label class="control-label" for="description">Description</label>
				<div class="controls">
					<c:if test="${products == null }">
						<input type="text" id="description" name="description"
							placeholder="Enter Description" class="input-xlarge"
							value="${description}">
					</c:if>
					<c:if test="${products != null }">
						<input type="text" id="description" name="description"
							class="input-xlarge" value="${products.getDescription()}">
					</c:if>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="upload"></label>
				<div class="controls">
					<c:if test="${products == null }">
						<input type="file" id="photo" name="photo" class="input-xlarge">
					</c:if>
					<c:if test="${products != null }">
						<div class="controls">
							<input type="file" id="photo" name="photo" class="input-xlarge">
						</div>
						<img src="uploads/${products.getImage()}" width="100">
					</c:if>

				</div>
			</div>
			<br>
			<div class="control-group">
				Option <select name="option">
					<c:if test="${products == null }">
						<c:forEach var="select" items="${category}">
							<option value="${select.getId()}" selected>${select.getName()}</option>
						</c:forEach>
					</c:if>
					<c:if test="${products != null }">
						<c:forEach var="select" items="${category}">
							<option value="${select.getId()}"
								<c:if test="${products.getCategory().getName() eq select.getName()}"> selected </c:if>>${select.getName()}</option>
						</c:forEach>
					</c:if>

				</select>
			</div>
			<br>

			<div class="control-group">
				<div class="controls">
					<button class="btn btn-success">Save</button>
				</div>
			</div>

		</fieldset>
		</form>
		<br>
	</div>
</body>
</html>