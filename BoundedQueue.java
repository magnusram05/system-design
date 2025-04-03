package org.java.practice;

public class BoundedQueue implements Queue<Object> {
    private final int capacity;
    private int size;
    private int insertIndex;
    private int deqeueIndex;
    private final Object [] q;

    public BoundedQueue(int capacity) {
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
        BoundedQueue boundedQueue = new BoundedQueue(2);
        boundedQueue.enque("a");
        boundedQueue.enque("b");
        try {
            boundedQueue.enque("c");
        } catch (Exception e){
            System.out.println("Unable to insert c");
        }
        System.out.println(boundedQueue.deque());
        boundedQueue.enque("c");
        System.out.println(boundedQueue.deque());
        System.out.println(boundedQueue.deque());
        boundedQueue.enque("e");
        System.out.println(boundedQueue.deque());
        try {
            System.out.println(boundedQueue.deque());
        } catch (Exception exception){}
        boundedQueue.enque("f");
        boundedQueue.enque("g");
        System.out.println(boundedQueue.deque());
        boundedQueue.enque("h");
        System.out.println(boundedQueue.deque());
        System.out.println(boundedQueue.deque());

    }
}
