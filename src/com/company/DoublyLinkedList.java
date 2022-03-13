package com.company;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements ILinkedList <T>{

    private ListNode<T> front;
    private int size = 0;


    //вставка в начало - меняется front
    @Override
    public void addFront(T value) {
        if(isEmpty()){
            front = new ListNode<T>(value);
        }
        else {
            ListNode<T> tmp = front;
            front = new ListNode<T>(value, tmp, null);
            tmp.prev = front;
        }
        size++;
    }

    @Override
    public void addEnd(T value) {
        if(isEmpty()){
            front = new ListNode<T>(value);
        }
        else {
            ListNode<T> tmp = front;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = new ListNode<T>(value, null, tmp);
        }
        size++;
    }

    @Override
    public void remove(T value) throws Exception{
        if (isEmpty())
            throw new NoSuchElementException("Element" + value.toString() + "not found");

        //если начало списка
        if (front.data.equals(value)){
            front = front.next;
            front.prev = null;
            return;
        }

        //Ищем
        ListNode<T> tmp = front;
        while(tmp != null && !tmp.data.equals(value)){
            tmp = tmp.next;
        }

        //Не нашли
        if(tmp == null){
            throw new NoSuchElementException("Element" + value.toString() + "not found");
        }
        //нашли
        if(tmp.next == null) {
            tmp.prev.next = null;
            return;
        }
        if(tmp.equals(front)){
            front = tmp.next;
            front.prev = null;
            return;
        }

        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;

        size--;

    }

    @Override
    public boolean contains(T value) {
        if(isEmpty()){
            return false;
        }

        //Ищем
        ListNode<T> tmp = front;
        while(tmp != null && !tmp.data.equals(value)){
            tmp = tmp.next;
        }

        return tmp == null ? false : true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printForward() {
        if(isEmpty()){
            return;
        }
        ListNode<T> tmp = front;
        while(tmp.next != null){
            System.out.print(tmp.data.toString() + " < = > ");
            tmp = tmp.next;
        }
        System.out.print(tmp.data.toString());
        System.out.println();
    }

    @Override
    public void printBackward() {
        if(isEmpty()){
            return;
        }
        ListNode<T> tmp = front;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        while(tmp.prev != null){
            System.out.print(tmp.data.toString() + " < = > ");
            tmp = tmp.prev;
        }
        System.out.print(tmp.data.toString() + ' ');
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }
}
