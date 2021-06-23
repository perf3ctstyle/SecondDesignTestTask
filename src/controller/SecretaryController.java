package controller;

import entity.Group;
import entity.Student;

import java.util.List;

public interface SecretaryController {

    void createGroup(String name, int courseNumber, List<Student> students);
    void deleteGroup(Group group);
    List<Student> getListOfStudents(Group group);
    List<Student> getListOfStudents(int courseNumber);
    void addStudentToGroup(Student student, Group group);
}
