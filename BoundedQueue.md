# Bounded queue

[BoundedQueue.java](https://github.com/magnusram05/system-design/edit/main/BoundedQueue.java)

```java

Queue q = new Queue(2)

int capacity = 2

int size = 0

int i = 0 // (insert index)

int d = 0 // (deqeue index)
```

## enqeue(a)

| 0 | 1 |
----|----
| a |   |


_increment i and size_

```java 
// i=1; d=0; size=1
```

## enqeue(b)

| 0 | 1 |
----|----
| a | b |


_increment i and size_

```java 
// i=2; d=0; size=2
```
_i == capacity, so reset it to 0_
```java 
// i=0; d=0; size=2
 ``` 

## enqeue(c)

_Throw RuntimeException as size == capacity_

| 0 | 1 |
----|----
| a | b |

```java 
// i=0; d=0; size=2 
```

## deqeue() ==> a
| 0 | 1 | 
----|----
|   | b |


_increment d and decrement size_


```java 
// i=0; d=1; size=1
```

## deqeue() ==> b
| 0 | 1 |
----|----
|   |   |

_increment d and decrement size_

```java 
// i=0; d=2; size=0
```

_d == capacity so reset it to 0_

```java 
// i=0; d=0; size=0
```

## enqeue(c)

| 0 | 1 |
----|----
| c |   |

_increment i and size_

```java 
// i=1; d=0; size=1
```


## enqeue(d)

| 0 | 1 |
----|----
| c | d |


_increment i and size_

```java
 // i=2; d=0; size=2
```
_i == capacity, so reset it to 0_
```java
// i=0; d=0; size=2
```

## deqeue() ==> c

| 0 | 1 |
----|----
|   | d |


_increment d and decrement size_

```java
// i=0; d=1; size=1
```




