package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.exception.ServletCustomException;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public DeleteUserCommand(final HttpServletRequest request,
                             final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }

    @Override
    public String execute() throws CommandException {

        try {
            Long userId = Long.valueOf(request.getParameter("id"));
            userService.deleteById(userId);

        } catch (Exception e) {
            throw new ServletCustomException(e);
        }
        return "mainServlet?action=users";
    }
}
