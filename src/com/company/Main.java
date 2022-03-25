package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        IHashtable<Integer> hashtable = new ChainHashtable<Integer>(true, new DoublyLinkedList<Integer>(), 7);
        if (testAdding(hashtable)){
            System.out.println("Adding good");
        }
        else{
            System.out.println("Adding not good");
        }
        if (testDeleting(hashtable)){
            System.out.println("Deleting good");
        }
        else{
            System.out.println("Deleting not good");
        }
        if (testContaining(hashtable)){
            System.out.println("Containing good");
        }
        else{
            System.out.println("Containing not good");
        }
        if (testCollectionable(hashtable)){
            System.out.println("Collectionable good");
        }
        else{
            System.out.println("Collectionable not good");
        }
    }

    private static boolean testAdding(IHashtable<Integer> hashtable) {
        hashtable.clear();
        for(int i = -100; i < 100; i+=2)
            try {
                hashtable.add(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        for(int i = -100; i < 100; i+=2){
            if(!hashtable.contains(i)){
                return false;
            }
        }
        return true;
    }

    private static boolean testDeleting(IHashtable<Integer> hashtable) {
        hashtable.clear();
        for(int i = -100; i < 100; i+=2){
            try {
                hashtable.add(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        for(int i = -100; i < 100; i+=2){
            try {
                hashtable.remove(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    private static boolean testContaining(IHashtable<Integer> hashtable) {
        hashtable.clear();
        for(int i = -500; i < 500; i+=2){
            try {
                hashtable.add(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        for(int i = -100; i < 100; i+=2){
            if(!hashtable.contains(i)){
                return false;
            }
        }
        return true;
    }
    private static boolean testCollectionable(IHashtable<Integer> hashtable) {
        hashtable.clear();
        for(int i = -1000; i < 1000; i++){
            try {
                hashtable.add(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        for(int i = -1000; i < -500; i++){
            try {
                hashtable.remove(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        for(int i = 500; i < 1000; i++){
            try {
                hashtable.remove(i);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        for(int i = -500; i < 500; i++){
            if(!hashtable.contains(i)){
                return false;
            }
        }
        return true;
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
