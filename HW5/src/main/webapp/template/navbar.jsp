<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <header>
	<!--- Navbar --->
	<nav class="navbar navbar-expand-lg">
		<div class="container">
			<a class="navbar-brand text-white" href="#"><i class="fa fa-graduation-cap fa-lg mr-2"></i>Product</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nvbCollapse" aria-controls="nvbCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="nvbCollapse">
				<h3 class="nav-item pl-1 nav-link text-white">${userLogin.getUser()}</h3>
				<ul class="navbar-nav ml-auto">
				
					<li class="nav-item pl-1">
						<a class="nav-link" href="./home"><i class="fa fa-home fa-fw mr-1"></i>HomePage</a>
					</li>
					<li class="nav-item  pl-1">
						<a class="nav-link" href="./product"><i class="fa fa-th-list fa-fw mr-1"></i>Product</a>
					</li>
					<li class="nav-item pl-1">
						<a class="nav-link" href="./category"><i class="fa fa-info-circle fa-fw mr-1"></i>Category</a>
					</li>
					<li class="nav-item  pl-1">
						<a class="nav-link" href="./shoppingcart"><i class="fa fa-th-list fa-fw mr-1"></i>Shopping Cart</a>
					</li>
					<li class="nav-item  pl-1">
						<a class="nav-link" href="./order?action=history"><i class="fa fa-th-list fa-fw mr-1"></i>History Order</a>
					</li>
					<li class="nav-item pl-1">
						<a class="nav-link" href="./logout"><i class="fa fa-sign-in fa-fw mr-1"></i>Log out</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<!--# Navbar #-->
	</header>
</body>
</html>