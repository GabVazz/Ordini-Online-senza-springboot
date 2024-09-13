<%@page import="java.util.Arrays"%>
<%@page import="com.gab.businesscomponent.facade.AdminFacade"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="com.gab.businesscomponent.model.Articolo"%>
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

<%
String query = request.getParameter("q");
Set<Articolo> articoli = null;
if (query != null && !query.equals("")) {
	articoli = AdminFacade.getIstance().cercaArticolo(query);
} else {
	articoli = new LinkedHashSet<Articolo>(Arrays.asList(AdminFacade.getIstance().getArticoli()));
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Gestione Articoli</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div class="container">
		<header>
			<h3>Articoli presenti disponibili</h3>
		</header>

		<div class="row">
			<div class="form-group col-md-6">
				<form id="q_form">
					<div class="query_input">
						<div class="input-group">
							<input id="query_input" class="form-control" type="search"
								name="q" value="<%=query == null ? "" : query%>"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search" style="height: 21px;"></span>
								</button>
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 20%;">Marca</th>
						<th style="width: 20%;">Modello</th>
						<th style="width: 20%;">Parziale</th>
						<th style="width: 20%;">&nbsp;</th>
						<th style="width: 20%;">&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Articolo a : articoli) {
					%>
					<tr>
						<td><%=a.getMarca()%></td>
						<td><%=a.getModello()%></td>
						<td><%=String.format("%.2f", a.getPrezzo())%> &euro;</td>
						<td>
							<button type="button" class="btn btn-info btn-sm"
								data-toggle="modal"
								data-target="#editModal_<%=a.getIdArticolo()%>">
								<span class="glyphicon glyphicon-pencil"></span>
							</button>
						</td>
						<td>
							<form
								action="/<%=application.getServletContextName()%>/rimuoviArticolo?id=<%=a.getIdArticolo()%>"
								method="post">
								<button type="submit" class="btn btn-danger btn-sm">
									Rimuovi <span class="glyphicon glyphicon-trash"></span>
								</button>
							</form>
						</td>
						<td><jsp:include page="editArticoloModal.jsp">
								<jsp:param name="id" value="<%=a.getIdArticolo()%>"></jsp:param>
							</jsp:include></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<footer>
		<jsp:include page="footer.html" />
	</footer>
</body>
</html>