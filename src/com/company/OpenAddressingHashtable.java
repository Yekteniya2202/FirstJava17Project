package com.company;

import java.util.Formatter;
import java.util.NoSuchElementException;

public class OpenAddressingHashtable<T> implements IHashtable<T> {


    private T[] table = null;
    private boolean isDeleted[] = null;
    private int step = 1;
    private boolean uniqueMode = false;

    public OpenAddressingHashtable(int powSize){
        table = (T[]) new Object[(int)Math.pow(2, powSize)];
        isDeleted = new boolean[(int)Math.pow(2, powSize)];
    }

    public OpenAddressingHashtable(int powSize, boolean uniqueMode){
        this(powSize);
        this.uniqueMode = uniqueMode;
    }
    //шаг не(не желательно) должен быть кратен размеру таблицы
    public void setStep(int step){
        this.step = step;
    }

    @Override
    public void add(T value) throws Exception {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }

        if (uniqueMode && contains(value)){
            throw new Exception("Element is already presented");
        }


        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int index = (hash + offset) % table.length;
        int startingIndex = index;
        while(isDeleted[index] == false && table[index] != null){
            offset += step;
            index = (hash + offset) % table.length;
            if (index == startingIndex){
                throw new OutOfMemoryError("Table is full");
            }
        }
        table[(hash + offset) % table.length] = value;
        isDeleted[index] = false;
    }

    @Override
    public void remove(T value) throws Exception {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        if(!contains(value)){
            throw new NoSuchElementException("Element not found");
        }
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int indexDel = -1;
        int index = (hash + offset) % table.length;
        int startingIndex = index;
        //узнаем индекс элемента на удаление
        while(table[index] != null){
            if (table[index].equals(value) && isDeleted[index] == false) {
                break;
            }
            offset += step;
            index = (hash + offset) % table.length;
        }
        isDeleted[index] = true;

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
    public void print() {
        for(int i = 0; i < table.length; i++){
            if (table[i] != null)
                System.out.println("Table[" + i + "] = " + table[i] + " IsDeleted = " + isDeleted[i]);

        }
    }

    @Override
    public boolean isNull() {
        return table == null;
    }
}
