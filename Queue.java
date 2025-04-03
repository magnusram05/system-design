package org.java.practice;

public interface Queue <E extends Object> {
    void enque(E e);
    E deque();
}
