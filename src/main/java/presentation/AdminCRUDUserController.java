package presentation;

import business.UserLogic;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCRUDUserController implements ActionListener {
    private AdminCRUDUserView view = new AdminCRUDUserView();

    public AdminCRUDUserController() {
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.addUser){
            addNewUser();
        }

        if(e.getSource() == view.editUser){
            editUser();
        }

        if(e.getSource() == view.deleteUser){
            deleteUser();
        }

    }

    private void initGUI(){
        view.adminCRUDUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.adminCRUDUserFrame.setSize(1000,500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        GridLayout layout = new GridLayout(0,4);
        layout.setHgap(10);
        layout.setVgap(10);

        view.addUser.addActionListener(this::actionPerformed);
        view.editUser.addActionListener(this::actionPerformed);
        view.deleteUser.addActionListener(this::actionPerformed);

        panel.setLayout(layout);
        panel.add(view.id);
        panel.add(view.username);
        panel.add(view.password);
        panel.add(view.role);
        panel.add(view.addUser);
        panel.add(view.editUser);
        panel.add(view.deleteUser);

        view.adminCRUDUserFrame.add(panel);
        view.adminCRUDUserFrame.setVisible(true);

    }

    private void addNewUser(){
        User newUser = new User(Integer.parseInt(view.id.getText()), view.username.getText(), view.password.getText(), Integer.parseInt(view.role.getText()));
        if(UserLogic.addNewUser(newUser) >= 1){
            JOptionPane.showMessageDialog(null, "User successfully added!");
            return;
        }
        JOptionPane.showMessageDialog(null, "User already exists!");
    }

    private void editUser(){
        User newUser = new User(Integer.parseInt(view.id.getText()), view.username.getText(), view.password.getText(), Integer.parseInt(view.role.getText()));
        if(UserLogic.editUser(newUser) >= 1){
            JOptionPane.showMessageDialog(null, "User successfully edited!");
            return;
        }
        JOptionPane.showMessageDialog(null, "User does not exist!");
    }

    private void deleteUser(){
        User newUser = new User(Integer.parseInt(view.id.getText()), view.username.getText(), view.password.getText(), Integer.parseInt(view.role.getText()));
        if(UserLogic.deleteUser(newUser) >= 1){
            JOptionPane.showMessageDialog(null, "User successfully removed!");
            return;
        }
        JOptionPane.showMessageDialog(null, "User does not exist!");
    }
}
