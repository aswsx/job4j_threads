package ru.job4j.cache;

import ru.job4j.cache.model.Base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 21:00
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (id, base) -> {
            if (model.getVersion() != base.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            base = new Base(id, base.getVersion() + 1);
            base.setName(model.getName());
            return base;
        }) != null;
    }

    public void delete(Base model) {
        memory.putIfAbsent(model.getId(), null);
    }

    public Base get(int i) {
        return memory.get(i);
    }
}
