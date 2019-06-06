package io.muzoo.ooc.homeworks.hw4.webapp;

import io.muzoo.ooc.homeworks.hw4.webapp.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCredentials {

    private static UserCredentials userCredentials = new UserCredentials();

    public static UserCredentials getInstance(){
        return userCredentials;
    }

    private UserCredentials(){}

    public String getPassword(String username){
        Connection con = DatabaseService.initializeDatabase();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("select * from users where username = ?");
            st.setString(1, username);
            rs = st.executeQuery();
            if(rs.next()) return rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseService.disconnectDb(rs, st, con);
        }
        return null;
    }

    public boolean hasUser(String username){
        return getPassword(username) != null;
    }

    public List<String> getUserList(){
        List<String> userList = new ArrayList<>();
        Connection con = DatabaseService.initializeDatabase();
        Statement st = null;
        ResultSet rs = null;
        try{
            st = con.createStatement();
            rs = st.executeQuery("select  * from users");
            while(rs.next()){
                userList.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseService.disconnectDb(rs, st, con);
        }
        return userList;
    }
}