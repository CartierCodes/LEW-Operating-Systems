import java.util.Scanner;

public class ThreadedSumAndProduct implements Runnable {
    public static int sum = 0;
    public static int product = 1;
    public static boolean stop = false;
    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("This program is designed to keep track of the sum and product of numbers");
        System.out.println("inputted by the user through the command line...");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("           <<<<<Type \"clear\" or \"reset\" to reset>>>>>");
        System.out.println("             <<<<<Type \"stop\" or \"end\" to exit>>>>>\n");
        Thread t1 = new Thread(new ThreadedSumAndProduct());
        t1.start();
        Thread t2 = new Thread(new ThreadedSumAndProduct());
        t2.start();
        
        int number;
        Scanner input = new Scanner(System.in);
        while (stop == false) {
            String inputString = input.next();
            try {
                number = Integer.parseInt(inputString);
                sum += number;
                product *= number;
            }
            catch (Exception e) {
                if (inputString.equalsIgnoreCase("clear") || inputString.equalsIgnoreCase("reset")) {
                    sum = 0;
                    product = 1;
                    System.out.println("Sum and Product have been cleared...");
                }
                if (inputString.equalsIgnoreCase("stop") || inputString.equalsIgnoreCase("end")) {
                    stop = true;
                }
            }
        }
        input.close();
        System.out.println("Program has ended...");
    }

    public void run() {
        try {
            while (stop == false) {
                if (Thread.currentThread().getName().equals("Thread-0")) {
                    System.out.println("Sum = " + sum);
                    Thread.sleep(1000 * 3);
                }
                else if (Thread.currentThread().getName().equals("Thread-1")) {
                    System.out.println("Product = " + product);
                    Thread.sleep(1000 * 5);
                }
            }
        } catch (InterruptedException ie) {}
    }
}