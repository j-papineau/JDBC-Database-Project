import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class mainViewController implements Initializable {

    @FXML private TableView<Employee> employeeTableView;
    @FXML private TableColumn<Employee, String> fnameColumn;
    @FXML private TableColumn<Employee, String> lnameColumn;
    @FXML private TableColumn<Employee, Integer> ssnColumn;
    @FXML private TableColumn<Employee, Date> dateColumn;
    @FXML private TableColumn<Employee, String> addressColumn;
    @FXML private TableColumn<Employee, String> sexColumn;
    @FXML private TableColumn<Employee, Integer> salaryColumn;
    @FXML private TableColumn<Employee, Integer> superColumn;
    @FXML private TableColumn<Employee, Integer> dnoColumn;
    @FXML private Pane patientsPane;
    @FXML private Pane departmentsPane;
    @FXML private Pane doctorsPane;
    @FXML private Button patientsTab;
    @FXML private Button departmentsTab;
    @FXML private Button doctorsTab;

    //test tab imports
    @FXML private TextField fnameText;
    @FXML private TextField lnameText;
    @FXML private TextField ssnText;
    @FXML private TextField bdateText;
    @FXML private TextField addressText;
    @FXML private TextField sexText;
    @FXML private TextField salaryText;
    @FXML private TextField superSSNText;
    @FXML private TextField dnoText;

    //department tab imports
    //@FXML private TableView<Department> departmentTableView;

    //doctor tab imports
    @FXML private TableView<Doctor> docTable;
    @FXML private TableColumn<Doctor, String> docIdCol;
    @FXML private TableColumn<Doctor, String> docPhoneCol;
    @FXML private TableColumn<Doctor, Date> docBdayCol;
    @FXML private TableColumn<Doctor, String> docAddressCol;
    @FXML private TableColumn<Doctor, String> docLNameCol;
    @FXML private TableColumn<Doctor, String> docFNameCol;
    @FXML private TableColumn<Doctor, String> docContactCol;
    @FXML private TableColumn<Doctor, String> docCodeCol;
    @FXML private TableColumn<Doctor, String> docSsnCol;
    @FXML private Label newDoctorMessage;
    //new doc imports
    @FXML private TextField newDocId;
    @FXML private TextField newDocPhone;
    @FXML private DatePicker newDocBday;
    @FXML private TextField newDocAddress;
    @FXML private TextField newDocFName;
    @FXML private TextField newDocLName;
    @FXML private TextField newDocContact;
    @FXML private TextField newDocCode;
    @FXML private TextField newDocSsn;
    //doctor search by DocID imports
    @FXML private TextField docIDSearch;
    @FXML private Label procedureCount;




    String usernamefromLogin;
    String passwordfromLogin;
    DBHandler db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize credentials from object from main
        usernamefromLogin = loginApp.credentials.getUsername();
        passwordfromLogin = loginApp.credentials.getPassword();


        //initialize dbhandler, and conn object on window open. conn does not need to be opened again
        //conn can be closed by calling connObject.closeConn()
        try {
            db = new DBHandler(usernamefromLogin, passwordfromLogin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DB Object Created");
        //prep all panes, set patients to visible first
        patientsPane.setVisible(true);
        departmentsPane.setVisible(false);
        doctorsPane.setVisible(false);
        patientsTab.setStyle("-fx-background-color: #002b70");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #001c4a");

     //print log of credentials from login window
        System.out.println("Initialize : Username: " + usernamefromLogin + "Password: " + passwordfromLogin);



    }//end initialize






    //methods for patients pane
    public void loadPatientData(){
        //set colls
        //patient object

    }//end load patient data
    public ObservableList<Patient> getPatientDate(){
        //call Dbhandler
        ObservableList<Patient> patients = FXCollections.observableArrayList();
      //get OL from method  patients = DBHandler.getPatients();
        return patients;
    }


    //methods for Doctor Pane
    public void loadDoctorData(){

        docIdCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("docID"));
        docContactCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("contactNum"));
        docBdayCol.setCellValueFactory(new PropertyValueFactory<Doctor, Date>("BDate"));
        docAddressCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("address"));
        docFNameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("FName"));
        docLNameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("LName"));
        docPhoneCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("phone"));
        docCodeCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("deptCode"));
        docSsnCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("ssn"));

        try{
            docTable.setItems(getDoctorData());
            System.out.println("Doctor Data Loaded.");
            docTable.refresh();

        } catch (SQLException ev) {
            throw new RuntimeException(ev);
        }
    }
    public void clearDoctorTable(ActionEvent e){
        docTable.getItems().clear();
    }
    public ObservableList<Doctor> getDoctorData() throws SQLException {
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        doctors = DBHandler.getDoctors();

        return doctors;
    }//end get doctors


    public void addNewDoc(){

        Doctor newDoctor = getNewDocInfo();
        boolean addDocStatus = DBHandler.addDoc(newDoctor);
        if(addDocStatus){
            newDoctorMessage.setStyle("-fx-text-fill: #00b306");
            newDoctorMessage.setText("New Doc added.");
            newDoctorMessage.setVisible(true);
            clearNewDoc();
        }
        else{
            newDoctorMessage.setStyle("-fx-text-fill: #d41117");
            newDoctorMessage.setText("Unable to add Doc.");
            newDoctorMessage.setVisible(true);
        }
        //validation of tuple add

    }//end new doc
    public Doctor getNewDocInfo(){

       String id = newDocId.getText();
       String phone = newDocPhone.getText();
       Date bday = Date.valueOf(newDocBday.getValue());
       String address = newDocAddress.getText();
       String fName = newDocFName.getText();
       String lName = newDocLName.getText();
       String contact = newDocContact.getText();
       String dCode = newDocCode.getText();
       String ssn = newDocSsn.getText();

        return new Doctor(id,contact,bday,address,fName,lName,phone,dCode,ssn);
    }//end new doc info

    public void clearNewDoc(){
        newDocId.setText("");
        newDocPhone.setText("");
        //newDocBday.set("");
        newDocAddress.setText("");
        newDocFName.setText("");
        newDocLName.setText("");
        newDocContact.setText("");
        newDocCode.setText("");
        newDocSsn.setText("");
        newDocBday.setValue(LocalDate.now());
    }

   public void proceduresByDoc(){
        //TODO get all procedures completed by doctor by ID
       // procedureCount.setText("80");
   }



    //methods for department pane




    /*
        Sidebar Menu Controller Section :)
     */
    public void switchToDepartmentsPane(ActionEvent e){
        System.out.println("change to departments");
        departmentsPane.setVisible(true);
        patientsPane.setVisible(false);
        doctorsPane.setVisible(false);
        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #002b70");
        doctorsTab.setStyle("-fx-background-color: #001c4a");

    }//end departments
    public void switchToPatientsPane(ActionEvent e){
        System.out.println("change to patients");
        patientsPane.setVisible(true);
        departmentsPane.setVisible(false);
        doctorsPane.setVisible(false);
        patientsTab.setStyle("-fx-background-color: #002b70");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #001c4a");

    }//end patients
    public void switchToDoctorsPane(ActionEvent e){
        System.out.println("change to doctors");
        doctorsPane.setVisible(true);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #002b70");
    }//end doctors
}
