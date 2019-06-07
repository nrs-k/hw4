package io.muzoo.ooc.homeworks.hw4.webapp;

public class User {
    private String username;
    private String hashedPassword;
    private String name;

    public User(String username, String hashedPassword, String name){
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
