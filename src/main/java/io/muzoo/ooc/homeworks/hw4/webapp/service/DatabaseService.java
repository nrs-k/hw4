package io.muzoo.ooc.homeworks.hw4.webapp.service;

import io.muzoo.ooc.homeworks.hw4.webapp.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseService {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    protected DatabaseService(){}

    private Connection initializeDatabase() {
        String HOSTNAME = Optional
                .ofNullable(System.getenv("DATABASE_HOSTNAME"))
                .orElse("localhost");
        String PORT = Optional
                .ofNullable(System.getenv("DATABASE_PORT"))
                .orElse("3307");

        System.out.println(HOSTNAME);
        System.out.println(PORT);
        String dbURL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/webapp";

        System.out.println("DATABASE");
        System.out.println(dbURL);

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

    private void disconnectDb(ResultSet rs, Statement st, Connection con) {
        try {
            if(rs!= null) rs.close();
            if(st!= null) st.close();
            if(con!= null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean add(String username, String hashedPassword, String name){
        con = initializeDatabase();
        try{
            ps = con.prepareStatement("insert into users (username, password, name) values (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.setString(3, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            disconnectDb(rs, ps, con);
        }
    }

    protected boolean remove(String username){
        con = initializeDatabase();
        int deleted = 0;
        try{
            ps = con.prepareStatement("delete from users where username = ?");
            ps.setString(1, username);
            deleted = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectDb(rs, ps, con);
        }
        return deleted != 0;
    }

    protected boolean update(String username, String field, String newValue){
        con = initializeDatabase();
        try{
            ps = con.prepareStatement("update users set " + field + " = ? where username = ?");
            ps.setString(1, newValue);
            ps.setString(2, username);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectDb(rs, ps, con);
        }
        return false;
    }

    protected List<User> getUserList() {
        con = initializeDatabase();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from users");
            List<User> userList = new ArrayList<>();
            while (rs.next()){
                String username = rs.getString("username");
                String hashedPassword = rs.getString("password");
                String name = rs.getString("name");
                User user = new User(username, hashedPassword, name);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectDb(rs, st, con);
        }
        return null;
    }
}

