package io.muzoo.ooc.homeworks.hw4.webapp;

import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;
import io.muzoo.ooc.homeworks.hw4.webapp.service.UserService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
