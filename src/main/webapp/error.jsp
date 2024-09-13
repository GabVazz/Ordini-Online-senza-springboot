<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.gab.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<title>Error Page</title>
<link rel="stylesheet"
	href="/<%=application.getServletContextName()%>/css/style.css">
<%@ include file="CDN.html"%>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<h3>Pagina di errore</h3>
		</header>
		<%
		if (exception instanceof ClassNotFoundException) {
		%>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h5>
					Tipo eccezione:
					<%=exception.getClass().getName()%></h5>
				<p>Driver non trovato</p>
			</div>
			<div class="panel-body">
				<p>
					Message:
					<%=exception.getMessage()%></p>
				<div style="border-top: 30px;">
					<button onclick="window.history.back()" class="btn btn-default">Indietro</button>
				</div>
			</div>
		</div>
		<%
		} else if (exception instanceof DAOException) {
		%>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h5>
					Tipo eccezione:
					<%=exception.getClass().getName()%></h5>
			</div>
			<div class="panel-body">
				<p>
					Message:
					<%=exception.getMessage()%></p>
				<div style="border-top: 30px;">
					<button onclick="window.history.back()" class="btn btn-default">Indietro</button>
				</div>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h5>Eccezione non prevista</h5>
				<p>
					Tipo eccezione:
					<%=exception.getClass().getName()%></p>
			</div>
			<div class="panel-body" style="word-wrap: break-word">
				<p>
					Message:
					<%=exception.getMessage()%></p>
				<p>StackTrace:</p>
				<p><%exception.printStackTrace(new java.io.PrintWriter(out));%></p>
				<div style="border-top: 30px;">
					<button onclick="window.history.back()" class="btn btn-default">Indietro</button>
				</div>
			</div>
		</div>
		<%
		}
		%>
		
	</div>
	<footer><%@ include file="footer.html"%></footer>
</body>
</html>
