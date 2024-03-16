package Catalog;

public interface Element {
    void accept(Visitor visitor);
}
