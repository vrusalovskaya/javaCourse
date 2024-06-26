package org.example;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> integerList = new CustomArrayList<>();
        CustomArrayList<String> stringList = new CustomArrayList<>(15);

        for (int i = 0; i < 50; i++) {
            integerList.put(i);
        }

        System.out.println("Received element: " + integerList.getByIndex(10));

        System.out.println("Deleted element: " + integerList.deleteByIndex(5));
        System.out.println("Size of integer list: " + integerList.getSize());

        for (Integer integer : integerList) {
            System.out.print(integer + ", ");
        }
        System.out.println();

        CustomHashSet<Integer> hashSet = new CustomHashSet<>();
        for (int i = 0; i < 100; i++) {
            hashSet.put(i);
        }

        System.out.println(hashSet.contains(52));
        System.out.println(hashSet.delete(80));

        for (Integer item : hashSet) {
            System.out.print(item + ", ");
        }
    }
}