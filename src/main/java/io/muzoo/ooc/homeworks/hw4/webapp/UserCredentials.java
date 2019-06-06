package io.muzoo.ooc.homeworks.hw4.webapp;

import java.sql.*;

public class UserCredentials {

    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public String getPassword(String username){
        con = DatabaseConnection.initializeDatabase();
        try {
            st = con.prepareStatement("select * from users where username = ?");
            st.setString(1, username);
            rs = st.executeQuery();
            if(rs.next()) return rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseConnection.disconnectDb(rs, st, con);
        }
        return null;
    }

    public boolean hasUser(String username){
        return getPassword(username) != null;
    }

}