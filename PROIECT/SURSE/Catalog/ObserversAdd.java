package Catalog;

import java.util.ArrayList;
import java.util.List;

public class ObserversAdd implements Subject{
    private List<Observer> observers = new ArrayList<>();
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
        List<Observer> observersLocal = new ArrayList<>(this.observers);

        Notification notification = new Notification(grade, null);
        for (Observer observer : observersLocal) {
            observer.update(notification);
        }
    }

    }

