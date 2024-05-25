package Stack;

import DLinkedList.DLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedListBaseStack<T> implements StackADT<T> {
    private final DLinkedList<T> list = new DLinkedList<T>();

    public LinkedListBaseStack() {
    }

    public LinkedListBaseStack(T element) {
        push(element);
    }

    @Override
    public void push(T element) {
        list.addLast(element);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    @Override
    public T top() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
