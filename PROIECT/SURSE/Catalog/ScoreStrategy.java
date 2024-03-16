package Catalog;

import Users.Student;

import java.util.HashMap;
import java.util.Map;

public interface ScoreStrategy{

    Student bestStudent(Course course);
}

class BestPartialScore implements ScoreStrategy{

    @Override
    public Student bestStudent(Course course) {
        HashMap<Student, Grade> studenti = course.getAllStudentGrades();
        Student bestSt = null;
        double scorMax = 0;
        for (Map.Entry<Student, Grade> entry : studenti.entrySet()) {
            Student student = entry.getKey();
            Grade grade = entry.getValue();
            double scorPartial = grade.getPartialScore();
            if (scorPartial > scorMax) {
                scorMax=scorPartial;
                bestSt = student;
            }
        }

        return bestSt;
    }
}

class BestExamScore implements ScoreStrategy{

    @Override
    public Student bestStudent(Course course) {
        HashMap<Student, Grade> studenti = course.getAllStudentGrades();
        Student bestSt = null;
        double scorMax = 0;
        for (Map.Entry<Student, Grade> entry : studenti.entrySet()) {
            Student student = entry.getKey();
            Grade grade = entry.getValue();
            double scorExam = grade.getExamScore();
            if (scorExam > scorMax) {
                scorMax=scorExam;
                bestSt = student;
            }
        }

        return bestSt;
    }
}

class BestTotalScore implements ScoreStrategy{

    @Override
    public Student bestStudent(Course course) {
        HashMap<Student, Grade> studenti = course.getAllStudentGrades();
        Student bestSt = null;
        double scorMax = 0;
        for (Map.Entry<Student, Grade> entry : studenti.entrySet()) {
            Student student = entry.getKey();
            Grade grade = entry.getValue();
            double scorTotal = grade.getTotal();
            if (scorTotal> scorMax) {
                scorMax=scorTotal;
                bestSt = student;
            }
        }

        return bestSt;
    }
}