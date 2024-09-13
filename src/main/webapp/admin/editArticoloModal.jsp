<%@ page import="com.gab.businesscomponent.facade.AdminFacade"%>
<%@ page import="com.gab.businesscomponent.model.Articolo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../error.jsp"%>

<%
long id = Long.valueOf(request.getParameter("id"));
Articolo a = null;
if (request.getParameter("id") == null) {
	response.sendRedirect("gestisciArticoli.jsp");
} else {

	a = AdminFacade.getIstance().findArticoloById(id);
	if (a == null) {
		a = new Articolo();
	}
%>

<div class="modal fade" id="editModal_<%=id%>" role="dialog">
	<div class="modal-dialog modal-md">
		<form
			action="/<%=application.getServletContextName()%>/inserisciArticolo"
			method="POST">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						Modifica articolo [<%=id > 0 ? id : ""%>]
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="hidden" name="id" value="<%=a.getIdArticolo()%>">

					<div class="form-group">
						<label for="marca">Marca</label> <input type="text"
							class="form-control" name="marca"
							value="<%=a.getMarca() == null ? "" : a.getMarca()%>">
					</div>

					<div class="form-group">
						<label for="modello">Modello</label> <input type="text"
							class="form-control" name="modello"
							value="<%=a.getModello() == null ? "" : a.getModello()%>">
					</div>

					<div class="form-group">
						<label for="prezzo">Prezzo</label> <input type="number"
							class="form-control" name="prezzo" step="0.05" min="0"
							value="<%=a.getPrezzo()%>">
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Salva
						modifiche</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
				</div>
			</div>
		</form>
	</div>
</div>
<%
}
%>