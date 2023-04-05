import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        String css = this.getClass().getResource("loginStyle.css").toExternalForm();
        loginScene.getStylesheets().add(css);
        primaryStage.setTitle("Login");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}