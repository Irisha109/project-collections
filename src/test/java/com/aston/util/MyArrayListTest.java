package com.aston.util;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    MyArrayList<String> arrayList;

    @BeforeEach
    void init() {
        arrayList = new MyArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");
    }

    @Test()
     void constructorTest(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new MyArrayList<String>(-5),
                "IllegalArgumentException error was expected");

        assertNotNull(arrayList);
        assertEquals("Illegal Capacity: -5", exception.getMessage());
    }

    @Test
    void size() {
        assertEquals(5, arrayList.size());
    }

    @ParameterizedTest
    @CsvSource(value = {"3, F"})
    void addWithIndex(int index, String element) {
        arrayList.add(index, element);

        assertEquals("F", arrayList.get(3));
        assertEquals(6, arrayList.size());
    }

    @Test
    void add() {
        arrayList.add("G");

        assertEquals("G", arrayList.get(5));
        assertEquals(6, arrayList.size());
    }

    @Test
    void remove() {
        arrayList.remove(2);

        assertEquals("D", arrayList.get(2));
        assertEquals(4, arrayList.size());
    }

    @Test
    void get() {
        assertEquals("A", arrayList.get(0));
    }

    @Test
    void set() {
        arrayList.set(1, "Z");

        assertEquals("Z", arrayList.get(1));
    }

    @Test
    void sublist() {
        MyArrayList<String> subArrayList = arrayList.sublist(0, 1);

        assertEquals(2, subArrayList.size());
        assertEquals("A", subArrayList.get(0));
    }
}