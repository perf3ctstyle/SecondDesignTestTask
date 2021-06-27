package entity;

import exception.StudentWithSameSurnameAlreadyExistsException;
import logic.GroupLogic;

import java.util.List;

public class Group {

    private final String name;
    private int courseNumber;
    private final List<Student> students;

    private static final String NULL_ARGUMENT = "The argument that was passed to this method is null.";

    public Group(String name, int courseNumber, List<Student> students) {
        this.name = name;
        this.courseNumber = courseNumber;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) throws StudentWithSameSurnameAlreadyExistsException {
        if (student == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        if (!GroupLogic.studentWithSameSurnameExistsInGroup(student, this)) {
            student.setIsStudying(true);
            students.add(student);
        } else {
            throw new StudentWithSameSurnameAlreadyExistsException();
        }
    }

    public void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        students.remove(student);
    }

    public boolean hasStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        return students.contains(student);
    }
}
