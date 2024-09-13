<%
	if(session.getAttribute("username") == null){
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="CDN.html"%>
<title>Registrazione</title>
<link rel="stylesheet" href="css/style.css">
<script src="js/validazione.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<header class="page-header">
			<h3>Inserisci i dati per la registrazione</h3>
		</header>
		<form action="/<%=application.getServletContextName()%>/registra"
			method="post" class="form-horizontal" id="utenteForm">
			<!-- Nome -->
			<div class="form-group">
				<label class="col-md-1 control-label">Nome</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input type="text" name="nome" id="nome" placeholder="Nome..."
							class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoNome"></div>
			</div>

			<!-- Cognome -->
			<div class="form-group">
				<label class="col-md-1 control-label">Cognome</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input type="text" name="cognome" id="cognome"
							placeholder="Cognome..." class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoCognome"></div>
			</div>

			<!-- Indirizzo -->
			<div class="form-group">
				<label class="col-md-1 control-label">Indirizzo</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-home"></i>
						</span>
						<textarea cols="40" rows="3" name="indirizzo"
							placeholder="Indirizzo..." id="indirizzo" class="form-control"
							style="resize: none"></textarea>
					</div>
				</div>
				<div class="col-md-7 error" id="infoIndirizzo"></div>
			</div>

			<!-- CAP -->
			<div class="form-group">
				<label class="col-md-1 control-label">Cap</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-home"></i>
						</span> <input type="text" id="cap" name="cap" placeholder="Cap..."
							class="form-control" maxlength="5" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoCap"></div>
			</div>

			<!-- Nascita -->
			<div class="form-group">
				<label class="col-md-1 control-label">Nascita</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group date" id="datePicker">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-calendar"></i>
						</span> <input type="text" id="nascita" name="nascita"
							placeholder="DD/MM/YYYY" class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoNascita"></div>
			</div>
			<script>
				$(function() {
					$('#datePicker').datepicker({
						format : 'dd/mm/yyyy',
						autoclose : true,
						startDate : '01/01/1900',
						endDate : new Date()
					}).on(
							'changeDate',
							function(event) {
								$('#utenteForm').bootstrapValidator(
										'revalidateField', 'nascita');
							});
				});
			</script>

			<!-- Username -->
			<div class="form-group">
				<label class="col-md-1 control-label">Username</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user"></i>
						</span> <input type="text" id="username" name="username"
							placeholder="Username..." class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoUsername"></div>
			</div>

			<!-- Password -->
			<div class="form-group">
				<label class="col-md-1 control-label">Password</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-lock"></i>
						</span> <input type="password" id="password" name="password"
							placeholder="Password..." class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoPassword"></div>
			</div>

			<!-- Email -->
			<div class="form-group">
				<label class="col-md-1 control-label">Email</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-envelope"></i>
						</span> <input type="text" id="email" name="email" placeholder="Email..."
							class="form-control" required>
					</div>
				</div>
				<div class="col-md-7 error" id="infoEmail"></div>
			</div>

			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					<button type="submit" class="btn btn-warning">
						Registrati&nbsp;&nbsp;<span class="glyphicon glyphicon-send"></span>
					</button>
				</div>
			</div>
		</form>
	</div>
	<footer><%@ include file="footer.html"%></footer>
	<%
	}else {
		response.sendRedirect("acquisti.jsp");
	}
	%>
</body>
</html>