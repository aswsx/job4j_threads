package ru.job4j.pooh.services;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Alex Gutorov
 * @version 1.3
 * @created 13/03/2022 - 13:00
 */
public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp rsl = new Resp("", "204");
        String reqType = req.httpRequestType();
        switch (reqType) {
            case "POST" -> {
                queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
                queue.get(req.getSourceName()).add(req.getParam());
                rsl = new Resp("", "200");
            }
            case "GET" -> {
                ConcurrentLinkedQueue<String> linkedQueue = queue.get(req.getSourceName());
                String text;
                if (linkedQueue != null) {
                    text = linkedQueue.poll();
                    if (text != null) {
                        rsl = new Resp(text, "200");
                    }
                }
            }
            default -> new Resp("", "501");
        }
        return rsl;
    }
}
