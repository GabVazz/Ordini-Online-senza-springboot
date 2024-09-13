<%@page import="java.util.Enumeration"%>
<%@ page import="com.gab.businesscomponent.facade.ClientFacade"%>
<%@ page import="com.gab.businesscomponent.model.Articolo"%>
<%
if (session.getAttribute("username") == null) {
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<jsp:useBean id="carrello"
	class="com.gab.businesscomponenet.utility.Carrello" scope="session" />
<!DOCTYPE html>
<html>
<head>
<%@include file="CDN.html"%>
<title>Riepilogo Carrello</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<%
			if (carrello.totaleComplessivo() == 0) {
			%>
			<h3>Il carrello è vuoto</h3>
			<hr
				style="height: 1px; border-width: 0; color: gray; background-color: gray">
			<button class="btn btn-info"
				onclick="window.location.href='acquisti.jsp'">Torna alla
				selezione prodotti</button>
			<%
			} else {
			%>
			<h3>Articoli presenti nel carrello</h3>
		</header>
		<p>
			Totale carrello: <strong><%=String.format("%.2f", carrello.totaleComplessivo())%>
				&euro;</strong>
		</p>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 20%;">Marca</th>
						<th style="width: 20%;">Modello</th>
						<th style="width: 20%;">Parziale</th>
						<th style="width: 20%;">Quantità</th>
						<th style="width: 20%;">Rimuovi</th>
					</tr>
				</thead>
				<tbody>
					<%
					Enumeration<String[]> prodotti = carrello.getProdotti();
					while (prodotti.hasMoreElements()) {
						String[] prodotto = prodotti.nextElement();
					%>
					<tr>
						<td><%=prodotto[0]%></td>
						<td><%=prodotto[1]%></td>
						<td><%=String.format("%.2f", carrello.totaleParziale(prodotto[4]))%>
							&euro;</td>
						<td><%=prodotto[3]%></td>
						<td>
							<form
								action="/<%=application.getServletContextName()%>/rimuoviCarrello?id=<%=prodotto[4]%>"
								method="post">
								<button type="submit" class="btn btn-danger btn-sm">Rimuovi</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<hr
			style="height: 1px; border-width: 0; color: gray; background-color: gray">
		<button class="btn btn-info"
			onclick="window.location.href='acquisti.jsp'">Torna alla
			selezione prodotti</button>
		<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
				<h4>
					<strong>Conferma Ordine</strong>
				</h4>
				<p style="text-align: right;">
					Totale articoli nel carello&nbsp;<%=carrello.getArticoli()%></p>
			</div>
			<div class="panel-body">
				<form action="/<%=application.getServletContextName()%>/conferma"
					method="post">
					<button type="submit" class="btn btn-success">&#10003; Acquista i
						prodotti</button>
				</form>
			</div>
		</div>
		<%
		}
		%>
	</div>
	<footer><%@ include file="footer.html"%></footer>
</body>
</html>
