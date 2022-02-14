package ru.job4j.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

    private final String targetFile;

    public Wget(String url, int speed, String targetFile) {
        this.url = url;
        this.speed = speed;
        this.targetFile = targetFile;
    }

    @Override
    public void run() {
        try (var in = new BufferedInputStream(new URL(url).openStream());
             var fileOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile))) {
            var dataBuffer = new byte[speed];
            int bytesRead;
            int bytesWrite = 0;
            var start = Instant.now();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWrite += bytesRead;
                var diff = Duration.between(start, Instant.now()).toMillis();
                if (bytesWrite >= speed && diff < 1000) {
                    Thread.sleep(1000 - diff);
                }
                bytesWrite = 0;
                start = Instant.now();
            }
        } catch (InterruptedException ie) {
            LOG.error("Interrupted exception", ie);
            Thread.currentThread().interrupt();
        } catch (IOException ioe) {
            LOG.error("IO exception", ioe);
        }
    }

    private static class ValidateArgs {
        private static void validate(String[] args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Аргументы введены некорректно"
                +System.lineSeparator()
                + "Введите ссылку на файл и необходимую скорость в Мб/с через пробел");
            }
            if (Integer.parseInt(args[1]) < 0) {
                throw new IllegalArgumentException("Скорость, Мб/с, Введите положительное число");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ValidateArgs.validate(args);
        var url = args[0];
        var speed = Integer.parseInt(args[1]) * 1048576;
        var targetFile = url.substring(url.lastIndexOf("/") + 1);
        LOG.info("Ссылка на файл {}", url);
        LOG.info("Скорость, б/с {}", speed);
        LOG.info("Выходной файл {}", targetFile);
        var wget = new Thread(new Wget(url, speed, targetFile));
        wget.start();
        wget.join();
    }
}
