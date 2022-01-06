package com.example.lab6;

import Controller.CourseController;
import Controller.TeacherController;
import Entities.Course;
import Entities.Student;
import Entities.Teacher;
import Repository.CourseRepoJDBC;
import Repository.TeacherRepoJDBC;
import javafx.scene.control.*;

import java.util.List;

public class TeacherView {
    public Button refreshButton;
    private TeacherRepoJDBC teacherRepoJDBC = new TeacherRepoJDBC();
    private TeacherController teacherController = new TeacherController(teacherRepoJDBC);
    private CourseRepoJDBC courseRepoJDBC = new CourseRepoJDBC();
    private CourseController courseController = new CourseController(courseRepoJDBC);

    public Button teacherLoginButton;
    public Label teacherLabel;
    public TextField teacherTextField;
    public ListView teacherListView;

    private int teacherId;

    public void initialize() {
        refreshButton.setVisible(false);
    }

    /**
     * Verifies if teacher name from text field exists and assigns teacher to view
     */
    public void login(){
        List<Teacher> teacherList = teacherController.getAll();
        for(Teacher teacher : teacherList)
        {
            if((teacher.getFirstName() + teacher.getLastName()).equals(teacherTextField.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Succes");
                this.teacherId = (int) teacher.getTeacherId();
                teacherTextField.setVisible(false);
                teacherLabel.setVisible(false);
                teacherLoginButton.setVisible(false);
                showCourses();
                refreshButton.setVisible(true);
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no teacher with this name");
    }

    /**
     * Adds to list view the students enrolled to current teacher courses
     */
    private void showCourses() {
        List<Course> courseList = courseController.getAll();
        for(Course course : courseList){
            if(course.getTeacher().getTeacherId() == teacherId){
                teacherListView.getItems().add(course.getCourseId() + " " + course.getName());
            }
        }
    }

    /**
     * Refreshes course list
     */
    public void refreshList(){
        teacherListView.getItems().removeAll(teacherListView.getItems());
        showCourses();
    }
}
