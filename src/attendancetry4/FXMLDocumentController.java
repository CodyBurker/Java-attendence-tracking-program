/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendancetry4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.*;
import java.awt.Panel;
import java.io.File;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author cody
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView studentList;

    @FXML
    private ListView profListView;

    @FXML
    private ListView classListView;

    @FXML
    private JFXButton signinBtn;

    @FXML
    private TextField nameField;

    @FXML
    private Label feedbackLabel;

    @FXML
    private TableView studentTableView;

    @FXML
    private JFXButton delete;

    @FXML
    private TableColumn x;

    @FXML
    private TableColumn y;
    @FXML
    private Label counterText;
    @FXML
    private Label counterText2;
    @FXML
    private ListView editProfListView;
    @FXML
    private ListView editClassListView;
    @FXML
    private TextField addProfTextField;
    @FXML
    private TextField addClassTextField;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private ToolBar tb;
    @FXML
    private Button abc;
    @FXML
    private JFXTabPane pane;
    @FXML
            private Tab exitTab;

    ObservableList<String> profList = FXCollections.observableArrayList(
            "Alsop", "Colburn-Alsop", "Brown", "Fermosa-Mayan", "Johnston");
    ObservableList<String> classList = FXCollections.observableArrayList(
            "SPA-110", "SPA-111", "SPA-210", "SPA-211", "SPA-315", "Other");
    ObservableList<String> allStudents = FXCollections.observableArrayList();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        boolean good = true;
        // Check for empty values
        if (nameField.getText().isEmpty()) {
            good = false;
        }
        if (profListView.getSelectionModel().isEmpty()) {
            good = false;
        }
        if (classListView.getSelectionModel().isEmpty()) {
            good = false;
        }

        // If good, then do this.
        if (good) {
            // Add name to list
            String name = nameField.getText() + "," + profListView.getSelectionModel().getSelectedItem() + "," + classListView.getSelectionModel().getSelectedItem();
            allStudents.add(name);
            // Change the color back to normal
            feedbackLabel.setStyle("-fx-background-color: #00796B");
            // Change the sign in text to be normal
            feedbackLabel.setText(nameField.getText() + " has signed in!");
            // Reset all the fields
            nameField.setText("");
            profListView.getSelectionModel().clearSelection();
            classListView.getSelectionModel().clearSelection();
        } // If not good, then do this.
        else {
           Alert alert = new Alert(AlertType.WARNING, "Please enter name, professor, and class.", ButtonType.OK);
           alert.titleProperty().setValue("Enter name, professor, and class."); 
           alert.headerTextProperty().setValue("Enter name, professor, and class.");
           alert.showAndWait();
            feedbackLabel.setStyle("-fx-background-color: #D32F2F");
            feedbackLabel.setText("Please enter name, profe, and class.");
        }
        updateCounters();
        

    }

    @FXML
    private void deleteItem(ActionEvent event) {
        if (allStudents.size()>0){
        String toDelete = studentList.getSelectionModel().getSelectedItem().toString();
        StringTokenizer nameToDelete;
        nameToDelete = new StringTokenizer(toDelete, ",");
        String name = nameToDelete.nextToken();
//        JOptionPane sure = new JOptionPane();
//        Object[] options = {"Delete",
//            "Cancel"};
//        int choice = JOptionPane.showOptionDialog(sure,
//            "Would you like to delete " + name + "?",
//            "Are you sure?",
//            JOptionPane.YES_NO_OPTION,
//            JOptionPane.QUESTION_MESSAGE,
//            null,
//            options,
//            options[1]);
//        if (choice==0){
//            System.out.print("Delete");
//        }
          allStudents.remove(studentList.getSelectionModel().getSelectedIndex());
          updateCounters();
        }
//        String SignIns;
//        if (allStudents.size() == 1) {
//            SignIns = +allStudents.size() + " student has signed in.";
//        } else {
//            SignIns = +allStudents.size() + " students have signed in.";
//        }
//        counterText.setText(SignIns);
//        counterText2.setText(SignIns);
//        feedbackLabel.setText("");
    }

    @FXML
    private void saveList(ActionEvent event) {
        Stage stage = (Stage) studentList.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (! (file == null)){
        String fileString = file.toString();
        fileString = fileString + ".csv";
        File x = new File(fileString);
        try {
            PrintWriter out = new PrintWriter(fileString);
            out.println("Student Name,Professor,Class");
            for (int i = 0; i < allStudents.size(); i++) {
                out.println(allStudents.get(i));
            }
            out.close();
        } catch (Exception e) {
        }
        }
    }

    @FXML
    private void addClass(ActionEvent event) {
        String newProf = addClassTextField.getText();
        if (!newProf.isEmpty()) {
            classList.add(newProf);
        }
    }
    
    @FXML
    private void moveWindow(ActionEvent event) {
        
    }

    @FXML
    private void addProf(ActionEvent event) {
        String newClass = addProfTextField.getText();
        if (!newClass.isEmpty()) {
            profList.add(newClass);
        }
    }

    @FXML
    private void deleteClass(ActionEvent event) {
        if (classList.size()>0 && !editClassListView.getSelectionModel().isEmpty())
            classList.remove(editClassListView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void deleteProf(ActionEvent event) {
        if (profList.size()>0 && !editClassListView.getSelectionModel().isEmpty())
        profList.remove(editProfListView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void onKeyPress(ActionEvent event){   
    }
    
    @FXML
    private void saveProfAndClass(ActionEvent event){
        String def = System.getProperty("user.home") + File.separator + ".jstock" + File.separator + File.separator + ".txt";
        File file = new File(def);
        try {
            PrintWriter out = new PrintWriter(file);
            for (int i = 0; i < allStudents.size(); i++) {
                out.println(allStudents.get(i));
            }
            out.close();
        } catch (Exception e) {
        }
        
    }
    
    @FXML
    private void clearForm(ActionEvent event){
        nameField.setText("");
        profListView.getSelectionModel().clearSelection();
        classListView.getSelectionModel().clearSelection();
        feedbackLabel.setText("");
        feedbackLabel.setStyle("-fx-background-color: #00796B");   
    }
    @FXML
    private void exitForm(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = pane.getSelectionModel();
        selectionModel.select(3);
        
        //pane.getTabs().add(exitTab);
        //System.exit(0);
    }
    
    @FXML
    private void confirmedExit(ActionEvent event){
        System.exit(0);
    }
    @FXML
    private void canceledExit(ActionEvent event){
        SingleSelectionModel<Tab> selectionModel = pane.getSelectionModel();
        selectionModel.select(0);
    }
    double screenX;
    double screenY;
    
    @FXML
    private void titleBarClicked(MouseEvent event){
        Stage stage = (Stage) pane.getScene().getWindow();
        screenX = stage.getX() - event.getScreenX();
        screenY = stage.getY() - event.getScreenY();
    }
    
    @FXML
    private void titleBarDragged(MouseEvent event){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setX(event.getScreenX() + screenX);
        stage.setY(event.getScreenY() + screenY);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        classListView.setItems(classList);
        profListView.setItems(profList);
        studentList.setItems(allStudents);

        editProfListView.setItems(profList);
        editClassListView.setItems(classList);
        
        

    }
    private void updateCounters(){
        String SignIns;
        if (allStudents.size() == 1) {
            SignIns = +allStudents.size() + " student has signed in.";
        } else {
            SignIns = +allStudents.size() + " students have signed in.";
        }
        counterText.setText(SignIns);
        counterText2.setText(SignIns);
               
    }
}
