package logic;

import entity.Group;
import entity.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GroupLogic {

    private static final String NULL_ARGUMENT = "The argument that was passed to this method is null.";

    public static boolean studentWithSameSurnameExistsInGroup(Student student, Group group) {
        if (student == null || group == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }

        String studentSurname = student.getSurname();

        Collection<Student> students = group.getStudents();
        for (Student currentStudent : students) {
            String currentStudentSurname = currentStudent.getSurname();

            if (studentSurname.equals(currentStudentSurname)) {
                return true;
            }
        }

        return false;
    }

    public static Optional<Group> findGroupOfStudent(Student student, List<Group> groups) {
        if (student == null || groups == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }

        for (Group currentGroup : groups) {
            List<Student> studentsOfCurrentGroup = currentGroup.getStudents();

            if (studentsOfCurrentGroup.contains(student)) {
                return Optional.of(currentGroup);
            }
        }

        return Optional.empty();
    }
}
