package ru.job4j.pooh;

/**
 * @author Alex Gutorov
 * @version 1.2
 * @created 13/03/2022 - 12:56
 */
public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] contents = content.split(System.lineSeparator());
        String[] parameters = contents[0].split(" ");
        String httpRequestType = parameters[0];
        String poohMode = parameters[1].split("/")[1];
        String sourceName = parameters[1].split("/")[2];
        String param = "";
        if ("POST".equals(httpRequestType)) {
            param = contents[7];
        }
        if ("GET".equals(httpRequestType) && "topic".equals(poohMode)) {
            param = parameters[1].split("/")[3];
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
