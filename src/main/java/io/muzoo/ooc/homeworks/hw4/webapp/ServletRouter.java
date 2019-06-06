package io.muzoo.ooc.homeworks.hw4.webapp;

import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.ErrorServlet;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.HomeServlet;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.LoginServlet;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.UsersServlet;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.users.AddUserServlet;
import io.muzoo.ooc.homeworks.hw4.webapp.servlet.users.EditUserServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private static final List<Class<? extends Routable>> routables = new ArrayList<>();

    static {
        routables.add(HomeServlet.class);
        routables.add(LoginServlet.class);
        routables.add(UsersServlet.class);
        routables.add(ErrorServlet.class);
        routables.add(AddUserServlet.class);
        routables.add(EditUserServlet.class);
    }

    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService){
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        for (Class<? extends Routable> routableClass : routables) {
            try {
                Routable routable = routableClass.newInstance();
                routable.setSecurityService(securityService);
                String name = routable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                ctx.addServletMapping(routable.getMapping(), name);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
