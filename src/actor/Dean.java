package actor;

import controller.DeanController;
import database.GeneralDatabase;
import database.GroupDatabase;
import database.StudentDatabase;
import entity.Group;
import entity.Student;
import entity.StudentInfo;
import exception.StudentWithSameSurnameAlreadyExistsException;
import logic.GroupLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dean implements DeanController {

    private final GeneralDatabase generalDatabase;
    private final GroupDatabase groupDatabase;
    private final StudentDatabase studentDatabase;

    private static final String STUDENT_WITH_SAME_SURNAME_EXISTS = "Student with the same surname already exists in this group.";
    private static final String NULL_ARGUMENT = "The argument that was passed to this method is null.";

    public Dean(GeneralDatabase generalDatabase) {
        this.generalDatabase = generalDatabase;
        this.groupDatabase = generalDatabase.getGroupDatabase();
        this.studentDatabase = generalDatabase.getStudentDatabase();
    }

    @Override
    public void changeIsStudentStudying(Student student, boolean isStudying) {
        synchronized (generalDatabase) {
            if (student == null) {
                throw new IllegalArgumentException(NULL_ARGUMENT);
            }
            student.setIsStudying(isStudying);
        }
    }

    @Override
    public List<StudentInfo> getListOfStudentInfosByCityOfResidence(String cityOfResidence) {
        synchronized (generalDatabase) {
            List<StudentInfo> studentInfos = new ArrayList<>();
            List<Student> students = studentDatabase.getListOfAllStudents();

            for (Student currentStudent : students) {
                String currentStudentCityOfResidence = currentStudent.getCityOfResidence();

                if (currentStudentCityOfResidence.equals(cityOfResidence)) {
                    List<Group> groups = groupDatabase.getListOfAllGroups();
                    Optional<Group> optionalGroup = GroupLogic.findGroupOfStudent(currentStudent, groups);

                    StudentInfo studentInfo = new StudentInfo(currentStudent, optionalGroup);
                    studentInfos.add(studentInfo);
                }
            }

            return studentInfos;
        }
    }

    @Override
    public void changeStudentGroup(Student student, Group fromGroup, Group toGroup) {
        synchronized (generalDatabase) {
            if (student == null || fromGroup == null || toGroup == null) {
                throw new IllegalArgumentException(NULL_ARGUMENT);
            }
            try {
                toGroup.addStudent(student);
                fromGroup.removeStudent(student);
            } catch (StudentWithSameSurnameAlreadyExistsException e) {
                System.out.println(STUDENT_WITH_SAME_SURNAME_EXISTS);
            }
        }
    }
}
