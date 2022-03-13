package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        OpenAddressingHashtable<String> hashtable = new OpenAddressingHashtable<String>(6);

        Random rng = new Random();
        for(int i = 0; i < 4; i++){
            hashtable.add("Petya");
        }
        System.out.println(hashtable.contains("Petya"));
        hashtable.print();
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
