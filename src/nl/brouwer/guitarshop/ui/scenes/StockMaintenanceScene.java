package nl.brouwer.guitarshop.ui.scenes;

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
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Article;

public class StockMaintenanceScene {
    private Scene scene;

    public Scene getScene() {
        return scene;
    }
    public StockMaintenanceScene(Database db, MenuBar menu){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        TableView<Article> articleTableView = new TableView<>();
        ObservableList<Article> articles = FXCollections.observableArrayList(db.getArticles());

        TableColumn<Article, String> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<Article, String> brandColumn = new TableColumn<>("Brand");
        TableColumn<Article, String> modelColumn = new TableColumn<>("Model");
        TableColumn<Article, String> acousticColumn = new TableColumn<>("Acoustic");
        TableColumn<Article, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Article, String> priceColumn = new TableColumn<>("Price");
        articleTableView.getColumns().addAll(quantityColumn, brandColumn, modelColumn,
                acousticColumn, typeColumn, priceColumn);

        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        acousticColumn.setCellValueFactory(new PropertyValueFactory<>("acoustic"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        articleTableView.setItems(articles);

        GridPane.setConstraints(menu, 0,0);
        GridPane.setConstraints(articleTableView, 0,1);

        HBox box = new HBox();
        box.setSpacing(20);
        TextField quantityChange = new TextField();
        quantityChange.setPromptText("Quantity");
        Button addButton = new Button("Add");
        Label negateLabel = new Label("Negate");
        CheckBox checkBox = new CheckBox();
        box.getChildren().addAll(quantityChange, checkBox, negateLabel, addButton);
        GridPane.setConstraints(box, 0,2);

        Label Message = new Label();
        GridPane.setConstraints(Message, 0,3);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Article a = articleTableView.getSelectionModel().getSelectedItem();
                    int change = Integer.parseInt(quantityChange.getText());
                    if (change != 0) {
                        if (checkBox.isSelected() && (a.getQuantity() - change >= 0)){
                            a.setQuantity(a.getQuantity() - change);
                        }
                        else if (!checkBox.isSelected()){
                            a.setQuantity(a.getQuantity() + change);
                        }
                        quantityChange.setText(null);
                        quantityChange.setPromptText("Quantity");
                    }
                }
                catch (Exception ex){
                    Message.setStyle("-fx-background-color: red");
                    Message.setText("something went wrong!  "+ex.getMessage());
                }
                articleTableView.refresh();
            }
        });

        grid.getChildren().addAll(menu, articleTableView, box, Message);

        scene = new StyledScene(grid);
    }
}
