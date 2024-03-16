package Users;

import Catalog.Grade;
import Catalog.Notification;
import Catalog.Observer;
import Catalog.Subject;

public class Student extends User{
    private Parent mother,father;


    public Student(String firstName, String lastName) {
        super(firstName, lastName);

    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }



}
