package ru.job4j.pooh.services;

import ru.job4j.pooh.Req;
import ru.job4j.pooh.Resp;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/03/2022 - 12:59
 */
public interface Service {
    Resp process(Req req);
}
