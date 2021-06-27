package controller;

import entity.Group;
import entity.Student;
import entity.StudentInfo;

import java.util.List;

public interface TeacherController {

    List<Student> getListOfStudentsByGroup(Group group);
    List<StudentInfo> getListOfStudentInfosBySurname(String surname);
}
