<%@ page import="java.util.HashMap" %>
<%@ page import="fr.ut1.espacemembre.record.Field" %><%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 19/01/2022
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <%@ include file="/include/navbar.jsp"%>
    <%
        Field firstName = new Field(
            request.getParameter("first_name") == null ? "" : request.getParameter("first_name"),
            request.getAttribute("first_name") == null ? "" : (String) request.getAttribute("first_name")
        );
        Field lastName = new Field(
                request.getParameter("last_name") == null ? "" : request.getParameter("last_name"),
                request.getAttribute("last_name") == null ? "" : (String) request.getAttribute("last_name")
        );
        Field email = new Field(
                request.getParameter("email") == null ? "" : request.getParameter("email"),
                request.getAttribute("email") == null ? "" : (String) request.getAttribute("email")
        );
        Field dateOfBirth = new Field(
                request.getParameter("date_of_birth") == null ? "" : request.getParameter("date_of_birth"),
                request.getAttribute("date_of_birth") == null ? "" : (String) request.getAttribute("date_of_birth")
        );

        String passwordError = request.getAttribute("password") == null ? "" : (String) request.getAttribute("password");


    %>
    <section>
        <div>
            <h1>Inscription</h1>
        </div>
        <form method="post">
            <div>
                <label for="last_name">Nom</label>
                <input type="text" id="last_name" name="last_name" value="<%= lastName.value() %>">
                <span><%= lastName.error() %></span>
            </div>
            <div>
                <label for="first_name">Prénom</label>
                <input type="text" id="first_name" name="first_name" value="<%= firstName.value() %>">
                <span><%= firstName.error() %></span>
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= email.value() %>">
                <span><%= email.error() %></span>
            </div>
            <div>
                <label for="date_of_birth">Date de naissance</label>
                <input type="date" id="date_of_birth" name="date_of_birth" value="<%= dateOfBirth.value() %>">
                <span><%= dateOfBirth.error() %></span>
            </div>
            <div>
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="password">
            </div>
            <div>
                <label for="password_confirm">Confirmer le mot de passe</label>
                <input type="password" id="password_confirm" name="password_confirm">
            </div>
            <div>
                <span><%= passwordError %></span>
            </div>
            <div>
                <a href="/login">Se connecter</a>
                <input type="submit" name="register" value="S'inscrire">
            </div>
        </form>
    </section>
</body>
</html>
