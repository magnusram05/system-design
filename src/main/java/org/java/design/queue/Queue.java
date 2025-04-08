package org.java.design.queue;

public interface Queue<E> {
    void enque(E o);

    E deque();
}
