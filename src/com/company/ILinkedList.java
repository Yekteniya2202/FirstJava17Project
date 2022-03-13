package com.company;

public interface ILinkedList<T> {
    void addFront(T value);
    void addEnd(T value);
    void remove(T value) throws Exception;
    boolean contains(T value);
    boolean isEmpty();
    void printForward();
    void printBackward();
    int size();

}
