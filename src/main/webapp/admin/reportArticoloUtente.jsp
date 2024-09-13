<%@page import="com.gab.businesscomponent.model.GenericReport"%>
<%@page import="com.gab.businesscomponent.facade.AdminFacade"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="../CDN.html"%>
<%@ include file="nav.jsp"%>

<%
if (session.getAttribute("admin") == null) {
	response.sendRedirect("../login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report amministratore</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<header>
			<h3>REPORT VENDITE</h3>
		</header>

		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 10%;">Nome</th>
						<th style="width: 10%;">Cognome</th>
						<th style="width: 10%;">Email</th>
						<th style="width: 10%;">ID Articolo</th>
						<th style="width: 10%;">Marca</th>
						<th style="width: 10%;">Modello</th>
						<th style="width: 15%;">Prezzo</th>
						<th style="width: 10%;">Quantita</th>
						<th style="width: 10%;">Totale Parziale</th>
					</tr>
				</thead>
				<tbody>
					<%
					String[] columns = { "nome", "cognome", "email", "id_articolo", "marca", "modello", "prezzo", "qta", "Totale Parziale" };
					String query = com.gab.architecture.dao.DAOConstants.SELECT_INFO_ARTICOLO_UTENTE;
					Set<GenericReport> reports = AdminFacade.getIstance().getReportData(query, columns);
					for (GenericReport report : reports) {
					%>
					<tr>
						<td><%=report.getColumn("nome")%></td>
						<td><%=report.getColumn("cognome")%></td>
						<td><%=report.getColumn("email")%></td>
						<td style = "text-align: center;"><%=report.getColumn("id_articolo")%></td>
						<td><%=report.getColumn("marca")%></td>
						<td><%=report.getColumn("modello")%></td>
						<td><%=String.format("%.2f", report.getColumn("prezzo"))%> €</td>
						<td style = "text-align: center;"><%=report.getColumn("qta")%></td>
						<td><%=String.format("%.2f", report.getColumn("Totale Parziale"))%> €</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<footer>
		<jsp:include page="footer.html"/>
	</footer>
</body>
</html>