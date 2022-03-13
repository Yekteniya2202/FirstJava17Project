package com.company;

public interface IHashtable<T> {
    void add(T value) throws Exception;
    void remove(T value) throws Exception;
    boolean contains(T value);
    void print();
    boolean isNull();
}
