package Repository;

import Entities.Student;
import Entities.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepoJDBC implements ICrudRepository<Teacher> {
    private static Statement statement;
    private static ResultSet results;

    public TeacherRepoJDBC() {    }

    /**
     * Adds teacher to repo
     * @param teacher
     * @return added teacher
     */
    @Override
    public Teacher create(Teacher teacher) {
        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO teacher " + "VALUES (" + teacher.getTeacherId() + ", '" +
                                        teacher.getFirstName() + "', '" + teacher.getLastName() + "')" );

            System.out.println(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    /**
     * Gets all the records from repo
     * @return list of records
     */
    @Override
    public List<Teacher> getAll() {
        String sql_select = "Select * From teacher";

        try(Connection conn = JDBCRepo.createDBconnection()){

            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            List<Teacher> teacherList = new ArrayList<Teacher>();

            while (results.next()) {

                Teacher teacher = new Teacher();

                teacher.setTeacherId(Long.parseLong(results.getString("idteacher")));
                teacher.setFirstName(results.getString("First_name"));
                teacher.setLastName(results.getString("Last_name"));

                teacherList.add(teacher);
            }

            return teacherList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * updates given teacher from repo
     * @param teacher
     * @return updated teacher
     */
    @Override
    public Teacher update(Teacher teacher) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE teacher " + "SET First_name = '" + teacher.getFirstName() + "', " +
                    "Last_name = '" + teacher.getLastName() + "' WHERE idteacher = " + teacher.getTeacherId());

            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes given teacher from repo
     * @param teacher
     */
    @Override
    public void delete(Teacher teacher) {
        try(Connection conn = JDBCRepo.createDBconnection()){
            statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM teacher WHERE idteacher = " + teacher.getTeacherId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
