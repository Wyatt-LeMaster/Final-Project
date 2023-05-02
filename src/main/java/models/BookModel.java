package models;

public class BookModel {
    int book_id;
    int topics_id;
    String book_name;



    String Author_name;
    int author_id;
    int is_available;

    public BookModel(int book_id, int topics_id, String book_name, int author_id, int is_available) {
        this.book_id = book_id;
        this.topics_id = topics_id;
        this.book_name = book_name;
        this.author_id = author_id;
        this.is_available = is_available;
        Author_name = "";
    }


    public int getBook_id() {
        return book_id;
    }

    public int getTopics_id() {
        return topics_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getIs_available() {
        return is_available;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setTopics_id(int topics_id) {
        this.topics_id = topics_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setIs_available(int is_available) {
        this.is_available = is_available;
    }

    public String getAuthor_name() {
        return Author_name;
    }

    public void setAuthor_name(String author_name) {
        Author_name = author_name;
    }
}
