/**
 *
 *  JSP Assignment 2
 *  Wyatt LeMaster
 *  5/2/2023
 *  servlet calls database to fetch book list object
 *
 *
 */


package servlets;

import models.ActivityModel;
import models.BookModel;
import models.TopicModel;
import models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "FetchRecommendedFriends", value = "/FetchRecommendedFriends")
public class FetchRecommendedFriends extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request, response);


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();


        if (session != null) {
            UserModel user = (UserModel) session.getAttribute("user");


            try {
                List<UserModel> friendList = db.recommendFriend(user);
                request.setAttribute("list_of_friends", friendList);


            } catch (SQLException e) {
                e.printStackTrace();
            }


            String file = (String) session.getAttribute("file");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
            requestDispatcher.forward(request, response);


        }


    }
}

