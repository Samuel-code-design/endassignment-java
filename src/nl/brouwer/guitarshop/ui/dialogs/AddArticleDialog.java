package nl.brouwer.guitarshop.ui.dialogs;

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
import javafx.stage.Stage;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Article;
import nl.brouwer.guitarshop.ui.scenes.StyledScene;

public class AddArticleDialog {

    private Stage window;
    public Stage getStage() {
        return window;
    }
    private Article selectedArticle;

    public Article getSelectedArticle(){
        return selectedArticle;
    }

    public AddArticleDialog(Database db){
        window = new Stage();
        window.setTitle("Guitarshop FX - Add article");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        ObservableList<Article> articles = FXCollections.observableArrayList(db.getArticles());
        TableView<Article> articleTableView = new TableView<>();
        GridPane.setConstraints(articleTableView, 0,0);

        TableColumn<Article, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableColumn<Article, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<Article, String> acousticColumn = new TableColumn<>("Acoustic");
        acousticColumn.setCellValueFactory(new PropertyValueFactory<>("acoustic"));
        TableColumn<Article, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Article, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        articleTableView.getColumns().addAll(brandColumn, modelColumn, acousticColumn, typeColumn, priceColumn);
        articleTableView.setItems(articles);

        HBox box1 = new HBox();
        Label quantityLabel = new Label("Quantity: ");
        TextField quantityField = new TextField();
        quantityField.setText("1");
        box1.getChildren().addAll(quantityLabel, quantityField);
        box1.setSpacing(10);
        GridPane.setConstraints(box1, 0,1);

        HBox box2 = new HBox();
        Button addbutton = new Button("Add");
        Button cancelButton = new Button("cancel");
        box2.getChildren().addAll(addbutton,cancelButton);
        box2.setSpacing(10);
        GridPane.setConstraints(box2, 0,2);

        Label Message = new Label();
        GridPane.setConstraints(Message, 0,4);

        addbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try
                {
                    Message.setVisible(false);
                    Article a = articleTableView.getSelectionModel().getSelectedItem();
                    int wantedQuanity  = Integer.valueOf(quantityField.getText());

                    if (a.getQuantity() < wantedQuanity || wantedQuanity <= 0) {
                        Message.setText("This number is not allowed there are " + selectedArticle.getQuantity() +
                                " items of this article left");
                        Message.setStyle("-fx-background-color: red");
                    }
                    else {
                        a.setOrderedQuantity(Integer.parseInt(quantityField.getText()));
                        selectedArticle = a;
                        //a.setQuantity(a.getQuantity() - wantedQuanity);
                        window.close();
                    }
                }
                catch (Exception ex){
                    quantityField.setText("not enough in stock");
                    Message.setVisible(true);
                    Message.setText(ex.getMessage());
                }
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedArticle = null;
                window.close();
            }
        });
        grid.getChildren().addAll(articleTableView,box2, box1, Message);

        Scene scene = new StyledScene(grid);
        window.setScene(scene);
    }
}
