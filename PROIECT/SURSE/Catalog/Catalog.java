package Catalog;
import Users.Student;

import java.util.ArrayList;
import java.util.List;


public class Catalog implements Subject{
    private static Catalog instance;
    private List<Course> courseList = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public static Catalog getInstance(){
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }
    public void addCourse(Course course) {

        if (!courseList.contains(course)) {
            courseList.add(course);
        } else System.out.println("Cursul exista deja in lista");
    }

    public void removeCourse(Course course) {
        if (courseList.contains(course)) {
            courseList.remove(course);
        } else System.out.println("Cursul nu se afla in lista");
    }

    public Course getCourse(String course) {
        for(Course course1: courseList){
            if(course1.getNume().equals(course))
                return course1;
        }
        return null;
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Grade grade) {
        String courseName = grade.getCourse();
        for(Course courseAux: this.courseList ){
            if(courseAux.getNume().equals(courseName)){
                for (Observer o: observers){
                    o.update(new Notification(grade,courseAux));
                }
                break;
            }
        }
    }


    public void addGrade(Course course, Grade grade){
        course.addGrade(grade);
        notifyObserver(grade);
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}

