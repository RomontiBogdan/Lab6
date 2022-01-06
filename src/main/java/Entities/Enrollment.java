package Entities;

public class Enrollment {
    private int idstudent;
    private int idcourse;

    public Enrollment(int idstudent, int idcourse) {
        this.idstudent = idstudent;
        this.idcourse = idcourse;
    }

    public Enrollment() {
        this.idcourse = -1;
        this.idstudent = -1;
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public int getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(int idcourse) {
        this.idcourse = idcourse;
    }
}
