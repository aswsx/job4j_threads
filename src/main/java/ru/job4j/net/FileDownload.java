package ru.job4j.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 12:09
 */
public class FileDownload {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownload.class);

    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException ioe) {
            LOG.error("IOExceptoion", ioe);
        }
    }
}
