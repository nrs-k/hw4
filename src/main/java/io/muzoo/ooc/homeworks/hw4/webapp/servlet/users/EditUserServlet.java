package io.muzoo.ooc.homeworks.hw4.webapp.servlet.users;

import io.muzoo.ooc.homeworks.hw4.webapp.Routable;
import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;
import io.muzoo.ooc.homeworks.hw4.webapp.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String mapping = "/users/edit";
    private String currentPath = "/users/edit.jsp";
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetUser = (String) request.getSession().getAttribute("targetUser");
        if (request.getParameter("save") != null) {
            String newName = request.getParameter("newName");
            if (!StringUtils.isBlank(newName)) userService.updateUser(targetUser, "name", newName);
        }
        response.sendRedirect("/users");
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
