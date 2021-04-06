package dao;

import model.Book;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class BookDAO {
    public static void insertBook (Book book) {
        try {
            File source = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element books = document.getDocumentElement();

            Element newBook = document.createElement("book");
            Attr attr = document.createAttribute("id");
            attr.setValue(book.getId().toString());
            newBook.setAttributeNode(attr);

            Node title = document.createElement("title");
            title.appendChild(document.createTextNode(book.getTitle()));
            newBook.appendChild(title);

            Node author = document.createElement("author");
            author.appendChild(document.createTextNode(book.getAuthor()));
            newBook.appendChild(author);

            Node genre = document.createElement("genre");
            genre.appendChild(document.createTextNode(book.getGenre()));
            newBook.appendChild(genre);

            Node quantity = document.createElement("quantity");
            quantity.appendChild(document.createTextNode(book.getQuantity().toString()));
            newBook.appendChild(quantity);

            Node price = document.createElement("price");
            price.appendChild(document.createTextNode(book.getPrice().toString()));
            newBook.appendChild(price);

            books.appendChild(newBook);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.transform(domSource, result);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> getBooks () {

        ArrayList<Book> books = new ArrayList<>();

        try {
            File source = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                Element element = (Element) node;

                Integer id = Integer.parseInt(element.getAttribute("id"));
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                String author = element.getElementsByTagName("author").item(0).getTextContent();
                String genre = element.getElementsByTagName("genre").item(0).getTextContent();
                Integer quantity = Integer.parseInt(element.getElementsByTagName("quantity").item(0).getTextContent());
                Integer price = Integer.parseInt(element.getElementsByTagName("price").item(0).getTextContent());

                books.add(new Book(id, title, author, genre, quantity, price));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public static void updateBookById (Integer id, Book book) {
        try {
            File source = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element head = document.getDocumentElement();
            Element bookToDelete = null;

            NodeList nodeList = document.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {

                Element element = (Element) nodeList.item(i);
                Integer currentId = Integer.parseInt(element.getAttribute("id"));

                if(currentId.equals(id)){

                    if(book.getTitle() != null) {
                        Node title = element.getElementsByTagName("title").item(0);
                        Node newTitle = document.createElement("title");
                        newTitle.appendChild(document.createTextNode(book.getTitle()));
                        element.replaceChild(newTitle, title);
                    }

                    if(book.getAuthor() != null) {
                        Node author = element.getElementsByTagName("author").item(0);
                        Node newAuthor = document.createElement("author");
                        newAuthor.appendChild(document.createTextNode(book.getAuthor()));
                        element.replaceChild(newAuthor, author);
                    }

                    if(book.getGenre() != null) {
                        Node genre = element.getElementsByTagName("genre").item(0);
                        Node newGenre = document.createElement("genre");
                        newGenre.appendChild(document.createTextNode(book.getGenre()));
                        element.replaceChild(newGenre, genre);
                    }

                    if(book.getQuantity() != null) {
                        Node quantity = element.getElementsByTagName("quantity").item(0);
                        Node newQuantity = document.createElement("quantity");
                        newQuantity.appendChild(document.createTextNode(book.getQuantity().toString()));
                        element.replaceChild(newQuantity, quantity);
                    }

                    if(book.getPrice() != null) {
                        Node price = element.getElementsByTagName("price").item(0);
                        Node newPrice = document.createElement("price");
                        newPrice.appendChild(document.createTextNode(book.getPrice().toString()));
                        element.replaceChild(newPrice, price);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.transform(domSource, result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBookById(Integer id) {
        try {
            File source = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            Element head = document.getDocumentElement();
            Element bookToDelete = null;

            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                Integer currentId = Integer.parseInt(element.getAttribute("id"));

                if(currentId.equals(id)){
                    bookToDelete = element;
                    break;
                }
            }

            if(bookToDelete != null){
                head.removeChild(bookToDelete);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.transform(domSource, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
