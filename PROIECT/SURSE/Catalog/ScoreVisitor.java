package Catalog;

import Users.Assistant;
import Users.Student;
import Users.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreVisitor implements Visitor{

    HashMap<Teacher, List<Tuple<Student, String , Double>>> examScores;
    HashMap<Assistant, List<Tuple<Student, String , Double>>>  partialScores;

    public ScoreVisitor(){
        examScores = new HashMap<>();
        partialScores = new HashMap<>();
    }

    private class Tuple<X,Y,Z>{
    private X x;
    private Y y;
    private Z z;

        public Tuple(X x, Y y, Z z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public X getX() {
            return x;
        }

        public Y getY() {
            return y;
        }

        public Z getZ() {
            return z;
        }
    }
    @Override
    public void visit(Assistant assistant) {
        if(partialScores.containsKey(assistant)){
            partialScores.put(assistant, new ArrayList<Tuple<Student,String, Double>>());
        }
    }

    @Override
    public void visit(Teacher teacher) {

        if(!examScores.containsKey(teacher)){
            examScores.put(teacher, new ArrayList<Tuple<Student,String, Double>>());
        }
    }
    public void addTeacherGrade(Teacher teacher, Grade grade, Catalog catalog){
        if (!examScores.containsKey(teacher)) {
            visit(teacher);
        }
        examScores.get(teacher).add(new Tuple<Student, String, Double>(grade.getStudent(),grade.getCourse(),grade.getExamScore()));
        catalog.notifyObserver(grade);

    }
    public void addAssistantGrade(Assistant assistant, Grade grade, Catalog catalog){
        if (!partialScores.containsKey(assistant)) {
            visit(assistant);
        }
        partialScores.get(assistant).add(new Tuple<Student, String, Double>(grade.getStudent(),grade.getCourse(),grade.getPartialScore()));
        catalog.notifyObserver(grade);
    }


    public void printAssistantGrades(Assistant assistant) {
        if (!partialScores.containsKey(assistant)) {
            System.out.println("Asistentul nu a adaugat note..");
            return;
        }

        List<Tuple<Student, String, Double>> teacherGrades = partialScores.get(assistant);

        for (Tuple<Student, String, Double> grade : teacherGrades) {
            System.out.println("Student: " + grade.getX().getFirstName() + " " + grade.getX().getLastName());
            System.out.println("Curs: " + grade.getY());
            System.out.println("Partial grade: " + grade.getZ());
        }
    }

    public void printTeacherGrades(Teacher teacher) {
        if (!examScores.containsKey(teacher)) {
            System.out.println("Profesorul nu a adaugat note.");
            return;
        }

        List<Tuple<Student, String, Double>> teacherGrades = examScores.get(teacher);

        for (Tuple<Student, String, Double> grade : teacherGrades) {
            System.out.println("Student: " + grade.getX().getFirstName() + " " + grade.getX().getLastName());
            System.out.println("Curs: " + grade.getY());
            System.out.println("Exam grade: " + grade.getZ());
        }
    }

    public Catalog updateCatalogT(Teacher teacher, Catalog catalog){
       if(examScores.containsKey(teacher)){
           List<Tuple<Student, String, Double>> teacherGrades = examScores.get(teacher);
           for (Tuple<Student, String, Double> grade : teacherGrades){
               Grade g = new Grade();
               g.setCourse(grade.getY());
               g.setStudent(grade.getX());
               g.setExamScore(grade.getZ());
               catalog.getCourse(grade.getY()).addGrade(g);
           }
           return catalog;
       }
       return catalog;
    }

    public Catalog updateCatalogA(Assistant assistant, Catalog catalog){
        if(partialScores.containsKey(assistant)){
            List<Tuple<Student, String, Double>> teacherGrades = partialScores.get(assistant);
            for (Tuple<Student, String, Double> grade : teacherGrades){
                Grade g = new Grade();
                g.setCourse(grade.getY());
                g.setStudent(grade.getX());
                g.setPartialScore(grade.getZ());
                catalog.getCourse(grade.getY()).addGrade(g);
            }
            return catalog;
        }
        return catalog;
    }

    public List<String> PGT(Teacher teacher){
        List<String> str = new ArrayList<>();

        List<Tuple<Student, String, Double>> teacherGrades = examScores.get(teacher);

        for (Tuple<Student, String, Double> grade : teacherGrades) {
            str.add(grade.getY());

        }
        return str;
    }

    public List<String> PGG(Teacher teacher){
        List<String> str = new ArrayList<>();

        List<Tuple<Student, String, Double>> teacherGrades = examScores.get(teacher);

        for (Tuple<Student, String, Double> grade : teacherGrades) {
            str.add(Double.toString(grade.getZ()));

        }
        return str;
    }

}
