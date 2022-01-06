package Controller;

import Entities.Course;
import Repository.ICrudRepository;

import java.util.List;


public class CourseController {
    private final ICrudRepository<Course> repo;

    public CourseController(ICrudRepository<Course> repo) {
        this.repo = repo;
    }

    /**
     * Adds course to repo
     * @param course
     */
    public void addCourse(Course course){
        this.repo.create(course);
    }

    /**
     * Updates course from repo
     * @param course
     */
    public void updateCourse(Course course){
        repo.update(course);
    }

    /**
     * Removes course from repo
     * @param course
     */
    public void removeCourse(Course course){
        repo.delete(course);
    }

    /**
     * Returns all records from repo
     * @return list of records
     */
    public List<Course> getAll(){
        return repo.getAll();
    }

    /**
     * Returns course with the given id
     * @param id
     * @return course with the given id
     */
    public Course getById(int id){
        List<Course> courseList = repo.getAll();
        for(Course course : courseList){
            if(course.getCourseId() == id){
                return course;
            }
        }
        return null;
    }

    /**
     * Sorts list ascending based on number of credits
     * @return sorted course list
     */
    public List<Course> sortList(){
        List<Course> courseList = repo.getAll();
        courseList.sort((c1, c2) -> {
            if (c1.getCredits() < c2.getCredits()) return -1;
            else if (c1.getCredits() == c2.getCredits()) return 0;
            return 1;
        });
        return courseList;
    }

    /**
     * Filters courses with number of credits > min
     * @param min
     * @return list of courses with more credits than min
     */
    public List<Course> filterByCredits(int min){
        List<Course> courseList = repo.getAll();
        return courseList.stream().filter(course->course.getCredits() > min).toList();
    }

}