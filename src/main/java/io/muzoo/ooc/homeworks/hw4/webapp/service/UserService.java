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

    public String get(String field, String username){
        con = DatabaseService.initializeDatabase();
        try {
            ps = con.prepareStatement("select * from users where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) return rs.getString(field);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseService.disconnectDb(rs, ps, con);
        }
        return null;
    }

    public boolean hasUser(String username){
        return get("name", username) != null;
    }

    public List<String[]> getUserList(){
        List<String[]> userList = new ArrayList<>();
        con = DatabaseService.initializeDatabase();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select  * from users");
            while(rs.next()){
                String[] userInfo = {rs.getString("username"), rs.getString("name")};
                userList.add(userInfo);
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

    public boolean add(String username, String password, String name){
        con = DatabaseService.initializeDatabase();
        try{
            ps = con.prepareStatement("insert into users (username, password, name) values (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            DatabaseService.disconnectDb(rs, ps, con);
        }
    }

    public boolean update(String field, String newValue, String username){
        con = DatabaseService.initializeDatabase();
        try{
            ps = con.prepareStatement("update users set " + field + " = ? where username = ?");
            ps.setString(1, newValue);
            ps.setString(2, username);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            DatabaseService.disconnectDb(rs, ps, con);
        }
    }

}