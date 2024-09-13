<%@page import="java.util.Enumeration"%>
<%
if (session.getAttribute("username") == null && session.getAttribute("carrello") == null) {
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
<title>Conferma Ordine</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<h3>Ordine confermato</h3>
		</header>
		<p>
			Codice ordine: <strong><%=session.getAttribute("idordine")%></strong>
		</p>
		<p>
			Totale complessivo: <strong><%=String.format("%.2f", carrello.totaleComplessivo())%>
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
	</div>
	<footer><%@ include file="footer.html"%></footer>

	<%
	session.removeAttribute("carrello");
	%>
</body>
</html>
