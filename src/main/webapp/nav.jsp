<jsp:useBean id="carrello"
	class="com.gab.businesscomponenet.utility.Carrello" scope="session" />
<link rel="stylesheet" href="css/style.css">
<nav class="navbar navbar-inverse">
	<div class="container-fluid">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="/<%=application.getServletContextName()%>/home.jsp">Negozio
				On-line</a>
		</div>

		<div class="collapse navbar-collapse" id="menu">
			<%
			String username = (String) session.getAttribute("username");
			if (username == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="/<%=application.getServletContextName()%>/registrazione.jsp">
						<span class="glyphicon glyphicon-user"></span> Sign up
				</a></li>
				<li><a
					href="/<%=application.getServletContextName()%>/login.jsp"> <span
						class="glyphicon glyphicon-log-in"></span> Login
				</a></li>
			</ul>
			<%
			} else {
			%>
			<ul class="nav navbar-nav">
				<li><a
					href="/<%=application.getServletContextName()%>/acquisti.jsp">
						Scelta articoli </a></li>
				<li><a
					href="/<%=application.getServletContextName()%>/carrello.jsp">
						Riepilogo carrello </a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a
					href="/<%=application.getServletContextName()%>/carrello.jsp">
						<span class="glyphicon glyphicon-shopping-cart"></span> <span
						class="badge"><%=carrello.getArticoli()%></span>
				</a></li>
				<li><a href="#"> <span class="glyphicon glyphicon-user"><%=username%></span>
				</a></li>
				<li><a
					href="/<%=application.getServletContextName()%>/logout.jsp"> <span
						class="glyphicon glyphicon-off"></span> Logout
				</a></li>
			</ul>
			<%
			}
			%>
		</div>
	</div>
</nav>