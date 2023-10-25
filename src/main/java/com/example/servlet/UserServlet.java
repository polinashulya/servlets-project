package com.example.servlet;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        System.out.println("alo blyat");

        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
