package ru.job4j.concurrent;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/02/2022 - 23:13
 */
public class Wget {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        System.out.print("\rLoading : " + i + "%");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        thread.start();
    }
}
