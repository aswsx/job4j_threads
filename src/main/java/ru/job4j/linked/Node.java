package ru.job4j.linked;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 15:10
 */

/**
 * Для создания immutable объекта поля класса обозначены как final. Для инициализации этих полей создан конструктор
 * Так как поля final, изменить их нельзя, соответственно убраны сеттеры для этих полей
 * Ключевым словом final также отмечен сам класс, что не позволяет изменить ссылку на объект после его создания
 * @param <T> тип
 */
public final class Node<T> {
    private static final Logger LOG = LoggerFactory.getLogger(Node.class);
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
