package ru.job4j.pooh;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/03/2022 - 12:58
 */
public class Resp {
    private final String text;
    private final String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }
}
