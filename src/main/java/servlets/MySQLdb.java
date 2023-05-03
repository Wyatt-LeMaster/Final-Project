/**
 *
 *  JSP Assignment 2
 *  Wyatt LeMaster
 *  Emma Ingram
 *  5/2/2023
 *  Class connects to Database and submits queries for users and books.
 *
 *
 */


package servlets;

import java.security.acl.Group;
import models.ActivityModel;
import models.GroupModel;
import models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/hobbyhelper";  //Database URL

    String username = "root";  // Username
    String password = "1234";  //password
    Connection connection = null;
    static MySQLdb instance = null;


    public MySQLdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }




    public static synchronized MySQLdb getInstance() {
        if (instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }


    public UserModel fetchUser(int user_id) throws SQLException {
        UserModel userModel = null;
        List<Integer> ActivityList = new ArrayList<Integer>();
        int activity = 0;
        // Statement
        String qLogin = "SELECT * FROM user_info WHERE user_id = '" + user_id + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);


        if (resultSet.next()) {
            String fname = resultSet.getString("first_name");
            String lname = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            int id = resultSet.getInt("user_id");
            String name = fname + " " + lname;
            resultSet.close();
            statement.close();

            String qActivityData = "SELECT * FROM user_activity_data WHERE user_id = '" + user_id + "'";
            Statement activityStatement = connection.createStatement();
            ResultSet activityResultSet = activityStatement.executeQuery(qActivityData);

            try {
                while (activityResultSet.next()) {
                    activity = activityResultSet.getInt("activity_id"); // here for debugging
                    ActivityList.add(activity);
                }
            }
            catch(SQLException e)
            {            e.printStackTrace();
            }

            userModel = new UserModel(id, fname, lname, username, password, email, ActivityList); //Uncomment this when working on it. !!!

//        preparedStatement.close();
            return userModel;
        } else {
            return null;
        }

    }



    /**
     * Queries database for user info and compares with inputted data.
     * If match creates userModel object and returns that
     *
     * @param username
     * @param password
     * @return UserModel with user data
     * @throws SQLException
     */
    public UserModel doLogin(String username, String password) throws SQLException {
        UserModel userModel = null;
        List<Integer> ActivityList = new ArrayList<Integer>();
        int activity = 0;
        // Statement
        String qLogin = "SELECT * FROM user_info WHERE username = '" + username + "' AND password = '" + password + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);


        if (resultSet.next()) {
            String fname = resultSet.getString("first_name");
            String lname = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            int id = resultSet.getInt("user_id");
            String name = fname + " " + lname;
            resultSet.close();
            statement.close();

            String qActivityData = "SELECT * FROM user_activity_data WHERE user_id = '" + id + "'";
            Statement activityStatement = connection.createStatement();
            ResultSet activityResultSet = activityStatement.executeQuery(qActivityData);

            try {
                while (activityResultSet.next()) {
                    activity = activityResultSet.getInt("activity_id"); // here for debugging
                    ActivityList.add(activity);
                }
            }
            catch(SQLException e)
            {            e.printStackTrace();
            }

            userModel = new UserModel(id, fname, lname, username, password, email, ActivityList); //Uncomment this when working on it. !!!

//        preparedStatement.close();
            return userModel;
        } else {
            return null;
        }

    }

    /**
     *
     * accepts user data and inserts into database.
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @return
     * @throws SQLException
     */
    public Boolean register(String username, String password, String firstName, String lastName, String email, List<Integer> activity_list) throws SQLException {
        UserModel userModel = null;


        String qGetID = "SELECT MAX(user_id) FROM user_info";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetID);
        ResultSet IDresult = preparedStatement.executeQuery();


        String UsernameCheck = "SELECT * FROM user_info WHERE username = '" + username + "'";
        PreparedStatement preparedStatementCheck = connection.prepareStatement(UsernameCheck);
        ResultSet UsernameResult = preparedStatementCheck.executeQuery();




        if (UsernameResult.next()) {

            return false;
        }
        else{
            int id = 0;
            try {
                while (IDresult.next()) {
                    id = IDresult.getInt("MAX(user_id)");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            id++; // adds one to the last ID
            String qLogin = "INSERT INTO user_info (user_id, first_name, last_name, username, password, email) VALUES ('" + id + "', '" + firstName + "', '" + lastName + "', '" + username + "', '" + password + "', '" + email +"')";
            Statement statement = connection.createStatement();
            try {
                int resultSet = statement.executeUpdate(qLogin);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for(Integer a : activity_list)
            {
                String qActivityinsert = "INSERT INTO user_activity_data (user_id, activity_id) VALUES ('" + id + "', '" + a + "')";
                Statement Actstatement = connection.createStatement();
                try {
                    int AresultSet = Actstatement.executeUpdate(qActivityinsert);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            return true;


        }

    }


    /**
     *
     * Gets book data from database
     *
     * @param
     * @return
     * @throws SQLException
     */
    public List<UserModel> recommendFriend(UserModel user) throws SQLException {
        String qGetFriends = null;
        int activityID = 0;
        List<UserModel> list = new ArrayList<>();
        UserModel possibleFriend = new UserModel(0,"","","","","",new ArrayList<>());

       // selecting the activities from the activity list that don't go to the requesting user.
        qGetFriends = "select distinct user_id from user_activity_data where user_id != '" + user.getUser_id() + "'";

        PreparedStatement preparedStatement = connection.prepareStatement(qGetFriends);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            possibleFriend = fetchUser(resultSet.getInt("user_id"));
            possibleFriend.getActivity_list().retainAll(user.getActivity_list()); // removes all activities that are not shared with the original user
            if(possibleFriend.getActivity_list().size()>0) { // if there are any matching activities add the user to the list.
                list.add(possibleFriend);
            }
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public List<ActivityModel> fetchActivities(int activity_id_in) throws SQLException {
        String qGetActivities = null;
        List<ActivityModel> list = new ArrayList<>();
        if (activity_id_in == 999) {
            qGetActivities = "SELECT * FROM activities";
        }
        else{

            qGetActivities = "SELECT * FROM activities WHERE activityID = '"+activity_id_in+"'";

        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(qGetActivities);

            ResultSet resultSet = preparedStatement.executeQuery();


            ;
            while (resultSet.next()) {
                int activity_id = resultSet.getInt("activityID");
                String name = resultSet.getString("name");
                int category = resultSet.getInt("category");


                ActivityModel activity = new ActivityModel(activity_id, name, category);
                list.add(activity);
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch(SQLException e){
        System.out.println(e);
        }
        return list;
    }

    public List<GroupModel> recommendGroups(UserModel user) throws SQLException {
        List<GroupModel> groupList = new ArrayList<>();
        // TODO

        return groupList;
    }
}


