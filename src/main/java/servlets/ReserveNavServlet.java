package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "ReserveNavServlet", value = "/ReserveNavServlet")
public class ReserveNavServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        boolean loggedIn = false;
        try {
            loggedIn = (boolean) session.getAttribute("loggedIn");
        }
        catch(Exception e)
        {

        }
        if (loggedIn == true) {

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Friends.jsp");
            request.setAttribute("login", true);
            requestDispatcher.forward(request, response);
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
            session.setAttribute("file","Friends.jsp");
            request.setAttribute("error", "Passwords must match!");
            requestDispatcher.forward(request, response);
        }

    }
}


