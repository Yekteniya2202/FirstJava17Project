package com.company;


public class ChainHashtable<T> implements IHashtable<T> {

    private ILinkedList<T>[] chains = null;
    private boolean uniqueMode = false;

    public ChainHashtable(int size){
        chains = new ILinkedList[size];
        for(int i = 0; i < chains.length; i++){
            //как прокинуть тип?
            chains[i] = new DoublyLinkedList<T>();
        }
    }

    public ChainHashtable(boolean uniqueMode, int size){
        this(size);
        this.uniqueMode = uniqueMode;
    }

    @Override
    public void add(T value) throws Exception {
        if (isNull())
            throw new NullPointerException("Table is null");
        int hash = Math.abs(value.hashCode());

        if (uniqueMode && chains[hash % chains.length].contains(value)){
            throw new Exception("Such element is already presented");
        }

        chains[hash % chains.length].addEnd(value);
    }

    @Override
    public void remove(T value) throws Exception {
        if (isNull())
            throw new NullPointerException("Table is null");
        int hash = Math.abs(value.hashCode());
        chains[hash % chains.length].remove(value);
    }

    @Override
    public boolean contains(T value) {
        if (isNull())
            throw new NullPointerException("Table is null");
        int hash = Math.abs(value.hashCode());
        return chains[hash % chains.length].contains(value);
    }

    public void print(){
        if (isNull())
            throw new NullPointerException("Table is null");
        for(int i = 0; i < chains.length; i++){
            if(chains[i].size() != 0) {
                System.out.println("Index = " + i);
                System.out.print("-> "); chains[i].printForward();
            }
        }
    }

    @Override
    public boolean isNull() {
        return chains == null;
    }

    public void clear(){
        chains = null;
    }
    public int size(){
        return chains.length;
    }
}
