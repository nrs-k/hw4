package io.muzoo.ooc.homeworks.hw4.webapp.servlet;

import io.muzoo.ooc.homeworks.hw4.webapp.Routable;
import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String mapping = "/error";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(authorized) {
            response.sendRedirect("/users");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF" + mapping + ".jsp");
            rd.include(request, response);
        }
    }

    @Override
    public String getMapping() {
        return mapping;
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
