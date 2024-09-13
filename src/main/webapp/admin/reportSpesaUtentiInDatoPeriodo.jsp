<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.gab.businesscomponent.model.GenericReport"%>
<%@ page import="com.gab.businesscomponent.facade.AdminFacade"%>
<%@ page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="../CDN.html"%>
<%@ include file="nav.jsp"%>

<%
if (session.getAttribute("admin") == null) {
	response.sendRedirect("../login.jsp");
	return;
}

String dataInizio = request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String spesaStr = request.getParameter("spesa");

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date startDate = null;
Date endDate = null;

if (dataInizio != null && !dataInizio.isEmpty()) {
	startDate = sdf.parse(dataInizio);
}
if (dataFine != null && !dataFine.isEmpty()) {
	endDate = sdf.parse(dataFine);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report utenti spesa in dato periodo</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<header>
			<h3>REPORT SPESA UTENTI IN DATO PERIODO</h3>
		</header>

		<form action="#" method="post">
			<div class="form-group">
				<label for="dataInizio">Data Inizio</label> <input type="date"
					id="dataInizio" name="dataInizio" class="form-control" />
			</div>
			<div class="form-group">
				<label for="dataFine">Data Fine</label> <input type="date"
					id="dataFine" name="dataFine" class="form-control" />
			</div>
			<div class="form-group">
				<label for="spesa">Spesa</label> <input type="number" id="spesa"
					name="spesa" step="1" min="0" class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Conferma</button>
		</form>

		<%
		if (dataInizio != null && dataInizio != "" && dataFine != null && dataFine != "" && spesaStr != null && spesaStr != "") {
		%>
		<h3>
			Risultati dal
			<%=dataInizio%>
			al
			<%=dataFine%>
			con spesa
			<%=spesaStr%>
			euro
		</h3>
		<form action="#" method="post">
			<input type="hidden" name="dataInizio" value= "">
			<input type="hidden" name="dataFine" value= "">
			<input type="hidden" name="spesa" value= "">
			<button type="submit" class="btn btn-danger btn-sm">Reset</button>
		</form>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Username</th>
						<th>Email</th>
						<th>Somma Totale</th>
					</tr>
				</thead>
				<tbody>
					<%
					String[] columns = { "username", "email", "totale_speso" };
					String query = "SELECT u.username, u.email, SUM(o.totale) AS totale_speso " + "FROM utente u "
							+ "JOIN ordine o ON u.username = o.username " + "WHERE o.data BETWEEN TO_DATE('" + dataInizio
							+ "', 'YYYY-MM-DD') AND TO_DATE('" + dataFine + "', 'YYYY-MM-DD') " + "GROUP BY u.username, u.email "
							+ "HAVING SUM(o.totale) > " + spesaStr + " " + "ORDER BY totale_speso DESC";
					Set<GenericReport> reports = AdminFacade.getIstance().getReportData(query, columns);
					for (GenericReport report : reports) {
					%>
					<tr>
						<td><%=report.getColumn("username")%></td>
						<td><%=report.getColumn("email")%></td>
						<td><%=report.getColumn("totale_speso")%> €</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<%
		}
		%>
	</div>
	<footer>
		<jsp:include page="footer.html" />
	</footer>
</body>
</html>
