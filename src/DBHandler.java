import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


    //closes conn object. must be reintialized if called
    public void closeConn() throws SQLException {
        conn.close();
    }
}//end all
