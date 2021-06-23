package database;

import entity.Student;

import java.util.List;

public class StudentDatabase {

    private final List<Student> students;

    public StudentDatabase(List<Student> students) {
        this.students = students;
    }

    public List<Student> getListOfAllStudents() {
        return students;
    }
}
