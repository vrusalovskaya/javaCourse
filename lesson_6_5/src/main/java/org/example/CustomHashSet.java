package org.example;

import java.lang.reflect.Array;
import java.util.Iterator;

public class CustomHashSet<T> implements Iterable<T> {
    private final static int DEFAULT_CAPACITY = 8;
    private final static int BUCKET_SIZE_LIMIT = 7;


    private CustomHashSetBucket<T>[] buckets;

    public CustomHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashSet(int capacity) {
        initializeBuckets(capacity);
    }

    public boolean put(T element) {
        CustomHashSetBucket<T> bucket = getBucketFor(element);
        if (bucket.contains(element)) {
            return false;
        }

        if (bucket.getSize() == BUCKET_SIZE_LIMIT) {
            resize();
        }

        bucket.add(element);
        return true;
    }

    public boolean contains(T element) {
        CustomHashSetBucket<T> bucket = getBucketFor(element);
        return bucket.contains(element);
    }

    public boolean delete(T element) {
        CustomHashSetBucket<T> bucket = getBucketFor(element);
        return bucket.delete(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomHashSetIterator(this.buckets);
    }

    private int calculateBucketIndex(T element) {
        return element.hashCode() % buckets.length;
    }

    private CustomHashSetBucket<T> getBucketFor(T element) {
        int index = calculateBucketIndex(element);
        return buckets[index];
    }

    private void resize() {
        var oldSet = buckets;
        initializeBuckets(oldSet.length * 2);
        for (CustomHashSetBucket<T> bucket : oldSet) {
            for (T element : bucket) {
                put(element);
            }
        }
    }

    private void initializeBuckets(int capacity) {
        buckets = (CustomHashSetBucket<T>[]) Array.newInstance(CustomHashSetBucket.class, capacity);
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new CustomHashSetBucket<>();
        }
    }

    private class CustomHashSetIterator implements Iterator<T> {
        private final CustomHashSetBucket<T>[] buckets;

        private Iterator<T> bucketIterator;
        private int bucketIndex;

        public CustomHashSetIterator(CustomHashSetBucket<T>[] buckets) {
            this.buckets = buckets;
            this.bucketIterator = buckets[bucketIndex].iterator();
        }

        @Override
        public boolean hasNext() {
            while (!bucketIterator.hasNext() && ++bucketIndex < buckets.length) {
                bucketIterator = buckets[bucketIndex].iterator();
            }

            return bucketIndex < buckets.length;
        }

        @Override
        public T next() {
            return bucketIterator.next();
        }
    }
}
