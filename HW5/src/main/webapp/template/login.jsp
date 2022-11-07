<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<c:if test="${empty action}">
		<form method="post" action="login">
			<input type="hidden" name="action" value="login" />
			<section class="vh-100" style="background-color: #508bfc;">
				<div class="container py-5 h-100">
					<div
						class="row d-flex justify-content-center align-items-center h-100">
						<div class="col-12 col-md-8 col-lg-6 col-xl-5">
							<div class="card shadow-2-strong" style="border-radius: 1rem;">
								<div class="card-body p-5 text-center">
									<h3 class="mb-5">Sign in</h3>
	</c:if>
	<c:if test="${not empty action}">
		<form method="post" action="login">
			<input type="hidden" name="action" value="createAccount" />
			<section class="vh-100" style="background-color: #508bfc;">
				<div class="container py-5 h-100">
					<div
						class="row d-flex justify-content-center align-items-center h-100">
						<div class="col-12 col-md-8 col-lg-6 col-xl-5">
							<div class="card shadow-2-strong" style="border-radius: 1rem;">
								<div class="card-body p-5 text-center">
									<h3 class="mb-5">Create</h3>
	</c:if>
	<div class="form-outline mb-4">
		<label for="user">User</label> <input type="text" id="user"
			name="user" class="form-control form-control-lg" value="${user}" />
		<span class="text-danger">${errors.user}</span>
	</div>
	<div class="form-outline mb-4">
		<label for="password">Password</label> <input type="password"
			id="password" name="password" class="form-control form-control-lg"
			value="${password }" /> <span class="text-danger">${errors.pass}</span>
	</div>
	<c:if test="${empty action}">
		<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
		<br>
		<h4>
			<a href="login?action=createAccount" />Create Account
		</h4>
	</c:if>

	<c:if test="${not empty action}">
		<button class="btn btn-primary btn-lg btn-block" type="submit">Create</button>
		<br>
	</c:if>

	<hr class="my-4">
	<span class="text-danger">${errors.login}</span>
	</div>
	</div>
	</div>
	</div>
	</div>
	</section>
	</form>
</body>
</html>