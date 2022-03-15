package ru.job4j.pooh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/03/2022 - 14:14
 */
public class PoohServ {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     InputStream input = socket.getInputStream()) {
                    byte[] buff = new byte[1_000_000];
                    var total = input.read(buff);
                    var text = new String(Arrays
                            .copyOfRange(buff, 0, total), StandardCharsets.UTF_8);
                    System.out.println(text);
                    out.write("HTTP/1.1 200 OK".getBytes());
                    out.write(System.getProperty("line.separator").getBytes(StandardCharsets.UTF_8));
                    out.write(text.getBytes());
                }
            }
        }
    }
}
