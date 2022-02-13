package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 17:24
 */
public class ContentSaver {
    private static final Logger LOG = LoggerFactory.getLogger(ContentSaver.class);
    private final File file;

    public ContentSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (var output = new BufferedOutputStream(new FileOutputStream(file))) {
            for (var i = 0; i < content.length(); i += 1) {
                output.write(content.charAt(i));
            }
        } catch (IOException ioe) {
            LOG.error("IOException", ioe);
        }
    }
}
