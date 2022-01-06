package Controller;

import Entities.Course;
import Entities.Enrollment;
import Entities.Student;
import Entities.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationSystem {
    StudentController studentController;
    CourseController courseController;
    TeacherController teacherController;
    EnrollmentController enrollmentController;

    public RegistrationSystem(StudentController studentController, CourseController courseController, TeacherController teacherController, EnrollmentController enrollmentController) {
        this.studentController = studentController;
        this.courseController = courseController;
        this.teacherController = teacherController;
        this.enrollmentController = enrollmentController;
    }

    /**
     * Adds course to repo
     * @param course
     */
    public void addCourse(Course course){
        courseController.addCourse(course);
    }

    /**
     * Adds student to repo
     * @param student
     */
    public void addStudent(Student student){
        studentController.addStudent(student);
    }

    /**
     * Adds teacher to repo
     * @param teacher
     */
    public void addTeacher(Teacher teacher){
        teacherController.addTeacher(teacher);
    }

    /**
     * Updates course from repo
     * @param course
     */
    public void updateCourse(Course course){
        courseController.updateCourse(course);
    }

    /**
     * Updates student from repo
     * @param student
     */
    public void updateStudent(Student student){
        studentController.updateStudent(student);
    }

    /**
     * Updates student from repo
     * @param teacher
     */
    public void updateTeacher(Teacher teacher){
        teacherController.updateTeacher(teacher);
    }

    /**
     * Removes course from repo
     * @param course
     */
    public void removeCourse(Course course){
        courseController.removeCourse(course);
    }

    /**
     * Removes student from repo
     * @param student
     */
    public void removeStudent(Student student){
        studentController.removeStudent(student);
    }

    /**
     * Removes teacher from repo
     * @param teacher
     */
    public void removeTeacher(Teacher teacher){
        teacherController.removeTeacher(teacher);
    }

    /**
     * Returns course with the given id
     * @param id
     * @return course with the given id
     */
    public Course getByIdCourse(int id){
        return courseController.getById(id);
    }

    /**
     * Returns student with the given id
     * @param id
     * @return student with the given id
     */
    public Student getByIdStudent(int id){
        return studentController.getById(id);
    }

    /**
     * Returns teacher with the given id
     * @param id
     * @return teacher with the given id
     */
    public Teacher getByIdTeacher(int id){
        return teacherController.getById(id);
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    public List<Course> getAllCourses(){
        return courseController.getAll();
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    public List<Student> getAllStudets(){
        return studentController.getAll();
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    public List<Teacher> getAllTeachers(){
        return teacherController.getAll();
    }

    /**
     * Adds course to student and student to course
     * @param course
     * @param student
     * @return true if the operation is successfull and false otherwise
     */
    public boolean register(Course course , Student student){
        if(student.getTotalCredits() + course.getCredits() > 30){
            return false;
        }

        if(course.getMaxStudendts() - enrollmentController.getEnrolledStudents(course).size() <= 0){
            return false;
        }

        List<Enrollment> enrollmentList = enrollmentController.getAll();
        for(Enrollment enrollment : enrollmentList){
            if(enrollment.getIdcourse() == enrollment.getIdcourse()){
                return false;
            }
        }
        Enrollment enrollment = new Enrollment((int) student.getStudentId(), course.getCourseId());

        enrollmentController.addEnrollment(enrollment);
        return true;
    }

    /**
     * Returns list of courses with free places
     * @return list of courses
     */
    public List<Course> coursesWithFreePlaces(){
        List<Course> courseList = courseController.getAll();
        List<Course> freeCourses = new ArrayList<Course>();
        for(Course course : courseList){
            if(enrollmentController.getEnrolledStudents(course).size() < course.getMaxStudendts()){
                freeCourses.add(course);
            }
        }
        return freeCourses;
    }

    /**
     * Returns number of credits the student has
     * @param student
     * @return number of credits
     */
    public int numberOfCredits(Student student){
        int total = 0;
        List<Enrollment> enrolls = enrollmentController.getEnrolledCourses(student);
        List<Course> enrolledCourses = new ArrayList<Course>();
        for(Enrollment enrollment : enrolls){
            enrolledCourses.add(courseController.getById(enrollment.getIdcourse()));
        }
        for(Course c : enrolledCourses){
            total += c.getCredits();
        }
        return total;
    }

    /**
     * Sorts list of students
     * @return sorted list
     */
    public List<Student> getSortedStudents(){
        return studentController.sortList();
    }

    /**
     * Sorts list of courses
     * @return sorted list
     */
    public List<Course> getSortedCourses(){
        return courseController.sortList();
    }

    /**
     * Filters students with more credits that min
     * @param min
     * @return filtered list
     */
    public List<Student> filterStudents(int min){
        return studentController.filterByCredits(min);
    }

    /**
     * Filters students with more credits that min
     * @param min
     * @return filtered list
     */
    public List<Course> filterCourses(int min){
        return courseController.filterByCredits(min);
    }

}