package io.muzoo.ooc.homeworks.hw4.webapp;

import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class Webapp {

    public static void main(String[] args) {

        File docBase = new File("src/main/webapp/WEB-INF/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);

        SecurityService securityService = new SecurityService();
        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);

        Context ctx;
        try{
            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            ctx.setAltDDName("src/main/webapp/WEB-INF/web.xml");
            servletRouter.init(ctx);

            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
