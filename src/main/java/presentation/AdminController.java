package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {
    private AdminView view = new AdminView();
    private AdminCRUDBookController adminCRUDBookController;
    private AdminCRUDUserController adminCRUDUserController;

    public AdminController() {
        initGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.addRemoveUser){
            adminCRUDUserController = new AdminCRUDUserController();
        }

        if(e.getSource() == view.addRemoveBook){
            adminCRUDBookController = new AdminCRUDBookController();
        }

        if(e.getSource() == view.generateReport){

        }
    }

    private void initGUI(){
        view.adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.adminFrame.setSize(1000,500);
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        GridLayout layout = new GridLayout(3,1);
        layout.setHgap(10);
        layout.setVgap(10);

        view.addRemoveUser.addActionListener(this::actionPerformed);
        view.addRemoveBook.addActionListener(this::actionPerformed);
        view.generateReport.addActionListener(this::actionPerformed);

        panel.setLayout(layout);
        panel.add(view.addRemoveUser);
        panel.add(view.addRemoveBook);
        panel.add(view.generateReport);

        view.adminFrame.add(panel);
        view.adminFrame.setVisible(true);
    }
}
