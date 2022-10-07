<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class=container>
		<form class="form-horizontal" action='editCate' method="POST">
			<fieldset>
				<div class="control-group">
					<div class="controls">
						<input type="hidden" id="id" name="id"
							class="input-xlarge" value="${category.getId()}">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${category.getName()}">
					</div>
					<span class="text-danger">${errors.name}</span>
				</div>


				<div class="control-group">
					<label class="control-label" for="description">Description</label>
					<div class="controls">
						<input type="text" id="description" name="description"
							placeholder="Enter Description" class="input-xlarge"
							value="${category.getDescription()}">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">
							<i class="fa fa-pencil" aria-hidden="true"> Edit</i>
						</button>
					</div>
				</div>
			</fieldset>
		</form>
		<br>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-success">
					<a href="./category"><i class="fa fa-arrow-left"
						aria-hidden="true"> Back</i></a>
				</button>
			</div>
		</div>
	</div>
</body>
</html>