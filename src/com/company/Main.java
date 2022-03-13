package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        OpenAddressingHashtable<String> hashtable = new OpenAddressingHashtable<String>(6);

        Random rng = new Random();
        for(int i = 0; i < 20; i++){
            hashtable.add(generateString(rng, "qwe", 3));
        }
        String randomStr = generateString(rng, "qwe", 3);
        System.out.println(randomStr);

        hashtable.remove(randomStr);
        hashtable.remove(randomStr);
        hashtable.remove(randomStr);
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
