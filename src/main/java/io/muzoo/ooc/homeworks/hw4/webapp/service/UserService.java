package io.muzoo.ooc.homeworks.hw4.webapp.service;

import io.muzoo.ooc.homeworks.hw4.webapp.User;

import java.util.*;

public class UserService {

    private DatabaseService databaseService = new DatabaseService();
    private Map<String, User> users = new HashMap<>();

    private static UserService userService = new UserService();

    public static UserService getInstance(){
        return userService;
    }

    private UserService(){
        List<User> userList = databaseService.getUserList();
        for (User user : userList){
            users.put(user.getUsername(), user);
        }
    }

    public String getHashedPassword(String username) {
        return users.get(username).getHashedPassword();
    }

    public String getName(String username) {
        return users.get(username).getName();
    }

    public boolean hasUser(String username){
        return users.get(username) != null;
    }

    public boolean addUser(String username, String password, String name){
        String hashedPassword = SecurityService.generateHash(password);
        users.put(username, new User(username, hashedPassword, name));
        return databaseService.add(username, hashedPassword, name);
    }

    public boolean removeUser(String username){
        users.remove(username);
        return databaseService.remove(username);
    }

    public boolean updateUser(String username, String field, String newValue){
        users.get(username).update(field, newValue);
        return databaseService.update(username, field, newValue);
    }

    public List<User> getUsers(){
        return new ArrayList<>(users.values());
    }

}