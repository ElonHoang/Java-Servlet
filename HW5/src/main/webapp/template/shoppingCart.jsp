<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShoppingCart</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<script type="text/javascript">
		function getQuantityPrice(){
			var quantity = document.getElementById('quantity').value;
			var price = document.getElementById('price').InnerHTML;
			
			var total = price * quantity;
			
			document.getElementById('total').InnerHTML = "abc";
		}
	</script>
</head>
<body>
	<section class="pt-5 pb-5">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
					<p class="mb-5 text-center">
						<i class="text-info font-weight-bold">${products.size()}</i> items in your cart
					</p>
					<table id="shoppingCart"
						class="table table-condensed table-responsive" >
						<thead>
							<tr>
								<th style="width: 20%">Product</th>
								<th style="width: 5%">Price</th>
								<th style="width: 5%">Quantity</th>
								<th style="width: 20%" class="text-center">Total</th>
								<th style="width: 10%">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${products}">
								<tr>

									<td data-th="Product">
										<div class="row">
											<div class="col-md-3 text-left">
												<img src="https://via.placeholder.com/250x250/5fa9f8/ffffff"
													alt=""
													class="img-fluid d-none d-md-block rounded mb-2 shadow ">
											</div>
											<div class="col-md-9 text-left mt-sm-2">
												<h4>${product.getName()}</h4>
												
											</div>
										</div>
									</td>
									<td data-th="Price"><span id="price">${product.getPrice()}</span></td>
									<td data-th="Quantity"><input type="number"
										class="form-control form-control-lg text-center" id = "quantity" name="quantity" value="${quantity}"
										onchange="getQuantityPrice()">
									</td>
									<td><span id="total">${product.getPrice() * quantity}</span></td>
									<td class="actions" data-th="">
										<div class="text-left">
											<button
												class="btn btn-white border-secondary bg-white btn-md mb-2">
												<a href="remove?id=${product.getId()}"><i class="fa fa-trash"></i></a>
											</button>
										</div>
									</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="float-right text-right">
						<h4>Subtotal:</h4>
						<h1>$99.00</h1>
					</div>
				</div>
			</div>
			<div class="row mt-4 d-flex align-items-center">
				<div class="col-sm-6 order-md-2 text-right">
					<a href="catalog.html"
						class="btn btn-primary mb-4 btn-lg pl-5 pr-5">Checkout</a>
				</div>
				<div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
					<a href="./productUI"> <i class="fa fa-arrow-left mr-2"></i>
						Continue Shopping
					</a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>