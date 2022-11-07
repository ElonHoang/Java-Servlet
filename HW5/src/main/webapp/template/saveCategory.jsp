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
		<c:if test="${category == null }">
			<form class="form-horizontal" action='category' method="POST">
				<input type="hidden" name="action" value="add">
		</c:if>
		<c:if test="${category != null }">
			<form class="form-horizontal" action='category' method="POST">
				<input type="hidden" name="action" value="edit">
		</c:if>
		<fieldset>
			<c:if test="${category != null }">
				<input type="hidden" name="id" value="${category.getId()}">
			</c:if>

			<div class="control-group">
				<label class="control-label" for="name">Name :</label>
				<div class="controls">
					<c:if test="${category == null }">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${name}">
					</c:if>
					<c:if test="${category != null }">
						<input type="text" id="name" name="name" class="input-xlarge"
							value="${category.getName()}">
					</c:if>
				</div>
				<span class="text-danger">${errors.name}</span>
			</div>

			<div class="control-group">
				<label class="control-label" for="description">Description</label>
				<div class="controls">
					<c:if test="${category == null }">
						<input type="text" id="description" name="description"
							placeholder="Enter Description" class="input-xlarge"
							value="${description}">
					</c:if>
					<c:if test="${category != null }">
						<input type="text" id="description" name="description"
							class="input-xlarge" value="${category.getDescription()}">
					</c:if>
				</div>
			</div>

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