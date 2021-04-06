package presentation;

import javax.swing.*;

public class AdminCRUDUserView {
    public JFrame adminCRUDUserFrame = new JFrame("Add, delete or edit user");
    public JButton addUser = new JButton("Add new user");
    public JButton editUser = new JButton("Edit existing user");
    public JButton deleteUser = new JButton("Delete user user");
    public JTextField id = new JTextField("User id");
    public JTextField username = new JTextField("Username");
    public JTextField password = new JTextField("Password");
    public JTextField role = new JTextField("Role");
}
