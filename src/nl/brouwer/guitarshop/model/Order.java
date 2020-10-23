package nl.brouwer.guitarshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Article> orderedArticles = new ArrayList<>();

    public Order(List<Article> orderedArticles, int orderNumber, LocalDate date, String customerName,
                 String city, String phoneNumber, String email, int count, double totalPrice) {
        this.orderedArticles = orderedArticles;
        this.orderNumber = orderNumber;
        this.date = date;
        this.customerName = customerName;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public List<Article> getOrderedArticles() {
        return orderedArticles;
    }

    public void setOrderedArticles(List<Article> orderedArticles) {
        this.orderedArticles = orderedArticles;
    }

    private int orderNumber;
    private LocalDate date;
    private String customerName;
    private String city;
    private String phoneNumber;
    private String email;
    private int count;
    private double totalPrice;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
