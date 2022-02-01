<%@ page import="fr.ut1.espacemembre.record.Field" %><%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 19/01/2022
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
    <%@ include file="/include/navbar.jsp"%>
    <%
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String emailError = request.getAttribute("email_error") == null ? "" : (String) request.getAttribute("email_error");
        String passwordError = request.getAttribute("password_error") == null ? "" : (String) request.getAttribute("password_error");
        String generalError = request.getAttribute("general_error") == null ? "" : (String) request.getAttribute("general_error");
    %>
    <section>
        <h1>Connexion</h1>
        <form method="post">
            <div>
                <label for="name">Email</label>
                <input type="email" id="name" name="email" value="<%= email %>">
                <span><%= emailError %></span>
            </div>
            <div>
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password">
                <span><%= passwordError %></span>
            </div>
            <div>
                <span><%= generalError %></span>
            </div>
            <div>
                <a href="/register">S'inscrire</a>
                <input type="submit" value="Se connecter">
            </div>
        </form>
    </section>
</body>
</html>
