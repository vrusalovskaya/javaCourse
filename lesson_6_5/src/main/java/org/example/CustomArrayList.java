package org.example;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class CustomArrayList<T> {
    private final int defaultCapacity = 10;
    private int size = 0;
    private Object[] elements;

    public CustomArrayList(){
        this.elements = new Object[defaultCapacity];
    }

    public CustomArrayList(int capacity){
        this.elements = new Object[capacity];
    }

    public void put(T element){
        if (size == elements.length){
            resize();
        }

        elements[size++] = element;
    }

    public T getByIndex (int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    public T deleteByIndex(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Object removedElement = elements[index];
        elements = ArrayUtils.removeElements(elements, elements[index]);
        return (T) removedElement;
    }


    private void resize(){
        elements = Arrays.copyOf(elements, elements.length*2);
    }
}
