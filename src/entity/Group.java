package entity;

import exception.StudentWithSameSurnameAlreadyExistsException;
import logic.GroupLogic;

import java.util.List;

public class Group {

    private final String name;
    private int courseNumber;
    private final List<Student> students;

    public Group(String name, int courseNumber, List<Student> students) {
        this.name = name;
        this.courseNumber = courseNumber;
        if (students != null) {
            this.students = students;
        } else {
            throw new IllegalArgumentException();
        }
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
        if (!GroupLogic.studentWithSameSurnameExistsInGroup(student, this)) {
            student.setIsStudying(true);
            students.add(student);
        } else {
            throw new StudentWithSameSurnameAlreadyExistsException();
        }
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public boolean hasStudent(Student student) {
        return students.contains(student);
    }
}
