package nl.brouwer.guitarshop.ui.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Customer;
import nl.brouwer.guitarshop.ui.scenes.StyledScene;

public class SearchCustomerDialog {

    private Stage window;

    public Stage getStage() {
        return window;
    }

    private Customer selectedCustomer;

    public Customer getGustomer(){
        return selectedCustomer;
    }

    public SearchCustomerDialog(Database db, String input){
        window = new Stage();
        window.setTitle("Guitarshop FX - Add Customer");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        TableView<Customer> customerTableView = new TableView<>();
        GridPane.setConstraints(customerTableView, 0,0);
        ObservableList<Customer> people = FXCollections.observableArrayList(db.getCustomers());
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        if (!input.equals("")) {
            for (Customer c : people) {
                if (c.getFirstName().toLowerCase().equals(input)){
                    customers.add(c);
                }
            }
            people = customers;
        }

        TableColumn<Customer, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Customer, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Customer, String> StreetAdressColumn = new TableColumn<>("Street Address");
        StreetAdressColumn.setCellValueFactory(new PropertyValueFactory<>("streetAdress"));

        TableColumn<Customer, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Customer, String> phoneNumberColumn = new TableColumn<>("Phone #");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        customerTableView.getColumns().addAll(firstNameColumn, lastNameColumn, cityColumn,
                StreetAdressColumn, phoneNumberColumn, emailColumn);
        customerTableView.setItems(people);

       Label Message = new Label();
       GridPane.setConstraints(Message, 0, 1);


        customerTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
                    window.close();
                }
                catch (Exception ex){
                    Message.setText("Someting went Wrong  " + ex.getMessage());
                }
            }
        });
        grid.getChildren().addAll(customerTableView, Message);

        Scene scene = new StyledScene(grid);
        window.setScene(scene);
    }
}
