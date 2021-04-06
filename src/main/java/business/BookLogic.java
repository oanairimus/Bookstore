package business;

import dao.BookDAO;
import dao.UserDAO;
import model.Book;
import model.User;
import presentation.LoginController;
import presentation.LoginView;

import java.util.ArrayList;
import java.util.List;

public class BookLogic {
    public static List<Book> getByGenre(String genre){
        List<Book> books = new ArrayList<>();
        List<Book> filteredBooks = new ArrayList<>();
        books = BookDAO.getBooks();


        for(Book b : books){
            if(b.getGenre().equals(genre)){
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }

    public static List<Book> getByTitle(String title){
        List<Book> books = new ArrayList<>();
        List<Book> filteredBooks = new ArrayList<>();
        books = BookDAO.getBooks();


        for(Book b : books){
            if(b.getTitle().equals(title)){
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }

    public static List<Book> getByAuthor(String author){
        List<Book> books = new ArrayList<>();
        List<Book> filteredBooks = new ArrayList<>();
        books = BookDAO.getBooks();


        for(Book b : books){
            if(b.getAuthor().equals(author)){
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }

    public static String toString(List<Book> books){
        String str = "";
        for(Book b : books){
            str += "CODE: " + b.getId() + "---" + b.getTitle() + ", " + b.getAuthor() + ", " + b.getGenre() + ", " + b.getPrice() + ", " + b.getQuantity() + "\n";
        }
        return str;
    }

    private static Book getBookById(int id){
        for(Book b : BookDAO.getBooks()){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }

    public static int sellBook(int id){
        try{
            Book book = new Book();
            book = getBookById(id);
            book.setQuantity(book.getQuantity() - 1);
            if(book.getPrice() >= 0){
                BookDAO.updateBookById(id, book);
                return 1;
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static int addNewBook(Book b){
        if(getBookById(b.getId()) == null){
            BookDAO.insertBook(b);
            return 1;
        }
        return -1;
    }

    public static int editBook(Book b){
        if(getBookById(b.getId()) != null){
            BookDAO.updateBookById(b.getId(), b);
            return 1;
        }
        return -1;
    }

    public static int deleteBook(Book b){
        if(getBookById(b.getId()) != null){
            BookDAO.deleteBookById(b.getId());
            return 1;
        }
        return -1;
    }

}
