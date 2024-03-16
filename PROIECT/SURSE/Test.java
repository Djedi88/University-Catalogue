
import Catalog.*;
import Users.*;

import java.util.*;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {

        Catalog catalog = Catalog.getInstance();

        Student s1 = new Student("Gigel", "Boss");
        Student s2 = new Student("Ana", "Alina");
        Student s3 = new Student("Maria", "Top");
        Student s4 = new Student("Andrei", "Ciorbea");
        Student s5 = new Student("Ion", "Vasile");
        Assistant assistant = new Assistant("Florin","Popescu");
        Assistant assistant1 = new Assistant("Ana", "Oancea");
        Group g = new Group("321CC", assistant);
        g.addStudent(s1);
        g.addStudent(s2);
        g.addStudent(s3);
        g.addStudent(s4);
        g.addStudent(s5);

        Student s6 = new Student("Florin", "Mihai");
        Student s7 = new Student("Dobrea", "Radu");
        Student s8 = new Student("Dana", "Fata");
        Student s9 = new Student("Andrei", " Negru");
        Student s10 = new Student("Cata", "Angel");

        Group g2 = new Group("322CC", new Assistant("Andrei", "Popa"));
        g2.addStudent(s6);
        g2.addStudent(s7);
        g2.addStudent(s8);
        g2.addStudent(s9);
        g2.addStudent(s10);

        UserFactory userFactory = new UserFactory();
        User m1 = userFactory.getUser("parent", "Diana", "Popa");
        User t1 = userFactory.getUser("parent", "Ion", "Popa");


        User m2 = userFactory.getUser("parent", "Roxana", "Matei");
        User t2 = userFactory.getUser("parent", "Vlad", "Matei");

        User m3 = userFactory.getUser("parent", "Elena", "Nicolae");
        User t3 = userFactory.getUser("parent", "Gheorghe", "Nicolae");

        s1.setMother((Parent)m1);
        s1.setFather((Parent)t1);
        s2.setMother((Parent)m2);
        s2.setFather((Parent)t2);
        s3.setMother((Parent)m3);
        s3.setFather((Parent)t3);

        Teacher tt1 = new Teacher("Mihai", "Nan");
        Course.FullCourseBuilder fullCourseBuilder = new Course.FullCourseBuilder("POO", tt1, 5);

        FullCourse course = fullCourseBuilder.build();
        catalog.addCourse(course);
        catalog.addObserver((Parent)m1);
        catalog.addObserver((Parent)t1);
        catalog.addObserver((Parent)m2);
        catalog.addObserver((Parent)t2);
        catalog.addObserver((Parent)m3);
        catalog.addObserver((Parent)t3);


        course.addGroup(g);
        course.addGroup(g2);
        course.addAssistant("321CC", assistant);
        course.addAssistant("322CC",assistant1);

        Grade agrade1 = new Grade();
        agrade1.setPartialScore(5.0);
        agrade1.setExamScore(3.0);
        agrade1.setStudent(s1);
        agrade1.setCourse("POO");
        Grade agrade2 = new Grade();
        agrade2.setPartialScore(3.5);
        agrade2.setExamScore(3.0);
        agrade2.setStudent(s2);
        agrade2.setCourse("POO");
        Grade agrade3 = new Grade();
        agrade3.setPartialScore(4.5);
        agrade3.setExamScore(2.5);
        agrade3.setStudent(s3);
        agrade3.setCourse("POO");
        Grade agrade4 = new Grade();
        agrade4.setPartialScore(5.5);
        agrade4.setExamScore(3.5);
        agrade4.setStudent(s4);
        agrade4.setCourse("POO");
        Grade agrade5 = new Grade();
        agrade5.setPartialScore(2.0);
        agrade5.setExamScore(5.5);
        agrade5.setStudent(s5);
        agrade5.setCourse("POO");


        ScoreVisitor visitor = new ScoreVisitor();
        Teacher tt = new Teacher("Ionel", "Adam");
        visitor.visit(tt);
        visitor.visit(tt1);
        visitor.addTeacherGrade(tt, agrade1,catalog);
        visitor.addTeacherGrade(tt, agrade2,catalog);
        visitor.addTeacherGrade(tt, agrade3,catalog);
        visitor.addTeacherGrade(tt, agrade4,catalog);
        visitor.addTeacherGrade(tt, agrade5,catalog);
        catalog = visitor.updateCatalogT(tt,catalog);

        HashMap<Student,Grade> studentGrades2 = course.getAllStudentGrades();
        for (Map.Entry<Student, Grade> entry : studentGrades2.entrySet()) {
            Student key = entry.getKey();
            Grade value = entry.getValue();
            System.out.println(key.getFirstName() + " " + key.getLastName() + " - " + value.getExamScore());
        }

        Grade cgrade2 = new Grade();
        cgrade2.setPartialScore(3.5);
        cgrade2.setExamScore(3.0);
        cgrade2.setStudent(s2);

        Grade cgrade3 = new Grade();
        cgrade3.setPartialScore(2.5);
        cgrade3.setExamScore(3.0);
        cgrade3.setStudent(s3);

        Grade cgrade4 = new Grade();
        cgrade4.setPartialScore(2.5);
        cgrade4.setExamScore(3.0);
        cgrade4.setStudent(s1);

        Course.PartialCourseBuilder partialCourseBuilder = new Course.PartialCourseBuilder("SO", new Teacher("Razvan","D"), 5);
        PartialCourse partialCourse = partialCourseBuilder.build();
        catalog.addCourse(partialCourse);
        Assistant assistant2 = new Assistant("Andrei", "B");
        partialCourse.addAssistant("322CC", assistant2);
        Grade bgrade1 = new Grade();
        bgrade1.setPartialScore(5.0);
        bgrade1.setExamScore(3.0);
        bgrade1.setStudent(s6);
        Grade bgrade2 = new Grade();
        bgrade2.setPartialScore(2.5);
        bgrade2.setExamScore(3.0);
        bgrade2.setStudent(s7);
        Grade bgrade3 = new Grade();
        bgrade3.setPartialScore(4.5);
        bgrade3.setExamScore(2.5);
        bgrade3.setStudent(s8);
        Grade bgrade4 = new Grade();
        bgrade4.setPartialScore(5.0);
        bgrade4.setExamScore(3.5);
        bgrade4.setStudent(s9);
        Grade bgrade5 = new Grade();
        bgrade5.setPartialScore(2.0);
        bgrade5.setExamScore(5.0);
        bgrade5.setStudent(s10);

        Grade bgrade6 = new Grade();
        bgrade6.setPartialScore(2.0);
        bgrade6.setExamScore(5.0);
        bgrade6.setStudent(s2);

        partialCourse.addGrade(bgrade1);
        partialCourse.addGrade(bgrade2);
        partialCourse.addGrade(bgrade3);
        partialCourse.addGrade(bgrade4);
        partialCourse.addGrade(bgrade5);


        Assistant a1 = new Assistant("Mihnea", "Radu");
        Assistant a2 = new Assistant("Marius", "Busescu");
        Assistant a3 = new Assistant("Florin", "Cosescu");
        Assistant a4 = new Assistant("Daniel", "Barlan");
        Assistant a5 = new Assistant("Andreea", "Vasile");
        Assistant a6 = new Assistant("David", "Neagru");

        course.addAssistant("322CC", a1);
        course.addAssistant("321CC", a2);
        course.addAssistant("322CC", a3);
        System.out.println(course.getAsList());

        partialCourse.addAssistant("323CC", a4);
        partialCourse.addAssistant("324CC", a5);
        partialCourse.addAssistant("323CC", a6);

        System.out.println(course.getGroup().get(0).sList());

        Course.FullCourseBuilder fcb = new Course.FullCourseBuilder("SDA", tt1, 5);
        FullCourse fc = fcb.build();
        catalog.addCourse(fc);
        new GUI(catalog);



    }
}