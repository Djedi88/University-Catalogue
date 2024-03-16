package Users;

import java.util.*;


public class Group extends ArrayList<Student> {

    private Assistant assistant;
    private String ID;
    private Comparator<Student> comp;
    public Group(String ID, Assistant assistant){
        this.ID = ID;
        this.assistant = assistant;

       setComp();
    }


    public Group(String ID, Assistant assistant, Comparator<Student> comp){
        this.ID= ID;
        this.assistant=assistant;
        setComp();

    }

    public void addStudent(Student student){
        add(student);
        Collections.sort(this,comp);

    }



    public Assistant getAssistant() {
        return assistant;
    }

    public void setComp() {
        this.comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public void setID(String ID) {
        this.ID = ID;
    }



    public String getID() {
        return ID;
    }

    public List<Student> sList(){
        return new ArrayList<>(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "assistant=" + assistant +
                ", ID='" + ID + '\'' +
                ", studentList=";    }
}
