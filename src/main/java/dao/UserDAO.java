package dao;

import model.Book;
import model.User;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class UserDAO {
    public static void insertUser(User user) {
        try {
            File source = new File("users.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element users = document.getDocumentElement();

            Element newUser = document.createElement("user");
            Attr attr = document.createAttribute("id");
            attr.setValue(user.getId().toString());
            newUser.setAttributeNode(attr);

            Node username = document.createElement("username");
            username.appendChild(document.createTextNode(user.getUsername()));
            newUser.appendChild(username);

            Node password = document.createElement("password");
            password.appendChild(document.createTextNode(user.getPassword()));
            newUser.appendChild(password);

            Node role = document.createElement("role");
            role.appendChild(document.createTextNode(user.getRole().toString()));
            newUser.appendChild(role);

            users.appendChild(newUser);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(domSource, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers () {

        ArrayList<User> users = new ArrayList<>();

        try {
            File source = new File("users.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("user");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element element = (Element) node;

                Integer id = Integer.parseInt(element.getAttribute("id"));
                String username = element.getElementsByTagName("username").item(0).getTextContent();
                String password = element.getElementsByTagName("password").item(0).getTextContent();
                Integer role = Integer.parseInt(element.getElementsByTagName("role").item(0).getTextContent());

                users.add(new User(id, username, password, role));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void updateUserById(Integer id, User user) {
        try {
            File source = new File("users.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element head = document.getDocumentElement();
            Element bookToDelete = null;

            NodeList nodeList = document.getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element element = (Element) nodeList.item(i);
                Integer currentId = Integer.parseInt(element.getAttribute("id"));

                if(currentId.equals(id)){

                    if(user.getUsername() != null) {
                        Node username = element.getElementsByTagName("username").item(0);
                        Node newUsername = document.createElement("username");
                        newUsername.appendChild(document.createTextNode(user.getUsername()));
                        element.replaceChild(newUsername, username);
                    }

                    if(user.getPassword() != null) {
                        Node password = element.getElementsByTagName("password").item(0);
                        Node newPassword = document.createElement("password");
                        newPassword.appendChild(document.createTextNode(user.getPassword()));
                        element.replaceChild(newPassword, password);
                    }

                    if(user.getRole() != null) {
                        Node role = element.getElementsByTagName("role").item(0);
                        Node newRole = document.createElement("role");
                        newRole.appendChild(document.createTextNode(user.getRole().toString()));
                        element.replaceChild(newRole, role);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(domSource, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserById(Integer id) {
        try {
            File source = new File("users.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element head = document.getDocumentElement();
            Element userToDelete = null;

            NodeList nodeList = document.getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element element = (Element) nodeList.item(i);
                Integer currentId = Integer.parseInt(element.getAttribute("id"));

                if(currentId.equals(id)){
                    userToDelete = element;
                    break;
                }
            }

            if(userToDelete != null){
                head.removeChild(userToDelete);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("users.xml"));
            transformer.transform(domSource, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
