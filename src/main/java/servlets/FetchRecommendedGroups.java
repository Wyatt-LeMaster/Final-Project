/**
 *
 *  JSP Assignment 2
 *  Emma Ingram
 *  5/3/2023
 *  servlet calls database to fetch groups
 *
 *
 */

package servlets;
import models.GroupModel;
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

@WebServlet(name = "FetchRecommendedGroups", value = "/FetchRecommendedGroups")
public class FetchRecommendedGroups extends HttpServlet {
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
        List<GroupModel> groupList = db.recommendGroups(user);
        request.setAttribute("list_of_groups", groupList);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      String file = (String) session.getAttribute("file");

      RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
      requestDispatcher.forward(request, response);
    }
  }
}
