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

public class ViewMainPageCommand implements Command {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserService userService;

    public ViewMainPageCommand(final HttpServletRequest request,
                               final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.userService = new UserServiceImpl();
    }


    @Override
    public String execute() throws CommandException {
        return "/index.jsp";
    }
}
