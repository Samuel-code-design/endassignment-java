package nl.brouwer.guitarshop.ui.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import nl.brouwer.guitarshop.model.Manager;
import nl.brouwer.guitarshop.model.User;

import java.time.LocalDate;

public class DashboardScene {
    private Scene scene;
    public Scene getScene() {
        return scene;
    }
    public DashboardScene(MenuBar menu, User u){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        String role = "";
        if (u instanceof Manager) {
            role= "manager";
        }
        else {
            role= "salesman";
        }

        Label welcomeLabel = new Label("Welcome " + u.getFirstName() + " " + u.getLastName() +
                "\n\nYour role is: " + role +
                "\ntoday is: " + LocalDate.now());
        GridPane.setConstraints(welcomeLabel, 1,1);

        grid.getChildren().addAll(welcomeLabel, menu);

        scene = new StyledScene(grid);
    }

}
