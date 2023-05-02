package servlets;

//import models.AlbumModel;

import models.UserModel;
import services.MySQLdb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ReserveServlet", value = "/ReserveServlet")
public class ReserveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int book_id = 999;
        boolean wasSuccess = false;
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        UserModel user = (UserModel) session.getAttribute("user");


        if (session != null) {

            if (request.getParameter("Res_Book_ID") != "") {
                book_id = Integer.parseInt(request.getParameter("Res_Book_ID"));
            }


            try {
               wasSuccess = db.doReserve(user,book_id);
               // request.setAttribute("list_of_books", bookModelList);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            String file = (String) session.getAttribute("file");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
            requestDispatcher.forward(request, response);


        }




    }
}

