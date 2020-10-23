package nl.brouwer.guitarshop.ui.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Article;
import nl.brouwer.guitarshop.model.Customer;
import nl.brouwer.guitarshop.ui.dialogs.AddArticleDialog;
import nl.brouwer.guitarshop.ui.dialogs.ConfirmOrderDialog;
import nl.brouwer.guitarshop.ui.dialogs.SearchCustomerDialog;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderScene {
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    private Customer customer;
    List<Article> orderedArticles = new ArrayList<>();

    public CreateOrderScene(Database db, MenuBar menu){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing
        scene = new StyledScene(grid);

        GridPane.setConstraints(menu, 0,0);

        Label createOrderLabel = new Label("Create Order #" + db.getOrderNumber());
        GridPane.setConstraints(createOrderLabel, 0,1);
        createOrderLabel.setStyle("-fx-font-size: 32");

        HBox searchcustomer = new HBox();
        Label customerLabel = new Label("Customer");
        customerLabel.setStyle("-fx-font-size: 22");
        searchcustomer.setSpacing(25);
        TextField customershearchbar = new TextField();
        Button searchButton = new Button("Search");
        searchcustomer.getChildren().addAll(customerLabel,customershearchbar, searchButton);
        GridPane.setConstraints(searchcustomer, 0, 3);

        VBox box1 = new VBox();
        box1.setSpacing(10);
        box1.setPadding(new Insets(5,5,5,5));
        Label firstNamelabel = new Label("First name");
        Label lastNameLabel = new Label("Last name");
        Label streetAddressLabel = new Label("Street address");
        box1.getChildren().addAll(firstNamelabel, lastNameLabel,streetAddressLabel);
        box1.setStyle("-fx-background-color: lightblue");

        VBox box2 = new VBox();
        box2.setSpacing(10);
        box2.setPadding(new Insets(5,5,5,5));
        Label cityLabel = new Label("City");
        Label phoneNumberLabel = new Label("Phone number");
        Label emailLabel = new Label("Email");
        box2.getChildren().addAll(cityLabel, phoneNumberLabel, emailLabel);
        box2.setStyle("-fx-background-color: lightblue");

        HBox box3 = new HBox();
        box3.setSpacing(50);
        box3.setPadding(new Insets(5,5,5,5));
        box3.getChildren().addAll(box1, box2);
        GridPane.setConstraints(box3, 0,5);
        box3.setStyle("-fx-background-color: lightblue");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchInput = customershearchbar.getText().toLowerCase();
                SearchCustomerDialog sc = new SearchCustomerDialog(db, searchInput);
                sc.getStage().initModality((Modality.APPLICATION_MODAL));
                sc.getStage().showAndWait();
                if (sc.getGustomer()!= null){
                    customer = sc.getGustomer();
                    firstNamelabel.setText("First name: " + customer.getFirstName());
                    lastNameLabel.setText("Last name: " +customer.getLastName());
                    streetAddressLabel.setText("Street address: " + customer.getStreetAdress());
                    cityLabel.setText("City: " + customer.getCity());
                    phoneNumberLabel.setText("Phone number: " + customer.getPhoneNumber());
                    emailLabel.setText("Email: " + customer.getEmail());
                }
            }
        });
        HBox box = new HBox();
        box.setSpacing(15);
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button confirmButton = new Button("Confirm");
        Button resetButton = new Button("Reset");
        box.getChildren().addAll(addButton, deleteButton, confirmButton, resetButton);
        GridPane.setConstraints(box, 0, 8);

        Label articleLabel = new Label("Articles");
        GridPane.setConstraints(articleLabel, 0,6);
        articleLabel.setStyle("-fx-font-size: 22");

        TableView<Article> articleTableView = new TableView<>();
        GridPane.setConstraints(articleTableView, 0,7);

        TableColumn<Article, String> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<Article, String> brandColumn = new TableColumn<>("Brand");
        TableColumn<Article, String> modelColumn = new TableColumn<>("Model");
        TableColumn<Article, String> acousticColumn = new TableColumn<>("Acoustic");
        TableColumn<Article, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Article, String> priceColumn = new TableColumn<>("Price");
        articleTableView.getColumns().addAll(quantityColumn, brandColumn, modelColumn,
                acousticColumn, typeColumn, priceColumn);

        Label Message = new Label();
        GridPane.setConstraints(Message, 0,8);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddArticleDialog aa = new AddArticleDialog(db);
                aa.getStage().initModality((Modality.APPLICATION_MODAL));

                aa.getStage().showAndWait();

                if (aa.getSelectedArticle()!=null){
                    orderedArticles.add(aa.getSelectedArticle());

                    ObservableList<Article> articles = FXCollections.observableArrayList(orderedArticles);

                    quantityColumn.setCellValueFactory(a -> new SimpleStringProperty(
                            String.valueOf(a.getValue().getOrderedQuantity())));

                    brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
                    modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
                    acousticColumn.setCellValueFactory(new PropertyValueFactory<>("acoustic"));
                    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                    articleTableView.setItems(articles);

                }
            }
        });
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Message.setVisible(false);
                    ConfirmOrderDialog co = new ConfirmOrderDialog(db, orderedArticles, customer);
                    co.getStage().initModality((Modality.APPLICATION_MODAL));

                    co.getStage().showAndWait();
                    CreateOrderScene scene = new CreateOrderScene(db, menu);

                    grid.getChildren().removeAll(grid.getChildren());
                    grid.getChildren().add(scene.getScene().getRoot());
                }
                catch (Exception ex){
                    Message.setVisible(true);
                    Message.setText("Someting went wrong  " + ex.getMessage());
                }

            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Message.setVisible(false);
                    Article a = articleTableView.getSelectionModel().getSelectedItem();
                    articleTableView.getItems().remove(a);
                    orderedArticles.remove(a);
                }
                catch (Exception ex){
                    Message.setVisible(true);
                    Message.setText("Something went wrong  " + ex.getMessage());
                }
            }
        });
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!articleTableView.getItems().isEmpty()){
                    articleTableView.getItems().remove(0);
                    orderedArticles.remove(0);
                }
                customer = null;
                customershearchbar.setText("");
                firstNamelabel.setText("First name: ");
                lastNameLabel.setText("Last name: ");
                streetAddressLabel.setText("Street address: ");
                cityLabel.setText("City: ");
                phoneNumberLabel.setText("Phone number: ");
                emailLabel.setText("Email: ");
            }
        });

        grid.getChildren().addAll(menu, createOrderLabel, searchcustomer, articleLabel, box3, box, articleTableView);
    }


}
