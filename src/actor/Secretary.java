package actor;

import controller.SecretaryController;
import database.GeneralDatabase;
import database.GroupDatabase;
import entity.Group;
import entity.Student;
import exception.NoSuchCourseException;
import exception.StudentWithSameSurnameAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class Secretary implements SecretaryController {

    private final GeneralDatabase generalDatabase;
    private final GroupDatabase groupDatabase;

    private static final String NO_SUCH_COURSE = "Unfortunately, the course with the given number doesn't exist.";
    private static final String STUDENT_WITH_SAME_SURNAME_EXISTS = "Student with the same surname already exists in this group.";

    public Secretary(GeneralDatabase generalDatabase) {
        this.generalDatabase = generalDatabase;
        this.groupDatabase = generalDatabase.getGroupDatabase();
    }

    @Override
    public void createGroup(String name, int courseNumber, List<Student> students) {
        synchronized (generalDatabase) {
            Group group;
            if (students != null) {
                group = new Group(name, courseNumber, students);
            } else {
                throw new IllegalArgumentException();
            }

            groupDatabase.addGroupToDatabase(group);
        }
    }

    @Override
    public void deleteGroup(Group group) {
        synchronized (generalDatabase) {
            groupDatabase.removeGroupFromDatabase(group);
        }
    }

    @Override
    public List<Student> getListOfStudents(Group group) {
        synchronized (generalDatabase) {
            return group.getStudents();
        }
    }

    @Override
    public List<Student> getListOfStudents(int courseNumber) {
        synchronized (generalDatabase) {
            List<Student> students = new ArrayList<>();
            List<Group> groups;
            try {
                groups = (List<Group>) groupDatabase.getGroupsOfRequiredCourse(courseNumber);
            } catch (NoSuchCourseException e) {
                System.out.println(NO_SUCH_COURSE);
                return students; // returning an empty list
            }

            for (Group group : groups) {
                List<Student> studentsOfCurrentGroup = group.getStudents();
                students.addAll(studentsOfCurrentGroup);
            }

            return students;
        }
    }

    @Override
    public void addStudentToGroup(Student student, Group group) {
        synchronized (generalDatabase) {
            try {
                group.addStudent(student);
            } catch (StudentWithSameSurnameAlreadyExistsException e) {
                System.out.println(STUDENT_WITH_SAME_SURNAME_EXISTS);
            }
        }
    }
}
