package Entities;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String name;
    private Teacher teacher;
    private List<Student> studentsEnrolled;
    private int credits;
    private int maxStudendts;

    public Course(int courseId, String name, Teacher teacher, List<Student> studentsEnrolled, int credits, int maxStudents) {
        this.courseId = courseId;
        this.name = name;
        this.teacher = teacher;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
        this.maxStudendts = maxStudents;
    }

    public Course() {
        this.courseId = 0;
        this.name = "";
        this.teacher = new Teacher();
        this.studentsEnrolled = new ArrayList<>();
        this.credits = 0;
        this.maxStudendts = 0;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getMaxStudendts() {
        return maxStudendts;
    }

    public void setMaxStudendts(int maxStudendts) {
        this.maxStudendts = maxStudendts;
    }
}