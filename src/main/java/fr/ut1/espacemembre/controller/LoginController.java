package fr.ut1.espacemembre.controller;

import fr.ut1.espacemembre.dao.UserDao;
import fr.ut1.espacemembre.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> errors = new HashMap<>();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty()) errors.put("email_error", "Veuillez renseigner l'adresse email");
        if (password == null || password.isEmpty()) errors.put("password_error", "Veuillez renseigner le mot de passe");

        if (!errors.isEmpty()) {
            errors.forEach(request::setAttribute);
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        } else {
            UserDao userDao = new UserDao();
            int userId = userDao.loginByEmailPwd(email, password);
            if (userId == 0) {
                request.setAttribute("general_error", "Mot de passe ou email incorrect");
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("auth", userId);
                response.sendRedirect("/");
            }

        }
    }
}
