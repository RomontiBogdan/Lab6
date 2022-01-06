package com.example.lab6;

import Controller.CourseController;
import Controller.EnrollmentController;
import Controller.StudentController;
import Entities.Course;
import Entities.Enrollment;
import Entities.Student;
import Entities.Teacher;
import Repository.CourseRepoJDBC;
import Repository.EnrolledRepoJDBC;
import Repository.StudentRepoJDBC;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class StudentView {
    public Button enrollButton;
    public Button creditsButton;
    public TextField courseIdText;
    public ListView coursesListView;
    public TextField userTextField;
    public Label userLabel;
    public Button loginButton;
    public Label courseIdLabel;
    private int studentId;
    private CourseRepoJDBC courseRepoJDBC = new CourseRepoJDBC();
    private CourseController courseController = new CourseController(courseRepoJDBC);
    private EnrolledRepoJDBC enrolledRepoJDBC = new EnrolledRepoJDBC();
    private EnrollmentController enrollmentController = new EnrollmentController(enrolledRepoJDBC);
    private StudentRepoJDBC studentRepoJDBC = new StudentRepoJDBC();
    private StudentController studentController = new StudentController(studentRepoJDBC);

    @FXML
    public void initialize() {
        courseIdText.setVisible(false);
        courseIdLabel.setVisible(false);
        creditsButton.setVisible(false);
        enrollButton.setVisible(false);
    }

    /**
     * Fills the list view with courses with free places
     */
    public void showcourses() {
        List<Course> courseList = courseController.getAll();
        for(Course course : courseList){
            if(enrollmentController.getEnrolledStudents(course).size() < course.getMaxStudendts()){
                coursesListView.getItems().add(course.getCourseId() + " " + course.getName());
            }
        }
    }

    /**
     * Register student to the chosen course
     */
    public void register(){
        int courseID = Integer.parseInt(courseIdText.getText());
        if(courseController.getById(courseID) == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("This course id does not exist");
            return;
        }
        if(studentController.getById(studentId).getTotalCredits() + courseController.getById(courseID).getCredits() > 30){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Max credits are exceeded");
            return;
        }
        List<Enrollment> enrollmentList = enrollmentController.getAll();
        for(Enrollment enrollment : enrollmentList){
            if(enrollment.getIdcourse() == courseID && enrollment.getIdstudent() == studentId){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You are already enrolled to this course");
                return;
            }
        }

        if(courseController.getById(courseID).getMaxStudendts() - enrollmentController.getEnrolledStudents(courseController.getById(courseID)).size() <= 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Max credits are exceeded");
            return;
        }
        Enrollment enrollment = new Enrollment(studentId, courseID);

        enrollmentController.addEnrollment(enrollment);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Succes");
    }

    /**
     * Verifies if student name from text field exists and assigns student to view
     */
    public void login(){
        List<Student> studentList = studentController.getAll();
        for(Student student : studentList)
        {
            if((student.getFirstName() + " " + student.getLastName()).equals(userTextField.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Succes");
                this.studentId = (int) student.getStudentId();
                userTextField.setVisible(false);
                userLabel.setVisible(false);
                loginButton.setVisible(false);
                courseIdText.setVisible(true);
                courseIdLabel.setVisible(true);
                creditsButton.setVisible(true);
                enrollButton.setVisible(true);
                showcourses();
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no student with this name");
    }


    /**
     * Pops up an alert with the number of credits the student has
     */
    public void showCredits(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You have " + studentController.getById(studentId).getTotalCredits() + " credits");
    }
}
