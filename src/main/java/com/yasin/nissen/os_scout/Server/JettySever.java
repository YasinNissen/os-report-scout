package com.yasin.nissen.os_scout.Server;


import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.jws.HandlerChain;

public class JettySever {
    //@HandlerChain()
    private Server server;

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] {connector});

        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(BlockingServlet.class, "/os-projects");
        server.setHandler(handler);
        server.start();
    }
}




