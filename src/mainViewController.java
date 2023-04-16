import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class mainViewController implements Initializable {

    //sidebar imports
    @FXML private Pane patientsPane;
    @FXML private Pane departmentsPane;
    @FXML private Pane doctorsPane;
    @FXML private Pane medicationsPane;
    @FXML private Button patientsTab;
    @FXML private Button departmentsTab;
    @FXML private Button doctorsTab;
    @FXML private Button medicationsTab;
    //department tab imports
    @FXML private TableView<Department> departmentTable;
    @FXML private TableColumn<Department,String> depCodeCol;
    @FXML private TableColumn<Department,String> depNameCol;
    @FXML private TableColumn<Department,String> depNumCol;
    @FXML private TableColumn<Department, String> depPhoneCol;
    @FXML private TableColumn<Department, String> depHeadCol;
    //new department imports
    @FXML private TextField depCodeText;
    @FXML private TextField officeNumText;
    @FXML private TextField officePhoneText;
    @FXML private TextField depHeadText;
    @FXML private TextField depNameText;
    @FXML private Label addDepMessage;
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
    //med tab imports
    @FXML private TableView<Medication> medicationsTable;
    @FXML private TableColumn<Medication, String> medNameCol;
    @FXML private TableColumn<Medication, String> medMakerCol;
    @FXML private TableColumn<Medication, String> medDescCol;
    //new med imports
    @FXML private TextField medNameText;
    @FXML private TextField medMakerText;
    @FXML private TextField medDescText;
    @FXML private Label addMedMessage;
    //patient tab imports
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient,String> patIDCol;
    @FXML private TableColumn<Patient,String> patDocCol;
    @FXML private TableColumn<Patient,String> patSSNCol;
    @FXML private TableColumn<Patient,String> patFNameCol;
    @FXML private TableColumn<Patient,String> patLNameCol;
    @FXML private TableColumn<Patient,String> patSexCol;
    @FXML private TableColumn<Patient,String> patAddressCol;
    @FXML private TableColumn<Patient,Date> patBDateCol;
    //add patient imports
    @FXML private ComboBox sexComboBox;
    @FXML private ComboBox patConditionBox;
    @FXML private Label newPatMessage;
    @FXML private TextField patID;
    @FXML private TextField patSSN;
    @FXML private TextField patPrimaryDoc;
    @FXML private TextField patSecondaryDoc;
    @FXML private TextField patFName;
    @FXML private TextField patLName;
    @FXML private TextField patAddress;
    @FXML private TextField patPhone;
    @FXML private TextField patPermCity;
    @FXML private TextField patPermState;
    @FXML private TextField patPermStreet;
    @FXML private TextField patPermZip;
    @FXML private TextField patPermPhone;
    @FXML private DatePicker patBDay;





    String usernamefromLogin;
    String passwordfromLogin;
    DBHandler db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialize credentials from object from main
        usernamefromLogin = loginApp.credentials.getUsername();
        passwordfromLogin = loginApp.credentials.getPassword();

        //set combobox items
        sexComboBox.getItems().clear();
        sexComboBox.getItems().addAll("Male", "Female","Other");
        patConditionBox.getItems().clear();
        patConditionBox.getItems().addAll("Critical","Stable","Fair");

        //initialize dbhandler, and conn object on window open. conn does not need to be opened again
        //conn can be closed by calling connObject.closeConn()
        try {
            db = new DBHandler(usernamefromLogin, passwordfromLogin);
        } catch (SQLException e) {
            System.out.println("Unable to connect to db.");
            throw new RuntimeException(e);
        }
        System.out.println("DB Object Created");
        //prep all panes, set patients to visible first
        switchToPatientsPane();

     //print log of credentials from login window
        System.out.println("Initialize : Username: " + usernamefromLogin + "Password: " + passwordfromLogin);

    }//end initialize


    //methods for patients pane
   public void loadPatientData(){

        patIDCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientID"));
        patSSNCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("ssn"));
        patDocCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("primaryDocID"));
        patFNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("fName"));
        patLNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lName"));
        patSexCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sex"));
        patAddressCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("currentAddress"));
        patBDateCol.setCellValueFactory(new PropertyValueFactory<Patient, Date>("bDate"));

        try{
            patientTable.setItems(getPatientData());
            System.out.println("Patient Data Loaded.");
            patientTable.refresh();
        } catch (SQLException e) {
            System.out.println("Unable to fetch patient data.");
            e.printStackTrace();
        }


   }
    public ObservableList<Patient> getPatientData() throws SQLException {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        patients = db.getPatients();
        return patients;
    }
    public void clearPatientTable(){
        patientTable.getItems().clear();
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
            System.out.println("Unable to fetch doctor data.");
            throw new RuntimeException(ev);
        }
    }
    public void clearDoctorTable(ActionEvent e){
        docTable.getItems().clear();
    }
    public ObservableList<Doctor> getDoctorData() throws SQLException {
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        doctors = db.getDoctors();

        return doctors;
    }//end get doctors


    public void addNewDoc(){

        Doctor newDoctor = getNewDocInfo();
        boolean addDocStatus = db.addDoc(newDoctor);
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

    public void loadDepartmentData() {
        depCodeCol.setCellValueFactory(new PropertyValueFactory<Department,String>("depCode"));
        depNameCol.setCellValueFactory(new PropertyValueFactory<Department,String>("depName"));
        depNumCol.setCellValueFactory(new PropertyValueFactory<Department,String>("officeNum"));
        depPhoneCol.setCellValueFactory(new PropertyValueFactory<Department,String>("officePhone"));
        depHeadCol.setCellValueFactory(new PropertyValueFactory<Department,String>("depHead"));
        try {
            departmentTable.setItems(getDepartmentData());
            System.out.println("Department data Loaded.");
            departmentTable.refresh();
        }catch (SQLException e){
            System.out.println("Unable to load department data.");
            e.printStackTrace();
        }
    }//end load departments

    public void addNewDepartment(){
        Department newDepartment = getNewDepInfo();
        boolean addDepStatus = DBHandler.addDepartment(newDepartment);
        if(addDepStatus){
            addDepMessage.setText("Department Added.");
            addDepMessage.setStyle("-fx-text-fill: #00b306");
            addDepMessage.setVisible(true);
            clearNewDepartment();
        }
        else{
            addDepMessage.setText("Error adding Department.");
            addDepMessage.setStyle("-fx-text-fill: #d41117");
            addDepMessage.setVisible(true);
        }

    }
    public void clearNewDepartment(){
        depCodeText.setText("");
        officeNumText.setText("");
        officePhoneText.setText("");
        depHeadText.setText("");
        depNameText.setText("");
    }
    public Department getNewDepInfo(){
        String depCode = depCodeText.getText();
        String officeNum = officeNumText.getText();
        String officePhone = officePhoneText.getText();
        String depHead = depHeadText.getText();
        String depName = depNameText.getText();

        return new Department(depCode, officeNum, officePhone, depHead, depName);

    }

    public void clearDepartmentData(){
        departmentTable.getItems().clear();
    }

    public ObservableList<Department> getDepartmentData() throws SQLException{

        ObservableList<Department> departments = FXCollections.observableArrayList();
        departments = DBHandler.getDepartments();

        return departments;
    }//end getDepartment


    //methods for Medications pane
    public void loadMedicationData(){
        medNameCol.setCellValueFactory(new PropertyValueFactory<Medication,String>("name"));
        medMakerCol.setCellValueFactory(new PropertyValueFactory<Medication, String>("maker"));
        medDescCol.setCellValueFactory(new PropertyValueFactory<Medication, String>("description"));

        try{
            medicationsTable.setItems(getMedicationData());
            System.out.println("Medication data loaded.");
            medicationsTable.refresh();

        }catch(SQLException e){
            System.out.println("Unable to load medication data.");
        }


    }
    public ObservableList<Medication> getMedicationData() throws SQLException {

        ObservableList<Medication> medications = FXCollections.observableArrayList();
        medications = DBHandler.getMedications();

        return medications;
    }//end getMed
    public void clearMedicationTable(){
        medicationsTable.getItems().clear();
    }
    public void addNewMedication(){
        String name = medNameText.getText();
        String maker = medMakerText.getText();
        String description = medDescText.getText();
        Medication newMed = new Medication(name, maker, description);
        boolean medAdded = DBHandler.addMed(newMed);
        if(medAdded){
            addMedMessage.setText("Medication added.");
            addMedMessage.setStyle("-fx-text-fill: #00b306");
            addMedMessage.setVisible(true);
        }
        else{
            addMedMessage.setText("Unable to add medication.");
            addMedMessage.setStyle("-fx-text-fill: #d41117");
            addMedMessage.setVisible(true);
        }

    }
    public void clearNewMedication(){
        medNameText.setText("");
        medMakerText.setText("");
        medDescText.setText("");
    }


    /*
        Sidebar Menu Control Section :)
     */

    //TODO At medications pane tab update prev methods and initialize

    public void switchToDepartmentsPane(){
     //   System.out.println("change to departments");
        departmentsPane.setVisible(true);
        patientsPane.setVisible(false);
        doctorsPane.setVisible(false);
        medicationsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #002b70");
        doctorsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");

    }//end departments
    public void switchToPatientsPane(){
      //  System.out.println("change to patients");
        patientsPane.setVisible(true);
        departmentsPane.setVisible(false);
        doctorsPane.setVisible(false);
        medicationsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #002b70");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");

    }//end patients
    public void switchToDoctorsPane(){
      //  System.out.println("change to doctors");
        doctorsPane.setVisible(true);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #002b70");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
    }//end doctors
    public void switchToMedicationsPane(){
        doctorsPane.setVisible(false);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(true);

        doctorsTab.setStyle("-fx-background-color: #001c4a");
        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #002b70");
    }
}//end controller
