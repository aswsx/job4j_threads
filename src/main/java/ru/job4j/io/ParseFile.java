package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.IntPredicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 16:08
 */
public final class ParseFile {
    private static final Logger LOG = LoggerFactory.getLogger(ParseFile.class);
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(IntPredicate predicate) {
        var output = "";
        var builder = new StringBuilder();
        try (var input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) > 0) {
                if (predicate.test(data)) {
                    builder.append((char)data);
                }
            }
        } catch (IOException ioe) {
            LOG.error("IOException", ioe);
        }
        output = builder.toString();
        return output;
    }

    public String getContentWithoutUnicode() {
        return getContent(data -> data < 0x80);
    }

    public String getContentWithUnicode() {
        return getContent(data -> true);
    }
}
