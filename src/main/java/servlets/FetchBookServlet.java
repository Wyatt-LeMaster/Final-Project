package servlets;

//import models.AlbumModel;

import models.BookModel;
import models.TopicModel;
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
import java.util.List;

@WebServlet(name = "FetchBookServlet", value = "/FetchBookServlet")
public class FetchBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topic_id = 999;
        String Book_name = "";
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {
            //if(session.getAttribute("user") != null) {



            if(request.getParameter("topic_id") != null) {
                topic_id = Integer.parseInt(request.getParameter("topic_id"));
            }




            try {
                List<BookModel> bookModelList = db.fetchBook(topic_id);
                request.setAttribute("list_of_books", bookModelList);


                List<TopicModel> TopicModelList = db.fetchTopic(999);
                request.setAttribute("list_of_Topics", TopicModelList);
                // List<AlbumModel> albumModelList = db.fetchAlbums();
                // request.setAttribute("list_of_album", albumModelList);

            } catch (SQLException e) {
                e.printStackTrace();
            }



            String file = (String) session.getAttribute("file");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(file);
            requestDispatcher.forward(request, response);


        }

        //  else {
        //   RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        //    request.setAttribute("error", "Please login to continue..!!!");
        //   requestDispatcher.forward(request, response);
        // }
        // } else {
        //   RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        //  request.setAttribute("error", "Please login to continue..!!!");
        //   requestDispatcher.forward(request, response);
        // }
        // }


    }
}

