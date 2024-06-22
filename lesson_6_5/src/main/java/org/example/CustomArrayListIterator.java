package org.example;

import java.util.Iterator;

class CustomArrayListIterator<T> implements Iterator<T> {
    private final CustomArrayList<T> customArrayList;

    private int currentIndex;

    public CustomArrayListIterator(CustomArrayList<T> customArrayList) {
        this.customArrayList = customArrayList;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < customArrayList.getSize();
    }

    @Override
    public T next() {
        return customArrayList.getByIndex(currentIndex++);
    }
}
