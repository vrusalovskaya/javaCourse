package org.example;

public class Main {
    public static void main(String[] args) {
//       CustomArrayList<String> stringList = new CustomArrayList<>();
//       CustomArrayList<Integer> integerList = new CustomArrayList<>(15);
//
//       stringList.put("one");
//       stringList.put("two");
//       stringList.put("three");
//       stringList.put("four");
//       stringList.put("five");
//       stringList.put("six");
//       stringList.put("seven");
//       stringList.put("eight");
//       stringList.put("nine");
//       stringList.put("ten");
//       stringList.put("eleven");
//
//       integerList.put(1);
//       integerList.put(2);
//       integerList.put(3);
//
//       String element = stringList.getByIndex(10);
//       System.out.println("Received element: " + element);
//
//       System.out.println("Deleted element: " + stringList.deleteByIndex(5));
//       System.out.println(stringList.getSize());
//
//       for (String string : stringList){
//          System.out.println(string);
//       }
//
//       for (Integer integer : integerList) {
//          System.out.println(integer);
//       }
//
        CustomHashSet<Integer> hashSet = new CustomHashSet<>();
        for (int i = 0; i < 100; i++) {
            hashSet.put(i);
        }


//        System.out.println(hashSet.contains(1));
//        System.out.println(hashSet.delete(1));
//        System.out.println(hashSet.contains(1));
        for (Integer item : hashSet) {
            System.out.println(item);
        }
    }
}