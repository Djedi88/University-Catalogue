package Catalog;

import Users.Student;
import Users.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullCourse extends Course {


    public FullCourse(FullCourseBuilder builder) {
        super(builder);
    }

    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<>();
        HashMap<Student,Grade> grades = this.getAllStudentGrades();
        for (Map.Entry<Student,Grade> entry : grades.entrySet()) {
            Student student = entry.getKey();
            Grade grade = entry.getValue();
            if(grade.getPartialScore()>=3 && grade.getExamScore()>=2)
                graduatedStudents.add(student);
        }
        return graduatedStudents;
    }


}