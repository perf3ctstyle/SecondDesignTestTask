package actor;

import controller.TeacherController;
import database.GeneralDatabase;
import database.GroupDatabase;
import database.StudentDatabase;
import entity.Group;
import entity.Student;
import entity.StudentInfo;
import logic.GroupLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Teacher implements TeacherController {

    private final GeneralDatabase generalDatabase;
    private final GroupDatabase groupDatabase;
    private final StudentDatabase studentDatabase;

    private static final String NULL_ARGUMENT = "The argument that was passed to this method is null.";

    public Teacher(GeneralDatabase generalDatabase) {
        this.generalDatabase = generalDatabase;
        this.groupDatabase = generalDatabase.getGroupDatabase();
        this.studentDatabase = generalDatabase.getStudentDatabase();
    }

    @Override
    public List<Student> getListOfStudentsByGroup(Group group) {
        synchronized (generalDatabase) {
            if (group == null) {
                throw new IllegalArgumentException(NULL_ARGUMENT);
            }
            return group.getStudents();
        }
    }

    @Override
    public List<StudentInfo> getListOfStudentInfosBySurname(String surname) {
        synchronized (generalDatabase) {
            List<StudentInfo> studentInfos = new ArrayList<>();
            List<Student> students = studentDatabase.getListOfAllStudents();

            for (Student currentStudent : students) {
                String currentStudentSurname = currentStudent.getSurname();

                if (currentStudentSurname.equals(surname)) {
                    List<Group> groups = groupDatabase.getListOfAllGroups();
                    Optional<Group> optionalGroup = GroupLogic.findGroupOfStudent(currentStudent, groups);
                    StudentInfo studentInfo = new StudentInfo(currentStudent, optionalGroup);
                    studentInfos.add(studentInfo);
                }
            }

            return studentInfos;
        }
    }
}
