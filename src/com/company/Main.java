package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        OpenAddressingHashtable<String> hashtable = new OpenAddressingHashtable<String>(5);

        Random rng = new Random();
        for(int i = 0; i < 100; i++){
            hashtable.add(generateString(rng, "qwe", 3));
        }

        hashtable.print();
        int failedRemovings = 0;
        for(int i = 0; i < 99999; i++){
            try {
                hashtable.remove(generateString(rng, "qwe", 3));
            } catch (Exception e){
                failedRemovings++;
            }
        }

        for(int i = 0; i < 10; i++){
            hashtable.add(generateString(rng, "qwe", 3));
        }
        System.out.println("===================================");
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
