package com.example.servlet;


import com.example.servlet.factory.CommandFactory;
import com.example.servlet.factory.impl.CommandFactoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mainWindow")
public class MainServlet extends HttpServlet {

    public static final String ACTION_TYPE = "action";
    public static final String ERROR_PAGE = "error_page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = getActionType(req);
        System.out.println("DO_GET command name " + action);

        try {
            final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            final Command command = commandFactory.createCommand(action, req, resp);
            final String path = command.execute();

            req.getRequestDispatcher(path).forward(req, resp);
        } catch (Exception e) {
            System.err.println();
          //  resp.sendRedirect("/WEB-INF/error_page.jsp");
            //resp.sendRedirect("error_page");
        }
    }

    private static String getActionType(HttpServletRequest req) {
        return req.getParameter(ACTION_TYPE) == null ? "main_page" : req.getParameter(ACTION_TYPE);
        //  return req.getParameter(ACTION_TYPE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String action = getActionType(req);
        System.out.println("DO POST command name  " + action);

        try {
            final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
            final Command command = commandFactory.createCommand(action, req, resp);
            final String path = command.execute();

            resp.sendRedirect(path);
        } catch (Exception e) {
            System.err.println();
            //resp.sendRedirect("/WEB-INF/error_page.jsp");
     //       resp.sendRedirect("error_page");
        }


    }

}
