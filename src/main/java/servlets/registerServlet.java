package servlets;

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

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    //Password still needs to be verified.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordCON");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (password.equals(passwordConfirm) == true) {
            UserModel userModel = new UserModel(1, firstName, lastName, username, password);


            //String fixed_password ="1234";
            //String fixed_userName = "earpboy";

            HttpSession session = request.getSession();

            session.setAttribute("user", userModel);


            MySQLdb db = MySQLdb.getInstance();
            //if(session.getAttribute("user") != null) {


            Boolean isSuccess = false;
            try {
                isSuccess = db.register(username, password, firstName, lastName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            session.setAttribute("loggedIn", true);
            request.setAttribute("login", true);
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            request.setAttribute("error", "Passwords must match!");
            requestDispatcher.forward(request, response);
        }
    }
}


    //if (username.equals(fixed_userName) && password.equals(fixed_password)) {

    //UserModel userModel = new UserModel(1,"jarod","Luther",username,password);


        /*
        List<BookModel> list = new ArrayList<>();
        list.add(new BookModel(1, 1, "back in black",1, 1));
        list.add(new BookModel(2, 3, "Fire in the Sky",1, 1));
        list.add(new BookModel(3, 1, "Under the black moon",6, 1));
        list.add(new BookModel(1, 1, "Camping or dummies",1, 1));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        session.setAttribute("List_of_Books", list);
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
*/

    //}
    //  else {
    // RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
    // request.setAttribute("error", "Incorrect email or pasword");
    //requestDispatcher.forward(request, response);
    //}

        /*
        // TASKS
        // ONE SERVLET FOR ONE TASK
        // 1. create doLogin() -- DONE
        // 2. fetch music from db -- DONE


        MySQLdb db = MySQLdb.getInstance();
        UserModel userModel = null;
        try {
            userModel = db.doLogin(email, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }




        if (userModel != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", userModel);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/FetchMusicServlet");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "Incorrect email or password..!!!");
            requestDispatcher.forward(request, response);
        }
    }

         */

