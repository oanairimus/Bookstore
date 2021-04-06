package presentation;

import javax.swing.*;

public class UserView {
    public JFrame userFrame = new JFrame("User homepage");
    public JButton searchByGenre = new JButton("Search by genre");
    public JButton searchByTitle = new JButton("Search by title");
    public JButton searchByAuthor = new JButton("Search by author");
    public JButton sell = new JButton("Sell");
    public JTextField genre = new JTextField("Genre");
    public JTextField title = new JTextField("Title");
    public JTextField author = new JTextField("Author");
    public JTextField id = new JTextField("Book ID");
    public JTextArea books = new JTextArea();
}
