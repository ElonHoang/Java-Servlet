<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<div class=container>
		<form class="form-horizontal" action='add' method="POST"
			enctype="multipart/form-data">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="id">Id :</label>
					<div class="controls">
						<input type="text" id="id" name="id" placeholder="Enter Id"
							class="input-xlarge" value="${id}">
					</div>
					<span class="text-danger">${errors.id}</span>
				</div>

				<div class="control-group">
					<label class="control-label" for="name">Name :</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${name}">
					</div>
					<span class="text-danger">${errors.name}</span>
				</div>

				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="price">Price</label>
					<div class="controls">
						<input type="text" id="price" name="price"
							placeholder="Enter Price" class="input-xlarge" value="${price}">
					</div>
					<span class="text-danger">${errors.price}</span>
				</div>

				<div class="control-group">
					<label class="control-label" for="description">Description</label>
					<div class="controls">
						<input type="text" id="description" name="description"
							placeholder="Enter Description" class="input-xlarge"
							value="${description}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="upload"></label>
					<div class="controls">
						<input type="file" id="photo" name="photo" class="input-xlarge">
					</div>
				</div>
				<br>
				<div class="control-group">
					Option <select name="option">
						<c:forEach var="select" items="${category}">
							<option value="${select.getId()}" selected>${select.getName()}</option>
						</c:forEach>
					</select>
				</div>
				<br>

				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">Add</button>
					</div>
				</div>

			</fieldset>
		</form>
		<br>
	</div>
</body>
</html>