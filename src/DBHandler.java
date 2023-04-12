import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import oracle.sql.DATE;
import java.sql.*;
import java.util.ArrayList;


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

    public static ObservableList<Employee> getEmployees() throws SQLException {

        ObservableList<Employee> employees = FXCollections.observableArrayList();

        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM EMPLOYEE";
        ResultSet rset = stmt.executeQuery(q);

        // ObservableList<Employee> data = FXCollections.observableArrayList()

        while (rset.next()) {
            String fname = rset.getString("FNAME");
            String lname = rset.getString("LNAME");
            int ssn = rset.getInt("SSN");
            Date bdate = rset.getDate("BDATE");
            String address = rset.getString("ADDRESS");
            String sex = rset.getString("SEX");
            int salary = rset.getInt("SALARY");
            int superssn = rset.getInt("SUPERSSN");
            int dno = rset.getInt("DNO");



            //check statement log
            System.out.println(fname + " " + lname + " " + ssn + " " + bdate + " " + address + " " + sex + " " + salary + " " + superssn + " " + dno + " ");

            Employee i = new Employee(fname, lname, ssn, bdate, address, sex, salary, superssn, dno);
            employees.add(i);
        }

        return employees;
    }//end getEmployees

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
        } catch (SQLException e) {
            System.out.println("Unable to add doctor.");
            succesful = false;
        }
        System.out.println("Doctor added.");
        return succesful;
    }


    //closes conn object. must be reintialized if called
    public void closeConn() throws SQLException {
        conn.close();
    }
}//end all
