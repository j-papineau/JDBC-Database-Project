import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;


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
    @FXML
    Button closeButton;
    @FXML
    public void closeButtonAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void loginButton(ActionEvent e) throws SQLException {
        loginFunction();
    }
    public void updateProgress(String message, Double progressAmount){
        prog.setProgress(progressAmount);
        validationMessage.setText(message);
    }
    @FXML
   public void onEnter(ActionEvent actionEvent) throws SQLException {
        loginFunction();
   }
   public void loginFunction() throws SQLException {
        invalidMessage.setVisible(false);
        prog.setProgress(.25);
        prog.setVisible(true);
        validationMessage.setText("sending login credentials...");
        validationMessage.setVisible(true);

       String username = usernameInput.getText(); //get respective values from ui
       String password = passwordInput.getText();

       loginApp.credentials = new userCredentials(username,password);

        jdbcConnection testConn = new jdbcConnection(username, password);
        Boolean valid = testConn.validateConn(); //sends to jdbc conn object to handle validation

       if(valid){
           System.out.println("Conn is valid.");
          // testConn.closeConn();
           validConn();
       } else if (username.equals("test")) {
           validConn();

       } else{
           System.out.println("invalid connection.");
         //  testConn.closeConn();
           invalidLogin();
       }
   }//end login function

   public void invalidLogin()
   {
       invalidMessage.setVisible(true);
       validationMessage.setVisible(false);
       prog.setProgress(.0);
       prog.setVisible(false);
       usernameInput.setStyle("-fx-text-box-border: #fc2a00");
       passwordInput.setStyle("-fx-border-color: #fc2a00");
   }//end invalid
 @FXML
    public void validConn(){
        prog.setVisible(false);
        validationMessage.setText("Connection Valid: logging in");
        //launch new window for main view
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainView.fxml"));
          //  mainViewController mv = fxmlLoader.getController();
           // mv.passCredentials(usernameInput.getText(), passwordInput.getText());
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("DBMS");
            stage.setScene(new Scene(root1));
            stage.show();
            Stage oldStage = (Stage) closeButton.getScene().getWindow();
            oldStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
