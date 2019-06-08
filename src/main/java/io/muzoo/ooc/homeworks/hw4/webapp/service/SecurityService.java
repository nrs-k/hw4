package io.muzoo.ooc.homeworks.hw4.webapp.service;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {

    private UserService userService = UserService.getInstance();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        return (username != null && userService.hasUser(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        if (userService.hasUser(username)) {
            String passwordInDB = userService.getHashedPassword(username);
            if (passwordInDB != null) {
                boolean isMatched = BCrypt.checkpw(password, passwordInDB);
                if (isMatched) {
                    request.getSession().setAttribute("username", username);
                    return true;
                }
            }
        }
        return false;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public static String generateHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
