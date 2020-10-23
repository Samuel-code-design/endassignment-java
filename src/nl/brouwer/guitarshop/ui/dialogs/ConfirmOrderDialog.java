package nl.brouwer.guitarshop.ui.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.Article;
import nl.brouwer.guitarshop.model.Customer;
import nl.brouwer.guitarshop.model.Order;
import nl.brouwer.guitarshop.ui.scenes.StyledScene;

import java.time.LocalDate;
import java.util.List;

public class ConfirmOrderDialog {
    private Stage window;
    public Stage getStage() {
        return window;
    }


    public ConfirmOrderDialog(Database db, List<Article> articles, Customer c){
        window = new Stage();
        window.setTitle("Guitarshop FX - Confirm order");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        Label customerLabel = new Label("Customer");
        GridPane.setConstraints(customerLabel,0,0);

        Label customerInfoLabel = new Label(c.getFirstName() + " " + c.getLastName() +
                "\n" + c.getStreetAdress() +
                "\n" + c.getCity()+
                "\n" + c.getPhoneNumber()+
                "\n" + c.getEmail());
        GridPane.setConstraints(customerInfoLabel, 0,1);

        Label articleLabel = new Label("QTY\tBrand\tModel\tType\tPrice");
        GridPane.setConstraints(articleLabel, 0,2);

        double totalPrice = 0;
        for (Article a : articles) {
            articleLabel.setText(articleLabel.getText()+ "\n" +
                    a.getOrderedQuantity()+ "\t" + a.getBrand() + "\t" +
                    a.getModel() + "\t" + a.getType() + "\t" + a.getPrice());

            totalPrice += a.getPrice() * a.getOrderedQuantity();
            a.setQuantity(a.getQuantity() - a.getOrderedQuantity());
        }
        Label totalPriceLabel = new Label("Total Price");
        GridPane.setConstraints(totalPriceLabel, 0,3);

        Label totalPriceInfoLabel = new Label(String.valueOf(totalPrice));
        GridPane.setConstraints(totalPriceInfoLabel,1,3);

        Button confirmButton = new Button("Confirm");
        GridPane.setConstraints(confirmButton, 0, 4);
        Label Message = new Label();
        GridPane.setConstraints(Message, 0,5);

        grid.getChildren().addAll(articleLabel, customerInfoLabel, customerLabel, totalPriceInfoLabel,
                totalPriceLabel, confirmButton, Message);


        double finalTotalPrice = totalPrice;
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Order newOrder = new Order(articles, db.getOrderNumber(), LocalDate.now(), c.getFirstName() +
                            " " + c.getLastName(), c.getCity(), c.getPhoneNumber(), c.getEmail(),
                            articles.size(), finalTotalPrice);

                    db.setOrderNumber(db.getOrderNumber() +1);
                    db.addOrder(newOrder);
                    window.close();
                }
                catch (Exception ex){
                    Message.setText("Someting went wrong  "+ ex.getMessage());
                }
            }
        });

        Scene scene = new StyledScene(grid);
        window.setScene(scene);
    }
}
