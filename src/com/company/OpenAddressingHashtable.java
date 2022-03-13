package com.company;

public class OpenAddressingHashtable<T> implements IHashtable<T> {


    private T[] table = null;
    private int step = 3;

    public OpenAddressingHashtable(int powSize){
        table = (T[]) new Object[(int)Math.pow(2, powSize)];
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
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        while(table[(hash + offset) % table.length] != null){
            offset += step;
            if ((hash + offset) % table.length == hash % table.length){
                throw new OutOfMemoryError("Table is full");
            }
        }
        table[(hash + offset) % table.length] = value;
    }

    @Override
    public void remove(T value) throws Exception {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        int indexDel;
        //узнаем индекс элемента на удаление
        while(table[(hash + offset) % table.length] != null){
            if (table[(hash + offset) % table.length].equals(value)) {
                indexDel = (hash + offset) % table.length;
                break;
            }
            offset += step;
        }

        //перестраиваем таблицу с заглядыванием вперёд на шаг
        while(table[(hash + offset) % table.length] != null){
            table[(hash + offset) % table.length] = table[(hash + offset + step) % table.length];
            offset += step;
        }
    }

    @Override
    public boolean contains(T value) {
        if (isNull()){
            throw new NullPointerException("Table is null");
        }
        int hash = Math.abs(value.hashCode());
        int offset = 0;
        while(table[(hash + offset) % table.length] != null){
            if (table[(hash + offset) % table.length].equals(value))
                return true;
            offset += step;
        }
        return false;
    }

    @Override
    public void print() {
        for(int i = 0; i < table.length; i++){
            if (table[i] != null)
                System.out.println("Table[" + i + "] = " + table[i]);
            else
                System.out.println("Table[" + i + "] = null");
        }
    }

    @Override
    public boolean isNull() {
        return table == null;
    }
}
