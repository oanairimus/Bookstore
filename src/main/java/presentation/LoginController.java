package presentation;

import business.UserLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView view = new LoginView();
    private UserController userController;
    private AdminController adminController;

    public LoginController(){
        initGUI();
    }

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.submit){
            System.out.println(verifyCredentials());

        }
    }

    public void initGUI(){
        view.loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.loginFrame.setSize(1000,500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(10);
        layout.setVgap(10);

        view.submit.addActionListener(this::actionPerformed);

        panel.setLayout(layout);
        panel.add(view.username);
        panel.add(view.password);
        panel.add(view.submit);

        view.loginFrame.add(panel);
        view.loginFrame.setVisible(true);
    }

    public int verifyCredentials(){
        if(UserLogic.existsUser(view.username.getText()) != null){
            if(UserLogic.correctCredentials(view.username.getText(), view.password.getText()) == 0){
                userController = new UserController();
                return 1;
            }
            if(UserLogic.correctCredentials(view.username.getText(), view.password.getText()) == 1){
                adminController = new AdminController();
                return 1;
            }
            return 0;
        }
        return -1;
    }
}
