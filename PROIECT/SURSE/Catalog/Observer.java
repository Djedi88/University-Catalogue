package Catalog;

import Catalog.Notification;

import java.util.Observable;

public interface Observer {
    void update(Notification notification);
}
