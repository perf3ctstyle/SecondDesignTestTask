package database;

import entity.Group;
import exception.NoSuchCourseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

public class GroupDatabase {

    private final List<Deque<Group>> groupsByCourses;

    private static final String NULL_ARGUMENT = "The argument that was passed to this method is null.";

    public GroupDatabase(List<Deque<Group>> groupsByCourses) {
        this.groupsByCourses = groupsByCourses;
    }

    public List<Deque<Group>> getGroupsByCourses() {
        return groupsByCourses;
    }

    public List<Group> getListOfAllGroups() {
        List<Group> groups = new ArrayList<>();
        for (Deque<Group> currentCourseGroups : groupsByCourses) {
            groups.addAll(currentCourseGroups);
        }
        return groups;
    }

    public void addGroupToDatabase(Group group) {
        if (group == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        int courseNumber = group.getCourseNumber();

        for (Deque<Group> groups : groupsByCourses) {
            Group currentGroup = groups.getFirst();
            int currentCourseNumber = currentGroup.getCourseNumber();

            if (courseNumber == currentCourseNumber) {
                groups.add(group);
                return;
            }
        }
    }

    public void removeGroupFromDatabase(Group group) {
        if (group == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        int courseNumber = group.getCourseNumber();

        for (Deque<Group> groups : groupsByCourses) {
            Group currentGroup = groups.getFirst();
            int currentCourseNumber = currentGroup.getCourseNumber();

            if (courseNumber == currentCourseNumber) {
                groups.remove(group);
                return;
            }
        }
    }

    public Collection<Group> getGroupsOfRequiredCourse(int courseNumber) throws NoSuchCourseException {
        for (Deque<Group> groups : groupsByCourses) {
            Group currentGroup = groups.getFirst();
            int currentCourseNumber = currentGroup.getCourseNumber();

            if (courseNumber == currentCourseNumber) {
                return groups;
            }
        }

        throw new NoSuchCourseException();
    }
}
