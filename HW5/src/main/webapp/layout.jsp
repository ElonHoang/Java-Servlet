<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title></title>
</head>
<body>
	<div class="container">
		<div class="header">
			<jsp:include page="/template/navbar.jsp"></jsp:include>
		</div>
		<div class="content">
			<div class="page">
				<jsp:include page="${view}"></jsp:include>
			</div>
		</div>
		<div class="footer">
		<jsp:include page="/template/footer.jsp"></jsp:include>
		</div>
	</div>


</body>
</html>