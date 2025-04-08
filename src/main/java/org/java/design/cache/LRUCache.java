package org.java.design.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache = new HashMap<>();
    private Node<K, V> left;
    private Node<K, V> right;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (this.cache.size() == this.capacity) {
            this.cache.remove(this.right.key);
            setRight(this.right.prev);
        }
        Node<K, V> node;
        if (this.cache.containsKey(key)) {
            node = this.cache.get(key);
            node.value = value;
        } else {
            node = createNode(key, value);
        }
        this.cache.put(key, node);
        if (this.left == null) {
            setLeft(node);
            setRight(node);
        } else {
            link(node, this.left);
            setLeft(node);
        }
    }

    public V get(K key) {
        Node<K, V> node = this.cache.get(key);
        if (node != null) {
            if (isNotInTheMiddle(node)) {
                link(node.prev, node.next);
            } else if (node == this.right) {
                setRight(node.prev);
            }
            link(node, this.left);
            setLeft(node);
            return node.value;
        }
        return null;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        private Node<K, V> prev;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }

    private Node<K, V> createNode(K key, V val) {
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = val;
        return node;
    }

    private void setLeft(Node<K, V> node) {
        this.left = node;
        this.left.prev = null;
    }

    private void setRight(Node<K, V> node) {
        this.right = node;
    }

    private void link(Node<K, V> node1, Node<K, V> node2) {
        node1.next = node2;
        node2.prev = node1;
    }

    private boolean isNotInTheMiddle(Node<K, V> node) {
        return node.prev != null && node.next != null;
    }
}
