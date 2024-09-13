
<%
if (session.getAttribute("username") != null) {
	response.sendRedirect("acquisti.jsp");
	return;
} else if (session.getAttribute("admin") != null) {
	response.sendRedirect("admin/admin.jsp");
	return;
} else {
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="CDN.html"%>
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<h3>Inserisci i dati per l'accesso</h3>
		</header>
		<form action="/<%=application.getServletContextName()%>/controllo"
			method="post" class="form-horizontal" id="utenteForm">

			<!-- Username -->
			<div class="form-group">
				<label class="col-md-1 control-label">Username</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input type="text" id="username" name="username"
							placeholder="Username..." class="form-control" required>
					</div>
				</div>
			</div>

			<!-- Password -->
			<div class="form-group">
				<label class="col-md-1 control-label">Password</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-lock"></i>
						</span> <input type="password" id="password" name="password"
							placeholder="Password..." class="form-control" required>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					<button type="submit" class="btn btn-warning">
						Accedi&nbsp;&nbsp;<span class="glyphicon glyphicon-send"></span>
					</button>
				</div>
			</div>
		</form>
	</div>
	<footer><%@ include file="footer.html"%></footer>
	<%
	}
	%>
</body>
</html>
