package io.muzoo.ooc.homeworks.hw4.webapp.servlet;

import io.muzoo.ooc.homeworks.hw4.webapp.Routable;
import io.muzoo.ooc.homeworks.hw4.webapp.service.UserService;
import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String mapping = "/users";
    private String currentPath = "WEB-INF" + mapping + ".jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            setTable(request);
            RequestDispatcher rd = request.getRequestDispatcher(currentPath);
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            if (request.getParameter("add") != null) {
                response.sendRedirect("/add");
            } else if (request.getParameter("remove") != null) {
                removeUser(request);
                setTable(request);
                RequestDispatcher rd = request.getRequestDispatcher(currentPath);
                rd.include(request, response);
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    private void removeUser(HttpServletRequest request){
        String targetUser = request.getParameter("user");
        if (!targetUser.equals(request.getSession().getAttribute("username"))) {
            UserService.getInstance().remove(request.getParameter("user"));
        } else {
            String error = "You cannot remove your own username.";
            request.setAttribute("error", error);
        }
    }

    private void setTable(HttpServletRequest request) {
        List<String[]> userList = UserService.getInstance().getUserList();
        request.setAttribute("userList", userList);
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
