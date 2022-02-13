package ru.job4j.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 12:34
 */
public class Wget implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(Wget.class);
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (var in = new BufferedInputStream(new URL(url).openStream());
             var fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            var start = Instant.now();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                var end = Instant.now();
                var diff = Duration.between(start, end).toMillis();
                if (diff < speed) {
                    Thread.sleep(speed - diff);
                }
            }
        } catch (IOException | InterruptedException e) {
            LOG.error("IO exception", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var url = args[0];
        var speed = Integer.parseInt(args[1]);
        var wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
