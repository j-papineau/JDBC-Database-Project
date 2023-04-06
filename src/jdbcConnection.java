import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class jdbcConnection {

    String serverName, portNumber, sid, url, username, password;
    Connection conn;

    public jdbcConnection(String usernameInput, String passwordInput) {
        //import driver
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e){
           System.out.println("unable to find class, jdbc.driver");
            e.printStackTrace();
        }

         serverName = "cisvm-oracle.unfcsd.unf.edu";
         portNumber = "1521";
         sid = "orcl";
         url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
         username = usernameInput;
         password = passwordInput;

    }

    public boolean validateConn() throws SQLException {

       // controller controller = new controller();

        boolean isValid;
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
          //  controller.updateProgress("Validating Connection...", .50);
            isValid = conn.isValid(10); // timeout 10 sec
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection unable to be reached");
            isValid = false;

        }

        return isValid;
    }

    public void closeConn() throws SQLException {
        //this.conn.close();
    }

}
