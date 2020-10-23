package nl.brouwer.guitarshop.ui.scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Article;
import nl.brouwer.guitarshop.model.Order;

import java.util.List;

public class ListOrderScene {
    private Scene scene;

    public Scene getScene() {
        return scene;
    }
    public ListOrderScene(Database db, MenuBar menu){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        TableView<Order> OrderTableView = new TableView<>();
        GridPane.setConstraints(OrderTableView, 0,0);

        ObservableList<Order> orders = FXCollections.observableArrayList(db.getOrders());

        TableColumn<Order, String> orderNumberColumn = new TableColumn<>("Order #");
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        TableColumn<Order, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Order, String> customerNameColumn = new TableColumn<>("Customer Name");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        TableColumn<Order, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<Order, String> phoneNumberColumn = new TableColumn<>("phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Order, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Order, String> countColumn = new TableColumn<>("Count");
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        TableColumn<Order, String> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        OrderTableView.getColumns().addAll(orderNumberColumn, dateColumn,customerNameColumn, cityColumn,
                phoneNumberColumn, emailColumn, countColumn, totalPriceColumn);
        OrderTableView.setItems(orders);

        TableView<Article> articleTableView = new TableView<>();
        articleTableView.setVisible(false);


        OrderTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Order selectedOrder = null;
                articleTableView.setVisible(true);
                try{
                    selectedOrder =  OrderTableView.getSelectionModel().getSelectedItem();
                }
                catch (Exception ex){

                }

                List<Article> articleList = selectedOrder.getOrderedArticles();
                ObservableList<Article> articles = FXCollections.observableArrayList(articleList);

                TableColumn<Article, String> quantityColumn = new TableColumn<>("Quantity");
                TableColumn<Article, String> brandColumn = new TableColumn<>("Brand");
                TableColumn<Article, String> modelColumn = new TableColumn<>("Model");
                TableColumn<Article, String> acousticColumn = new TableColumn<>("Acoustic");
                TableColumn<Article, String> typeColumn = new TableColumn<>("Type");
                TableColumn<Article, String> priceColumn = new TableColumn<>("Price");
                articleTableView.getColumns().addAll(quantityColumn, brandColumn, modelColumn,
                        acousticColumn, typeColumn, priceColumn);

                quantityColumn.setCellValueFactory(a -> new SimpleStringProperty(
                        String.valueOf(a.getValue().getOrderedQuantity())));

                brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
                modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
                acousticColumn.setCellValueFactory(new PropertyValueFactory<>("acoustic"));
                typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                articleTableView.setItems(articles);
            }
        });



        GridPane.setConstraints(menu, 0, 0);
        GridPane.setConstraints(OrderTableView, 0,1);
        GridPane.setConstraints(articleTableView, 0,2);
        grid.getChildren().addAll(menu, OrderTableView, articleTableView);

        scene = new StyledScene(grid);
    }
}
