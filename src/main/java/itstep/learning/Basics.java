package itstep.learning;

import java.util.*;

/**
 * Основи мови  програмування
 */
public class Basics {
    public void Run() {
        System.out.println("Basics");
        // типи даних та змінні
        // primitives - Value types
        byte b = 1;     // 8 bit    !! безнакових (unsigned) варіації не існує
        short s = 1;    // 16       byte b = -1;    1111 1111
        int i = 1;      // 32       [short]  b      0000 0000 1111 1111 == 255
        long l = 1;     // 64       (short)  b      1111 1111 1111 1111 == -1
        float f = 1.0f;     // 32
        double d = 1.0E-2;    // 64
        char c = 'Ї';
        boolean bool = true;
        System.out.println(c);


        // reference types
        // boxing
        Byte bb = b;
        Short ss = new Short(s);


        // Arrays, Collections, Loops
        int[] arr1 = {1,2,3,4};
        int[] arr2 = new int[]{1,2,3,4};
        int[] arr3 = new int[4];

        System.out.println("arr1");
        for (int j = 0; j < arr1.length; j++) {
            System.out.print(arr1[j] + " ");
        }

        System.out.println();
        System.out.println("arr2");
        for (int j = 0; j < arr2.length; j++) {
            System.out.print(arr2[j] + " ");
        }

        System.out.println();
        System.out.println("arr3");
        for (int element : arr3) {      //foreach
            System.out.print(element + " ");
        }
        System.out.println();


        int[][] arr2d = {       // only jagged
                {1,2,3},
                {4,5,6,7},
                {8,9}
        };

        System.out.println("arr2d");

        for ( int[] elementI : arr2d)
        {
            for ( int elementJ : elementI)
            {
                System.out.print(elementJ + " ");
            }
            System.out.println();
        }

        // одномірна колекція List
        // List - interface // ArrayList, LinkedList - class
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        System.out.println(strings.get(1));     // indexer [] - не перевантажується
        for(String str : strings)
        {
            System.out.print(str + " ");
        }
        System.out.println();


        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        if (str1 == str2)
        {
            System.out.println("str1 == str2");
        }
        else
        {
            System.out.println("str1 != str2");
        }
        if (str1.equals(str3))
        {
            System.out.println("str1 == str2");
        }
        else
        {
            System.out.println("str1 != str3");
        }
        str1 += "!";

        System.out.println( str1 + ", " + str2);

        if ( Objects.equals(str2, str1))
        {
            System.out.println("str1 == str2");
        }
        else
        {
            System.out.println("str1 != str2");
        }
        if (str2.equals(str3))
        {
            System.out.println("str1 == str2");
        }
        else
        {
            System.out.println("str1 != str3");
        }
        /*Порівняння завжди за посиланням
        // тобто рівні між собою тільки
        // два посилання на один об'єкт.
        // Оскільки оператори не перевантажуються
        // це ж правило діє і для String.
        // Однак, перші два об'єкти показують
        // рівність, а з третім ні.
        // String Pool / Immutable
        */



        // Асоціативні колекції (~Dictionary)
        Map<String, String> map = new HashMap<>();
        map.put("cat", "кіт");
        map.put("map", "мапа");
        map.put("equal", "рівний");
        map.put("print", "друк");

        for(Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("------------------------------------------------------");
        for(String key : map.keySet())
        {
            System.out.println(key + " " + map.get(key));
        }
    }
}
