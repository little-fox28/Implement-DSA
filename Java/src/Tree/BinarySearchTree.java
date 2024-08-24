package Tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import DLinkedList.DLinkedList;
import Stack.LinkedListBaseStack;
import Stack.StackADT;
import Queue.LinkedListBaseQueue;
import Queue.QueueADT;

public class BinarySearchTree<T extends Comparable<T>> implements TreeADT<T> {
    private int nodeCount = 0;
    private Node<T> root;
    private Node<T> travel;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    @Override
    public boolean add(T elem) {
        if (contains(elem)) return false;
        root = add(root, elem);
        nodeCount++;
        return true;
    }

    @Override
    public boolean remove(T elem) {
        if (!contains(elem)) return false;
        root = remove(root, elem);
        nodeCount--;
        return true;
    }

    @Override
    public Iterator<T> traverse(TreeTraverseType type) {
        switch (type) {
            case PRE_ORDER:
                return preOrderTraverse();
            case IN_ORDER:
                return inOrderTraverse();
            case POST_ORDER:
                return postOrderTraverse();
            case LEVEL_ORDER:
                return levelOrderTraverse();
            default:
                throw new IllegalArgumentException("Unknown traversal type: " + type);
        }
    }

    // Level-order traversal
    private Iterator<T> levelOrderTraverse() {
        final int expectedCount = nodeCount;
        QueueADT<Node<T>> queue = new LinkedListBaseQueue<>();
        queue.enQueue(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                if (!hasNext()) throw new NoSuchElementException();

                Node<T> currentNode = queue.deQueue();
                if (currentNode.getLeft() != null) {
                    queue.enQueue(currentNode.getLeft());
                }
                if (currentNode.getRight() != null) {
                    queue.enQueue(currentNode.getRight());
                }
                return currentNode.getData();
            }
        };
    }

    // Post-order traversal
    private Iterator<T> postOrderTraverse() {
        final int expectedCount = nodeCount;
        StackADT<Node<T>> stack = new LinkedListBaseStack<>();
        StackADT<Node<T>> postOrderStack = new LinkedListBaseStack<>();
        if (root != null) stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                return !postOrderStack.isEmpty() || !stack.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();

                while (!stack.isEmpty()) {
                    Node<T> currentNode = stack.pop();
                    postOrderStack.push(currentNode);

                    if (currentNode.getLeft() != null) stack.push(currentNode.getLeft());
                    if (currentNode.getRight() != null) stack.push(currentNode.getRight());
                }

                Node<T> nextNode = postOrderStack.pop();
                return nextNode.getData();
            }
        };
    }

    // In-order traversal
    private Iterator<T> inOrderTraverse() {
        final int expectedCount = nodeCount;
        StackADT<Node<T>> stack = new LinkedListBaseStack<>();
        travel = root;

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                return travel != null || !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                if (!hasNext()) throw new NoSuchElementException();

                while (travel != null) {
                    stack.push(travel);
                    travel = travel.getLeft();
                }

                Node<T> node = stack.pop();
                travel = node.getRight();
                return node.getData();
            }
        };
    }

    // Pre-order traversal
    private Iterator<T> preOrderTraverse() {
        final int expectedCount = nodeCount;
        StackADT<Node<T>> stack = new LinkedListBaseStack<>();
        if (root != null) stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();

                Node<T> node = stack.pop();
                if (node.getRight() != null) stack.push(node.getRight());
                if (node.getLeft() != null) stack.push(node.getLeft());
                return node.getData();
            }
        };
    }

    // Private helper methods
    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    private boolean contains(Node<T> node, T elem) {
        if (node == null) return false;
        int result = elem.compareTo(node.getData());
        if (result < 0) {
            return contains(node.getLeft(), elem);
        } else if (result > 0) {
            return contains(node.getRight(), elem);
        } else {
            return true;
        }
    }

    private Node<T> add(Node<T> node, T elem) {
        if (node == null) return new Node<>(elem);
        int result = elem.compareTo(node.getData());
        if (result < 0) {
            node.setLeft(add(node.getLeft(), elem));
        } else if (result > 0) {
            node.setRight(add(node.getRight(), elem));
        }
        return node;
    }

    private Node<T> remove(Node<T> node, T elem) {
        if (node == null) return null;

        int result = elem.compareTo(node.getData());
        if (result < 0) {
            node.setLeft(remove(node.getLeft(), elem));
        } else if (result > 0) {
            node.setRight(remove(node.getRight(), elem));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            T minValue = minValue(node.getRight());
            node.setData(minValue);
            node.setRight(remove(node.getRight(), minValue));
        }
        return node;
    }

    private T minValue(Node<T> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node.getData();
    }
}
