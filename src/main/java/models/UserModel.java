/*
 *
 *  JSP Assignment 2
 *  Wyatt LeMaster
 *  5/2/2023
 *  Class represents a user object
 *
 *
 */


package models;

import java.util.ArrayList;
import java.util.List;

public class UserModel {


    int user_id;

    String fname, lname, username, password;
    List<Integer> activity_list = new ArrayList<Integer>();


    public UserModel(int user_id, String fname, String lname, String username, String password, List<Integer> activity_list) {
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.activity_list = activity_list;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
