package test;

import model.MyLinkedList;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    @org.junit.Test
    public void indexOf() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        assertEquals(myLinkedList.indexOf("Edward"), 2);
    }

    @org.junit.Test
    public void remove() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        MyLinkedList test = new MyLinkedList();
        test.add("Dias");
        test.add("Malika");
        test.add("Edward");
        assertEquals(test.remove(2), myLinkedList.get(2));
    }

    @org.junit.Test
    public void contains() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        assertEquals(myLinkedList.contains("Dias"), true);
    }

    @org.junit.Test
    public void size() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        assertEquals(myLinkedList.size(), 3);
    }

    @org.junit.Test
    public void get() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        assertEquals(myLinkedList.get(2), "Edward");
    }

    @org.junit.Test
    public void reset() throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("Dias");
        myLinkedList.add("Malika");
        myLinkedList.add("Edward");
        myLinkedList.reset();
        assertEquals(myLinkedList.size(), 0);
    }
}