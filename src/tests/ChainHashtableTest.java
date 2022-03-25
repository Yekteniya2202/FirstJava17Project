package tests;

import com.company.ChainHashtable;
import com.company.DoublyLinkedList;
import com.company.IHashtable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainHashtableTest {

    IHashtable<Integer> hashtable = new ChainHashtable<Integer>(true, new DoublyLinkedList<Integer>(), 20);
    @BeforeEach
    void setUp() {
        hashtable.clear();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void add() throws Exception {
        Assertions.assertDoesNotThrow(() -> {
            for(int i = -100; i < 100; i+=2) {
                hashtable.add(i);
            }
        });
        for(int i = -100; i < 100; i+=2){
            Assertions.assertTrue(hashtable.contains(i));
        }
    }

    @Test
    void remove() {
        Assertions.assertDoesNotThrow(() -> {
            for(int i = -100; i < 100; i+=2) {
                hashtable.add(i);
            }
        });
        Assertions.assertDoesNotThrow(() -> {
            for(int i = -100; i < 100; i+=2) {
                hashtable.remove(i);
            }
        });
    }

    @Test
    void contains() {
        Assertions.assertDoesNotThrow(() -> {
            for(int i = -100; i < 100; i+=2) {
                hashtable.add(i);
            }
        });
        for(int i = -100; i < 100; i+=2){
            Assertions.assertTrue(hashtable.contains(i));
        }
    }

}