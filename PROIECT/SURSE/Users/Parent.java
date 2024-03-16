package Users;

import Catalog.Grade;
import Catalog.Notification;
import Catalog.Observer;
import Catalog.Subject;

import java.util.ArrayList;
import java.util.List;

public class Parent extends User implements Observer {

    private String lastName;
    private List<Notification> notificari = new ArrayList<>();

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
        this.lastName = lastName;
    }



    @Override
    public void update(Notification notification) {
        if(notification.getGrade().getStudent().getFather()!=null || notification.getGrade().getStudent().getMother()!=null)
            if(notification.getGrade().getStudent().getFather().getLastName().equals(lastName)){
                notificari.add(notification);
            System.out.println(notification);
        }
    }
    public List<String> notifies(Parent parent){
        List<String> notifiess = new ArrayList<>();
        for(Notification notification: notificari){
            String s = notificari.toString();
            notifiess.add(s);
        }
        return notifiess;
    }
}
