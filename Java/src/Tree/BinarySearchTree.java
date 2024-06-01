package Tree;

import Stack.LinkedListBaseStack;
import Stack.StackADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> implements TreeADT<T> {
    private int nodeCount = 0;
    private Node<T> root = null;

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
        return Height(root);
    }

    @Override
    public boolean contains(T elem) {
        return Contains(root, elem);
    }

    @Override
    public boolean add(T elem) {
        if (contains(elem)) return false;
        root = Add(root, elem);
        nodeCount++;
        return true;
    }

    @Override
    public boolean remove(T elem) {
        if (!contains(elem)) return false;
        root = Remove(root, elem);
        nodeCount--;
        return true;
    }

    @Override
    public Iterator<T> traverse(TreeTraverseType type) {
        return switch (type) {
            case PRE_ORDER -> preOrderTraverse();
            case IN_ORDER -> inOrderTraverse();
            case POST_ORDER -> postOrderTraverse();
            case LEVEL_ORDER -> levelOrderTraverse();
            default -> null;
        };
    }

    private Iterator<T> levelOrderTraverse() {
    }

    private Iterator<T> postOrderTraverse() {
    }

    private Iterator<T> inOrderTraverse() {
    }

    private Iterator<T> preOrderTraverse() {
        final int expectedCount = nodeCount;
        StackADT<Node<T>> stack = new LinkedListBaseStack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();

                Node<T> node = stack.pop();

                if (node.getRight() != null) stack.push(node.getRight());
                if (node.getLeft() != null) stack.push(node.getLeft());

                return node.getData();
            }
        };
    }

    // Private
    private int Height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(Height(node.getLeft()), Height(node.getRight()));
    }

    private boolean Contains(Node<T> node, T elem) {
        if (node == null) return false;
        int result = elem.compareTo(node.getData());
        if (result < 0) {
            return Contains(node.getLeft(), elem);
        } else if (result > 0) {
            return Contains(node.getRight(), elem);
        } else {
            return true;
        }
    }

    private Node<T> Add(Node<T> node, T elem) {
        if (node == null) return node = new Node<>(null, elem, null);
        else {
            if (elem.compareTo(node.getData()) > 0) {
                node.setRight(Add(node.getRight(), elem));
            } else {
                node.setLeft(Add(node.getLeft(), elem));
            }
        }
        return node;
    }

    private Node<T> Remove(Node<T> node, T elem) {
        int result = elem.compareTo(node.getData());
        if (result > 0) {
            node.setRight(Remove(node.getRight(), elem));
        } else if (result < 0) {
            node.setLeft(Remove(node.getLeft(), elem));
        } else {
            if (node.getLeft() == null) {
                node.setData(node.getRight().getData());
            } else if (node.getRight() == null) {
                node.setData(node.getLeft().getData());
            } else {
                if (node.getLeft() == null) {
                    Node<T> nodeRight = node.getRight();

                    node.setData(null);
                    node = null;

                    return nodeRight;
                } else if (node.getRight() == null) {
                    Node<T> nodeLeft = node.getLeft();

                    node.setData(null);
                    node = null;

                    return nodeLeft;
                } else {
                    T tmp = minRight(node); // maxLeft(node)

                    node.setData(tmp);
                    node.setRight(Remove(node.getRight(), tmp));
                }
            }
        }
        return node;
    }

    private T minRight(Node<T> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node.getData();
    }

    private T maxLeft(Node<T> node) {
        while (node.getRight() != null) node = node.getRight();
        return node.getData();
    }
}
