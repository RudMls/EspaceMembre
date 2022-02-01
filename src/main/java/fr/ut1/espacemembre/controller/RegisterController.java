package fr.ut1.espacemembre.controller;

import fr.ut1.espacemembre.dao.UserDao;
import fr.ut1.espacemembre.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> errors = new HashMap<>();
        UserDao userDao = new UserDao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("date_of_birth");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("password_confirm");

        if (firstName == null || firstName.isEmpty()) errors.put("first_name", "Veuillez renseigner ce champ");
        if (lastName == null || lastName.isEmpty()) errors.put("last_name", "Veuillez renseigner ce champ");
        if (email == null || email.isEmpty()) errors.put("email", "Veuillez renseigner ce champ");
        else if (userDao.isEmailExist(email)) errors.put("email", "L'adresse email existe déja");
        if (dateOfBirth == null || dateOfBirth.isEmpty()) errors.put("date_of_birth", "Veuillez renseigner ce champ");
        if (password == null || password.isEmpty() || passwordConfirm == null || passwordConfirm.isEmpty())
            errors.put("password", "Veuillez renseigner ce champ");
        else if (!password.equals(passwordConfirm))
            errors.put("password", "Les mots de passes ne sont pas identiques");

        if (!errors.isEmpty()) {
            errors.forEach(request::setAttribute);
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        } else {
            try {
                userDao.create(new User(
                        firstName,
                        lastName,
                        new Date(sdf.parse(dateOfBirth).getTime()),
                        email,
                        password
                ));
                response.sendRedirect("/view/login.jsp");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
}
