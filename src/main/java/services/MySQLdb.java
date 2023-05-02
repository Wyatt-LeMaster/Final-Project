package services;

//import models.AlbumModel;
import models.BookModel;
import models.TopicModel;
import models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/library_catalog";
    String username = "root";
    String password = "1234";
    Connection connection = null;
    static MySQLdb instance = null;

/*
    public MySQLdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
*/
public MySQLdb() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException | ClassNotFoundException exception) {
        exception.printStackTrace();
    }
}

/*
    public static synchronized MySQLdb getInstance() {
        if(instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }
*/
    public String findBook()
    {
        System.out.print("test");
        return "";
    }


    public static synchronized MySQLdb getInstance() {
        if(instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }



    /*
    public UserModel doLogin(String email, String password) throws SQLException {
        UserModel userModel = null;

        // PreparedStatement

//        String qLogin = "SELECT name FROM users WHERE email = ? AND password = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(qLogin);
//        preparedStatement.setString(1, email);
//        preparedStatement.setString(2, password);
//        ResultSet resultSet = preparedStatement.executeQuery();


        // Statement
        String qLogin = "SELECT name FROM users WHERE email = '"+ email +"' AND password = '"+ password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);


        if(resultSet.next()) {
            String name = resultSet.getString("name");
            userModel = new UserModel(email, name);
        }
        resultSet.close();
        statement.close();
//        preparedStatement.close();
        return userModel;

    }

     */

    public UserModel doLogin(String username, String password) throws SQLException {
        UserModel userModel = null;

        // PreparedStatement

//        String qLogin = "SELECT name FROM users WHERE email = ? AND password = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(qLogin);
//        preparedStatement.setString(1, email);
//        preparedStatement.setString(2, password);
//        ResultSet resultSet = preparedStatement.executeQuery();


        // Statement
        String qLogin = "SELECT * FROM users WHERE username = '"+ username +"' AND password = '"+ password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);


        if(resultSet.next()) {
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            int id = resultSet.getInt("user_id");
            String name = fname + " " + lname;


            userModel = new UserModel(id, fname, lname, username,password);
            resultSet.close();
            statement.close();
//        preparedStatement.close();
            return userModel;
        }

        else {
            return null;
        }

    }

    public Boolean register(String username, String password, String firstName,  String lastName) throws SQLException {
        UserModel userModel = null;

        // PreparedStatement

//        String qLogin = "SELECT name FROM users WHERE email = ? AND password = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(qLogin);
//        preparedStatement.setString(1, email);
//        preparedStatement.setString(2, password);
//        ResultSet resultSet = preparedStatement.executeQuery();

        String qGetID = "SELECT MAX(user_id) FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetID);
        ResultSet IDresult = preparedStatement.executeQuery();


        //String IDcheck = "SELECT MAX(user_id) FROM users";
       // Statement IDstatement = connection.createStatement();

       // ResultSet IDresult = IDstatement.executeQuery(IDcheck);
        int id = 0;
        try {
            while(IDresult.next()) {
                id = IDresult.getInt("MAX(user_id)");
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }

        // Statement
        id++;
        String qLogin = "INSERT INTO users (user_id, fname, lname, username, password) VALUES ('"+id+"', '"+firstName+"', '"+lastName+"', '"+username+"', '"+password+"')";
        Statement statement = connection.createStatement();
        try {
            int resultSet = statement.executeUpdate(qLogin);
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return true;
       // preparedStatement.setString(1, firstName);
       // preparedStatement.setString(2, lastName);
        //preparedStatement.setString(3, username);
        //preparedStatement.setString(4, password);

        //ResultSet resultSet = preparedStatement.executeQuery(qLogin);


        //preparedStatement.close();



    }
/*
    public List<MusicModel> fetchMusic(int albumid) throws SQLException {
        String qGetMusic = null;
        List<MusicModel> list = new ArrayList<>();
        if(albumid == 999) {
            qGetMusic = "SELECT A.album_name, S.song_name, S.song_id, S.album_id FROM albums as A, songs as S WHERE A.album_id = S.album_id";
        } else {
            qGetMusic = "SELECT A.album_name, S.song_name, S.song_id, S.album_id FROM albums as A, songs as S WHERE A.album_id = S.album_id AND S.album_id = '"+albumid+"'";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(qGetMusic);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int song_id = resultSet.getInt("song_id");
            int album_id = resultSet.getInt("album_id");
            String song_name = resultSet.getString("song_name");
            String album_name = resultSet.getString("album_name");
            MusicModel musicModel = new MusicModel(song_id, album_id, song_name, album_name);

            list.add(musicModel);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }


    public List<BookModel> fetchBook(int topic_id_in) throws SQLException {
        String qGetBook = null;
        List<BookModel> list = new ArrayList<>();
        if(topic_id_in == 999) {
            qGetBook = "SELECT B.book_id, B.topic_id, B.book_name, B.author_id, B.is_available FROM books as B";
        }
        else {

            qGetBook = "SELECT B.book_id, B.topic_id, B.book_name, B.author_id, B.is_available FROM books as B WHERE B.topic_id = '" + topic_id_in + "'";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(qGetBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int book_id = resultSet.getInt("book_id");
            int topic_id = resultSet.getInt("topic_id");
            String book_name = resultSet.getString("book_name");
            int author_id = resultSet.getInt("author_id");
            int is_available = resultSet.getInt("is_available");

            BookModel bookModel = new BookModel( book_id,  topic_id, book_name, author_id, is_available);

            list.add(bookModel);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }
*/
    public List<BookModel> fetchBook(int topic_ID) throws SQLException {
        String qGetBook = null;
        List<BookModel> list = new ArrayList<>();
        if(topic_ID == 999) {
            qGetBook = "SELECT B.book_id, B.topic_id, B.book_name, B.author_id, B.is_available, A.author_name FROM books as B, authors as A WHERE A.author_id = B.author_id;";
        }
        else {

            qGetBook = "SELECT B.book_id, B.topic_id, B.book_name, B.author_id, B.is_available, A.author_name FROM books as B, authors as A WHERE A.author_id = B.author_id AND B.topic_id = '" + topic_ID + "'";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(qGetBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int book_id = resultSet.getInt("book_id");
            int topic_id = resultSet.getInt("topic_id");
            String book_name = resultSet.getString("book_name");
            int author_id = resultSet.getInt("author_id");
            int is_available = resultSet.getInt("is_available");
            String Author_name = resultSet.getString("author_name");

            BookModel bookModel = new BookModel( book_id,  topic_id, book_name, author_id, is_available);
            bookModel.setAuthor_name(Author_name);
            list.add(bookModel);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public List<TopicModel> fetchTopic(int topic_id_in) throws SQLException {
        String qGetTopic = null;
        List<TopicModel> list = new ArrayList<>();
        if(topic_id_in == 999) {
            qGetTopic = "SELECT T.topic_id, T.topic_name FROM topics as T";
        }
        else {

            qGetTopic = "SELECT T.topic_id, T.topic_name FROM topics as T WHERE T.topic_id = '" + topic_id_in + "'";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(qGetTopic);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int topic_id = resultSet.getInt("topic_id");
            String topic_name = resultSet.getString("topic_name");

            TopicModel topicModel = new TopicModel( topic_id, topic_name);

            list.add(topicModel);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }


    // WHEN USING INSERT/UPDATE/DELETE --> executeUpdate()
    // SELECT --> executeQuery()
    public boolean doReserve(UserModel user, int book_id) throws SQLException {
        boolean result = false;
        int userID = user.getUser_id();
        if (book_id == 999) {

            return false;
        }
        else {

            String qDoReserve = "INSERT INTO reservations VALUES(?, ?)";
            String qBooksUpdate = "UPDATE books SET is_available = ? WHERE book_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(qDoReserve);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, book_id);
            int rows_update = preparedStatement.executeUpdate();

            PreparedStatement preparedStatemenUpdate = connection.prepareStatement(qBooksUpdate);
            preparedStatemenUpdate.setInt(1, 0);
            preparedStatemenUpdate.setInt(2, book_id);

            int Books_update = preparedStatemenUpdate.executeUpdate();
            if (rows_update > 0 && Books_update>0) {
                result = true;
            }
            preparedStatement.close();
            preparedStatemenUpdate.close();
            return result;
        }
    }

    /*
    public List<AlbumModel> fetchAlbums() throws SQLException {
        List<AlbumModel> list = new ArrayList<>();
        String qGetAlbums = "SELECT * FROM albums";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetAlbums);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int album_id = resultSet.getInt("album_id");
            String album_name = resultSet.getString("album_name");
            AlbumModel albumModel = new AlbumModel(album_id, album_name);
            list.add(albumModel);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }


    // WHEN USING INSERT/UPDATE/DELETE --> executeUpdate()
    // SELECT --> executeQuery()
    public boolean doReserve(String email, int song_id) throws SQLException {
        boolean result = false;
        String qDoReserve = "INSERT INTO reserve VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(qDoReserve);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, song_id);
        int rows_update = preparedStatement.executeUpdate();
        if(rows_update > 0) {
            result = true;
        }
        preparedStatement.close();
        return result;
    }

    public List<MusicModel> getReservedMusic(String email) throws SQLException {
        List<MusicModel> list = new ArrayList<>();
        String qGetReserved = "SELECT S.song_id, A.album_id, S.song_name, A.album_name FROM songs as S, albums as A, reserve as R WHERE R.song_id = S.song_id AND S.album_id = A.album_id AND R.email = '"+email+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetReserved);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int album_id = resultSet.getInt("album_id");
            String album_name = resultSet.getString("album_name");
            int song_id = resultSet.getInt("song_id");
            String song_name = resultSet.getString("song_name");
            MusicModel musicModel = new MusicModel(song_id, album_id, song_name, album_name);
            list.add(musicModel);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }

     */
}
