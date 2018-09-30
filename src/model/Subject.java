package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Subject<T> {

    private String subjectId;

    private ArrayList<Observer<T>> observers = new ArrayList<>();

    public Subject(String subjectId) {

    }


    public void emit(Consumer<? super Subject> algorithm) {
        observers.forEach( algorithm );
    }

    public void subscribe() {
        observers.add(algorithm);
    }
}
