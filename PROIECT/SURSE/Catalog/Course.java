package Catalog;

import Users.Assistant;
import Users.Teacher;
import Users.Group;
import Users.Student;

import java.util.*;



public abstract class Course {
    private String nume;
    private Teacher pTitular;
    private List<Assistant> asList; // set de asistenti
    private List<Grade> grades;
    private HashMap<String, Group> dictionar;
    private int puncteCredit;
    private Snapshot snapshot;

    private List<Observer> observers = new ArrayList<>();

    public HashMap<String, Group> getDictionar() {
        return dictionar;
    }



    public Course(CourseBuilder courseBuilder) {
        this.nume = courseBuilder.nume;
        this.pTitular = courseBuilder.pTitular;
        this.puncteCredit = courseBuilder.puncteCredit;
        asList = new ArrayList<>();
        grades = new ArrayList<>();
        dictionar = new HashMap<>();

    }



    public int getPuncteCredit() {
        return puncteCredit;
    }

    public String getNume(){
        return this.nume;
    }
    public void addAssistant(String ID, Assistant assistant){

            if (!asList.contains(assistant)) {
                asList.add(assistant);
            }

    }

    public List<Assistant> getAsList() {
        return asList;
    }

    public void addStudent(String ID, Student student){
        if(dictionar.containsKey(ID)){
            Group gAux = dictionar.get(ID);
            gAux.addStudent(student);
            dictionar.put(ID,gAux);
        }
        else System.out.println("Nu exista groupa");
    }
    public void addGroup(Group group){
        dictionar.put(group.getID(),group);
    }
    public void addGroup(String ID, Assistant assistant){
        Group group = new Group(ID,assistant);
        this.addGroup(group);
    }

    public List<Group> getGroup(){
        List<Group> groupList = new ArrayList<>();
        for (Map.Entry<String, Group> entry : dictionar.entrySet()) {
            String key = entry.getKey();
            Group value = entry.getValue();
            groupList.add(value);
        }
        return groupList;
    }
    public void addGroup(String ID, Assistant assistant, Comparator<Student> comp){
        Group group = new Group(ID,assistant,comp);
        this.addGroup(group);
    }

    public Grade getGrade(Student student){

            for(Grade grade1: grades)
                if(grade1.getStudent().equals(student))
                    return grade1;

        return null;
    }

    public void addGrade(Grade grade){
        grades.add(grade);
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> sList = new ArrayList<>();
        for(Group g: dictionar.values())
            sList.addAll(g);


        return sList;
    }

    public HashMap<Student, Grade> getAllStudentGrades(){
        HashMap<Student,Grade> studentGrades = new HashMap<>();
        for(Grade grade: grades){
            studentGrades.put(grade.getStudent(),grade);
        }
        return studentGrades;
    }

    public Teacher getpTitular() {
        return pTitular;
    }

    public abstract ArrayList<Student> getGraduatedStudents();

    public Student getBestStudent(){
        int number;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdu metoda de alegere a celui mai bun student:\n\t 1 - Best partial score\n\t 2 - Best exam score\n" +
                "\t 3 - Best total score");
        int alegere = sc.nextInt();
        switch (alegere){
            case 1:
                return new BestPartialScore().bestStudent(this);
            case 2:
                return new BestExamScore().bestStudent(this);
            case 3:
                return new BestTotalScore().bestStudent(this);
        }
        return null;
    }


   public abstract static class CourseBuilder{

        private  String nume;
        private Teacher pTitular;
        private int puncteCredit;

        public CourseBuilder(String nume, Teacher pTitular, int puncteCredit){
            this.nume = nume;
            this.pTitular = pTitular;
            this.puncteCredit = puncteCredit;
        }

       public abstract Course build();

    }
    public static class FullCourseBuilder extends CourseBuilder {

        public FullCourseBuilder(String nume, Teacher pTitular, int puncteCredit) {
            super(nume, pTitular, puncteCredit);
        }

        public FullCourse build() {
            return new FullCourse(this);
        }
    }

    public static class PartialCourseBuilder extends CourseBuilder {

        public PartialCourseBuilder(String nume, Teacher pTitular, int puncteCredit) {
            super(nume, pTitular, puncteCredit);
        }

        public PartialCourse build() {
            return new PartialCourse(this);
        }
    }


    public void setSnapshot() {
        this.snapshot = makeSnapshot();
    }
    public Snapshot makeSnapshot(){
        return new Snapshot(this.grades);
    }
    private static class Snapshot{
        private List<Grade> grades;

        public Snapshot(List<Grade> grades) {
            this.grades = new ArrayList<>();
            for (Grade grade : grades) {
                this.grades.add((Grade)grade.clone());
            }
        }

        public List<Grade> getGrades() {
            return grades;
        }
    }
    public void makeBackup() {
        this.snapshot = new Snapshot(grades);
    }

    public void undo() {
        this.grades = snapshot.getGrades();
    }
}




class assistSet implements Set<Assistant>{

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Assistant> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Assistant assistant) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Assistant> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

}

