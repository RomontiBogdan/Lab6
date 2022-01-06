package Controller;

import Entities.Teacher;
import Repository.ICrudRepository;

import java.util.List;

public class TeacherController {
    private ICrudRepository repo;

    public TeacherController(ICrudRepository repo) {
        this.repo = repo;
    }

    /**
     * Adds teacher to repo
     * @param teacher
     */
    public void addTeacher(Teacher teacher){
        this.repo.create(teacher);
    }

    /**
     * Updates teacher from repo
     * @param teacher
     */
    public void updateTeacher(Teacher teacher){
        repo.update(teacher);
    }

    /**
     * Removes teacher from repo
     * @param teacher
     */
    public void removeTeacher(Teacher teacher){
        repo.delete(teacher);
    }

    /**
     * Returns all records from repo
     * @return list of records
     */
    public List<Teacher> getAll(){
        return repo.getAll();
    }

    /**
     * Returns teacher with the given id
     * @param id
     * @return teacher with the given id
     */
    public Teacher getById(int id){
        List<Teacher> teacherList = repo.getAll();
        for(Teacher teacher : teacherList){
            if(teacher.getTeacherId() == id){
                return teacher;
            }
        }
        return null;
    }
}