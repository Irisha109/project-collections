package com.aston.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    MyLinkedList<String> linkedList;

    @BeforeEach
    void init() {
        linkedList = new MyLinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");
    }
    @Test()
    void constructorTest(){
        assertNotNull(linkedList);
    }


    @Test
    void add() {
        linkedList.add("G");

        assertEquals("G", linkedList.get(5));
        assertEquals(6, linkedList.size());
    }

    @ParameterizedTest
    @CsvSource(value = {"0, Z",
                        "3, F",
                        "4, X"})
    void addWithIndex(int index, String element) {
        linkedList.add(index, element);

        assertEquals(6, linkedList.size());
    }

    @Test
    void remove() {
        linkedList.remove(2);

        assertEquals("D", linkedList.get(2));
        assertEquals(4, linkedList.size());
    }

    @Test
    void get() {
        assertEquals("A", linkedList.get(0));
    }

    @Test
    void set() {
        linkedList.set(1, "Z");

        assertEquals("Z", linkedList.get(1));
    }

    @Test
    void size() {
        assertEquals(5, linkedList.size());
    }

    @Test
    void sublist() {
        MyLinkedList<String> subList = linkedList.sublist(0, 1);

        assertEquals(2, subList.size());
        assertEquals("A", subList.get(0));
    }
}