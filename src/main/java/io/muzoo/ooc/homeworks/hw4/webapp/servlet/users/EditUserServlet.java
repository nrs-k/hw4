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
        boolean validInput = true;
        if (request.getParameter("save") != null) {
            String newUsername = request.getParameter("newUsername");
            String newName = request.getParameter("newName");
            if (!StringUtils.isBlank(newUsername)) {
                validInput = userService.update("username", newUsername, targetUser);
            }
            if (!StringUtils.isBlank(newName)) userService.update("name", newName, targetUser);
        }

        if (validInput) {
            response.sendRedirect("/users");
        } else {

            String error = "This username already exists.";
            request.setAttribute("error", error);
            request.setAttribute("currentUsername", targetUser);
            request.setAttribute("currentName", userService.get("name", targetUser));
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
