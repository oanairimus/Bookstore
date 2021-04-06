package presentation;

import business.BookLogic;
import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCRUDBookController implements ActionListener {
    AdminCRUDBookView view = new AdminCRUDBookView();

    public AdminCRUDBookController() {
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.addBook){
            addNewBook();
        }

        if(e.getSource() == view.editBook){
            editBook();
        }

        if(e.getSource() == view.deleteBook){
            deleteBook();
        }
    }

    private void initGUI(){
        view.adminCRUDBookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.adminCRUDBookFrame.setSize(1000,500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(10);
        layout.setVgap(10);

        view.addBook.addActionListener(this::actionPerformed);
        view.editBook.addActionListener(this::actionPerformed);
        view.deleteBook.addActionListener(this::actionPerformed);

        panel.setLayout(layout);
        panel.add(view.id);
        panel.add(view.title);
        panel.add(view.author);
        panel.add(view.genre);
        panel.add(view.price);
        panel.add(view.quantity);
        panel.add(view.addBook);
        panel.add(view.editBook);
        panel.add(view.deleteBook);

        view.adminCRUDBookFrame.add(panel);
        view.adminCRUDBookFrame.setVisible(true);
    }

    private void addNewBook(){
        Book newBook = new Book(Integer.parseInt(view.id.getText()), view.title.getText(), view.author.getText(), view.genre.getText(), Integer.parseInt(view.quantity.getText()), Integer.parseInt(view.price.getText()));
        if(BookLogic.addNewBook(newBook) >= 1){
            JOptionPane.showMessageDialog(null, "Book successfully added!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Book already exists!");
    }

    private void editBook(){
        Book newBook = new Book(Integer.parseInt(view.id.getText()), view.title.getText(), view.author.getText(), view.genre.getText(), Integer.parseInt(view.quantity.getText()), Integer.parseInt(view.price.getText()));
        if(BookLogic.editBook(newBook) >= 1){
            JOptionPane.showMessageDialog(null, "Book successfully edited!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Book does not exist!");
    }

    private void deleteBook(){
        Book newBook = new Book(Integer.parseInt(view.id.getText()), view.title.getText(), view.author.getText(), view.genre.getText(), Integer.parseInt(view.quantity.getText()), Integer.parseInt(view.price.getText()));
        if(BookLogic.deleteBook(newBook) >= 1){
            JOptionPane.showMessageDialog(null, "Book successfully deleted!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Book does not exist!");
    }
}
