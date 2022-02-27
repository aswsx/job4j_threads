package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 14/02/2022 - 22:40
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    int[] array = {1};

    public SingleLockList(List<T> list) {
        this.list = (List<T>) copy(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized Iterable<T> copy(List<T> list) {
        return new ArrayList<>(list);
    }
}
