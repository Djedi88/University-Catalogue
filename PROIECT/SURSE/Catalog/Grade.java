package Catalog;

import Users.Student;

import java.nio.channels.Channel;

public class Grade implements Comparable,Cloneable,Observer{
    private Double partialScore, examScore;
    private Student student;
    private String course;

    public Course getCourseReturnat(Course courseReturnat) {
        return courseReturnat;
    }

    public double getTotal(){
        return partialScore+examScore;
    }
    public Double getPartialScore() {
        return partialScore;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int compareTo(Object o) {
        if (o == null ) throw new NullPointerException ();
        if(!(o instanceof Grade))
            throw new ClassCastException("Nu se pot compara");
        Grade g = (Grade) o;
        return (int)Math.ceil((getTotal() - g.getTotal()));
    }
    public Object clone(){
        try {
        return super.clone();
    } catch (CloneNotSupportedException e) {
        throw new Error("Clona nu s=a reusit");
    }
    }

    @Override
    public void update(Notification notification) {
        System.out.println(notification.toString());
    }

}
