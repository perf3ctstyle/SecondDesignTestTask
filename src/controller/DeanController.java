package controller;

import entity.Group;
import entity.Student;
import entity.StudentInfo;

import java.util.List;

public interface DeanController {

    void changeIsStudentStudying(Student student, boolean isStudying);
    List<StudentInfo> getListOfStudentInfosByCityOfResidence(String cityOfResidence);
    void changeStudentGroup(Student student, Group fromGroup, Group toGroup);
}