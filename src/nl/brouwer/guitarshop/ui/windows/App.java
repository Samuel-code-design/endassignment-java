package nl.brouwer.guitarshop.ui.windows;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.brouwer.guitarshop.data.Database;
import nl.brouwer.guitarshop.model.User;
import nl.brouwer.guitarshop.ui.scenes.StyledScene;

import java.util.List;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage window) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(12); //verticale spacing
        grid.setHgap(10); // horizontal spacing

        window.setTitle("Login");

        Label login = new Label("Login");
        GridPane.setConstraints(login,0,0);

        Label username = new Label("username: ");
        GridPane.setConstraints(username, 0,1);

        Label password = new Label("password: ");
        GridPane.setConstraints(password, 0,2);

        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1,1);

        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1,2);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1,3);
        loginButton.setVisible(false);

        Label Message = new Label("");
        GridPane.setConstraints(Message, 1,4);

        grid.getChildren().addAll(login, username, password, usernameInput, passwordInput, loginButton, Message);

        StringProperty stringPropertyPassword = passwordInput.textProperty();

        stringPropertyPassword.addListener(new ChangeListener<String>() {
            @Override //minimaal 6 karakters in wachtwoord voor de knop zichtbaar word
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (passwordInput.getText().length() >= 6){
                    loginButton.setVisible(true);
                }
            }
        });
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Database db = new Database();
                List<User> users = db.getUsers();
                for (User u : users) {
                    if (u.getUserName().equals(usernameInput.getText()) && u.getPassword().equals(passwordInput.getText())){
                        MainWindow d = new MainWindow(u);
                        d.getStage().show();
                        Message.setVisible(false);
                    } else{
                        Message.setVisible(true);
                        Message.setText("someting went wrong try again");
                        Message.setStyle("-fx-background-color: red");
                    }
                }
            }
        });
        Scene scene = new StyledScene(grid);
        window.setScene(scene);
        window.show();
    }
}
