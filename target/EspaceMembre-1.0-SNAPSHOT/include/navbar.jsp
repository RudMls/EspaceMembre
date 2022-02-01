<%
    int userId = request.getSession().getAttribute("auth") == null ? 0 : (int) request.getSession().getAttribute("auth");
%>

<%!
    String getNavbar(int userId) {
        String navbar = "<li><a href='/'>Accueil</a></li>" +
                "<li><a href='/fiche-appel'>Fiche d'appel</a></li>";
        if (userId != 0) {
            navbar +=
                    "<li><a href=/user/profil>Profil</a></li>" +
                    "<li><a href=/logout>Se déconnecter</a></li>";
        } else {
            navbar +=
                    "<li><a href=/register>S'inscrire</a></li>" +
                    "<li><a href=/login>Se connecter</a></li>";
        }
        return navbar;
    }
%>


<nav>
    <ul>
        <%= getNavbar(userId) %>
    </ul>
    <div><%= userId %></div>
    <div>${sessionScope.auth}</div>
</nav>