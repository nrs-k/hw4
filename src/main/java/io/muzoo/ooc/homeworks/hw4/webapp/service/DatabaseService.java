package io.muzoo.ooc.homeworks.hw4.webapp.service;

import java.sql.*;

public class DatabaseService {

    public static Connection initializeDatabase() {
        String dbURL = "jdbc:mysql://localhost:3307/webapp";
        String dbUsername = "root";
        String dbPassword = "123456";

        Connection con = null;

        try {
            con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void disconnectDb(ResultSet rs, Statement st, Connection con) {
        try {
            if(rs!= null) rs.close();
            if(st!= null) st.close();
            if(con!= null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

