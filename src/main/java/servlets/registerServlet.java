/**
 *
 *  JSP Assignment 2
 *  Wyatt LeMaster
 *  5/2/2023
 *  servlet collects user data from jsp and registers new user account
 *
 *
 */



package servlets;

import models.ActivityModel;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        List<ActivityModel> ActivityModelList = null;
        String temp = "";
        String check = "";
        List<Integer> userActivities = new ArrayList<Integer>();
        try {
            ActivityModelList = db.fetchActivities(999);
           // request.setAttribute("list_of_activities", ActivityModel);


            //List<TopicModel> TopicModelList = db.fetchTopic(999);
            // request.setAttribute("list_of_Topics", TopicModelList);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        for (ActivityModel a: ActivityModelList) {
            check = "Checkbox " + a.getActivityID();
            temp = request.getParameter(check);
            if (temp != null) {
                userActivities.add(Integer.parseInt(temp));
            }

        }



        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordCON");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (password.equals(passwordConfirm) == true) {



            UserModel userModel = new UserModel(1, firstName, lastName, username, password, userActivities);



            session.setAttribute("user", userModel);


            Boolean isSuccess = false;
            try {
                isSuccess = db.register(username, password, firstName, lastName,userActivities);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (isSuccess == false)
            {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                request.setAttribute("error", "username already exists");
                requestDispatcher.forward(request, response);

            }
            else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                session.setAttribute("loggedIn", true);
                request.setAttribute("login", true);
                requestDispatcher.forward(request, response);
            }
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            request.setAttribute("error", "Passwords must match!");
            requestDispatcher.forward(request, response);
        }


    }
}




