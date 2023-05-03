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

@WebServlet(name = "FetchActivitiesServlet", value = "/FetchActivitiesServlet")
public class FetchActivitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  doPost(request, response);
        int Activity = 999;
        String Book_name = "";
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {


            if (request.getParameter("Activity") != null) {
                Activity = Integer.parseInt(request.getParameter("Activity"));
            }


            try {
                List<ActivityModel> ActivityModel = db.fetchActivities(Activity);
                request.setAttribute("list_of_activities", ActivityModel);


                //List<TopicModel> TopicModelList = db.fetchTopic(999);
                // request.setAttribute("list_of_Topics", TopicModelList);

            } catch (SQLException e) {
                e.printStackTrace();
            }


            //String file = (String) session.getAttribute("file");

            // if(Objects.equals(file, "index.jsp")){file = "home.jsp";}

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);


        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Activity = 999;
        String Book_name = "";
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {



            if(request.getParameter("Activity") != null) {
                Activity = Integer.parseInt(request.getParameter("Activity"));
            }




            try {
                List<ActivityModel> ActivityModel = db.fetchActivities(Activity);
                request.setAttribute("list_of_activities", ActivityModel);


                //List<TopicModel> TopicModelList = db.fetchTopic(999);
               // request.setAttribute("list_of_Topics", TopicModelList);

            } catch (SQLException e) {
                e.printStackTrace();
            }



            String file = (String) session.getAttribute("file");

           // if(Objects.equals(file, "index.jsp")){file = "home.jsp";}

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
            requestDispatcher.forward(request, response);


        }




    }
}

