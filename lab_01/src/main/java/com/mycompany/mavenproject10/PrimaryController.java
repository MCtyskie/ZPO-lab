package com.mycompany.mavenproject10;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class PrimaryController implements Initializable {

    //TextFields:
    @FXML
    private TextField dirName2;
    @FXML
    private TextField dirName3;
    @FXML
    private TextField pathField4;
    @FXML
    private TextField pathField5;

    //Buttons:
    @FXML
    private Button createDir;
    @FXML
    private Button deleteDir;
    @FXML
    private Button refresh;
    @FXML
    private Button copyFile;
    @FXML
    private Button moveFile;
    @FXML
    private Button prevDir;
    @FXML
    private Button prevDir2;

    //RadioButtons:
    @FXML
    private RadioButton cpyRadioButton;
    @FXML
    private ToggleGroup cpymv;
    @FXML
    private RadioButton mveRadioButton;

    //Table components:
    @FXML
    private TableView<String> dirFiles;
    @FXML
    private TableColumn<String, String> dirFilesTable;
    @FXML
    private TableView<String> dirFiles2;
    @FXML
    private TableColumn<String, String> dirFilesTable2;

    //Labels:
    @FXML
    private Label pathFieldLabel;
    @FXML
    private Label pathFieldLabel2;
    @FXML
    private Label freeSpaceC;
    @FXML
    private Label freeSpaceD;

    private ObservableList<String> data = FXCollections.observableArrayList();
    private ObservableList<String> data2 = FXCollections.observableArrayList();

    //user's home path
    private String home = System.getProperty("user.home");

    @FXML
    void refresh(ActionEvent event) {
        refresh();
    }

    @FXML
    void createDir(ActionEvent event) {
        Path dir = Paths.get(pathFieldLabel.getText() + "\\" + dirName2.getText());
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
                System.out.println("Directory Created");
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            System.out.println("Directory already exists!");
        }
        dirName2.setText("");
        refresh();
    }

    @FXML
    void deleteDir(ActionEvent event) {
        Path dir = Paths.get(dirName3.getText());
        if (Files.exists(dir)) {
            try {
                Files.delete(dir);
                System.out.println("Directory/file deleted");
            } catch (Exception e) {
                System.err.print(e);
            }
        } else {
            System.out.println("Directory/file doesn't exist!");
        }
        refresh();
    }

    //works fine but dont copy directory with files
    @FXML
    void copyFile(ActionEvent event) throws IOException {
        copyFile();
    }

    void copyFile() throws IOException {
        Path dir1 = Paths.get(dirName3.getText());
        Path dir2 = Paths.get(pathField4.getText());

        String filename = dir1.getFileName().toString();
        String dirwithname = dir2.toString() + "\\" + filename;
        Path finaldir2 = Paths.get(dirwithname);
        if (!Files.exists(dir1)) {
            System.out.println("File doesn't exists");
        } else if (Files.exists(dir2)) {
            //kopiowanie z updatem
            Files.copy(dir1, finaldir2, REPLACE_EXISTING);
            System.out.println("File updated by copying");
        } else {
            //kopiowanie pliku
            Files.copy(dir1, finaldir2);
            System.out.println("File copied");
        }
        refresh();
    }

    @FXML
    void moveFile(ActionEvent event) throws IOException {
        moveFile();
    }

    void moveFile() throws IOException {
        Path dir1 = Paths.get(dirName3.getText());
        Path dir2 = Paths.get(pathField5.getText());

        String filename = dir1.getFileName().toString();
        String dirwithname = dir2.toString() + "\\" + filename;
        Path finaldir2 = Paths.get(dirwithname);
        if (!Files.exists(dir1)) {
            System.out.println("File doesn't exists");
        } else if (Files.exists(dir2)) {
            //kopiowanie z updatem
            Files.move(dir1, finaldir2, REPLACE_EXISTING);
            System.out.println("File updated by moving");
        } else {
            //kopiowanie pliku
            Files.move(dir1, finaldir2);
            System.out.println("File moved");
        }
        refresh();
    }

    @FXML
    void mouseClicked(MouseEvent event) throws IOException {
        String selected = dirFiles.getSelectionModel().getSelectedItem();
        Path dir = Paths.get(pathFieldLabel.getText() + "\\" + selected);

        if (event.getClickCount() == 2) {
            if (!Files.exists(dir)) {
                System.out.println("Directory/file doesn't exists. Make sure that it exists");
            } else if (Files.isDirectory(dir)) {
                pathFieldLabel.setText(dir.toString());
                System.out.println("Directory opened");
            } else if (!Files.isExecutable(dir)) {
                System.out.println("File is not executable");
            } else if (!Files.isReadable(dir)) {
                System.out.println("File is not readable");
            } //obsługa otwarcia pliku
            else {
                System.out.println(dir);
                File file = new File(dir.toString());
                Desktop.getDesktop().open(file);
                System.out.println("File opened");
            }
            refresh();
        } else if (event.getClickCount() == 1) {
            dirName3.setText(dir.toString());
        }
    }

    @FXML
    void mouseClicked2(MouseEvent event) throws IOException {
        String selected = dirFiles2.getSelectionModel().getSelectedItem();
        Path dir = Paths.get(pathFieldLabel2.getText() + "\\" + selected);

        if (event.getClickCount() == 2) {
            if (!Files.exists(dir)) {
                System.out.println("Directory/file doesn't exists. Make sure that it exists");
            } else if (Files.isDirectory(dir)) {
                pathFieldLabel2.setText(dir.toString());
                System.out.println("Directory opened");
            } else if (!Files.isExecutable(dir)) {
                System.out.println("File is not executable");
            } else if (!Files.isReadable(dir)) {
                System.out.println("File is not readable");
            } //obsługa otwarcia pliku
            else {
                System.out.println(dir);
                File file = new File(dir.toString());
                Desktop.getDesktop().open(file);
                System.out.println("File opened");
            }

            refresh();
        } else if (event.getClickCount() == 1) {
            pathField4.setText(dir.toString());
            pathField5.setText(dir.toString());
        }
    }

    @FXML
    void handleDragDetection(MouseEvent event) {
        Dragboard db = dirFiles2.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(dirName3.getText());
        db.setContent(cb);
        event.consume();
        System.out.println(dirName3.getText());
    }

    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleDragDrop(DragEvent event) throws IOException {
        String str = event.getDragboard().getString();
        System.out.println(str);
        if (cpyRadioButton.isSelected()) {
            copyFile();
        } else if (mveRadioButton.isSelected()) {
            moveFile();
        }
        refresh();
    }

    @FXML
    void prevDir(ActionEvent event) {
        Path dir = Paths.get(pathFieldLabel.getText());
        Path prevDir = dir.getParent();
        dirName3.setText("");
        if (!Files.exists(prevDir)) {
            System.out.println("Directory/file doesn't exists. Make sure that it exists");
        } else if (Files.isDirectory(prevDir)) {
            pathFieldLabel.setText(prevDir.toString());
            System.out.println("Directory opened");
        } else if (!Files.isExecutable(prevDir)) {
            System.out.println("File is not executable");
        } else if (!Files.isReadable(prevDir)) {
            System.out.println("File is not readable");
        } //obsługa otwarcia pliku
        else {
            System.out.println(prevDir);
            File file = new File(prevDir.toString());
            System.out.println("File opened");
        }
        refresh();
    }

    @FXML
    void prevDir2(ActionEvent event) {
        Path dir = Paths.get(pathFieldLabel2.getText());
        Path prevDir = dir.getParent();
        if (!Files.exists(prevDir)) {
            System.out.println("Directory/file doesn't exists. Make sure that it exists");
        } else if (Files.isDirectory(prevDir)) {
            pathFieldLabel2.setText(prevDir.toString());
            System.out.println("Directory opened");
        } else if (!Files.isExecutable(prevDir)) {
            System.out.println("File is not executable");
        } else if (!Files.isReadable(prevDir)) {
            System.out.println("File is not readable");
        } //obsługa otwarcia pliku
        else {
            System.out.println(prevDir);
            File file = new File(prevDir.toString());
            System.out.println("File opened");
        }
        refresh();
    }

    @FXML
    void cpyRadioButton(ActionEvent event) {
        check();
    }

    @FXML
    void mveRadioButton(ActionEvent event) {
        check();
    }

    void check() {
        if (cpyRadioButton.isSelected()) {
            System.out.println("cpy");
        } else if (mveRadioButton.isSelected()) {
            System.out.println("mve");
        }
    }

    void refresh() {
        //1st table view
        String[] pathnames;
        File file = new File(pathFieldLabel.getText());
        pathnames = file.list();
        data.clear();
        for (String pathname : pathnames) {
            data.add(pathname);
        }
        dirFilesTable.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        dirFiles.setItems(data);
        dirFiles.refresh();

        //2nd table view
        String[] pathnames2;
        File file2 = new File(pathFieldLabel2.getText());
        pathnames2 = file2.list();
        data2.clear();
        for (String pathname2 : pathnames2) {
            data2.add(pathname2);
        }
        dirFilesTable2.setCellValueFactory(param2 -> new ReadOnlyStringWrapper(param2.getValue()));
        dirFiles2.setItems(data2);
        dirFiles2.refresh();

        //usable disc space
        File discC = new File("c:");
        File discD = new File("d:");
        long usableSpaceC = discC.getUsableSpace();
        long usableSpaceD = discD.getUsableSpace();
        freeSpaceC.setText("C: " + usableSpaceC / 1024 / 1024 + " MB | " + usableSpaceC / 1024 / 1024 / 1024 + " GB");
        freeSpaceD.setText("D: " + usableSpaceD / 1024 / 1024 + " MB | " + usableSpaceD / 1024 / 1024 / 1024 + " GB");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pathFieldLabel.setText(home);
        pathFieldLabel2.setText(home);
        mveRadioButton.setSelected(true);
        refresh();
        check();
    }

}
