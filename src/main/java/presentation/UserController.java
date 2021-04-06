package presentation;

import business.BookLogic;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserController implements ActionListener {
    private UserView view = new UserView();
    private int search;

    public UserController() {
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.searchByGenre){
            search = 0;
            searchBooks();
        }

        if(e.getSource() == view.searchByTitle){
            search = 1;
            searchBooks();
        }

        if(e.getSource() == view.searchByAuthor){
            search = 2;
            searchBooks();
        }

        if(e.getSource() == view.sell){
            sellBook();
        }
    }

    public void initGUI(){
        view.userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.userFrame.setSize(1000,500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        GridLayout layout = new GridLayout(0,2);
        layout.setHgap(10);
        layout.setVgap(10);

        view.searchByGenre.addActionListener(this::actionPerformed);
        view.searchByTitle.addActionListener(this::actionPerformed);
        view.searchByAuthor.addActionListener(this::actionPerformed);
        view.sell.addActionListener(this::actionPerformed);

        panel.setLayout(layout);
        panel.add(view.genre);
        panel.add(view.searchByGenre);

        panel.add(view.title);
        panel.add(view.searchByTitle);

        panel.add(view.author);
        panel.add(view.searchByAuthor);

        panel.add(view.id);
        panel.add(view.sell);

        panel.add(view.books);

        view.userFrame.add(panel);
        view.userFrame.setVisible(true);
    }

    private void searchBooks(){
        List<Book> books = new ArrayList<>();
        if(search == 0){
            books = BookLogic.getByGenre(view.genre.getText());
        }
        if(search == 1){
            books = BookLogic.getByGenre(view.title.getText());
        }
        if(search == 2){
            books = BookLogic.getByGenre(view.author.getText());
        }

        view.books.setText(BookLogic.toString(books));
    }

    private void sellBook(){
        if(BookLogic.sellBook(Integer.parseInt(view.id.getText())) == 1){
            view.books.setText("Book sold!");
            return;
        }
        view.books.setText("Book not found or insufficient stock");

    }
}
