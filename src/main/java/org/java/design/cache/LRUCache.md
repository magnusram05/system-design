# What is an LRU cache?

Least recently used item in the cache is evicted

# How does it work?

Use doubly LinkedList is used to keep track of the least recently used item

When an item is accessed (put or get), move it to the top of the linked list (left)

When cache is full, remove the right node

```java
private final int capacity;

private final Map<K, Node<K, V>> cache = new HashMap<>();

private Node<K, V> left;  // * most * recently accessed
private Node<K, V> right; // * least * recently accessed

public LRUCache(int capacity) {
    this.capacity = capacity;
}
```

* Start

- LinkedList will be empty
- Left and right nodes pointing to 'null'

| left | right |            
|------|-------|            
| null | null  |   

* Put("k1", "v1")

- First element in the cache so set left and right node to point to node1

```java
if(this.left ==null){

setLeft(node);

setRight(node);
}
```

| node1 (left) | node2 (right) |   
|--------------|---------------|   
| k1v1         | k1v1          |   

* Put("k2", "v2")

- Link this new node (node2) to existing left node (node1)
- Make this the recently accessed by setting this as the 'left' node

```java
link(node, this.left);

setLeft(node);
```

| node2  (left) | node1  (right) |                 
|---------------|----------------|                        
| k2v2          | k1v1           |                        

* Put("k3", "v3")

- Link this new node (node2) to existing left node (node2)
- Make this the recently accessed by setting this as the 'left' node

```java
link(node, this.left);

setLeft(node);
```

| node3 (left) | node2 | (node1)right |                             
|--------------|-------|--------------|                             
| k3v3         | k2v2  | k1v1         | 

* Put("k4", "v4")

- _Cache is full, so remove the last element = node1_

```java
if(this.cache.size() ==this.capacity){
        this.cache.

remove(this.right.key);

setRight(this.right.prev);
}
```

- Link this new node (node4) to existing left node (node3)
- Make this the recently accessed by setting this as the 'left' node

```java
link(node, this.left);

setLeft(node);
```

| node4 (left) | node3 | node2 (right) |  
|--------------|-------|---------------|       
| k4v4         | k3v3  | k2v2          |    

* Get("k2")

- Need to move node2 to the top of the linked list
- node2 is the last (right) node, so make node2.previous (node3) the 'right' node

```java
else if(node ==this.right){

setRight(node.prev);
}
```

- link node2 and left node
- set node2 as the left node

| node2 (left) | node4 | node3 (right) |                
|--------------|-------|---------------|                  
| k2v2         | k4v4  | k3v3          |

* Get("k4")

- Need to move node4 to the top of the linked list
- node4 is in the middle of the linked list so need to link node4.prev (node2) and node4.next (node3)

```java
if(isNotInTheMiddle(node)){

link(node.prev, node.next);
} 
```

- link node4 and left node (node2)
- set node4 as the left node

| node4 (left) | node2 | node3 (right) | 
|--------------|-------|---------------| 
| k4v4         | k2v2  | k3v3          | 