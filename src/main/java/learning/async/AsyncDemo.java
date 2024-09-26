package learning.async;

public class AsyncDemo {
    public void run() {
        System.out.println("Async demo");
        valueWithAllDigit();
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

    private double sum;
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
                Thread.sleep(500);
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
}}
