package Queue;

import java.util.Iterator;

public class CircularArrayBaseQueue<T> implements QueueADT<T> {
    private final T[] array;
    private int front;
    private int end;
    private final int size;

    public CircularArrayBaseQueue(int size) {
        this.size = size + 1;
        this.array = (T[]) new Object[this.size];
        this.front = 0;
        this.end = 0;
    }

    @Override
    public void enQueue(T element) {
        array[end] = element;
        if (++end == size) end = 0;
        if (end == front) throw new RuntimeException("Queue is full!");
    }

    @Override
    public T deQueue() {
        if (isEmpty()) throw new RuntimeException("Queue empty!");
        T enQueueElem = array[front];
        array[front] = null;
        if (++front == size) front = 0;
        return enQueueElem;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue empty!");
        return array[front];
    }

    @Override
    public int size() {
        if (front > end) return end + size - front;
        return end - front;
    }

    @Override
    public boolean isEmpty() {
        return front == end;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
