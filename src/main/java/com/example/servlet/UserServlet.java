package com.example.servlet;

import com.example.entity.User;
import com.example.exception.ServiceException;
import com.example.exception.ServletCustomException;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@WebServlet(name = "UserServlet", value = "/users")
//@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    private final UserService userService;

    public UserServlet() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = req.getParameter("action");

        if (("add_user").equals(action)) {
            req.getRequestDispatcher("/WEB-INF/add_user.jsp").forward(req, resp);
        } else {
            List<User> users = userService.getAll();

            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String page = "userServlet?action=users";

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String firstName = req.getParameter("firstName");
            String secondName = req.getParameter("secondName");
            String birthDate = req.getParameter("birthDate");


            User user = User.builder()
                    .login(login)
                    .password(password)
                    .firstName(firstName)
                    .secondName(secondName)
                    .birthDate(LocalDate.parse(birthDate))
                    .banned(false)
                    .deleted(false)
                    .build();


            userService.add(user);
            req.setAttribute("user", user);

            resp.sendRedirect(page);

        } catch (Exception e) {
            throw new ServletCustomException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.getAll();

            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletCustomException(e);
        }
    }
}
