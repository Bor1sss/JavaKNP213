package learning;

import java.util.*;

/**
 * Основи мови програмування
 */
public class Basics {

    public void run() {
        System.out.println( "Basics" );
        // типи даних та змінні
        // primitives - Value types
        byte b = -1;   // 8 bit   !! беззнакових (unsigned) варіацій не існує
        short s = 1;   // 16      byte b = -1:    1111 1111                        (byte) s
        int i = 1;     // 32      [short] b       0000 0000 1111 1111 == 255   ||  1111 1111 0000 0000
        long l = 1L;   // 64      (short) b       1111 1111 1111 1111 == -1
        float f = 1.0f;     // 32
        double d = 1.0E-2;  // 64
        char c = 'Ї';
        boolean bool = true;
        System.out.println( c );

        // reference types
        // boxing
        Byte bb = b;
        Short ss = new Short( s );

        // Arrays, Collections, Loops
        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = new int[] { 3, 4, 5 };
        int[] arr3 = new int[4];   // default == 0
        for (int j = 0; j < arr1.length; j++) {
            System.out.print( arr1[j] + " " );
        }
        System.out.println();

        for( int elem : arr2 ) {   // foreach
            System.out.print( elem + " " );
        }
        System.out.println();

        int[][] arr2d = {    // Only jagged
                { 1, 2, 3 },
                { 4, 5, 6, 7 },
                { 8, 9 }
        };

        // Одномірна колекція List
        // List - interface / ArrayList, LinkedList - class
        List<String> strings = new ArrayList<>();
        strings.add( "Hello" );
        strings.add( "World" );
        strings.add( "Java" );
        System.out.println( strings.get(1) );   // indexer [] - не перевантажується
        for( String str : strings ) {
            System.out.print( str + " " );
        }
        System.out.println();

        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        if( str1 == str2 ) {                         // Порівняння завжди за посиланням
            System.out.println("str1 == str2");      // тобто рівні між собою тільки
        }                                            // два посилання на один об'єкт.
        else {                                       // Оскільки оператори не перевантажуються,
            System.out.println("str1 != str2");      // це ж правило діє і для String.
        }                                            // Однак, перші два об'єкти показують
        if( str1 == str3 ) {                         // рівність, а з третім - ні.
            System.out.println("str1 == str3");      // String Pool / Immutable
        }                                            //
        else {                                       //
            System.out.println("str1 != str3");      //
        }
        str1 += "!";   // Immutable: ... = new String(str1 + "!")
        System.out.println( str1 + ", " + str2 );
        if( str1 == str2 ) {
            System.out.println("str1 == str2");
        }
        else {
            System.out.println("str1 != str2");
        }
        // правильне порівняння контенту - .equals
        if( str2.equals( str3 ) ) {
            System.out.println("str2 == str3");
        }
        else {
            System.out.println("str2 != str3");
        }
        // ... або Objects.equals, якщо немає гарантії, що об'єкт не null
        if( Objects.equals(str2, str3) ) {
            System.out.println("str2 == str3");
        }
        else {
            System.out.println("str2 != str3");
        }

        // Асоціативні колекції (~Dictionary)
        Map<String, String> map = new LinkedHashMap<>();
        map.put( "cat", "кіт" );
        map.put( "map", "мапа" );
        map.put( "equal", "рівний" );
        map.put( "print", "друк" );
        for( Map.Entry<String, String> entry : map.entrySet() ) {
            System.out.println( entry.getKey() + " " + entry.getValue() );
        }
        System.out.println( "-------------------------------" );
        for( String key : map.keySet() ) {
            System.out.println( key + " " + map.get(key) );
        }
        int[][] matrixA = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        int[][] matrixB = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };
    }
}
/*
Скласти програму, яка визначатиме добуток двох матриць.
Для прикладу можна використати
int[][] matrixA = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 9 }
};
int[][] matrixB = {
        { 9, 8, 7 },
        { 6, 5, 4 },
        { 3, 2, 1 }
};
** Вивести результат у вигляді
1 2 3     9 8 7
4 5 6  Х  6 5 4  = ...
7 8 9     3 2 1

 */