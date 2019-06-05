package io.muzoo.ooc.homeworks.hw4.webapp;

import io.muzoo.ooc.homeworks.hw4.webapp.service.SecurityService;

public interface Routable {

    String getMapping();

    void setSecurityService(SecurityService securityService);
}
