import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.sql.DATE;

import java.sql.*;
import java.util.ArrayList;


public class DBHandler {

    String serverName, portNumber, sid, url, username, password;
     static Connection conn;
    public DBHandler(String usernameInput, String passwordInput) throws SQLException {
        //import driver
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e){
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
        this.conn = DriverManager.getConnection(url,username,password);

    }//end constructor

    public static ObservableList<Employee> getEmployees() throws SQLException {

        ObservableList<Employee> employees = FXCollections.observableArrayList();

        Statement stmt = conn.createStatement();
        String q = "SELECT * FROM EMPLOYEE";
        ResultSet rset = stmt.executeQuery(q);

       // ObservableList<Employee> data = FXCollections.observableArrayList()

        while (rset.next()){
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

            Employee i = new Employee(fname,lname,ssn,bdate,address,sex,salary,superssn,dno);
            employees.add(i);
        }

        return employees;
    }//end getEmployees


    public void newEmployee() throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO EMPLOYEE (FNAME, LNAME, SSN, BDATE, ADDRESS, SEX, SALARY, SUPERSSN, DNO) "
        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?");




    }




public void closeConn() throws SQLException {
        conn.close();
}




}//end all
