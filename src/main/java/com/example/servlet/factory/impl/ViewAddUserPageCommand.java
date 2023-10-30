package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewAddUserPageCommand implements Command {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ViewAddUserPageCommand(final HttpServletRequest request,
                                  final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    @Override
    public String execute() throws CommandException {
        return "/WEB-INF/add_user.jsp";
    }
}
