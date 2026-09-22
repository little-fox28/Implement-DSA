package DLinkedList;

public interface iDLinkedList<T> extends Iterable<T> {
    void clear();

    int size();

    boolean isEmpty();

    void add(T emlement);

    void addFirst(T element);

    void addLast(T element);

    T peekFirst();

    T peekLast();

    T removeFirst();

    T removeLast();

    T remove(Node<T> node);

    boolean remove(Object object);

    T removeAt(int index);

    int indexOf(Object object);

    boolean contains(Object object);

}
