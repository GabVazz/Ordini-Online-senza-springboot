<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.gab.architecture.dao.DAOException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error 403</title>
    <link rel="stylesheet" href="/<%=application.getServletContextName()%>/css/style.css">
    <%@ include file="CDN.html" %>
</head>
<body>
    <jsp:include page="nav.jsp" />
    <div class="container">
        <header class="page-header">
            <h3>Autorizzazione negata</h3>
        </header>

        <div class="panel panel-danger">
            <div class="panel-heading">
                <h5>Impossibile caricare la risorsa richiesta</h5>
            </div>
            <div class="panel-body" style="word-wrap: break-word;">
                <p>Non hai l'autorizzazione per accedere</p>
                <p>Per segnalare l'eventuale problema contattare l'amministratore</p>
                <p><a href="mailto:ciccio@gmail.com">ciccio@gmail.com</a></p>
                <div style="border-top: 30px;">
                    <button onclick="window.history.back()" class="btn btn-default">Indietro</button>
                </div>
            </div>
        </div>
    </div>
    <footer><%@ include file="footer.html" %></footer>
</body>
</html>
