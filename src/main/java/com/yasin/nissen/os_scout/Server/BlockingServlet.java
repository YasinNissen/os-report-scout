package com.yasin.nissen.os_scout.Server;

import com.yasin.nissen.os_scout.DataBaseOperations.DataBaseObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.yasin.nissen.os_scout.DataBaseOperations.DataBaseObject;
import com.yasin.nissen.os_scout.Model.GitResponse;
import com.yasin.nissen.os_scout.Server.JettySever;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;


import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class BlockingServlet extends HttpServlet {
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        //create DataBase Object with correct URL and Headers
        DataBaseObject mydatabase = new DataBaseObject("jdbc:postgresql://localhost/postgres?user=ynissen","org.postgresql.Driver");

        //Open Connection to DataBase
        mydatabase.connect();
        /*
        //Store API Response into a List of GitResponses
        JacksonJsonProvider configure = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JerseyClient client = JerseyClientBuilder.createClient(new ClientConfig(configure));
        List<GitResponse> gitResponses = client.target("https://api.github.com/users/zalando/repos?per_page=100")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<GitResponse>>() {});
        //System.out.println(gitResponses.toString());

        //Perform SQL Insert statement to put GitResponses into DataBase
        for(GitResponse responses : gitResponses) {

            mydatabase.insertOsProjects(responses);
        }
        */
        //Perform SQL query to retrieve GitResponse Objects from Database into new List of DataBaseObjects

        response.getWriter().println("{\""+mydatabase.selectFrom()+"\"}");

        //response.getWriter().println("{ \""+mydatabase.selectFrom()+"\"}");
        //response.getWriter().println("{ \"status\": \"ok\"}"+mydatabase.selectFrom());
        //response.getWriter().println("{ \"status\": \"ok\"}");
    }
}
