package io.muzoo.ooc.homeworks.hw4.webapp;

import java.sql.*;

public class DatabaseConnection {

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

    protected static void disconnectDb(ResultSet rs, Statement st, Connection con) {
        try {
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

