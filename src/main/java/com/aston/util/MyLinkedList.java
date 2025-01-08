package com.aston.util;

import java.util.Objects;


public class MyLinkedList<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    /**
     * The method add an element to list to end position;
     *
     * @param element - the value of the new element
     */
    public void add(E element) {
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(lastNode, element, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
    }

    /**
     * The method add an element from the specified index;
     * This method calling helper method searchByIndex(index),
     * which checks the correct index value using the method (Objects.CheckIndex());
     *
     * @param index   - the index of the new element
     * @param element - the value of the new element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public void add(int index, E element) {
        Node<E> currentNode = searchByIndex(index);
        Node<E> previousNode = currentNode.previous;
        Node<E> newNode = new Node<>(previousNode, element, currentNode);
        currentNode.previous = newNode;
        if (previousNode != null) {
            previousNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    /**
     * The method removes an element from the specified position (index),
     * This method calling helper method searchByIndex(index),
     * which checks the correct index value using the method (Objects.CheckIndex());
     *
     * @param index - index of the item being deleted
     * @return E (oldValue) - the value of the deleted element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E remove(int index) {
        Node<E> currentNode = searchByIndex(index);
        E oldValue = currentNode.item;
        currentNode.item = null;
        Node<E> nextNode = currentNode.next;
        Node<E> previousNode = currentNode.previous;
        if (previousNode == null) {
            first = nextNode;
        } else {
            previousNode.next = nextNode;
            currentNode.previous = null;
        }

        if (nextNode == null) {
            last = previousNode;
        } else {
            nextNode.previous = previousNode;
            currentNode.next = null;
        }
        size--;
        return oldValue;
    }

    /**
     * The method returns the value of an element
     * from the specified position (index)
     * This method calling helper method searchByIndex(index),
     * which checks the correct index value using the method (Objects.CheckIndex());
     *
     * @param index - index of the returned element
     * @return value - value of the returned element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E get(int index) {
        return searchByIndex(index).item;
    }

    /**
     * The method changes the value of the element at the specified position
     * This method calling helper method searchByIndex(index),
     * which checks the correct index value using the method (Objects.CheckIndex());
     *
     * @param index   - the index of the element being modified
     * @param element - the value of the element being modified
     * @return - the old value of the modified element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        E oldValue = searchByIndex(index).item;
        searchByIndex(index).item = element;
        return oldValue;
    }

    /**
     * The method returns the number of elements in the list
     *
     * @return - the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * The method returns a new list of elements from the main array
     * if the indexes are within
     *
     * @param fromIndex - the initial position of the new list
     * @param toIndex   - the final position of the new list
     * @return new list
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (fromIndex < toIndex && fromIndex > -1 && toIndex <= size)
     */
    public MyLinkedList<E> sublist(int fromIndex, int toIndex) {
        if (fromIndex < toIndex && fromIndex > -1 && toIndex <= size) {
            MyLinkedList<E> subLinkedList = new MyLinkedList<>();
            for (int i = fromIndex; i <= toIndex; i++) {
                subLinkedList.add(get(i));
            }
            return subLinkedList;
        } else
            throw new IndexOutOfBoundsException("Indexes out of bounds");
    }

    /**
     * Compares the specified object with this list for equality;
     * returns true if and only if the specified object is also a list,
     * both lists have the same size, and all corresponding
     * pairs of elements in the two lists are equal;
     * (Two elements e1 and e2 are equal if Objects.equals(e1, e2))
     *
     * @param object - the compare value
     * @return true - if two objects equal, else - false
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        MyLinkedList<?> that = (MyLinkedList<?>) object;
        return size == that.size && Objects.equals(first, that.first)
                && Objects.equals(last, that.last);
    }

    /**
     * Returns the hash code value for this list
     *
     * @return - the hash code int value
     */
    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Objects.hashCode(first);
        result = 31 * result + Objects.hashCode(last);
        return result;
    }

    /**
     * The method returns a string representation of the list
     *
     * @return - a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyLinkedList {");
        for (int i = 0; i < size - 1; i++) {
            builder.append(get(i)).append(", ");
        }
        builder.append(get(size - 1)).append("}");
        return builder.toString();
    }

    /**
     * The helper class Node that contains the element value
     * and references to the previous and next elements
     *
     * @param <E> - type of value
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        Node(Node<E> previous, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.previous = previous;
        }
    }

    /**
     * The helper method for search Node by index;
     * This method checks if the index is included
     * in the specified range using the method Objects.checkIndex
     *
     * @param index - the index search element
     * @return - Node
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    private Node<E> searchByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<E> element;
        if (index <= (size / 2)) {
            element = first;
            for (int i = 0; i < index; i++)
                element = element.next;
        } else {
            element = last;
            for (int i = size - 1; i > index; i--)
                element = element.previous;
        }
        return element;
    }

}
