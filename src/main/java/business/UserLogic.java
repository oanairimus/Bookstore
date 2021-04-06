package business;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {

    public static User existsUser(String username){
        List<User> users = new ArrayList<>();
        users = UserDAO.getUsers();

        for (User u : users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public static Integer correctCredentials(String username, String password){
        List<User> users = new ArrayList<>();
        users = UserDAO.getUsers();

        for (User u : users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u.getRole();
            }
        }
        return -1;
    }

    public static int addNewUser(User u){
        if(existsUser(u.getUsername()) == null){
            UserDAO.insertUser(u);
            return 1;
        }
        return -1;
    }

    public static int editUser(User u){
        if(existsUser(u.getUsername()) != null){
            UserDAO.updateUserById(u.getId(), u);
            return 1;
        }
        return -1;
    }

    public static int deleteUser(User u){
        if(existsUser(u.getUsername()) != null){
            UserDAO.deleteUserById(u.getId());
            return 1;
        }
        return -1;
    }

}
