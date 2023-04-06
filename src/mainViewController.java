import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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

    @FXML
    private void initialize() {

    }

    DBHandler db;
    public void createConnection(ActionEvent e) throws SQLException {
    }

    public void loadEmployees(ActionEvent e) throws SQLException {
        db.getEmployees();
    }


    public void loadEmployeeData() throws SQLException {

      //  employeeTableView.getItems().setAll(getEmployeeData());
        try {
            employeeTableView.setItems(getEmployeeData());
            System.out.println("Data loaded");
            employeeTableView.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//end load

    public void clearEmployeeTable(ActionEvent e){
        employeeTableView.getItems().clear();
    }
    public void refreshTable(){
        employeeTableView.refresh();
    }

    public ObservableList<Employee> getEmployeeData() throws SQLException {

            ObservableList<Employee> employees = FXCollections.observableArrayList();
            employees = db.getEmployees();

            return employees;

    }//end get employees

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("DB Object Created");
        try {
            db = new DBHandler("n01533921","3921");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        fnameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("fname"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("lname"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("ssn"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Employee,Date>("bdate"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("address"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("sex"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
        superColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("superssn"));
        dnoColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("dno"));
        /*
        try {
            employeeTableView.setItems(getEmployeeData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

    }//end initialize

    public void employeeTabClicker(ActionEvent e){

        System.out.println("Employee Tab Clicked");


    }//end employee tab

    public void departmentsTabClicked(ActionEvent e){

        System.out.println("Departments tabe clicked");
    }
}
