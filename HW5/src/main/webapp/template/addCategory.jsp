<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form class="form-horizontal" action='addCate' method="POST">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="name">Name :</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="Enter Name"
							class="input-xlarge" value="${name}">
					</div>
					<span class="text-danger">${errors.name}</span>
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