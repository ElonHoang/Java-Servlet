<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShoppingCart</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<section class="pt-5 pb-5">
		<div class="container">
			<div class="row w-100">
				<div class="col-lg-12 col-md-12 col-12">
					<div class="float-left text-left">
						<h4>Subtotal:</h4>
						<h1>${total_price}</h1>
					</div>
					<h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
					<p class="mb-5 text-center">
						<i class="text-info font-weight-bold">${shoppingCart.size()}</i>
						items in your cart
					</p>
					
					<form action="shoppingcart" method="POST">
					<input type="hidden" name = "action" value = "checkout">
						<table id="shoppingCart"
							class="table table-condensed table-responsive">
							<thead>
								<tr>
									<th style="width: 20%">Product</th>
									<th style="width: 5%">Price</th>
									<th style="width: 5%">Quantity</th>
									<th style="width: 20%" class="text-center">Total</th>
									<th style="width: 10%">Action</th>
									<th style="width: 10%">Buy</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sc" items="${shoppingCart}">
									<tr>

										<td data-th="Product">
											<div class="row">
												<div class="col-md-3 text-left">
													<img
														src="https://via.placeholder.com/250x250/5fa9f8/ffffff"
														alt=""
														class="img-fluid d-none d-md-block rounded mb-2 shadow ">
												</div>
												<input type="hidden" name="id" value="${sc.getId()}" />
												<div class="col-md-9 text-left mt-sm-2">
													<h4>${sc.getProduct().getName()}</h4>

												</div>
											</div>
										</td>
										<td data-th="Price"><span id="price">${sc.getProduct().getPrice()}</span></td>
										<td data-th="Quantity">
											<div class="form-group d-flex justify-content-between">
												<a class="btn btn-sm btn-incre"
													href="shoppingcart?action=plus&id=${sc.getProduct().getId()}"><i
													class="fa fa-plus-square"></i></a> <input type="text"
													name="quantity" class="form-control"
													value="${sc.getQuantity()}" readonly>
												<c:if test="${sc.getQuantity() > 1}">
													<a class="btn btn-sm btn-decre"
														href="shoppingcart?action=minus&id=${sc.getProduct().getId()}"><i
														class="fa fa-minus-square"></i></a>
												</c:if>

											</div>
										</td>

										<td><span id="total">${sc.getProduct().getPrice() * sc.getQuantity()}</span></td>
										<td class="actions" data-th="">
											<div class="text-left">
													<a href="shoppingcart?action=deleteCart&id=${sc.getId()}"><i
														class="btn btn-white border-secondary bg-white btn-md mb-2 fa fa-trash"></i></a>
											</div>
										</td>
										<td data-th="Check">
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="checkbox" id="check"
													name="check" value="${sc.getProduct().getId()}">
											</div>

										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					<div class="col-sm-12 order-md-2 text-right">
									<input type="submit" name = "Checkout" value="Checkout" class="btn btn-primary"/>
								</div>	
					</form>
					

				</div>
			</div>
			<div class="row mt-4 d-flex align-items-center">

				<div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
					<a href="./home"> <i class="fa fa-arrow-left mr-2"></i>
						Continue Shopping
					</a>
				</div>
			</div>
		</div>
	</section>
</body>
</html>