package com.example.lab6;

import Controller.StudentController;
import Controller.TeacherController;
import Entities.Student;
import Entities.Teacher;
import Repository.StudentRepoJDBC;
import Repository.TeacherRepoJDBC;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AccesController {
    public TextField userTextField;
    public ComboBox userComboBox;

    private final StudentRepoJDBC studentRepoJDBC = new StudentRepoJDBC();
    private final StudentController studentController = new StudentController(studentRepoJDBC);
    private final TeacherRepoJDBC teacherRepoJDBC = new TeacherRepoJDBC();
    private final TeacherController teacherController = new TeacherController(teacherRepoJDBC);

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * At initialisation adds options to combobox
     */
    @FXML
    public void initialize() {
        userComboBox.getItems().removeAll(userComboBox.getItems());
        userComboBox.getItems().addAll("Student", "Teacher");
        userComboBox.getSelectionModel().select("Student");

    }

    /**
     * Verifies if choosen user type is student or teacher
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonPressed(ActionEvent event) throws IOException {
        if (userComboBox.getValue().equals("Student"))
            switchToStudent(event);
        else if(userComboBox.getValue().equals("Teacher"))
            switchToTeacher(event);
    }

    /**
     * Switches to student scene
     * @param event
     * @throws IOException
     */
    public void switchToStudent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Teacher");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to teacher scene
     * @param event
     * @throws IOException
     */
    public void switchToTeacher(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("teacherView.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Teacher");
        stage.setScene(scene);
        stage.show();
    }


}
