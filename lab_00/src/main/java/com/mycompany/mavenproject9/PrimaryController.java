package com.mycompany.mavenproject9;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements EmployeeDAO {

    //Buttons
    @FXML
    private Button load_data;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_byid_button;
    @FXML
    private Button search_byname_button;

    //TableView and TableColumns
    @FXML
    private TableView<Employee> EmployeeTable;
    @FXML
    private TableColumn<Employee, Integer> idColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, Float> salaryColumn;

    //TextFields
    @FXML
    private TextField add_name;
    @FXML
    private TextField add_email;
    @FXML
    private TextField add_salary;
    @FXML
    private TextField add_id;
    @FXML
    private TextField delete_idname;

    //Labels
    @FXML
    private Label add_label_error;
    @FXML
    private Label delete_label_error;
    @FXML
    private Label search_res;

    //RadioButtons and ToggleGroup for RadioButtons
    @FXML
    private RadioButton deletebyid;
    @FXML
    private RadioButton deletebyname;
    @FXML
    private ToggleGroup deletegroup;

    //other important declarations
    private ResultSet rs = null;
    List<Employee> list = new ArrayList<>();
    private ObservableList<Employee> obs_data = FXCollections.observableArrayList();
    dbConnection dbconn = new dbConnection("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/zpo_lab0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "bazy1920");

    /**
     *
     * @add_button (FXML)- method, that adds employee, if employee exists- updates him.
     * @delete_byid_button (FXML)- method, that deletes employee by given id.
     * @deletebyid and deletebyname (FXML)- radio buttons, that only updates table if they are being clicked.
     * @load_data (FXML)- refresh table (used mainly at beginning of program).
     * @load()- puts into table data (id, name, email and salary)
     * @setToList()- changes ResultSet into ObservableList, so we can put it into table.
     * @findOne()- finds employee by given id.
     * @findAll()- finds all employees. @findByName()- finds employee/ employees by name
     * @delete()- deletes employee by given id.
     * @save()- if new employee- saves as new, if employee exists- update.
     * 
     */
    @FXML
    void add_button(ActionEvent event) {
        int id = Integer.parseInt(add_id.getText());
        String name = add_name.getText();
        String email = add_email.getText();
        float salary = Float.parseFloat(add_salary.getText());
        if (add_id.getText().isEmpty() || add_name.getText().isEmpty() || add_email.getText().isEmpty() || add_salary.getText().isEmpty()) {
            add_label_error.setText("Error- provide all data");
            add_label_error.setStyle("-fx-text-fill: red;");
        } else {
            save(new Employee(id, name, email, salary));
            //System.out.println("ADDED/ MODIFIED");
            add_label_error.setText("added/modified successfully");
            add_label_error.setStyle("-fx-text-fill: green;");
        }
        add_id.setText("");
        add_name.setText("");
        add_email.setText("");
        add_salary.setText("");
        load();
    }

    @FXML
    void delete_byid_button(ActionEvent event) {
        if (delete_idname.getText().isEmpty()) {
            delete_label_error.setText("Error- provide data");
            delete_label_error.setStyle("-fx-text-fill: red;");
        } else {
            Optional op = findOne(Integer.parseInt(delete_idname.getText()));
            op.ifPresent(action -> {
                delete((Employee) op.get());
                //ystem.out.println("Deleted successfully");
                delete_label_error.setText("Deleted successfully");
                delete_label_error.setStyle("-fx-text-fill: green;");
            });
            delete_idname.setText("");
        }
        load();
    }

    //uusnac
    @FXML
    void search_byname_button(ActionEvent event) {
        if (delete_idname.getText().isEmpty()) {
            delete_label_error.setText("Error- provide data");
            delete_label_error.setStyle("-fx-text-fill: red;");
        } else {
            Optional op = findByName(delete_idname.getText());
            obs_data.clear();
            if (op.isPresent()) {
                obs_data.add((Employee) op.get());
            } else {
                delete_label_error.setText("Error- employee doesn't exist");
                delete_label_error.setStyle("-fx-text-fill: red;");
            }
            EmployeeTable.refresh();
        }
    }

    @FXML
    void deletebyid(ActionEvent event) {
        check();
    }

    @FXML
    void deletebyname(ActionEvent event) {
        check();
    }

    @FXML
    void load_data(ActionEvent event) {
        load();
        deletebyid.setSelected(true);
        check();
        System.out.println("REFRESHED");
    }

    void load() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        String query = "select * from employee";
        obs_data = setToList(dbconn.getResultSet(query));
        EmployeeTable.setItems(obs_data);
        EmployeeTable.refresh();
    }

    void check() {
        if (deletebyid.isSelected()) {
            search_byname_button.setDisable(true);
            delete_byid_button.setDisable(false);
        } else if (deletebyname.isSelected()) {
            delete_byid_button.setDisable(true);
            search_byname_button.setDisable(false);
        }
    }

    ObservableList<Employee> setToList(ResultSet rs) {
        ObservableList<Employee> obs_data = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                obs_data.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return obs_data;
    }

    @Override
    public Optional<Employee> findOne(Integer id) {
        Optional<Employee> fone = Optional.empty();
        String query = "select * from employee where id=" + id;
        try {
            rs = dbconn.getResultSet(query);
            if (rs.next()) {
                fone = Optional.of(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return fone;
    }

    @Override
    public List<Employee> findAll() {
        String query = "select * from employee";
        try {
            rs = dbconn.getResultSet(query);
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return list;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        Optional<Employee> one = Optional.empty();
        String query = "select * from employee where name like\"" + name + "\"";
        try {
            rs = dbconn.getResultSet(query);
            if (rs.next()) {
                one = Optional.of(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return one;
    }

    @Override
    public void delete(Employee employee) {
        String query = "delete from employee where id=" + employee.getId();
        dbconn.executeQuery(query);
    }

    @Override
    public void save(Employee employee) {
        String query = "select * from employee where id=" + employee.getId();
        try {
            if (dbconn.getResultSet(query).next()) {
                query = "update employee set name=\"" + employee.getName()
                        + "\",email=\"" + employee.getEmail()
                        + "\", salary=" + employee.getSalary()
                        + "where id=" + employee.getId();
            } else {
                query = "insert into employee(id, name, email, salary) values ("
                        + employee.getId() + ", \""
                        + employee.getName() + "\", \""
                        + employee.getEmail() + "\", "
                        + employee.getSalary() + ")";
            }
            dbconn.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
