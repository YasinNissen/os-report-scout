package com.yasin.nissen.os_scout.DataBaseOperations;

import com.yasin.nissen.os_scout.Model.GitResponse;

import java.net.SocketPermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ynissen on 7/24/17.
 */
public class DataBaseObject {
    private String db_url;
    private String db_driver;
    private Connection db_connection;


    public DataBaseObject(String db_url, String db_driver) {
        this.db_url = db_url;
        this.db_driver = db_driver;
    }
    /*connect to database */
    public void connect(){

        try {
            Class.forName(db_driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            db_connection = DriverManager.getConnection(db_url);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*disconnect database */
    public void disconnect(){

        try {
            db_connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Inserts a the GitResponse Objects into os_projects*/
    public void insertOsProjects(GitResponse gitResponse){

        String sqlcommand ="INSERT INTO os_projects(git_id, projectname,language,forks, open_issues,watchers, created, last_update, git_url, owner) " +
                "Values("+gitResponse.getId()
                +",'" +gitResponse.getName()
                +"',(select id from language where language = "+"'"+gitResponse.getLanguage()
                +"')," +gitResponse.getForks_count()
                +"," +gitResponse.getOpen_issues()
                +"," +gitResponse.getWatchers()
                +",'" +gitResponse.getCreated_at()
                +"','" +gitResponse.getUpdated_at()
                +"','" +gitResponse.getHtml_url()
                +"',(select id from owner where owner = "+"'"+gitResponse.getOwner().getLogin()
                +"'))";

        try {
            Statement statement = db_connection.createStatement();
            statement.executeUpdate(sqlcommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*Inserts a the GitResponse Objects into os_projects*/
    public void insertOnwer(GitResponse gitResponse){

        String sqlcommand ="INSERT INTO owner(owner) " +
                "Values('"+gitResponse.getOwner().getLogin() +"')";

        try {
            Statement statement = db_connection.createStatement();
            statement.executeUpdate(sqlcommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*Inserts a the GitResponse Objects into language*/
    public void insertLanguage(GitResponse gitResponse){

        String sqlcommand ="INSERT INTO language(language) " +
                "Values('"+gitResponse.getLanguage()+"')";

        try {
            Statement statement = db_connection.createStatement();
            statement.executeUpdate(sqlcommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*Retrives all Data in a Select query into a List of GitResponses*/
    public List<GitResponse> selectFrom(){
        String sqlcommand ="Select git_id, projectname, forks, open_issues, watchers, created, last_update, git_url, owner.owner, language.language FROM os_projects LEFT JOIN language ON os_projects.language = language.id LEFT JOIN owner ON os_projects.owner = owner.id;";
        //String sqlcommand = "SELECT * FROM os_projects;";
        GitResponse response = new GitResponse();
        List<GitResponse> dbResponses = new ArrayList<GitResponse>();

        try {
            Statement statement = db_connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlcommand);
            while (queryResult.next()) {
                    response.setId(queryResult.getInt("git_id"));
                    response.setName(queryResult.getString("projectname"));
                    response.setLanguage(queryResult.getString("language"));
                    response.setForks_count(queryResult.getInt("forks"));
                    response.setOpen_issues(queryResult.getInt("open_issues"));
                    response.setWatchers(queryResult.getInt("watchers"));
                    response.setCreated_at(queryResult.getString("created"));
                    response.setUpdated_at(queryResult.getString("last_update"));
                    response.setHtml_url(queryResult.getString("git_url"));
                    response.setOwnerName(queryResult.getString("owner"));

                    dbResponses.add(response);
            }


        } catch (SQLException e ) {
                //JDBCTutorialUtilities.printSQLException(e);

        }
        return dbResponses;
    }
}
