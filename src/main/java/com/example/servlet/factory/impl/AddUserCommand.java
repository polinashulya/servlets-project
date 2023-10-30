package com.example.servlet.factory.impl;

import com.example.entity.User;
import com.example.exception.CommandException;
import com.example.exception.ServletCustomException;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public class AddUserCommand implements Command {


    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public AddUserCommand(final HttpServletRequest request,
                          final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute() throws CommandException {

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String secondName = request.getParameter("secondName");
            String birthDate = request.getParameter("birthDate");


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
            request.setAttribute("user", user);

        } catch (Exception e) {
            throw new ServletCustomException(e);
        }
        return "/WEB-INF/users.jsp";
    }
}
