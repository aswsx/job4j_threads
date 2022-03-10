package ru.job4j.thread.email;

import ru.job4j.thread.email.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 10/03/2022 - 14:45
 */
public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private void emailTo(User user) {
        var name = user.getName();
        var email = user.getEmail();
        var subject = String.format("Notification %s to email %s", name, email);
        var body = String.format("Add a new event to %s", name);
        pool.submit(() -> send(subject, body, email));
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }
}
