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

public class AddUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;
    private String mapping = "/users/add";
    private String currentPath = "/users/add.jsp";

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if(authorized) {
            RequestDispatcher rd = request.getRequestDispatcher(currentPath);
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
            if (username.length() > 8) {
                request.setAttribute("error", "The username must not be longer than 8 characters.");
            } else {
                if(StringUtils.isBlank(name)) name = "n/a";
                boolean notDuplicate = userService.addUser(username, password, name);
                if (notDuplicate){
                    response.sendRedirect("/users");
                }
                else {
                    request.setAttribute("error", "This username already exists.");
                }
            }
        } else {
            request.setAttribute("error", "Username or password is missing.");
        }
        RequestDispatcher rd = request.getRequestDispatcher(currentPath);
        rd.include(request, response);
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
