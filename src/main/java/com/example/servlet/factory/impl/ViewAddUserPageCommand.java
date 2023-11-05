package com.example.servlet.factory.impl;

import com.example.entity.Country;
import com.example.exception.CommandException;
import com.example.service.CountryService;
import com.example.service.impl.CountryServiceImpl;
import com.example.servlet.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class ViewAddUserPageCommand implements Command {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final CountryService countryService;

    public ViewAddUserPageCommand(final HttpServletRequest request,
                                  final HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.countryService = new CountryServiceImpl();
    }


    @Override
    public String execute() throws CommandException {

        List<Country> countries = this.countryService.findAll();
        request.setAttribute("countries", countries);

        return "/WEB-INF/add_user.jsp";
    }
}
