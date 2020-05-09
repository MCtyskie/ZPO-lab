package com.mycompany.mavenproject13;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField classpackagefield;
    @FXML
    private TextField setterNewValueField;
//    @FXML
//    private TextField setterNewValueField2;

    @FXML
    private Button classpackageconfirmed;
    @FXML
    private Button editSetter;
    @FXML
    private Button runMethod;
//    @FXML
//    private Button editSetter2;

    @FXML
    private TableView<Method> tableview;
    @FXML
    private TableColumn<Method, String> methodesColumn;
    @FXML
    private TableView<Method> tableview2;
    @FXML
    private TableColumn<Method, String> settersColumn;

    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton RBint;
    @FXML
    private RadioButton RBboolean;
    @FXML
    private RadioButton RBfloat;
    @FXML
    private RadioButton RBString;
    @FXML
    private RadioButton RBdate;
    @FXML
    private RadioButton RBEnum;

    private ObservableList<Method> methods = FXCollections.observableArrayList();
    private ObservableList<Method> setterObservableList = FXCollections.observableArrayList();

    Class c = null;
    Object o = null;

    @FXML
    void classpackageconfirmed(ActionEvent event) {
        tableview.getItems().clear();
        tableview2.getItems().clear();
        String classpackagename = classpackagefield.getText();

        try {
            c = Class.forName(classpackagename);
            o = c.getDeclaredConstructor().newInstance();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Method[] m = c.getDeclaredMethods();
        for (Method method : m) {
            methods.add(method);
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                Object obj = null;
                try {
                    obj = method.invoke(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String methodName = method.getName();
                String fieldName = methodName.replaceAll("get", "");
                String finalFieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
                //String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                System.out.println("Getter: " + method + ". Field name: " + finalFieldName + ", value:" + obj.toString());
            } else if (method.getName().startsWith("set")) {
                //System.out.println("Setter: "+method);
                setterObservableList.add(method);
            }
        }

//        Field[] f = c.getDeclaredFields();
//        for (Field field : f) {
//            System.out.println("Field that has getter: " + field);
//        }
        //todo:
        //wartosci wszystkich pól, dla ktorych istieja gettery
        refresh();
    }

    //uzycie settera dla argumentów róznego typu
    @FXML
    void editSetter(ActionEvent event) throws ParseException {
        Method selectedSetter = tableview2.getSelectionModel().getSelectedItems().get(0);
        System.out.println("Selected setter: " + selectedSetter);
        if (RBString.isSelected()) {
            String newArg = setterNewValueField.getText();
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(RBint.isSelected()){
            int newArg=Integer.parseInt(setterNewValueField.getText());
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(RBboolean.isSelected()){
            boolean newArg=Boolean.parseBoolean(setterNewValueField.getText());
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(RBfloat.isSelected()){
            float newArg=Float.parseFloat(setterNewValueField.getText());
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(RBEnum.isSelected()){
            Enum newArg=Enum.valueOf(StudentsSexEnum.class,setterNewValueField.getText());
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(RBdate.isSelected()){
            Date newArg=new SimpleDateFormat("dd/MM/yyyy").parse(setterNewValueField.getText());
            //Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
            try {
                selectedSetter.invoke(o, newArg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void runMethod(ActionEvent event) {
        Method selectedMethod = tableview.getSelectionModel().getSelectedItems().get(0);
        System.out.println(selectedMethod);
        try {
            //wywolanie prywatnej metody
            selectedMethod.setAccessible(true);
            selectedMethod.invoke(o, null);
        } catch (Exception e) {
            //System.out.println("exception occured");
            e.printStackTrace();
        }
    }

    void refresh() {
        methodesColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().toString()));
        //methodesColumn.setCellValueFactory (new PropertyValueFactory<>("methodes"));
        //methodesColumn.setCellValueFactory(new PropertyValueFactory<Method,String>("methodes"));
        tableview.setItems(methods);
        tableview.getSortOrder().addAll(methodesColumn);
        tableview.refresh();

        settersColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().toString()));
        //settersColumn.setCellValueFactory (new PropertyValueFactory<>("setters"));
        //settersColumn.setCellValueFactory(new PropertyValueFactory<Method,String>("setters"));
        tableview2.setItems(setterObservableList);
        tableview2.getSortOrder().addAll(settersColumn);
        tableview2.refresh();

        RBString.setSelected(true);
    }
}
