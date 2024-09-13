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
<title>Report articoli più venduti</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<header>
			<h3>REPORT ARTICOLI PIU VENDUTI</h3>
		</header>

		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 20%;">ID_Articolo</th>
						<th style="width: 20%;">Marca</th>
						<th style="width: 20%;">Modello</th>
						<th style="width: 20%;">Totale Vendite</th>
						<th style="width: 20%;">Quantita venduta</th>
					</tr>
				</thead>
				<tbody>
					<%
					String[] columns = { "id_articolo", "marca", "modello", "totale_vendite", "quantita_venduta" };
					String query = com.gab.architecture.dao.DAOConstants.SELECT_ARTICOLI_PIU_VENDUTI;
					Set<GenericReport> reports = AdminFacade.getIstance().getReportData(query, columns);
					for (GenericReport report : reports) {
					%>
					<tr>
						<td><%=report.getColumn("id_articolo")%></td>
						<td><%=report.getColumn("marca")%></td>
						<td><%=report.getColumn("modello")%></td>
						<td><%=report.getColumn("totale_vendite")%></td>
						<td><%=report.getColumn("quantita_venduta")%></td>
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