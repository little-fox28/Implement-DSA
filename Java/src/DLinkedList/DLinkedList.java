package DLinkedList;

import java.util.Iterator;

public class DLinkedList<T> implements iDLinkedList<T> {
    private int size;
    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public void clear() {
        Node<T> currentNode = head;
        while (currentNode != null) {
            Node<T> nextNode = currentNode.getNext();

            currentNode.setPrev(null);
            currentNode.setData(null);
            currentNode.setNext(null);
            currentNode = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(T emlement) {
        addLast(emlement);
    }

    @Override
    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(null, element, null);
            size++;
        } else {
            Node<T> newNode = new Node<T>(head, element, null);
            head.setPrev(newNode);
            head = head.getPrev();
            size++;
        }

    }

    @Override
    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(null, element, null);
            size++;
        } else {
            Node<T> newNode = new Node<T>(null, element, tail);
            tail.setNext(newNode);
            tail = tail.getNext();
            size++;
        }
    }

    @Override
    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List!");
        } else {
            return head.getData();
        }
    }

    @Override
    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List!");
        } else {
            return tail.getData();
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List!");
        }

        T data = head.getData();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        } else {
            head.setPrev(null);
        }

        return data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List!");
        T data = tail.getData();
        tail = tail.getPrev();
        size--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.setNext(null);
        }
        return data;
    }

    @Override
    public T remove(Node<T> node) {
        if (isEmpty()) throw new RuntimeException("Empty List!");
        if (node.getData() == null) throw new RuntimeException("Input Not Found!");

        if (node.getPrev() == null) removeFirst();
        if (node.getNext() == null) removeLast();

        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());

        T data = node.getData();

        node.setNext(null);
        node.setData(null);
        node.setPrev(null);
        node = null;

        size--;

        return data;
    }

    @Override
    public boolean remove(Object object) {
        if (isEmpty()) throw new RuntimeException("Empty List!");
        Node<T> currentNode = head;

        if (object == null) {
            while (currentNode != null) {
                if (currentNode.getData() == null) {
                    remove(currentNode);
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        } else {
            while (currentNode != null) {
                if (currentNode.getData() == object) {
                    remove(currentNode);
                    return true;
                }
                currentNode = currentNode.getNext();

            }
        }
        ;

        return false;

    }

    @Override
    public T removeAt(int index) {
        if (isEmpty()) throw new RuntimeException("Empty List!");
        if (index < 0 || index > size) throw new IllegalArgumentException();

        int i;
        Node<T> currentNode;

        if (index < size / 2) {
            i = 0;
            currentNode = head;
            while (i != index) {
                currentNode = currentNode.getNext();
                i++;
            }
        } else {
            i = size - 1;
            currentNode = tail;
            while (i != index) {
                currentNode = currentNode.getPrev();
                i--;
            }
        }
        return remove(currentNode);
    }

    @Override
    public int indexOf(Object object) {
        if (isEmpty()) throw new RuntimeException("Empty List!");

        int index = 0;
        Node<T> currentNode = head;
        if (object == null) {
            while (currentNode.getData() != null) {
                if (currentNode.getData() == null) {
                    return index;
                }
                index++;
                currentNode = currentNode.getNext();
            }
        } else {
            while (currentNode.getData() != null) {
                if (currentNode.getData() == object) {
                    return index;
                }
                index++;
                currentNode = currentNode.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }


    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(size);

            Node<T> currentNode = head;
            while (currentNode != null) {
                sb.append("[");
                sb.append(currentNode.getData());
                sb.append("]");
                sb.append("<=>");
                currentNode = currentNode.getNext();
            }
            sb.append("null");
            return sb.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private boolean isEntering = true;
            private Node<T> currentNode = null;

            @Override
            public boolean hasNext() {
                if (isEntering){
                    return head != null;
                }
                return currentNode.getNext() != null;
            }

            @Override
            public T next() {
                T data = null;
                if (isEntering){
                    data = head.getData();
                    currentNode = head;
                    isEntering = false;
                } else {
                    currentNode = currentNode.getNext();
                    data = currentNode.getData();
                }
                return data;
            }
        };
    }

}
