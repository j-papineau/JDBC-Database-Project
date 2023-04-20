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
    @FXML private Pane patientRecordPane;
    @FXML private Pane interactionsPane;
    @FXML private Button patientsTab;
    @FXML private Button departmentsTab;
    @FXML private Button doctorsTab;
    @FXML private Button medicationsTab;
    @FXML private Button patientRecordTab;
    @FXML private Button interactionsTab;
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
    //new procedure by department imports
    @FXML private TextField procNameText;
    @FXML private TextField procNumText;
    @FXML private TextField procDurationText;
    @FXML private TextField procDepCodeText;
    @FXML private TextField procDescText;
    @FXML private Label newProcedureMessage;
    //search proc by dep imports
    @FXML private TableView<Procedure> procByDepTable;
    @FXML private TableColumn<Procedure,String> procDepCol;
    @FXML private TableColumn<Procedure,String> procNameCol;
    @FXML private TableColumn<Procedure,String> procNumCol;
    @FXML private TableColumn<Procedure,String> procDurationCol;
    @FXML private TableColumn<Procedure,String> procDescCol;
    @FXML private TextField searchDepText;
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
    //prescription imports
    @FXML private Label newPreMessage;
    @FXML private TextField newPreName;
    @FXML private TextField newPreDoc;
    @FXML private TextField newPrePat;
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
    //patient record pane imports
    @FXML private TextField searchPatientText;
    @FXML private TableView <Prescription> searchPatPrescriptionTable;
    @FXML private TableColumn<Prescription,String> searchPatPreName;
    @FXML private TableColumn<Prescription,String> searchPatPreDoc;
    //General Patient Information
    @FXML private TextField genPatID;
    @FXML private TextField genPatSSN;
    @FXML private TextField genPatPrim;
    @FXML private TextField genPatSec;
    @FXML private TextField genPatFName;
    @FXML private TextField genPatMInit;
    @FXML private TextField genPatLName;
    @FXML private TextField genPatCurrAdd;
    @FXML private TextField genPatCurrPhone;
    @FXML private TextField genPatCondition;
    @FXML private TextField genPatBDay;
    @FXML private TextField genPatPermCity;
    @FXML private TextField genPatPermState;
    @FXML private TextField genPatPermZip;
    @FXML private TextField genPatPermPhone;
    @FXML private TextField genPatSex;
    @FXML private TextField genPatPermStreet;
    //patient int record
    @FXML private TableView<Interaction> intRecordTable;
    @FXML private TableColumn<Interaction, Integer> intRecordIdCol;
    @FXML private TableColumn<Interaction, Date> intRecordDateCol;
    @FXML private TableColumn<Interaction, String> intRecordTimeCol;
    @FXML private TableColumn<Interaction, String> intRecordDescCol;
    //interaction imports
    @FXML private Label newInterMessage;
    @FXML private TextField interPID;
    @FXML private DatePicker interDate;
    @FXML private TextField interTime;
    @FXML private TextField interDesc;
    //new procedure on patient import
    @FXML private TextField newProcNum;
    @FXML private TextField newProcDur;
    @FXML private TextField newProcDesc;
    @FXML private TextField newProcName;
    @FXML private TextField newProcPID;
    @FXML private TextField newProcDID;
    @FXML private TextField newProcDCode;
    @FXML private TextArea newProcNotes;
    @FXML private DatePicker newProcDate;
    @FXML private TextField newProcTime;
    @FXML private Label newProcMessage;








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

    //TODO add general procedures for each department
    //TODO create patient procedure
    //TODO create interactions
    //TODO pull procedures by docID



    //methods for patients pane
    public void addNewPatient(){
        Patient newPatient = getNewPatientInfo();
        boolean addPatientStatus = db.addPatient(newPatient);
        if(addPatientStatus){
            newPatMessage.setText("Patient added.");
            newPatMessage.setStyle("-fx-text-fill: #00b306");
            newPatMessage.setVisible(true);
            clearNewPatient();
        }
        else{
            newPatMessage.setText("Unable to add Patient.");
            newPatMessage.setStyle("-fx-text-fill: #d41117");
            newPatMessage.setVisible(true);
        }
    }
    public void clearNewPatient(){
        patID.setText("");
        patSSN.setText("");
        patPrimaryDoc.setText("");
        patSecondaryDoc.setText("");
        patFName.setText("");
        patLName.setText("");
        patAddress.setText("");
        patPhone.setText("");
        patPermCity.setText("");
        patPermState.setText("");
        patPermState.setText("");
        patPermStreet.setText("");
        patPermZip.setText("");
        patPermPhone.setText("");
    }
    public Patient getNewPatientInfo(){

        String patientID = patID.getText();
        String ssn = patSSN.getText();
        String primaryDocID = patPrimaryDoc.getText();
        String secondaryDocID = patSecondaryDoc.getText();
        String fname = patFName.getText();
        String lname = patLName.getText();
        String currentAddress = patAddress.getText();
        String currentPhone = patPhone.getText();
        String patientCondition = patConditionBox.getValue().toString();
        Date bdate = Date.valueOf(patBDay.getValue());
        String permCity = patPermCity.getText();
        String permState = patPermState.getText();
        String permStreet = patPermStreet.getText();
        String permZip = patPermZip.getText();
        String permPhone = patPermPhone.getText();
        String sex = sexComboBox.getValue().toString();
        if(sex.equals("Male")){
            sex = "M";
        }
        else if(sex.equals("Female"))
        {
            sex = "F";
        }
        else{
            sex = "O";
        }



        return new Patient(patientID,ssn,primaryDocID,secondaryDocID,fname,lname,currentAddress,
                currentPhone,patientCondition,bdate,permCity,permState,permStreet,permZip,permPhone,sex);
    }
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
        ObservableList<Doctor> doctors;
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

    //add "generic" procedure
    public void addProcedureByDep(){

        String name, num, duration, depCode, desc;
        name = procNameText.getText();
        num = procNumText.getText();
        duration = procDurationText.getText();
        depCode = procDepCodeText.getText();
        desc = procDescText.getText();

        Procedure proc = new Procedure(name, num, duration, depCode, desc);
        Boolean successful = db.addProc(proc);
        if (successful){
            newProcedureMessage.setText("Procedure added.");
            newProcedureMessage.setStyle("-fx-text-fill: #00b306");
            newProcedureMessage.setVisible(true);
            clearNewProcedure();

        }
        else{
            newProcedureMessage.setText("Unable to add procedure.");
            newProcedureMessage.setStyle("-fx-text-fill: #d41117");
            newProcedureMessage.setVisible(true);
        }
    }
    public void clearNewProcedure(){
        procNameText.setText("");
        procNumText.setText("");
        procDurationText.setText("");
        procDepCodeText.setText("");
        procDescText.setText("");
    }
    //search procedures by department
    public void getProcByDep() throws SQLException {
        String search = searchDepText.getText();
        clearProcByDep();

        procDepCol.setCellValueFactory(new PropertyValueFactory<Procedure,String>("depCode"));
        procNameCol.setCellValueFactory(new PropertyValueFactory<Procedure,String>("name"));
        procNumCol.setCellValueFactory(new PropertyValueFactory<Procedure, String>("procNum"));
        procDurationCol.setCellValueFactory(new PropertyValueFactory<Procedure,String>("duration"));
        procDescCol.setCellValueFactory(new PropertyValueFactory<Procedure,String>("description"));
        try{
            ObservableList<Procedure> procedures = FXCollections.observableArrayList();
            procedures = db.searchProcbyDep(search);
            procByDepTable.setItems(procedures);
            procByDepTable.refresh();
            System.out.println("Procedure information loaded.");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Unable to load procedure data.");
        }
    }
    public void clearProcByDep(){
        procByDepTable.getItems().clear();
    }

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
            clearNewMedication();
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
    public void addNewPrescription(){

        String name = newPreName.getText();
        String docID = newPreDoc.getText();
        String patID = newPrePat.getText();

        Prescription newP = new Prescription(name, docID, patID);
        boolean success = db.addPrescription(newP);

        if(success){

            newPreMessage.setText("Prescription added.");
            newPreMessage.setStyle("-fx-text-fill: #00b306");
            newPreMessage.setVisible(true);
            clearNewPre();
        }else{
            newPreMessage.setText("Unable to add prescription.");
            newPreMessage.setStyle("-fx-text-fill: #d41117");
            newPreMessage.setVisible(true);
        }

    }
    public void clearNewPre(){
        newPreName.setText("");
        newPreDoc.setText("");
        newPrePat.setText("");
    }

    //patient record pane methods

    //TODO implement Patient search by ID

    public void searchByPatID(){

        String id = searchPatientText.getText();
        setPrescriptionTable(id);
        setGenPatientInfo(id);
        loadIntRecord(id);

    }
    //same as above method but takes in ID parameter
    public void searchByPatIDRedirect(String id){
        switchToPatientRecordPane();
        setPrescriptionTable(id);
        setGenPatientInfo(id);
        loadIntRecord(id);

    }
    public void getSelectedPatientRow(){

        Patient patient = patientTable.getSelectionModel().getSelectedItem();
        String id = patient.getPatientID();
        searchByPatIDRedirect(id);


    }
    public void setPrescriptionTable(String id){

        searchPatPreName.setCellValueFactory(new PropertyValueFactory<Prescription, String>("name"));
        searchPatPreDoc.setCellValueFactory(new PropertyValueFactory<Prescription,String>("docID"));
        try{
            searchPatPrescriptionTable.setItems(db.searchPrescriptionsByPatientID(id));
            searchPatPrescriptionTable.refresh();
            System.out.println("Prescription data loaded for patient");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to load prescription data for patient.");
        }

    }
    public void setGenPatientInfo(String id){
        //TODO Minit addition
        Patient p = db.getPatFromID(id);

        genPatID.setText(p.getPatientID());
        genPatSSN.setText(p.getSsn());
        genPatPrim.setText(p.getPrimaryDocID());
        genPatSec.setText(p.getSecondaryDocID());
        genPatFName.setText(p.getFName());
        genPatLName.setText(p.getLName());
        genPatCurrAdd.setText(p.getCurrentAddress());
        genPatCurrPhone.setText(p.getCurrentPhone());
        genPatBDay.setText(String.valueOf(p.getBDate()));
        genPatCondition.setText(p.getPatientCondition());
        genPatPermCity.setText(p.getPermCity());
        genPatPermState.setText(p.getPermState());
        genPatPermStreet.setText(p.getPermStreet());
        genPatPermZip.setText(p.getPermZip());
        genPatPermPhone.setText(p.getPermPhone());
        genPatSex.setText(p.getPermPhone());
    }//end setGenPat
    public void loadIntRecord(String id){

        intRecordIdCol.setCellValueFactory(new PropertyValueFactory<Interaction, Integer>("interID"));
        intRecordDateCol.setCellValueFactory(new PropertyValueFactory<Interaction, Date>("date"));
        intRecordTimeCol.setCellValueFactory(new PropertyValueFactory<Interaction, String>("interTime"));
        intRecordDescCol.setCellValueFactory(new PropertyValueFactory<Interaction, String>("interDesc"));

        try{
            intRecordTable.setItems(db.loadInteractions(id));
            intRecordTable.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to get interactions for patient id.");
        }


    }
    public void setPatientInfo(String id){

    }//TODO SETUP information for patient based on query result




    //interaction pane methods

    public void newInteraction(){
        String id = interPID.getText();
        Date date = Date.valueOf(interDate.getValue());
        String time = interTime.getText();
        String desc = interDesc.getText();
        int count = countInteractions(id);
        System.out.println("Current interactions: " + count);
        int interID = count + 1;

        Interaction interaction = new Interaction(interID, id, time, date, desc);
        boolean succesful = db.newInteraction(interaction);
        if(succesful){
            newInterMessage.setText("Interaction added.");
            newInterMessage.setStyle("-fx-text-fill: #00b306");
            newInterMessage.setVisible(true);
            clearNewInt();
        }else{
            newInterMessage.setText("Unable to add interaction");
            newInterMessage.setStyle("-fx-text-fill: #d41117");
            newInterMessage.setVisible(true);
        }
    }
    public void clearNewInt(){
        interPID.setText("");
        interTime.setText("");
        interDesc.setText("");

    }
    public int countInteractions(String id){

        int intCount = 0;
        try {
            intCount = db.countInteractions(id);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to count ints.");
        }

        return intCount;
    }
    public void newProcedureOnPatient(){

        String procNum = newProcNum.getText();
        String duration = newProcDur.getText();
        String description = newProcDesc.getText();
        String name = newProcName.getText();
        String patientID = newProcPID.getText();
        String docID = newProcDID.getText();
        String depCode = newProcDCode.getText();
        String notes = newProcNotes.getText();
        String time = newProcTime.getText();
        Date date = Date.valueOf(newProcDate.getValue());

        Procedure p = new Procedure(procNum,duration,description,name,patientID,docID,depCode,
                notes,date,time);

        Boolean succesful = db.addProc(p);

        if(succesful){

            newProcMessage.setText("Procedure added.");
            newProcMessage.setStyle("-fx-text-fill: #00b306");
            newProcMessage.setVisible(true);
            clearNewProcOnPatient();

        }else{
            newProcMessage.setText("Unable to add Procedure.");
            newProcMessage.setStyle("-fx-text-fill: #d41117");
            newProcMessage.setVisible(true);
        }

    }
    public void clearNewProcOnPatient(){
        newProcNum.setText("");
        newProcDur.setText("");
        newProcDesc.setText("");
        newProcName.setText("");
        newProcPID.setText("");
        newProcDID.setText("");
        newProcDCode.setText("");
        newProcNotes.setText("");
        newProcTime.setText("");
    }

    /*
        Sidebar Menu Control Section :)
     */

    public void switchToDepartmentsPane(){
     //   System.out.println("change to departments");
        departmentsPane.setVisible(true);
        patientsPane.setVisible(false);
        doctorsPane.setVisible(false);
        medicationsPane.setVisible(false);
        patientRecordPane.setVisible(false);
        interactionsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #002b70");
        doctorsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
        patientRecordTab.setStyle("-fx-background-color: #001c4a");
        interactionsTab.setStyle("-fx-background-color: #001c4a");

    }//end departments
    public void switchToPatientsPane(){
      //  System.out.println("change to patients");
        patientsPane.setVisible(true);
        departmentsPane.setVisible(false);
        doctorsPane.setVisible(false);
        medicationsPane.setVisible(false);
        patientRecordPane.setVisible(false);
        interactionsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #002b70");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
        patientRecordTab.setStyle("-fx-background-color: #001c4a");
        interactionsTab.setStyle("-fx-background-color: #001c4a");

    }//end patients
    public void switchToDoctorsPane(){
      //  System.out.println("change to doctors");
        doctorsPane.setVisible(true);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(false);
        patientRecordPane.setVisible(false);
        interactionsPane.setVisible(false);

        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        doctorsTab.setStyle("-fx-background-color: #002b70");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
        patientRecordTab.setStyle("-fx-background-color: #001c4a");
        interactionsTab.setStyle("-fx-background-color: #001c4a");
    }//end doctors
    public void switchToMedicationsPane(){
        doctorsPane.setVisible(false);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(true);
        patientRecordPane.setVisible(false);
        interactionsPane.setVisible(false);

        doctorsTab.setStyle("-fx-background-color: #001c4a");
        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #002b70");
        patientRecordTab.setStyle("-fx-background-color: #001c4a");
        interactionsTab.setStyle("-fx-background-color: #001c4a");
    }
    public void switchToPatientRecordPane(){

        doctorsPane.setVisible(false);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(false);
        patientRecordPane.setVisible(true);
        interactionsPane.setVisible(false);

        doctorsTab.setStyle("-fx-background-color: #001c4a");
        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
        patientRecordTab.setStyle("-fx-background-color: #002b70");
        interactionsTab.setStyle("-fx-background-color: #001c4a");
    }
    public void switchToInteractionsPane(){

        doctorsPane.setVisible(false);
        patientsPane.setVisible(false);
        departmentsPane.setVisible(false);
        medicationsPane.setVisible(false);
        patientRecordPane.setVisible(false);
        interactionsPane.setVisible(true);

        doctorsTab.setStyle("-fx-background-color: #001c4a");
        patientsTab.setStyle("-fx-background-color: #001c4a");
        departmentsTab.setStyle("-fx-background-color: #001c4a");
        medicationsTab.setStyle("-fx-background-color: #001c4a");
        patientRecordTab.setStyle("-fx-background-color: #001c4a");
        interactionsTab.setStyle("-fx-background-color: #002b70");

    }
}//end controller
