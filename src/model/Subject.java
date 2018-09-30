package model;

import java.util.ArrayList;
import java.util.function.Function;

public class Subject<T> {



    private ArrayList<Function<T, Boolean>> functions = new ArrayList<>();



    public void emit(T value) {
        functions.forEach( observer -> observer.apply(value) );
    }

    public void subscribe(Function fn) {
        functions.add(fn);
    }
}
