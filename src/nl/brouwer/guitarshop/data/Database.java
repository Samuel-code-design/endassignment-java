package nl.brouwer.guitarshop.data;
import nl.brouwer.guitarshop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();
    private int orderNumber;

    public Database() {
        orderNumber = 1;

        users.add(new Manager("pieter", "jan", "plaayend", "manager"));
        users.add(new SalesPerson("klaas", "van dam", "plaayer", "salesman"));

        customers.add(new Customer("Jantje", "van Haren", "mentawistraat 13", "Haarlem", "06-38234536","jantje.vanharen@gmail.com"));
        customers.add(new Customer("Kees", "van der Spek", "wittejassenstraat 109", "overveen", "06-58264506","kees.isbekend@hotmail.com"));
        customers.add(new Customer("Lotte", "meijer", "brouwerskade 109", "Haarlem", "06-28464512","lotte.meijer@yahoomail.com"));
        customers.add(new Customer("Mark", "Rutte", "wittehuisstraat 1", "Denhaag", "06-99999998","alleen.samen@corona.com"));

        articles.add(new Article("Fender", "Telecaster", false, "REGULAR", 1200.69, 10));
        articles.add(new Article("Fender", "Precision", false, "BASS", 1300.50, 10));
        articles.add(new Article("Simon Patrick", "Pro Flame Maple", true, "REGULAR", 1920.30, 10));
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order o){
        orders.add(o);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<User> getUsers() {
        return users;
    }
}
