package nl.brouwer.guitarshop.model;

public class Customer {
    private String firstName;
    private String lastName;
    private String streetAdress;
    private String city;
    private String phoneNumber;
    private String email;

    public Customer(String fistName, String lastName, String streetAdress, String city, String phoneNumber, String email) {
        this.firstName = fistName;
        this.lastName = lastName;
        this.streetAdress = streetAdress;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
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
}
