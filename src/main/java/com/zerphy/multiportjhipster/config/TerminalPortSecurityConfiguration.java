package com.zerphy.multiportjhipster.config;

import io.undertow.security.api.AuthenticationMechanism;
import io.undertow.security.api.SecurityContext;
import io.undertow.server.HttpServerExchange;

public class TerminalPortSecurityConfiguration implements AuthenticationMechanism {

    private int terminalPositionReportPort = 3120;
    private String terminalPath = "/terminal";

    @Override
    public AuthenticationMechanismOutcome authenticate(HttpServerExchange httpServerExchange,
                                                       SecurityContext securityContext) {
        // terminal api on appropriate port
        if (terminalPath.equals(httpServerExchange.getRequestPath())
                && httpServerExchange.getHostPort() == terminalPositionReportPort) {
            return AuthenticationMechanismOutcome.AUTHENTICATED;
        } else if (terminalPath.equals(httpServerExchange.getRequestPath())
                || httpServerExchange.getHostPort() == terminalPositionReportPort) {
            return AuthenticationMechanismOutcome.NOT_AUTHENTICATED;
        } else {
            return AuthenticationMechanismOutcome.NOT_ATTEMPTED;
        }
    }

    @Override
    public ChallengeResult sendChallenge(HttpServerExchange httpServerExchange, SecurityContext securityContext) {
        return ChallengeResult.NOT_SENT;
    }
}
