package entity;

import java.util.Optional;

public class StudentInfo {

    private final Student student;
    private final Optional<Group> optionalGroup;

    public StudentInfo(Student student, Optional<Group> optionalGroup) {
        this.student = student;
        this.optionalGroup = optionalGroup;
    }

    public Student getStudent() {
        return student;
    }

    public Optional<Group> getOptionalGroup() {
        return optionalGroup;
    }
}
