package com.example.servlet.factory.impl;

import com.example.exception.CommandException;
import com.example.servlet.Command;
import com.example.servlet.factory.CommandFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

public class CommandFactoryImpl implements CommandFactory {

    @Getter
    private static final CommandFactoryImpl instance = new CommandFactoryImpl();

    private CommandFactoryImpl() {

    }

    @Override
    public Command createCommand(final String action,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)
            throws CommandException {

        switch (action) {
            case "users" -> {
                return new ViewUsersTableCommand(request, response);
            }
            case "adding_form" -> {
                return new ViewAddUserPageCommand(request, response);
            }
            case "add_user" -> {
                return new AddUserCommand(request, response);
            }
            case "delete_user" -> {
                return new DeleteUserCommand(request, response);
            }
            case "main_page" -> {
                return new ViewMainPageCommand(request, response);
            }
        }

        throw new CommandException("No command with name " + action);
    }


}