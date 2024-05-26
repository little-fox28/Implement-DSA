package Queue;

import DLinkedList.DLinkedList;

import java.util.Iterator;

public class LinkedListBaseQueue<T> implements QueueADT<T> {

    private final DLinkedList<T> linkedList = new DLinkedList<T>();

    public LinkedListBaseQueue() {
    }

    public LinkedListBaseQueue(T firstElement) {
        enQueue(firstElement);
    }


    @Override
    public void enQueue(T element) {
        linkedList.addLast(element);
    }

    @Override
    public T deQueue() {
        if (isEmpty()) throw new RuntimeException("Queue empty!");
        return linkedList.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue empty!");
        return linkedList.peekFirst();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
