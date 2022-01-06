package Repository;

import Entities.Course;
import Entities.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRepoJDBC implements ICrudRepository<Course>{
    private static Statement statement;
    private static ResultSet results;

    public CourseRepoJDBC() {
    }

    /**
     * Adds a course to repo
     * @param course
     * @return added course
     */
    @Override
    public Course create(Course course) {
        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO course " + "VALUES (" + course.getCourseId() + ", '" +
                                        course.getName() + "', " + course.getTeacher().getTeacherId()  + ", " + course.getCredits() +
                                        ", " + course.getMaxStudendts() + ")" );

            System.out.println(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    @Override
    public List<Course> getAll() {
        String sql_select = "Select * From course";

        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            List<Course> courseList = new ArrayList<Course>();

            while (results.next()) {

                Course course = new Course();
                Teacher teacher = new Teacher();

                course.setCourseId(Integer.parseInt(results.getString("idcourse")));
                course.setName(results.getString("name"));
                teacher.setTeacherId(Integer.parseInt(results.getString("teacher_id")));
                course.setTeacher(teacher);
                course.setCredits(Integer.parseInt(results.getString("credits")));
                course.setMaxStudendts(Integer.parseInt(results.getString("max_students")));

                courseList.add(course);
            }

            return courseList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates given course from repo
     * @param course
     * @return updated course
     */
    @Override
    public Course update(Course course) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE course " + "SET name = '"  + course.getName() + "', teacher_id = " + course.getTeacher().getTeacherId()  +
                                    ", credits = " + course.getCredits() + ", max_students = " + course.getMaxStudendts() + "WHERE idcourse = " + course.getCourseId());;

            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes given course from repo
     * @param course
     */
    @Override
    public void delete(Course course) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM course WHERE idcourse = " + course.getCourseId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
