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
						<th style="width: 20%;">Username</th>
						<th style="width: 20%;">Email</th>
						<th style="width: 20%;">ID Ordine</th>
						<th style="width: 20%;">Totale</th>
						<th style="width: 20%;">Quantita</th>
					</tr>
				</thead>
				<tbody>
					<%
					String[] columns = { "username", "email", "id_ordine", "totale", "qta" };
					String query = com.gab.architecture.dao.DAOConstants.SELECT_REPORT;
					Set<GenericReport> reports = AdminFacade.getIstance().getReportData(query, columns);
					for (GenericReport report : reports) {
					%>
					<tr>
						<td><%=report.getColumn("username")%></td>
						<td><%=report.getColumn("email")%></td>
						<td><%=report.getColumn("id_ordine")%></td>
						<td><%=String.format("%.2f", report.getColumn("totale"))%> €</td>
						<td><%=report.getColumn("qta")%></td>
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