package org.example;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayList<T> implements Iterable<T> {
    private final static int DEFAULT_CAPACITY = 8;

    private int size = 0;
    private T[] elements;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be less than zero");
        }

        this.elements = (T[]) new Object[capacity];
    }

    public void put(T element) {
        if (size == elements.length) {
            resize();
        }

        elements[size++] = element;
    }

    public T getByIndex(int index) {
        validateIndex(index);
        return elements[index];
    }

    public T deleteByIndex(int index) {
        validateIndex(index);
        T removedElement = elements[index];

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return removedElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomArrayListIterator<>(this);
    }

    public int getSize() {
        return size;
    }

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
