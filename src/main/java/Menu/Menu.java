package Menu;

import Controller.RegistrationSystem;
import Entities.Course;
import Entities.Student;
import Entities.Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    RegistrationSystem controller;

    public Menu(RegistrationSystem controller) {
        this.controller = controller;
    }

    public void menu() throws IOException {
        int option = 0;
        do {
            System.out.println("1. Add a student");
            System.out.println("2. Add a teacher");
            System.out.println("3. Add a course");
            System.out.println("4. Update a student");
            System.out.println("5. Update a teacher");
            System.out.println("6. Update a course");
            System.out.println("7. Register student to course");
            System.out.println("8. Show Courses With Free Places");
            System.out.println("9. Sort students by credits");
            System.out.println("10. Sort courses by credits");
            System.out.println("11. Filter students based on credits");
            System.out.println("12. Filter courses based on credits");
            System.out.println("13. Show students");
            System.out.println("14. Show teachers");
            System.out.println("15. Show courses");
            System.out.println("16. Exit");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Id:");
                    int studentId = scanner.nextInt();
                    System.out.println("First name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String firstNameS = reader.readLine();
                    System.out.println("Last name:");
                    String lastNameS = reader.readLine();
                    System.out.println("Credits:");
                    int credits = scanner.nextInt();
                    Student student = new Student();
                    student.setStudentId(studentId);
                    student.setFirstName(firstNameS);
                    student.setLastName(lastNameS);
                    student.setTotalCredits(credits);
                    controller.addStudent(student);
                }
                case 2 -> {
                    System.out.println("Id:");
                    int teacherId = scanner.nextInt();
                    System.out.println("First name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String firstNameT = reader.readLine();
                    System.out.println("Last name:");
                    String lastNameT = reader.readLine();
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(teacherId);
                    teacher.setFirstName(firstNameT);
                    teacher.setLastName(lastNameT);
                    controller.addTeacher(teacher);
                }
                case 3 -> {
                    System.out.println("Id:");
                    int courseId = scanner.nextInt();
                    System.out.println("Name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String name = reader.readLine();
                    System.out.println("Teacher id:");
                    int Idteacher = scanner.nextInt();
                    System.out.println("Credits:");
                    int credits = scanner.nextInt();
                    System.out.println("Max students:");
                    int maxStudents = scanner.nextInt();
                    Course course = new Course();
                    course.setCourseId(courseId);
                    course.setName(name);
                    course.setTeacher(controller.getByIdTeacher(Idteacher));
                    course.setCredits(credits);
                    course.setMaxStudendts(maxStudents);
                    controller.addCourse(course);
                }
                case 4 -> {
                    System.out.println("Id:");
                    int studentId = scanner.nextInt();
                    System.out.println("First name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String firstNameS = reader.readLine();
                    System.out.println("Last name:");
                    String lastNameS = reader.readLine();
                    System.out.println("Credits:");
                    int credits = scanner.nextInt();
                    Student student = new Student();
                    student.setStudentId(studentId);
                    student.setFirstName(firstNameS);
                    student.setLastName(lastNameS);
                    student.setTotalCredits(credits);
                    if(controller.getByIdStudent(studentId) == null)
                        System.out.println("There is no student with this id");
                    else
                        controller.updateStudent(student);
                }
                case 5 -> {
                    System.out.println("Id:");
                    int teacherId = scanner.nextInt();
                    System.out.println("First name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String firstNameT = reader.readLine();
                    System.out.println("Last name:");
                    String lastNameT = reader.readLine();
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(teacherId);
                    teacher.setFirstName(firstNameT);
                    teacher.setLastName(lastNameT);
                    if(controller.getByIdTeacher(teacherId) == null)
                        System.out.println("There is no teacher with this id");
                    else
                        controller.updateTeacher(teacher);
                }
                case 6 -> {
                    System.out.println("Id:");
                    int courseId = scanner.nextInt();
                    System.out.println("Name:");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String name = reader.readLine();
                    System.out.println("Teacher id:");
                    int Idteacher = scanner.nextInt();
                    System.out.println("Credits:");
                    int credits = scanner.nextInt();
                    System.out.println("Max students:");
                    int maxStudents = scanner.nextInt();
                    Course course = new Course();
                    course.setCourseId(courseId);
                    course.setName(name);
                    course.setTeacher(controller.getByIdTeacher(Idteacher));
                    course.setCredits(credits);
                    course.setMaxStudendts(maxStudents);
                    if(controller.getByIdCourse(courseId) == null)
                        System.out.println("There is no course with this id");
                    else
                        controller.updateCourse(course);
                }
                case 7 -> {
                    System.out.println("Student Id:");
                    int studentId = scanner.nextInt();
                    System.out.println("Course Id:");
                    int courseId = scanner.nextInt();
                    if(controller.getByIdStudent(studentId) == null)
                        System.out.println("There is no student with this id");
                    else if (controller.getByIdCourse(courseId) == null)
                        System.out.println("There is no course with this id");
                    else
                        controller.register(controller.getByIdCourse(courseId), controller.getByIdStudent(studentId));
                }
                case 8 -> {
                    List<Course> courseList = controller.coursesWithFreePlaces();
                    for(Course course : courseList){
                        System.out.println("Id: " + course.getCourseId() + ", Course: " + course.getName() + ", Teacher: " + course.getTeacher().getLastName() + ", Credits: " + course.getCredits() + ", " + course.getStudentsEnrolled().size() + "/" + course.getMaxStudendts());
                    }
                }
                case 9 -> {
                    List<Student> studentList = controller.getSortedStudents();
                    for(Student student : studentList){
                        System.out.println("Id: " + student.getStudentId() + ",Student : " + student.getLastName() +" "+ student.getFirstName() + ", Credits: " + controller.numberOfCredits(student));
                    }
                }
                case 10 -> {
                    List<Course> courseList = controller.getSortedCourses();
                    for(Course course : courseList){
                        System.out.println("Id: " + course.getCourseId() + ", Course: " + course.getName() + ", Teacher: " + course.getTeacher().getLastName() + ", Credits: " + course.getCredits() + ", " + course.getStudentsEnrolled().size() + "/" + course.getMaxStudendts());
                    }
                }
                case 11 -> {
                    System.out.println("Min credits: ");
                    int min = scanner.nextInt();
                    List<Student> studentList = controller.filterStudents(min);
                    for(Student student : studentList){
                        System.out.println("Id: " + student.getStudentId() + ",Student : " + student.getLastName() +" "+ student.getFirstName() + ", Credits: " + controller.numberOfCredits(student));
                    }
                }
                case 12 -> {
                    System.out.println("Min credits: ");
                    int min = scanner.nextInt();
                    List<Course> courseList = controller.filterCourses(min);
                    for(Course course : courseList){
                        System.out.println("Id: " + course.getCourseId() + ", Course: " + course.getName() + ", Teacher: " + course.getTeacher().getLastName() + ", Credits: " + course.getCredits() + ", " + course.getStudentsEnrolled().size() + "/" + course.getMaxStudendts());
                    }
                }
                case 13 -> {
                    List<Student> studentList = controller.getAllStudets();
                    for(Student student : studentList){
                        System.out.println("Id: " + student.getStudentId() + ",Student : " + student.getLastName() +" "+ student.getFirstName() + ", Credits: " + controller.numberOfCredits(student));
                    }
                }
                case 14 -> {
                    List<Teacher> teacherList = controller.getAllTeachers();
                    for(Teacher teacher : teacherList){
                        System.out.println("Id: " + teacher.getTeacherId() + ",Teacher : " + teacher.getLastName() + " " + teacher.getFirstName());
                    }
                }
                case 15 -> {
                    List<Course> courseList = controller.getAllCourses();
                    for(Course course : courseList){
                        System.out.println("Id: " + course.getCourseId() + ", Course: " + course.getName() + ", Teacher: " + course.getTeacher().getLastName() + ", Credits: " + course.getCredits() + ", " + course.getStudentsEnrolled().size() + "/" + course.getMaxStudendts());
                    }
                }
                case 16 -> {
                    return;
                }
            }
        }while(option <= 16 && option >= 1);
    }

}