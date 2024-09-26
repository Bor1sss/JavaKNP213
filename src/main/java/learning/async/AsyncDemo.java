package learning.async;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class AsyncDemo {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private long startTime;

    public void run() {
        System.out.println("Async demo: make choise");
        System.out.println("1 - Thread demo");
        System.out.println("2 - Percent (thread) demo");
        System.out.println("3 - valueWithAllDigit");
        System.out.println("4 - Task demo");
        System.out.println("5 - taskPercentDemo");
        System.out.println("6 - taskOrderDemo");
        System.out.println("7 - valueWithAllDigitTask");

        System.out.println("0 - Quit");

        Scanner kbScanner = new Scanner(System.in);
        int choice = kbScanner.nextInt();
        switch (choice) {
            case 1: ThreadDemo(); break;
            case 2: percentDemo(); break;
            case 3: valueWithAllDigit(); break;
            case 4: taskDemo(); break;
            case 5: taskPercentDemo(); break;
            case 6: taskOrderDemo(); break;
            case 7: valueWithAllDigitTask(); break;
        }
    }

    private void valueWithAllDigitTask() {
        startTime = System.currentTimeMillis();

        digitStr = new StringBuilder();
        Future<String>[] tasks = new Future[10];
        for( int i = 0; i <= 9; i++ ) {
            tasks[i] = threadPool.submit( new DigitTask(Integer.toString(i)) );
        }


        try {
            for (int i = 9; i >= 0; i--) {
                digitStr.append(tasks[i].get());
                System.out.println(  "added : " + i + " : " + digitStr);
            }
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() );
        }
        stopExecutor();
    }

    private class DigitTask implements Callable<String> {
        private final String str;

        public DigitTask(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            System.out.println(
                    System.currentTimeMillis() - startTime +
                            " DigitTask " + str + " started" );
            return str;
        }
    }


    private void taskOrderDemo()
    {
        // щодо порядку запуск задач
        // Нехай, маємо чотири завдання:
        // 1. Підключення до БД
        Callable<String> dbConnection = () ->
        {
            System.out.println(System.currentTimeMillis() - startTime + " DB start");

            TimeUnit.MILLISECONDS.sleep(500);
            return "Database connection established";
        };

        // 2. Підключення (запит) до API
        Callable<String> apiRequest = () ->
        {
            System.out.println(System.currentTimeMillis() - startTime + " API start");
            TimeUnit.MILLISECONDS.sleep(800);
            return "API data received";
        };

        // 3. Зчитування файлового кешу
        Callable<String> cashLoad = () ->
        {
            System.out.println(System.currentTimeMillis() - startTime + " Cash start");
            TimeUnit.MILLISECONDS.sleep(300);
            return "Сash load completed";
        };

        // 4. Зчитування конфігурації (з файлу)
        Callable<String> configLoad = () ->
        {
            System.out.println(System.currentTimeMillis() - startTime + " Config start");
            TimeUnit.MILLISECONDS.sleep(300);
            return "Config load completed";
        };



        startTime = System.currentTimeMillis();

        Future<String> apiTask2 = threadPool.submit(apiRequest);
        Future<String> configTask4 = threadPool.submit(configLoad);
        Future<String> cashTask3 = threadPool.submit(cashLoad);
        await(configTask4);

        Future<String> dbTask1 = threadPool.submit(dbConnection);

        await(cashTask3);
        await(dbTask1);
        await(apiTask2);

        try
        {
            CompletableFuture.supplyAsync(() -> {
                        System.out.println(System.currentTimeMillis() - startTime + " Config start");

                        try {
                            TimeUnit.MILLISECONDS.sleep(300);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        return "Config load completed";
                    }, threadPool)
            .thenAccept((str) -> {
                            System.out.println(System.currentTimeMillis() - startTime + " Config completed");
                        }).get();
        }
        catch (Exception e)
            { throw new RuntimeException(e);}



        stopExecutor();

    }

    private void await(Future<String> task)
    {
        try {
            String result = task.get();
            System.out.println(System.currentTimeMillis() - startTime + " "  + result);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    private double sum;

    private void taskPercentDemo() {
        startTime = System.currentTimeMillis();

        sum = 100;
        Future<Double>[] tasks = new Future[12];
        for( int i = 1; i <= 12; i++ ) {
            tasks[i - 1] = threadPool.submit( new RateTask(i) );
        }


        try {
            for (int i = 12; i >= 1; i--) {
                sum *= tasks[i - 1].get();
                System.out.println(System.currentTimeMillis() - startTime + " Rate " + i + " sum = " + sum);
            }
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() );
        }
        stopExecutor();
    }


    private void taskPercentDemoWrong() {
        startTime = System.currentTimeMillis();
        try {
            sum = 100;
            for( int i = 1; i <= 12; i++ ) {
                sum *= threadPool.submit( new RateTask(i) ).get();
                System.out.println(
                        System.currentTimeMillis() - startTime +
                                " Rate " + i + " sum = " + sum);
            }
        }
        catch( Exception ex ) {
            System.err.println( ex.getMessage() );
        }
        stopExecutor();
    }

    private class RateTask implements Callable<Double> {
        private final int month;

        public RateTask(int month) {
            this.month = month;
        }

        @Override
        public Double call() throws Exception {
            System.out.println(
                    System.currentTimeMillis() - startTime +
                            " RateTask " + month + " started" );
            double percent;
            Thread.sleep( 500 );  // імітація запиту
            percent = 10.0;
            return  (1 + percent / 100.0);
        }
    }

    private void taskDemo() {
        startTime = System.currentTimeMillis();

        Future<?> task1 = threadPool.submit( new Rate(2) );

        try {
            task1.get();
            System.out.println(
                    System.currentTimeMillis() - startTime +       //
                            " Task 1 got");                        //
        }                                                          //
        catch( InterruptedException | ExecutionException ex ) {    //
            System.err.println( ex.getMessage() );                 //
        }                                                          //

        Future<String> task2 = threadPool.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        TimeUnit.MILLISECONDS.sleep( 500 );   // а також містить Exception
                        return "Hello Callable";              // у сигнатурі (у тілі немає потреби
                    }                                         // try-catch)
                });
        try {
            String res = task2.get();   // res = await task2
            System.out.println( System.currentTimeMillis() - startTime + " Task 2 finished with result: " + res );
        }
        catch( InterruptedException | ExecutionException ex ) {
            System.err.println( System.currentTimeMillis() - startTime + " Task 2 finished with exception: " + ex.getMessage() );
        }
        stopExecutor();
    }

    private void stopExecutor() {
        threadPool.shutdown();  // припиняємо прийом нових задач
        try {
            boolean isDone = threadPool.awaitTermination( 300, TimeUnit.MILLISECONDS );
            if ( !isDone ) {
                List<Runnable> cancelledTasks = threadPool.shutdownNow();   // "жорстка" зупинка
                if ( !cancelledTasks.isEmpty() ) {
                    System.err.println( System.currentTimeMillis() - startTime + " Tasks cancelled:" );
                    for ( Runnable task : cancelledTasks ) {
                        System.err.println( task.toString() );
                    }
                }
            }
        }
        catch( InterruptedException ignored ) { }
    }



    private void ThreadDemo()
    {

        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Hello Thread");
                    }
                }
        );
        thread.start();

        System.out.println("1 Hello Main");
        System.out.println("2 Hello Main");
        System.out.println("3 Hello Main");
        System.out.println("4 Hello Main");
        System.out.println("5 Hello Main");
        System.out.println("6 Hello Main");
        System.out.println("7 Hello Main");

    }

    private final Object sumLock = new Object();

    private StringBuilder digitStr;

    private void valueWithAllDigit()
    {
        digitStr = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            new Thread(new Digit(Integer.toString(i))).start();
        }
    }

    private  class Digit implements Runnable {
        private final String digit;

        public Digit(String digit) {
            this.digit = digit;
        }

        @Override
        public void run() {
            System.out.println("added " + digit + " started");
            try{
                Thread.sleep(500); // імітація запиту
            }
            catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                return;
            }
            synchronized (sumLock) {
                digitStr.append(digit);
                System.out.println("added " + digit + ": " + digitStr.toString() + " finished");
            }
        }
}
    private void percentDemo()
    {
        sum = 100.0;
        for (int i = 1; i <= 12; i++) {
            new Thread(new Rate(i)).start();
        }
    }

    private  class Rate implements Runnable {
        private final int month;

        public Rate(int month) {
            this.month = month;
        }

        @Override
        public void run() {
            System.out.println("Rate " + month + " started");
            double percent;
            try{
                Thread.sleep(500);
                percent = 10.0;
            }
            catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
                return;
            }
            synchronized (sumLock) {
                sum = sum * (1 + percent / 100.0);
                System.out.println("Rate " + month + " finished with sum " + sum);
            }
        }
    }
}
