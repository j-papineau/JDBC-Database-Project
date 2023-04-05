import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class controller {
    @FXML
    TextField usernameInput;
    @FXML
    PasswordField passwordInput;
    @FXML
    Label loginLabel;
    public void loginButton(ActionEvent e){
        System.out.println("login pressed");
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        System.out.println("Username: " + username + " Password: " + password);
    }
    public void changeScene(ActionEvent e){

    }
}
