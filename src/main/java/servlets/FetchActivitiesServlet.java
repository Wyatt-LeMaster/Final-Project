/**
 *
 *
 *  Wyatt LeMaster
 *  5/2/2023
 *  servlet calls database to fetch activities list object
 *
 *
 */


package servlets;

import models.ActivityModel;

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

@WebServlet(name = "FetchActivitiesServlet", value = "/FetchActivitiesServlet")
public class FetchActivitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  doPost(request, response); dont delete this
        int Activity = 999;
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {


            if (request.getParameter("Activity") != null) {
                Activity = Integer.parseInt(request.getParameter("Activity"));
            }


            try {
                List<ActivityModel> ActivityModel = db.fetchActivities(Activity);
                request.setAttribute("list_of_activities", ActivityModel);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // this is almost certainly the wrong way to do this. but it works right now
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);


        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Activity = 999;
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {



            if(request.getParameter("Activity") != null) {
                Activity = Integer.parseInt(request.getParameter("Activity"));
            }

            try {
                List<ActivityModel> ActivityModel = db.fetchActivities(Activity);
                request.setAttribute("list_of_activities", ActivityModel);



            } catch (SQLException e) {
                e.printStackTrace();
            }


            // sends you back to whatever file you requested from.
            String file = (String) session.getAttribute("file");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
            requestDispatcher.forward(request, response);


        }
    }
}

