package com.example.servlet;


//import com.example.exception.CommandException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/mainWindow")
//public class MainServlet extends HttpServlet {
//
//    public static final String ALLOWED = "allowed";
//    public static final String REDIRECT_COMMAND = "redirectToCommand";
//
//    public static final String ACTION_TYPE = "action";
//    public static final String ERROR_PAGE = "error_page";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        final boolean allowed = (boolean) req.getAttribute(ALLOWED);
//
//        if (allowed) {
//            final String action = req.getParameter(ACTION_TYPE);
//            System.out.println("DO_GET command name " + action);
//
//            try {
//                final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
//                final Command command = commandFactory.createCommand(action, req, resp);
//                final String path = command.execute();
//
//                req.getRequestDispatcher(path).forward(req, resp);
//            } catch (CommandException e) {
//                System.err.println();
//                resp.sendRedirect(ERROR_PAGE);
//            }
//        } else {
//            final String redirectTo = (String) req.getAttribute(REDIRECT_COMMAND);
//            resp.sendRedirect(redirectTo);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        final boolean allowed = (boolean) req.getAttribute(ALLOWED);
//
//        if (allowed) {
//            final String action = req.getParameter(ACTION_TYPE);
//            System.out.println("DO POST command name  " + action);
//
//            try {
//                final CommandFactory commandFactory = CommandFactoryImpl.getInstance();
//                final Command command = commandFactory.createCommand(action, req, resp);
//                final String path = command.execute();
//
//                resp.sendRedirect(path);
//            } catch (CommandException e) {
//                System.err.println();
//                resp.sendRedirect(ERROR_PAGE);
//            }
//        } else {
//            final String redirectTo = (String) req.getAttribute(REDIRECT_COMMAND);
//            resp.sendRedirect(redirectTo);
//        }
//    }
//
//
//}
