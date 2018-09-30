package model;

public interface Observer<T> {
    void subjectChange(T newValue);
}
