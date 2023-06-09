import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.*;


public class DBHandler {

    String serverName, portNumber, sid, url, username, password;
    static Connection conn;

    //constructor: CONN IS ESTABLISHED DURING CONSTRUCTOR, DO NOT CALL DBHANDLER AS NEW UNLESS RECONNECTING
    public DBHandler(String usernameInput, String passwordInput) throws SQLException {
        //import driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("unable to find class, jdbc.driver");
            e.printStackTrace();
        }

        this.serverName = "cisvm-oracle.unfcsd.unf.edu";
        this.portNumber = "1521";
        this.sid = "orcl";
        this.url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
        this.username = usernameInput;
        this.password = passwordInput;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection(url, username, password);

    }//end constructor

    public static ObservableList<Doctor> getDoctors() throws SQLException {

        ObservableList<Doctor> doctors = FXCollections.observableArrayList();

        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM DOCTOR";
        ResultSet rset = stmt.executeQuery(q);
        System.out.println("Querying Doctors from DB");

        while(rset.next()){

            String docId = rset.getString("DOC_ID");
            String contact = rset.getString("CONTACT_NUM");
            Date bdate = rset.getDate("BDAY");
            String address = rset.getString("ADDRESS");
            String fname = rset.getString("FNAME");
            String lname = rset.getString("LNAME");
            String phone = rset.getString("PHONE");
            String dCode = rset.getString("DEPT_CODE");
            String ssn = rset.getString("SSN");


            Doctor i = new Doctor(docId,contact,bdate,address,fname,lname,phone,dCode,ssn);
            doctors.add(i);

            //System.out.println(i.getlName() + i.getfName());
        }
        return doctors;
    }//end getDocs
    public static ObservableList<Patient> getPatients() throws SQLException {

        ObservableList<Patient> patients = FXCollections.observableArrayList();

        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM PATIENT";
        ResultSet rset = stmt.executeQuery(q);
        System.out.println("Querying Patients from DB");

        while(rset.next()){

            String patientID = rset.getString("PATIENT_ID");
            String ssn = rset.getString("SSN");
            String primaryDocID = rset.getString("PRIMARY_DOC_ID");
            String secondaryDocID = rset.getString("SECONDARY_DOC_ID");
            String FName = rset.getString("FNAME");
            String LName = rset.getString("LNAME");
            String currentAddress = rset.getString("CURRENT_ADDRESS");
            String currentPhone = rset.getString("CURRENT_PHONE");
            String patientCondition = rset.getString("PATIENT_CONDITION");
            Date bdate = rset.getDate("BDAY");
            String permCity = rset.getString("PERM_CITY");
            String permState = rset.getString("PERM_STATE");
            String permStreet = rset.getString("PERM_STREET");
            String permZip = rset.getString("PERM_ZIP");
            String permPhone = rset.getString("PERM_PHONE");
            String sex = rset.getString("SEX");

            Patient p = new Patient(patientID,ssn,primaryDocID,secondaryDocID, FName,LName,currentAddress,currentPhone,patientCondition,bdate,
                    permCity,permState,permStreet,permZip,permPhone, sex);
            patients.add(p);

        }

        return patients;
    }//end getPatients
    public static boolean addDoc(Doctor doc){
        boolean succesful = true;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DOCTOR(DOC_ID, CONTACT_NUM, BDAY, ADDRESS, FNAME, LNAME, PHONE, DEPT_CODE, SSN) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            //prepare statement from doc object
            pstmt.setString(1, doc.getDocID());
            pstmt.setString(2,doc.getContactNum());
            pstmt.setDate(3,doc.getBDate());
            pstmt.setString(4,doc.getAddress());
            pstmt.setString(5,doc.getFName());
            pstmt.setString(6,doc.getLName());
            pstmt.setString(7,doc.getPhone());
            pstmt.setString(8,doc.getDeptCode());
            pstmt.setString(9,doc.getSsn());
            //execute INSERT
            pstmt.executeUpdate();
            System.out.println("Doctor added.");
        } catch (SQLException e) {
            System.out.println("Unable to add doctor.");
            succesful = false;
        }

        return succesful;
    }
    public static boolean addPatient(Patient pat){
        try{
            //16 vals
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PATIENT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?)");

            pstmt.setString(1,pat.getPatientID());
            pstmt.setString(2,pat.getSsn());
            pstmt.setString(3,pat.getPrimaryDocID());
            pstmt.setString(4,pat.getSecondaryDocID());
            pstmt.setString(5,pat.getFName());
            pstmt.setString(6,pat.getLName());
            pstmt.setString(7,pat.getCurrentAddress());
            pstmt.setString(8, pat.getCurrentPhone());
            pstmt.setString(9,pat.getPatientCondition());
            pstmt.setDate(10,pat.getBDate());
            pstmt.setString(11,pat.getPermCity());
            pstmt.setString(12,pat.getPermState());
            pstmt.setString(13,pat.getPermStreet());
            pstmt.setString(14,pat.getPermZip());
            pstmt.setString(15,pat.getPermPhone());
            pstmt.setString(16,pat.getSex());
            pstmt.executeUpdate();
            System.out.println("Patient added.");

        } catch (SQLException e) {
            System.out.println("Unable to add patient.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ObservableList<Department> getDepartments() throws SQLException {

        ObservableList<Department> departments = FXCollections.observableArrayList();

        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM DEPARTMENT";
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){
            String depCode = rset.getString("DEPT_CODE");
            String officeNum = rset.getString("OFFICE_NUM");
            String officePhone = rset.getString("OFFICE_PHONE");
            String depHead = rset.getString("DEPT_HEAD");
            String depName = rset.getString("DEPT_NAME");

            Department d = new Department(depCode,officeNum,officePhone,depHead,depName);
            departments.add(d);
        }
        return departments;
    }//end getDepartments

    public static boolean addDepartment(Department dep){
        boolean succesful = true;
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DEPARTMENT VALUES (?, ?, ?, ?, ?)");
            //prepare statement from dep object
            pstmt.setString(1,dep.getDepCode());
            pstmt.setString(2,dep.getOfficeNum());
            pstmt.setString(3,dep.getOfficePhone());
            pstmt.setString(4,dep.getDepHead());
            pstmt.setString(5,dep.getDepName());
            //execute
            pstmt.executeUpdate();
            System.out.println("Department added.");

        } catch (SQLException e) {
            System.out.println("Unable to add department.");
            succesful = false;
            e.printStackTrace();
            return succesful;
        }

        return succesful;
    }
    public static ObservableList<Medication> getMedications() throws SQLException {

        ObservableList<Medication> medications = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM MEDICATION";
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){
            String name = rset.getString("MED_NAME");
            String maker = rset.getString("MAKER");
            String description = rset.getString("DESCRIPTION");

            Medication m = new Medication(name, maker, description);
            medications.add(m);
        }

        return medications;
    }
    public static boolean addMed(Medication med){
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MEDICATION VALUES(?, ?, ?)");
            pstmt.setString(1,med.getName());
            pstmt.setString(2,med.getMaker());
            pstmt.setString(3, med.getDescription());
            pstmt.executeUpdate();
            System.out.println("Medication added.");

        } catch (SQLException e) {
            System.out.println("Unable to add medication.");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //closes conn object. must be reintialized if called
    public void closeConn() throws SQLException {
        conn.close();
    }

    //general type add procedure
    public boolean addProc(Procedure proc) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PROCEDURES(PROCEDURE_NUM, DURATION, DESCRIPTION, NAME, DEPT_CODE)" +
                    " VALUES(?,?,?,?,?)");

            pstmt.setString(1, proc.getProcNum());
            pstmt.setString(2,proc.getDuration());
            pstmt.setString(3,proc.getDescription());
            pstmt.setString(4,proc.getName());
            pstmt.setString(5,proc.getDepCode());
            pstmt.executeUpdate();


            System.out.println("Procedure added.");
            return true;

        } catch (SQLException e) {
            System.out.println("Unable to add procedure.");
            e.printStackTrace();
            return false;
        }


    }
    public boolean addPrescription(Prescription pre){
        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRESCRIPTION VALUES(?, ?, ?, ?)");
            pstmt.setString(1,pre.getName());
            pstmt.setString(2, pre.getDocID());
            pstmt.setString(3, pre.getPatID());
            pstmt.setDate(4, pre.getDate());
            pstmt.executeUpdate();

            System.out.println("Prescription added.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to add prescription.");
            return false;
        }
    }

    public ObservableList<Procedure> searchProcbyDep(String search) throws SQLException {
        ObservableList<Procedure> procedures = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "SELECT DEPT_CODE, NAME, PROCEDURE_NUM, DURATION, DESCRIPTION FROM PROCEDURES WHERE PATIENT_ID IS NULL AND DEPT_CODE= '" + search + "'";
        System.out.println("QUERYING: " + q);
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){
            String code = rset.getString("DEPT_CODE");
            String name = rset.getString("NAME");
            String num = rset.getString("PROCEDURE_NUM");
            String duration = rset.getString("DURATION");
            String desc = rset.getString("DESCRIPTION");

            Procedure proc = new Procedure(name,num,duration,code,desc);
            procedures.add(proc);
        }

        return procedures;
    }//end searchProcByDep

    public ObservableList<Procedure> searchProcbyDepName(String search) throws SQLException {
        ObservableList<Procedure> procedures = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "Select * from procedures p, department d where p.dept_code = d.dept_code  AND d.dept_Name = '" + search + "' AND p.patient_id is NULL";
        System.out.println("QUERYING: " + q);
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){
            String code = rset.getString("DEPT_CODE");
            String name = rset.getString("NAME");
            String num = rset.getString("PROCEDURE_NUM");
            String duration = rset.getString("DURATION");
            String desc = rset.getString("DESCRIPTION");

            Procedure proc = new Procedure(name,num,duration,code,desc);
            procedures.add(proc);
        }

        return procedures;
    }//end searchProcByDep


    public ObservableList<Prescription> searchPrescriptionsByPatientID(String id) throws SQLException {

        ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM PRESCRIPTION WHERE PATIENT_ID= '" + id + "' ";
        System.out.println("Querying: " + q);
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){

            String medName = rset.getString("MED_NAME");
            String docID = rset.getString("PRESCRIBING_DOC");
            String patID = rset.getString("PATIENT_ID");
            Date date = rset.getDate("Pres_date");
            System.out.println("Pres stuff: " + medName + docID);
            Prescription pre = new Prescription(medName,docID,patID, date);
            prescriptions.add(pre);
        }

        return prescriptions;

    }
    public Patient getPatFromID(String id){
        //TODO minit addition
        try {
            Statement stmt = conn.createStatement();
            String q = "SELECT * FROM PATIENT WHERE PATIENT_ID= '" + id + "'";
            System.out.println("Pulling Patient " + id + " Info.");
            ResultSet rset = stmt.executeQuery(q);

            while(rset.next()){

                String patientID = rset.getString("PATIENT_ID");
                String ssn = rset.getString("SSN");
                String primaryDocID = rset.getString("PRIMARY_DOC_ID");
                String secondaryDocID = rset.getString("SECONDARY_DOC_ID");
                String FName = rset.getString("FNAME");
                String minit = rset.getString("MINIT");
                String LName = rset.getString("LNAME");
                String currentAddress = rset.getString("CURRENT_ADDRESS");
                String currentPhone = rset.getString("CURRENT_PHONE");
                String patientCondition = rset.getString("PATIENT_CONDITION");
                Date bdate = rset.getDate("BDAY");
                String permCity = rset.getString("PERM_CITY");
                String permState = rset.getString("PERM_STATE");
                String permStreet = rset.getString("PERM_STREET");
                String permZip = rset.getString("PERM_ZIP");
                String permPhone = rset.getString("PERM_PHONE");
                String sex = rset.getString("SEX");

                Patient p = new Patient(patientID,ssn,primaryDocID,secondaryDocID, FName,minit,LName,currentAddress,currentPhone,patientCondition,bdate,
                        permCity,permState,permStreet,permZip,permPhone, sex);
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to retrieve patient.");
            return null;
        }
        return null;
    }

    public int countInteractions(String id) throws SQLException {

        Statement stmt = conn.createStatement();
        String q = "Select count(*) COUNT FROM INTER_RECORD WHERE PATIENT_ID= '" + id + "'";
        try {
            ResultSet rset = stmt.executeQuery(q);

            while(rset.next()){
                int count = rset.getInt("COUNT");
                return count;
            }
        } catch (SQLException e) {
            System.out.println("Unable to add interaction");
            return 99;
        }
        return 99;
    }
    public boolean newInteraction(Interaction interaction){

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO INTER_RECORD VALUES(?,?,?,?,?)");
            pstmt.setInt(1, interaction.getInterID());
            pstmt.setString(2, interaction.getPatientID());
            pstmt.setDate(3,interaction.getDate());
            pstmt.setString(4, interaction.getInterTime());
            pstmt.setString(5, interaction.getInterDesc());
            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to add interaction.");
            return false;
        }

    }//end newInt

    public ObservableList<Interaction> loadInteractions(String id) throws SQLException {

            ObservableList<Interaction> interactions = FXCollections.observableArrayList();
            Statement stmt = conn.createStatement();
            String q = "Select * FROM INTER_RECORD WHERE PATIENT_ID= '" + id + "'";
            ResultSet rset = stmt.executeQuery(q);

            while(rset.next()){

                int interID = rset.getInt("INTER_ID");
                String PID = rset.getString("PATIENT_ID");
                Date date = rset.getDate("INTER_DATE");
                String time = rset.getString("INTER_TIME");
                String desc = rset.getString("DESCRIPTION");

                Interaction i = new Interaction(interID,PID, time, date, desc);
                interactions.add(i);

            }

            return interactions;
    }
    public ObservableList<Procedure> getProcRecordByID(String id) throws SQLException {
        ObservableList<Procedure> procedures = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM PROCEDURES WHERE PATIENT_ID = '" + id + "'";
        System.out.println(q);
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){

            String procNum = rset.getString("PROCEDURE_NUM");
            String duration = rset.getString("DURATION");
            String desc = rset.getString("DESCRIPTION");
            String name = rset.getString("NAME");
            String docID = rset.getString("DOCTOR_ID");
            String depCode = rset.getString("DEPT_CODE");
            String notes = rset.getString("NOTES");
            Date date = rset.getDate("PROC_DATE");
            String time = rset.getString("PROC_TIME");
            String pID = "";

            Procedure p = new Procedure(procNum, duration, desc, name, pID, docID, depCode, notes, date, time);
            System.out.println(p.getDepCode());
            procedures.add(p);

        }

        return procedures;

    }
    public boolean addProcByID(Procedure proc) {

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PROCEDURES VALUES(?,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, proc.getProcNum());
            pstmt.setString(2,proc.getDuration());
            pstmt.setString(3,proc.getDescription());
            pstmt.setString(4,proc.getName());
            pstmt.setString(5,proc.getPatientID());
            pstmt.setString(6,proc.getDocID());
            pstmt.setString(7,proc.getDepCode());
            pstmt.setString(8,proc.getNotes());
            pstmt.setDate(9, proc.getDate());
            pstmt.setString(10,proc.getTime());

            pstmt.executeUpdate();


            System.out.println("Procedure added.");
            return true;

        } catch (SQLException e) {
            System.out.println("Unable to add procedure.");
            e.printStackTrace();
            return false;
        }


    }

    public ObservableList<Procedure> getProcByDoc(String id) throws SQLException {

        ObservableList<Procedure> procedures = FXCollections.observableArrayList();
        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM PROCEDURES WHERE DOCTOR_ID = '" + id + "'";
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){

            String code = rset.getString("DEPT_CODE");
            String name = rset.getString("NAME");
            String num = rset.getString("PROCEDURE_NUM");
            String duration = rset.getString("DURATION");
            String desc = rset.getString("DESCRIPTION");
            Date date = rset.getDate("PROC_DATE");

            Procedure proc = new Procedure(name,num,duration,code,desc, date);
            procedures.add(proc);

        }

       return procedures;

    }
    public int countProc(String id) throws SQLException {

        Statement stmt = conn.createStatement();
        String q = "SELECT COUNT(*) E FROM PROCEDURES WHERE DOCTOR_ID = '" + id + "'";
        ResultSet rset = stmt.executeQuery(q);

        while(rset.next()){
            int count = rset.getInt("E");
            return count;
        }
        return 0;
    }
    public void changeNotes(String notes, String newNotes) throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement("UPDATE PROCEDURES SET NOTES = '" + newNotes + "' WHERE NOTES = '" + notes + "'");
        System.out.println("UPDATE PROCEDURES SET NOTES = '" + newNotes + "' WHERE NOTES = '" + notes + "'");
        pstmt.executeUpdate();



    }
}//end all
