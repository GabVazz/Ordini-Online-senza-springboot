<%
if (session.getAttribute("username") != null || session.getAttribute("admin") != null) {
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="/<%=application.getServletContextName()%>/css/style.css">
    <%@ include file="CDN.html" %>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="container">
        <header class="page-header">
            <h3>Logout</h3>
        </header>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h5>Hai appena effettuato il logout</h5>
            </div>
            <div class="panel-body" style="word-wrap: break-word;">
                <p>Per accedere di nuovo ai servizi, effettua di nuovo il login</p>
                <p><a href="login.jsp">Pagina di login</a></p>
            </div>
        </div>
    </div>
    <footer><%@ include file="footer.html" %></footer>
</body>
</html>
<%
    // Invalidare la sessione per effettuare il logout
    session.invalidate();
} else {
    response.sendRedirect("login.jsp");
}
%>
