import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import java.awt.event.KeyEvent;


public class controller {
    @FXML
    TextField usernameInput;
    @FXML
    PasswordField passwordInput;
    @FXML
    Label loginLabel;
    @FXML
    ProgressBar prog;
    @FXML
    Label validationMessage;
    @FXML
    Label invalidMessage;

    public void loginButton(ActionEvent e){
        loginFunction();
    }
    public void updateProgress(String message, Double progressAmount){
        prog.setProgress(progressAmount);
        validationMessage.setText(message);
    }
    @FXML
   public void onEnter(ActionEvent actionEvent){
        loginFunction();
   }
   public void loginFunction(){
        invalidMessage.setVisible(false);
        prog.setProgress(.25);
        prog.setVisible(true);
        validationMessage.setText("sending login credentials...");
        validationMessage.setVisible(true);



        //console logging purposes
        System.out.println("login submit");
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        System.out.println("Username: " + username + " Password: " + password);
        invalidLogin();
   }

   public void invalidLogin()
   {
       invalidMessage.setVisible(true);
       validationMessage.setVisible(false);
       prog.setProgress(.0);
       prog.setVisible(false);
       usernameInput.setStyle("-fx-text-box-border: #fc2a00");
       passwordInput.setStyle("-fx-border-color: #fc2a00");
   }

    public void changeScene(ActionEvent e){

    }
}
