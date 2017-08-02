package com.yasin.nissen.os_scout;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.yasin.nissen.os_scout.DataBaseOperations.DataBaseObject;
import com.yasin.nissen.os_scout.Model.GitResponse;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ynissen on 7/14/17.
 */
public class OsScoutApplication {
    public static void main(String[] args) {


        //Store API Response into a List of GitResponses
        JacksonJsonProvider configure = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JerseyClient client = JerseyClientBuilder.createClient(new ClientConfig(configure));
        List<GitResponse> gitResponses = client.target("https://api.github.com/users/zalando/repos?per_page=100")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<GitResponse>>() {});

        //create DataBase Object with correct URL and Headers
        DataBaseObject mydatabase = new DataBaseObject("jdbc:postgresql://localhost/ynissen?user=ynissen&password=123password","org.postgresql.Driver");

        //Open Connection to DataBase
        mydatabase.connect();

        //Perform SQL Insert statement to put GitResponses into DataBase
        for(GitResponse response : gitResponses) {


            try {
                mydatabase.insertInto(response);
            } catch (Exception e) {
                System.out.println("ist schon da");

        }}

        //Perform SQL query to retrieve GitResponse Objects from Database into new List of DataBaseObjects
        List<GitResponse> dbResponses = new ArrayList<GitResponse>();

        dbResponses = mydatabase.selectFrom();

        for(GitResponse response : dbResponses) {

            System.out.println(response.toString());

        }

        /* Close Connection to database */
        mydatabase.disconnect();
    }
}
