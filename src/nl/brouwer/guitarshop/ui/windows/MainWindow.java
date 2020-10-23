package nl.brouwer.guitarshop.ui.windows;

import javafx.scene.control.*;
import nl.brouwer.guitarshop.model.*;
import nl.brouwer.guitarshop.ui.scenes.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.brouwer.guitarshop.data.Database;


public class MainWindow {
    private Stage window;
    public Stage getStage() {
        return window;
    }
    Database db = new Database();

    public MainWindow(User u){
        window = new Stage();
        window.setTitle("Guitarshop FX");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        window.setMinWidth(600);
        window.setMinHeight(600);


        HBox menu = new HBox();
        MenuBar menuBar = new MenuBar();
        if (u instanceof Manager){
            mangerMenu(menuBar, grid, u);
        } else if (u instanceof SalesPerson){
            salesMenu(menuBar, grid, u);
        }
        menu.getChildren().add(menuBar);
        GridPane.setConstraints(menuBar, 0,0);

        DashboardScene ds = new DashboardScene(menuBar, u);
        GridPane.setConstraints(ds.getScene().getRoot(), 0,1);

        grid.getChildren().addAll(menu, ds.getScene().getRoot());

        Scene main = new StyledScene(grid);
        window.setScene(main);
        window.show();
    }
    public void mangerMenu(MenuBar menuBar, GridPane grid, User u){
        Menu homeMenu = new Menu("Home");
        Menu salesMenu = new Menu("Sales");
        Menu stockMenu = new Menu("Stock");

        MenuItem homeItem = new MenuItem("Home");
        MenuItem listOrderItem = new MenuItem("list Orders");
        MenuItem maintainItem = new MenuItem("Maintain");

        homeMenu.getItems().add(homeItem);
        salesMenu.getItems().add(listOrderItem);
        stockMenu.getItems().add(maintainItem);
        menuBar.getMenus().addAll(homeMenu, salesMenu, stockMenu);

        maintainItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StockMaintenanceScene stockMaintenanceScene = new StockMaintenanceScene(db, menuBar);
                grid.getChildren().remove(1);
                grid.getChildren().add(stockMaintenanceScene.getScene().getRoot());
                window.setTitle("Guitarshop FX - Stock maintenance");

            }
        });

        homeItem.setOnAction(actionEvent -> {
            DashboardScene dashboardScene = new DashboardScene(menuBar, u);
            grid.getChildren().remove(1);
            grid.getChildren().add(dashboardScene.getScene().getRoot());
            window.setTitle("Guitarshop FX");

        });
        listOrderItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListOrderScene listOrderScene = new ListOrderScene(db, menuBar);
                grid.getChildren().remove(1);
                grid.getChildren().add(listOrderScene.getScene().getRoot());
                window.setTitle("Guitarshop FX - List orders");

            }
        });
    }
    public void salesMenu(MenuBar menuBar, GridPane grid, User u){
        Menu homeMenu = new Menu("Home");
        Menu salesMenu = new Menu("Sales");

        MenuItem homeItem = new MenuItem("Home");
        MenuItem orderItem = new MenuItem("Order");
        MenuItem listOrderItem = new MenuItem("list Order");

        homeMenu.getItems().add(homeItem);
        salesMenu.getItems().addAll(orderItem, listOrderItem);
        menuBar.getMenus().addAll(homeMenu, salesMenu);


        homeItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DashboardScene dashboardScene = new DashboardScene(menuBar, u);
                grid.getChildren().remove(1);
                grid.getChildren().add(dashboardScene.getScene().getRoot());
                window.setTitle("Guitarshop FX");
            }
        });
        listOrderItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListOrderScene listOrderScene = new ListOrderScene(db, menuBar);
                window.setTitle(window.getTitle() + " - ");
                grid.getChildren().remove(1);
                grid.getChildren().add(listOrderScene.getScene().getRoot());
                window.setTitle("Guitarshop FX - List orders");

            }
        });
        orderItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateOrderScene createOrderScene = new CreateOrderScene(db, menuBar);
                grid.getChildren().remove(1);
                grid.getChildren().add(createOrderScene.getScene().getRoot());
                window.setTitle("Guitarshop FX - Create order");

            }
        });
    }
}
