<%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 24/01/2022
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
    <%@ include file="/include/navbar.jsp"%>
    <section>
        <h1>Bienvenue</h1>
        <div>${requestScope.general_error}</div>
    </section>
</body>
</html>
