package com.company;

import sun.awt.geom.Crossings;

import java.util.NoSuchElementException;

public class OpenAddressingHashtable<T> implements IHashtable<T> {


    private T[] table = null;
    private boolean[] isDeleted = null;
    private int step = 1;
    private boolean uniqueMode = false;
    int filledSize = 0;
    double filledKoef = 0;
    int powSize = 0;

    public OpenAddressingHashtable(int powSize){
        this.powSize = powSize;
        table = (T[]) new Object[(int)Math.pow(2, powSize)];
        isDeleted = new boolean[(int)Math.pow(2, powSize)];
    }

    public OpenAddressingHashtable(int powSize, boolean uniqueMode){
        this(powSize);
        this.uniqueMode = uniqueMode;
    }
    //шаг не(не желательно) должен быть кратен размеру таблицы
    public void setStep(int step) throws Exception {
        if (step <= 0){
            throw new Exception("Step can not be less than 0");
        }
        int size = 1;
        for(int i = 1; i <= powSize; i++){
            size *= 2;
        }
        if (size % step != 0){
            throw new Exception("Step is not multiple to size");
        }
        this.step = step;
        //меняя шаг необходимо перестроить таблицу
        ResizeTable(this.powSize);
    }

    @Override
    public void add(T value) throws Exception {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        if (uniqueMode && contains(value)){
            throw new Exception("Element is already presented");
        }
        addTo(value, table, isDeleted);
        filledSize++;
        StretchOrCompress();
    }

    private void addTo(T value, T[] destTable, boolean[] destIsDeleted) throws OutOfMemoryError {
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int index = (hash + offset) % destTable.length;
        int startingIndex = index;
        while(destIsDeleted[index] == false && destTable[index] != null){
            offset += step;
            index = (hash + offset) % destTable.length;
            if (index == startingIndex){
                throw new OutOfMemoryError("Table is full");
            }
        }
        destTable[(hash + offset) % destTable.length] = value;
        destIsDeleted[index] = false;
    }

    @Override
    public void remove(T value) throws NullPointerException, NoSuchElementException {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        if(!contains(value)){
            throw new NoSuchElementException("Element not found");
        }

        removeFrom(value, table, isDeleted);

        filledSize--;
        StretchOrCompress();
    }

    private void removeFrom(T value, T[] srcTable, boolean[] srcIsDeleted) {
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int index = (hash + offset) % srcTable.length;
        //узнаем индекс элемента на удаление
        while(srcTable[index] != null){
            if (srcTable[index].equals(value) && srcIsDeleted[index] == false) {
                break;
            }
            offset += step;
            index = (hash + offset) % srcTable.length;
        }
        srcIsDeleted[index] = true;
    }

    private void StretchOrCompress() {
        filledKoef = filledSize * 1.0 / table.length;

        if(filledKoef > 0.7) {
            ResizeTable(++powSize);
        }
        else if (filledKoef < 0.2) {
            ResizeTable(--powSize);
        }
    }

    private void ResizeTable(int powSize){
        T[] newTable = (T[])new Object[(int)Math.pow(2, powSize)];
        int l = newTable.length;
        boolean[] newIsDeleted = new boolean[(int)Math.pow(2, powSize)];
        for(int i = 0; i < table.length; i++){
            if (table[i] != null && isDeleted[i] == false){
                addTo(table[i], newTable, newIsDeleted);
            }
        }
        table = newTable;
        isDeleted = newIsDeleted;
    }


    @Override
    public boolean contains(T value) {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int index = (hash + offset) % table.length;
        int startingIndex = index;
        while(table[index] != null){
            if (isDeleted[index] == false && table[index].equals(value))
                return true;
            offset += step;
            index = (hash + offset) % table.length;
            if (index == startingIndex){
                return false;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for(int i = 0; i < table.length; i++){
            table[i] = null;
            isDeleted[i] = false;
        }
        filledSize = 0;
        StretchOrCompress();
    }

    @Override
    public void print() {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        for(int i = 0; i < table.length; i++){
            System.out.println("Table[" + i + "] = " + (table[i] != null ? table[i] : "null") + " IsDeleted = " + isDeleted[i]);
        }
    }

    @Override
    public boolean isNull() {
        return table == null;
    }
}
