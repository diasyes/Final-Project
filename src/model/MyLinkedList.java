package model;

import model.MyAbstractList;

import java.io.Serializable;

public class MyLinkedList<E> extends MyAbstractList<E> implements Serializable{

    static final long serialVersionUID = 1L;
    private Node<E> head, tail;

    /** Create a default list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }

        size++; // Increase size
    }
    public int indexOf(E e) {
        if(size == 0) {
            return -1;
        } else {
            Node<E> tmp = head;
            int result = 0;
            while(tmp != null) {
                if(tmp.element.equals(e)) {
                    return result;
                } else {
                    tmp = tmp.next;
                    result++;
                }
            }
        }
        return -1;
    }

    @Override
    /** Add a new element at the specified index
     * in this list. The index of the head element is 0 */
    public void add(int index, E e) {
        Node<E> now = head;
        while (now!=null){
            System.out.println(now);
            if (now.element.equals(e)){
                return;
            }
            now = now.next;
        }
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    /** Remove the element at the specified position in this
     *  list. Return the element that was removed from the list. */
    public E remove(int index) {

        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override
    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @Override
    /** Clear the list */
    public void reset() {
        size = 0;
        head = tail = null;
    }

    @Override
    /** Return true if this list contains the element e */
    public boolean contains(E e) {
        if(size == 0) {
            return false;
        } else {
            Node<E> tmp = head;
            while(tmp != null) {
                if(tmp.element.equals(e)) {
                    return true;
                } else {
                    tmp = tmp.next;
                }
            }
        }
        return false;
    }
    public int size(){
        return size;
    }
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    /** Return the element at the specified index */
    public E get(int index) {
        checkIndex(index);
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private static class Node<E> implements Serializable{
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
    private class LinkedListIterator implements java.util.Iterator<E>,Serializable {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }
    }
}

