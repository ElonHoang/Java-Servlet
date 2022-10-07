<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form method="post" action="login">
		<section class="vh-100" style="background-color: #508bfc;">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-8 col-lg-6 col-xl-5">
						<div class="card shadow-2-strong" style="border-radius: 1rem;">
							<div class="card-body p-5 text-center">

								<h3 class="mb-5">Sign in</h3>

								<div class="form-outline mb-4">
									<label for="user">User</label> <input type="text" id="user"
										name="user" class="form-control form-control-lg" /> <span
										class="text-danger">${errors.user}</span>
								</div>

								<div class="form-outline mb-4">
									<label for="password">Password</label> <input type="password"
										id="password" name="password"
										class="form-control form-control-lg" /> <span
										class="text-danger">${errors.pass}</span>
								</div>

								<!-- Checkbox -->
								<div class="form-check d-flex justify-content-start mb-4">
									<input class="form-check-input" type="checkbox" value=""
										id="form1Example3" /> <label class="form-check-label"
										for="form1Example3"> Remember password </label>
								</div>

								<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>

								<hr class="my-4">
								<span class="text-danger">${errors.login}</span>

								<button class="btn btn-lg btn-block btn-primary"
									style="background-color: #dd4b39;" type="submit">
									<i class="fab fa-google me-2"></i> Sign in with google
								</button>
								<button class="btn btn-lg btn-block btn-primary mb-2"
									style="background-color: #3b5998;" type="submit">
									<i class="fab fa-facebook-f me-2"></i>Sign in with facebook
								</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form>
</body>
</html>