package com.mycompany.mavenproject9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class dbConnection {

    //declaration of fields given to constructior during call
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;
    private String url = null;

    /*
    constructor of dbConnection class. Fields:
    jdbc- Java DataBase Connectivity (in this calse- ConnectorJ for mysql),
    url2- database to which we connect,
    user- username,
    password- user's password
    */
    public dbConnection(String jdbc, String url2, String user, String password) {
        try {
            //"com.mysql.cj.jdbc.Driver"
            Class.forName(jdbc);
            //"jdbc:mysql://localhost/zpo_lab0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
            url = url2;
            Properties prop = new Properties();
            //"root"
            prop.setProperty("user", user);
            //"bazy1920"
            prop.setProperty("password", password);
            conn = DriverManager.getConnection(url, prop);
            stat = conn.createStatement();
            System.out.println("CONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //method that executes query- used in insert, update and delete statements
    void executeQuery(String query) {
        try {
            stat.execute(query);
            System.out.println("EXECUTED");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    
    //method that returns ResultSet from given query- used in select statements
    ResultSet getResultSet(String query){
        rs=null;
        try{
            executeQuery(query);
            rs=stat.getResultSet();
            System.out.println("SHOWN");
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return rs;
    }

}
