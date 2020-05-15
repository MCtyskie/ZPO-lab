package com.mycompany.mavenproject14;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.lang.reflect.Field;

public class PrimaryController implements Initializable {

    @FXML
    private Button createButton;
    @FXML
    private Button deleteAbut;
    @FXML
    private Button deleteBbut;
    @FXML
    private Button deleteCbut;
    @FXML
    private Button editButton;

    @FXML
    private TextField createField;
    @FXML
    private TextField editFirst;
    @FXML
    private TextField editSecond;
    @FXML
    private TextField editThird;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableView<Object> tableViewA;
    @FXML
    private TableColumn<Object, String> column1a;
    @FXML
    private TableColumn<Object, String> column2a;
    @FXML
    private TableColumn<Object, String> column3a;
    @FXML
    private TableColumn<Object, String> column4a;

    private ObservableList<Object> Aobjects = FXCollections.observableArrayList();
    private ObservableList<Object> Bobjects = FXCollections.observableArrayList();
    private ObservableList<Object> Cobjects = FXCollections.observableArrayList();
    private ArrayList allObjects = new ArrayList();

    AClass oA1 = new AClass();
    AClass oA2 = new AClass();
    BClass oB1 = new BClass();
    BClass oB2 = new BClass();
    BClass oB3 = new BClass();
    CClass oC1 = new CClass();
    CClass oC2 = new CClass();
    CClass oC3 = new CClass();
    CClass oC4 = new CClass();

    Class c = null;
    Object o = null;

    @FXML
    void display(ActionEvent event) throws IOException {
        switch (comboBox.getValue()) {
            case "AClass":
                tableViewA.getItems().clear();
                for (Object o : allObjects) {
                    if (o instanceof AClass) {
                        Aobjects.add(o);
                    }
                }
                tableViewA.setItems(Aobjects);
                refreshA();
                break;
            case "BClass":
                tableViewA.getItems().clear();
                for (Object o : allObjects) {
                    if (o instanceof BClass) {
                        Bobjects.add(o);
                    }
                }
                tableViewA.setItems(Bobjects);
                refreshA();
                break;
            case "CClass":
                tableViewA.getItems().clear();
                for (Object o : allObjects) {
                    if (o instanceof CClass) {
                        Cobjects.add(o);
                    }
                }
                tableViewA.setItems(Cobjects);
                refreshA();
                break;
            default:
                System.out.println("Choose correct class");
                break;
        }
    }

    void refreshA() {
        //column1a.setCellValueFactory(new PropertyValueFactory<>("object"));
        column1a.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().toString()));
        column1a.setCellFactory(TextFieldTableCell.forTableColumn());
        column2a.setCellValueFactory(new PropertyValueFactory<>("name"));
        column3a.setCellValueFactory(new PropertyValueFactory<>("day"));
        column4a.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        if (comboBox.getValue().equals("AClass")) {
            try {
                Class cls = Class.forName("com.mycompany.mavenproject14.AClass");
                setAnnotation(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (comboBox.getValue().equals("BClass")) {
            try {
                Class cls = Class.forName("com.mycompany.mavenproject14.BClass");
                setAnnotation(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (comboBox.getValue().equals("CClass")) {
            try {
                Class cls = Class.forName("com.mycompany.mavenproject14.CClass");
                setAnnotation(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        tableViewA.setItems(Aobjects);
        tableViewA.refresh();
    }

    void setAnnotation(Class c) {
        int counter = 1;
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(Named.class) && counter == 1) {
                Named n = field.getAnnotation(Named.class);
                column2a.setText(n.value());
                counter=2;
            } else if (field.isAnnotationPresent(Named.class) && counter == 2) {
                Named n = field.getAnnotation(Named.class);
                column3a.setText(n.value());
                counter=3;
            } else if (field.isAnnotationPresent(Named.class) && counter == 3) {
                Named n = field.getAnnotation(Named.class);
                column4a.setText(n.value());
                counter=0;
            }
        }
    }

    @FXML
    void createButton(ActionEvent event) {
        String classpackagename = createField.getText();
        try {
            c = Class.forName(classpackagename);
            o = c.getDeclaredConstructor().newInstance();
            allObjects.add(o);
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (o instanceof AClass) {
            Aobjects.add(o);
        } else if (o instanceof BClass) {
            Bobjects.add(o);
        } else if (o instanceof CClass) {
            Cobjects.add(o);
        }
        refreshA();
    }

    @FXML
    void deleteAbut(ActionEvent event) {
        Object selectedobject = tableViewA.getSelectionModel().getSelectedItems().get(0);
        if (selectedobject != null) {
            allObjects.remove(selectedobject);
            if (selectedobject instanceof AClass) {
                Aobjects.remove(selectedobject);
            } else if (selectedobject instanceof BClass) {
                Bobjects.remove(selectedobject);
            } else if (selectedobject instanceof CClass) {
                Cobjects.remove(selectedobject);
            }
            selectedobject = null;
        }
        refreshA();
    }

    @FXML
    void editButton(ActionEvent event) {
        Object selectedobject = tableViewA.getSelectionModel().getSelectedItems().get(0);
        if (!editFirst.getText().equals("")) {
            if (comboBox.getValue().equals("AClass")) {
                AClass obj = (AClass) selectedobject;
                obj.setName(editFirst.getText());
            } else if (comboBox.getValue().equals("BClass")) {
                BClass obj = (BClass) selectedobject;
                obj.setName(editFirst.getText());
            } else if (comboBox.getValue().equals("CClass")) {
                CClass obj = (CClass) selectedobject;
                obj.setName(editFirst.getText());
            }
        }
        if (!editSecond.getText().equals("")) {
            if (comboBox.getValue().equals("AClass")) {
                AClass obj = (AClass) selectedobject;
                DayEnum arg = Enum.valueOf(DayEnum.class, editSecond.getText().toUpperCase());
                obj.setDay(arg);
            } else if (comboBox.getValue().equals("BClass")) {
                BClass obj = (BClass) selectedobject;
                DayEnum arg = Enum.valueOf(DayEnum.class, editSecond.getText().toUpperCase());
                obj.setDay(arg);
            } else if (comboBox.getValue().equals("CClass")) {
                CClass obj = (CClass) selectedobject;
                DayEnum arg = Enum.valueOf(DayEnum.class, editSecond.getText().toUpperCase());
                obj.setDay(arg);
            }
        }
        if (!editThird.getText().equals("")) {
            if (comboBox.getValue().equals("AClass")) {
                AClass obj = (AClass) selectedobject;
                LocalDate arg = LocalDate.parse(editThird.getText());
                obj.setDate(arg);
            } else if (comboBox.getValue().equals("BClass")) {
                BClass obj = (BClass) selectedobject;
                LocalDate arg = LocalDate.parse(editThird.getText());
                obj.setDate(arg);
            } else if (comboBox.getValue().equals("CClass")) {
                CClass obj = (CClass) selectedobject;
                LocalDate arg = LocalDate.parse(editThird.getText());
                obj.setDate(arg);
            }
        }
        refreshA();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.setValue("<choose>");
        comboBox.getItems().setAll("AClass", "BClass", "CClass");
        allObjects.add(oA1);
        allObjects.add(oA2);
        allObjects.add(oB1);
        allObjects.add(oB2);
        allObjects.add(oB3);
        allObjects.add(oC1);
        allObjects.add(oC2);
        allObjects.add(oC3);
        allObjects.add(oC4);
        tableViewA.setVisible(true);
        refreshA();
    }
}
