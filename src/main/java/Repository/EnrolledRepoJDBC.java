package Repository;

import Entities.Enrollment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnrolledRepoJDBC implements ICrudRepository<Enrollment>{
    private static Statement statement;
    private static ResultSet results;

    public EnrolledRepoJDBC() {
    }

    /**
     * Adds enrollment to repo
     * @param enrollment
     * @return added enrollment
     */
    @Override
    public Enrollment create(Enrollment enrollment) {
        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO course-student " + "VALUES (" + enrollment.getIdcourse() + ", " +
                                        enrollment.getIdstudent() + ")" );

            System.out.println(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollment;
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    @Override
    public List<Enrollment> getAll() {
        String sql_select = "Select * From course-student";

        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            List<Enrollment> enrollmentList = new ArrayList<Enrollment>();

            while (results.next()) {

                Enrollment enrollment = new Enrollment();

                enrollment.setIdcourse(Integer.parseInt(results.getString("idcourse")));
                enrollment.setIdstudent(Integer.parseInt(results.getString("idstudent")));

                enrollmentList.add(enrollment);
            }

            return enrollmentList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates given teacher from repo
     * @param enrollment
     * @return updated enrollment
     */
    @Override
    public Enrollment update(Enrollment enrollment) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE course-student " + "SET idcourse = " + enrollment.getIdcourse() + ", " +
                    "idstudent = " + enrollment.getIdstudent());

            return enrollment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes given teacher from repo
     * @param enrollment
     */
    @Override
    public void delete(Enrollment enrollment) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM course-student WHERE idcourse = " + enrollment.getIdcourse() + " AND idstudent = " + enrollment.getIdstudent());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
