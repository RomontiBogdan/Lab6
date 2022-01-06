package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Student;


public class StudentRepoJDBC implements ICrudRepository<Student>{
    private static Statement statement;
    private static ResultSet results;

    public StudentRepoJDBC() {}

    /**
     * Adds student to repo
     * @param student
     * @return added student
     */
    @Override
    public Student create(Student student) {
        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO student " + "VALUES (" + student.getStudentId() + ", '" + student.getFirstName() + "', '" + student.getLastName() +
                                    "', " + student.getTotalCredits() + ")" );

            System.out.println(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    @Override
    public List getAll() {
        String sql_select = "Select * From student";

        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            List<Student> studentsList = new ArrayList<Student>();

            while (results.next()) {

                Student student = new Student();

                student.setStudentId(Integer.parseInt(results.getString("idstudent")));
                student.setFirstName(results.getString("First_name"));
                student.setLastName(results.getString("Last_name"));
                student.setTotalCredits(Integer.parseInt(results.getString("total_credits")));

                studentsList.add(student);
            }

            return studentsList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates given student from repo
     * @param student
     * @return updated teacher
     */
    @Override
    public Student update(Student student) {
        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            statement.executeUpdate("UPDATE student " + "SET First_name = '" + student.getFirstName() + "', " +
                                        "Last_name = '" + student.getLastName() + "', total_credits = " + student.getTotalCredits() +
                                        " WHERE idstudent = " + student.getStudentId());

            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes given student from repo
     * @param student
     */
    @Override
    public void delete(Student student) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM student WHERE idstudent = " + student.getStudentId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
