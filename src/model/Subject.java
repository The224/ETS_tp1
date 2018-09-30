package model;

import java.util.ArrayList;
import java.util.function.Function;

public class Subject<T> {

    private ArrayList<Function<T, Boolean>> observersFunction = new ArrayList<>();

    public void emit(T value) {
        observersFunction.forEach( observer -> observer.apply(value) );
    }

    public void subscribe(Function fn) {
        observersFunction.add(fn);
    }

    public void unsubscribeToAll() {
        this.observersFunction.clear();
    }

}
