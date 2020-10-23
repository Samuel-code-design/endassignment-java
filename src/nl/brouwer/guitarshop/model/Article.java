package nl.brouwer.guitarshop.model;

public class Article {

    private String brand;
    private String model;
    private Boolean acoustic;
    private String type;
    private double price;
    private int quantity;

    private int orderedquantity;

    public int getOrderedQuantity(){
        return  orderedquantity;
    }
    public void setOrderedQuantity(int qty){
        orderedquantity = qty;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getAcoustic() {
        return acoustic;
    }

    public void setAcoustic(Boolean acoustic) {
        this.acoustic = acoustic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Article(String brand, String model, Boolean acoustic, String type, double price, int quantity) {
        this.brand = brand;
        this.model = model;
        this.acoustic = acoustic;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
}
