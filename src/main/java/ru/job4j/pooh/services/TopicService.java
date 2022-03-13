package ru.job4j.pooh.services;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Alex Gutorov
 * @version 1.6
 * @created 13/03/2022 - 13:01
 */
public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics
            = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        Resp rsl = new Resp("", "204");
        if ("POST".equals(req.httpRequestType())) {
            topics.get(req.getSourceName()).values().forEach(v -> v.add(req.getParam()));
        }
        if ("GET".equals(req.httpRequestType())) {
            topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> map =
                    topics.get(req.getSourceName());
            map.putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            String s = map.get(req.getParam()).poll();
            if (s != null) {
                rsl = new Resp(s, "200");
            }
        }
        return rsl;
    }
}
