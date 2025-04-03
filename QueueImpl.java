package org.java.practice;

public class QueueImpl implements Queue<Object> {
    private final int capacity;
    private int size;
    private int insertIndex;
    private int deqeueIndex;
    private final Object [] q;

    public QueueImpl(int capacity) {
        this.capacity = capacity;
        q = new Object[this.capacity];
    }

    @Override
    public void enque(Object o) {
         if (size == capacity) throw new RuntimeException("Q is full");
         q[insertIndex++] = o;
         size++;
         if (insertIndex == capacity) insertIndex = 0;
    }

    @Override
    public Object deque() {
        if (size == 0) return new RuntimeException("Q is empty");
        Object itemToReturn = q[deqeueIndex++];
        q[deqeueIndex-1] = null;
        size--;
        if (deqeueIndex == capacity) deqeueIndex = 0;
        return itemToReturn;
    }

    public static void main(String[] args) {
        QueueImpl queueImpl = new QueueImpl(2);
        queueImpl.enque("a");
        queueImpl.enque("b");
        try {
            queueImpl.enque("c");
        } catch (Exception e){
            System.out.println("Unable to insert c");
        }
        System.out.println(queueImpl.deque());
        queueImpl.enque("c");
        System.out.println(queueImpl.deque());
        System.out.println(queueImpl.deque());
        queueImpl.enque("e");
        System.out.println(queueImpl.deque());
        try {
            System.out.println(queueImpl.deque());
        } catch (Exception exception){}
        queueImpl.enque("f");
        queueImpl.enque("g");
        System.out.println(queueImpl.deque());
        queueImpl.enque("h");
        System.out.println(queueImpl.deque());
        System.out.println(queueImpl.deque());

    }
}
