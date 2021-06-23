package database;

public class GeneralDatabase {

    private final GroupDatabase groupDatabase;
    private final StudentDatabase studentDatabase;

    public GeneralDatabase(GroupDatabase groupDatabase, StudentDatabase studentDatabase) {
        this.groupDatabase = groupDatabase;
        this.studentDatabase = studentDatabase;
    }

    public GroupDatabase getGroupDatabase() {
        return groupDatabase;
    }

    public StudentDatabase getStudentDatabase() {
        return studentDatabase;
    }
}
