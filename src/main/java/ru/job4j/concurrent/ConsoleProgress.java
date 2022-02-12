package ru.job4j.concurrent;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/02/2022 - 10:43
 */
public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        var progress = new Thread(new ConsoleProgress());

        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }

    @Override
    public void run() {
        String[] process = {"|", "*", "/", "*", "~", "*", "\\", "*"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (var p : process) {
                    Thread.sleep(220);
                    System.out.print("\r load: " + p);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





