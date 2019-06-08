package io.muzoo.ooc.homeworks.hw4.webapp.servlet;

import io.muzoo.ooc.homeworks.hw4.webapp.Routable;
import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String mapping = "/login";
    private String currentPath = "/login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(!authorized) {
            RequestDispatcher rd = request.getRequestDispatcher(currentPath);
            rd.include(request, response);
        } else {
            response.sendRedirect("/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            if (securityService.authenticate(username, password, request)){
                response.sendRedirect("/users");
            } else {
                request.setAttribute("error", "Wrong username or password.");
                RequestDispatcher rd = request.getRequestDispatcher(currentPath);
                rd.include(request, response);
            }
        } else {
            request.setAttribute("error", "Username or password is missing.");
            RequestDispatcher rd = request.getRequestDispatcher(currentPath);
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
