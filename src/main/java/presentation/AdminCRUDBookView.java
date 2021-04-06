package presentation;

import javax.swing.*;

public class AdminCRUDBookView {
    public JFrame adminCRUDBookFrame = new JFrame("Add, delete or edit book");
    public JButton addBook = new JButton("Add new book");
    public JButton editBook = new JButton("Edit existing book");
    public JButton deleteBook = new JButton("Delete book ");
    public JTextField id = new JTextField("Book id");
    public JTextField title = new JTextField("Title");
    public JTextField author = new JTextField("Author");
    public JTextField genre = new JTextField("Genre");
    public JTextField price = new JTextField("Price");
    public JTextField quantity = new JTextField("Quantity");
}
