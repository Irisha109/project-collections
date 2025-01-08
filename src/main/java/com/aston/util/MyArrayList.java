package com.aston.util;

import java.util.*;

public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final double INCREASE_FACTOR = 1.5;
    private Object[] elementData;
    private int size = 0;

    /**
     * Creates a new array with the initial size
     * DEFAULT_CAPACITY = 10
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates a new array with the initial size
     * to be set and verifies the correctness of the parameter
     *
     * @param initialCapacity - the value capacity
     * @throws IllegalArgumentException - if initialCapacity < 0
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * The method add an element to MyArrayList to end position;
     * checkCapacityIncrease() checks the filling of the array
     * and if the size is greater than or equal
     * to DEFAULT_LOAD_FACTOR = 0.75,
     * the array is enlarged by calling the increaseSize() method
     *
     * @param element - the value of the new element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public void add(E element) {
        if (checkCapacityIncrease()) {
            increaseSize();
        }
        elementData[size++] = element;
    }

    /**
     * The method add an element from the specified index.
     * Objects.CheckIndex() - checks the incoming parameter - index.
     * checkCapacityIncrease() - checks the filling of the array
     * and if the size is greater than or equal to
     * DEFAULT_LOAD_FACTOR = 0.75;
     * the array is enlarged by calling the increaseSize() method,
     * System.arraycopy() is used to shift elements.
     *
     * @param index   - the index of the new element
     * @param element - the value of the new element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public void add(int index, E element) {
        Objects.checkIndex(index, size);
        if (checkCapacityIncrease()) {
            increaseSize();
        }
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * The method removes an element from the specified position (index).
     * The method (Objects.CheckIndex()) - checks the incoming parameter - index.
     * if the element is not in the last position,
     * then we shift the remaining elements with method System.arraycopy();
     *
     * @param index - index of the item being deleted
     * @return E (oldValue) - the value of the deleted element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldValue = (E) elementData[index];
        if ((size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, (size - 1) - index);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * The method returns the value of an element
     * from the specified position (index).
     * The method (Objects.CheckIndex()) - checks the incoming parameter - index.
     *
     * @param index - index of the returned element
     * @return value - value of the returned element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elementData[index];
    }

    /**
     * The method changes the value of the element at the specified position.
     * The method (Objects.CheckIndex()) - checks the incoming parameter - index.
     *
     * @param index   - the index of the element being modified
     * @param element - the value of the element being modified
     * @return - the old value of the modified element
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
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
     * The method returns a new list of elements from the main list
     * if the indexes are within
     *
     * @param fromIndex - the initial position of the new list
     * @param toIndex   - the final position of the new list
     * @return new list
     * @throws IndexOutOfBoundsException -  if the index is out of range
     *                                   (fromIndex < toIndex && fromIndex > -1 && toIndex <= size)
     */
    public MyArrayList<E> sublist(int fromIndex, int toIndex) {
        if (fromIndex < toIndex && fromIndex > -1 && toIndex <= size) {
            MyArrayList<E> subList = new MyArrayList<>();
            for (int i = fromIndex; i <= toIndex; i++) {
                subList.add((E) elementData[i]);
            }
            return subList;
        } else
            throw new IndexOutOfBoundsException("Index out of bounds");
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

        MyArrayList<?> that = (MyArrayList<?>) object;
        return size == that.size && Arrays.equals(elementData, that.elementData);
    }

    /**
     * Returns the hash code value for this list
     *
     * @return - the hash code int value
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(elementData);
        result = 31 * result + size;
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
        builder.append("MyArrayList {");
        for (int i = 0; i < size - 1; i++) {
            builder.append(elementData[i]).append(", ");
        }
        builder.append(elementData[size - 1]).append("}");
        return builder.toString();
    }

    /**
     * The helper method checks the filling of the array
     * and if the size is greater than or equal to
     * DEFAULT_LOAD_FACTOR = 0.75 returns true
     *
     * @return true if the number of elements is greater than or equal to
     * DEFAULT_LOAD_FACTOR
     */
    private boolean checkCapacityIncrease() {
        return size >= (int) (elementData.length * DEFAULT_LOAD_FACTOR);
    }

    /**
     * The helper method,
     * that increases the original array by INCREASE_FACTOR = 1.5
     * with the help System.arraycopy() method
     */
    private void increaseSize() {
        Object[] newElementData = new Object[(int) (elementData.length * INCREASE_FACTOR) + 1];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        elementData = newElementData;
    }

}
