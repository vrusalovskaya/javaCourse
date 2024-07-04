package org.example;

import java.util.Iterator;
import java.util.Objects;

class CustomHashSetBucket<T> implements Iterable<T> {
    private class Node {
        public T data;
        public Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public void add(T data) {
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
        size++;
    }

    public boolean delete(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.data, data)) {
            head = head.next;
            size--;
            return true;
        }

        Node temp = head;
        Node prev = null;

        while (temp != null && !Objects.equals(temp.data, data)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp != null && Objects.equals(temp.data, data)) {
            prev.next = temp.next;
            size--;
            return true;
        }

        return false;
    }

    public boolean contains(T data) {
        Node temp = head;
        while (temp != null && !Objects.equals(temp.data, data)) {
            temp = temp.next;
        }

        return temp != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomHashSetBucketIterator(this.head);
    }

    public int getSize() {
        return size;
    }

    private class CustomHashSetBucketIterator implements Iterator<T> {
        private Node head;

        public CustomHashSetBucketIterator(Node head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T returnedData = head.data;
            head = head.next;
            return returnedData;
        }
    }
}
