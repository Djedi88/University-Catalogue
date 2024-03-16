package Catalog;

import Users.Student;
import Users.Teacher;

import java.util.*;

public class PartialCourse extends Course {

    public PartialCourse(PartialCourseBuilder courseBuilder) {
        super(courseBuilder);
    }

    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<>();
        HashMap<Student,Grade> grades = this.getAllStudentGrades();
        for (Map.Entry<Student,Grade> entry : grades.entrySet()) {
            Student student = entry.getKey();
            Grade grade = entry.getValue();
            if(grade.getTotal()>=5) {
                graduatedStudents.add(student);
            }
            }
        return graduatedStudents;
    }
}