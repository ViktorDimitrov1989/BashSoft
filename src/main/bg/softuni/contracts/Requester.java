package main.bg.softuni.contracts;

import main.bg.softuni.models.CourseImpl;
import main.bg.softuni.models.StudentImpl;

import java.util.Comparator;

public interface Requester {

    void getStudentMarkInCourse(String courseName, String studentName);

    void getStudentsByCourse(String courseName);

    SimpleOrderedBag<CourseImpl> getAllCoursesSorted(Comparator<CourseImpl> cmp);

    SimpleOrderedBag<StudentImpl> getAllStudentsSorted(Comparator<StudentImpl> cmp);

}
