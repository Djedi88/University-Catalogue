package Catalog;

import Users.Student;

import java.util.Observable;
import java.util.Observer;

public class Notification {

    private String notificare;
    private Grade grade;
    private Course course;

    public Notification(Grade grade,Course course) {

        this.grade = grade;
        this.course = course;
        notificare = "Fiul dvs. " + grade.getStudent().getLastName() + " a primit nota " + grade.getTotal() + " la " + course.getNume();

    }
    public Grade getGrade(){
        return grade;
    }

    @Override
    public String toString() {
        return notificare;
    }

    public void setNotificare(String notificare){
        System.out.println(notificare);
    }
}
