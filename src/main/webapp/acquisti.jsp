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
<title>Acquisti</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<h3>Articoli presenti in catalogo</h3>
		</header>
		<p>
			Totale carrello: <strong><%=String.format("%.2f", carrello.totaleComplessivo())%>
				&euro;</strong>
		</p>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 20%;">&nbsp;</th>
						<th style="width: 20%;">Marca</th>
						<th style="width: 20%;">Modello</th>
						<th style="width: 20%;">Prezzo</th>
						<th style="width: 20%;">Ordine</th>
					</tr>
				</thead>
				<tbody>
					<%
					Articolo[] a = ClientFacade.getIstance().getArticoli();
					for (int i = 0; i < a.length; i++) {
					%>
					<tr>
						<td style="vertical-align: middle;"><img
							src="<%=ClientFacade.getIstance().findImmagineById(a[i].getIdArticolo()) != null
		? ClientFacade.getIstance().findImmagineById(a[i].getIdArticolo()).getUrl()
		: "https://t3.ftcdn.net/jpg/05/79/68/24/360_F_579682465_CBq4AWAFmFT1otwioF5X327rCjkVICyH.jpg"%>"
							width="50px" height="50px"></td>
						<td style="vertical-align: middle;"><%=a[i].getMarca()%></td>
						<td style="vertical-align: middle;"><%=a[i].getModello()%></td>
						<td style="vertical-align: middle;"><%=String.format("%.2f", a[i].getPrezzo())%>
							&euro;</td>
						<td style="vertical-align: middle;">
							<form action="/<%=application.getServletContextName()%>/aggiungiCarrello"
								method="post">
								<input type="hidden" name="id" value="<%=a[i].getIdArticolo()%>">
								<button type="submit" class="btn btn-primary btn-sm">Aggiungi</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<hr style="height:1px;border-width:0;color:gray;background-color:gray">
		<button class="btn btn-info" onclick="window.location.href='carrello.jsp'">Visualizza articoli nel carrello</button>
	</div>
	<footer><%@ include file="footer.html"%></footer>
</body>
</html>
