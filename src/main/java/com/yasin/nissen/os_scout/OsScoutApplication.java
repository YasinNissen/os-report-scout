package com.yasin.nissen.os_scout;



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

/**
 * Created by ynissen on 7/14/17.
 */
public class OsScoutApplication {
    public static void main(String[] args) {

        JettySever jetty = new JettySever();
        try {
            jetty.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create DataBase Object with correct URL and Headers
        //DataBaseObject mydatabase = new DataBaseObject("jdbc:postgresql://localhost/postgres?user=ynissen","org.postgresql.Driver");

        //Open Connection to DataBase
        //mydatabase.connect();

        /*
        //Store API Response into a List of GitResponses
        JacksonJsonProvider configure = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JerseyClient client = JerseyClientBuilder.createClient(new ClientConfig(configure));
        List<GitResponse> gitResponses = client.target("https://api.github.com/users/zalando/repos?per_page=100")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<GitResponse>>() {});
        //System.out.println(gitResponses.toString());

        //create DataBase Object with correct URL and Headers
        DataBaseObject mydatabase = new DataBaseObject("jdbc:postgresql://localhost/postgres?user=ynissen","org.postgresql.Driver");

        //Open Connection to DataBase
        mydatabase.connect();

        //insert gitResponses into owner objects
        for(GitResponse response : gitResponses) {

            mydatabase.insertOnwer(response);
        }

        // insert gitResonses into Language Objects
        for(GitResponse response : gitResponses) {

            mydatabase.insertLanguage(response);
        }

        //Perform SQL Insert statement to put GitResponses into DataBase
        for(GitResponse response : gitResponses) {

            mydatabase.insertOsProjects(response);
        }




        /* Close Connection to database*/
        //mydatabase.disconnect();


    }
}