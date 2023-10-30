package com.example.servlet.factory.impl;

import com.example.entity.User;
import com.example.exception.CommandException;
import com.example.exception.ServiceException;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ViewUsersTableCommand implements Command {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public ViewUsersTableCommand(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }


    @Override
    public String execute() throws CommandException {

        final String action = request.getParameter("action");
        String page;

        try {
            if (("adding_form").equals(action)) {
               page="/WEB-INF/add_user.jsp";
            } else {
                List<User> users = userService.getAll();

                request.setAttribute("users", users);
                page="/WEB-INF/users.jsp";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
