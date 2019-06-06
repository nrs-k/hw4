package io.muzoo.ooc.homeworks.hw4.webapp.service;

import io.muzoo.ooc.homeworks.hw4.webapp.UserCredentials;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {

    private UserCredentials userCredentials = new UserCredentials();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        return (username != null && userCredentials.hasUser(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String passwordInDB = userCredentials.getPassword(username);
        boolean isMatched = StringUtils.equals(password, passwordInDB);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        }
        return false;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
