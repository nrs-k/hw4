package io.muzoo.ooc.homeworks.hw4.webapp.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    private static UserService userService = new UserService();

    public static UserService getInstance(){
        return userService;
    }

    private UserService(){}

    public String getPassword(String username){
        con = DatabaseService.initializeDatabase();
        try {
            ps = con.prepareStatement("select * from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseService.disconnectDb(rs, ps, con);
        }
        return null;
    }

    public boolean hasUser(String username){
        return getPassword(username) != null;
    }

    public List<String> getUserList(){
        List<String> userList = new ArrayList<>();
        con = DatabaseService.initializeDatabase();
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

    public boolean remove(String username){
        con = DatabaseService.initializeDatabase();
        int deleted = 0;
        try{
            ps = con.prepareStatement("delete from users where username = ?");
            ps.setString(1, username);
            deleted = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseService.disconnectDb(rs, ps, con);
        }
        return deleted != 0;
    }
}