package com.company;

public class ListNode<T> {
    T data;
    ListNode<T> next;
    ListNode<T> prev;

    ListNode(T data){
        this.data = data;
        this.next = this.prev = null;
    }
    ListNode(T data, ListNode<T> next, ListNode<T> prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
