package Controller;

import Entities.Course;
import Entities.Enrollment;
import Entities.Student;
import Repository.ICrudRepository;

import java.util.List;

public class EnrollmentController {
    ICrudRepository repo;

    public EnrollmentController(ICrudRepository repo) {

        this.repo = repo;
    }

    /**
     * Adds enrollment to repo
     * @param enrollment
     */
    public void addEnrollment(Enrollment enrollment){
        repo.create(enrollment);
    }

    /**
     * Updates enrollment from repo
     * @param enrollment
     */
    public void updateEnrollment(Enrollment enrollment){
        repo.update(enrollment);
    }

    /**
     * Removes enrollment from repo
     * @param enrollment
     */
    public void removeEnrollment(Enrollment enrollment){
        repo.delete(enrollment);
    }

    /**
     * Returns all records from repo
     * @return list of records
     */
    public List<Enrollment> getAll(){
        return repo.getAll();
    }

    /**
     * Returns list of students enrolled to the given course
     * @param course
     * @return list of enrolled students
     */
    public List<Enrollment> getEnrolledStudents(Course course){
        List<Enrollment> enrollmentList = repo.getAll();
        enrollmentList.removeIf(enrollment -> enrollment.getIdcourse() != course.getCourseId());
        return enrollmentList;
    }

    /**
     * Returns list of courses the student is enrolled to
     * @param student
     * @return list of student's courses
     */
    public List<Enrollment> getEnrolledCourses(Student student){
        List<Enrollment> enrollmentList = repo.getAll();
        enrollmentList.removeIf(enrollment -> enrollment.getIdcourse() != student.getStudentId());
        return enrollmentList;
    }

}