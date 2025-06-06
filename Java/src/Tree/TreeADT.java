package Tree;

import java.util.Iterator;

public interface TreeADT<T> {
    boolean isEmpty();
    int size();
    int height();
    boolean contains(T elem);
    boolean add(T elem);
    boolean remove(T elem);
    Iterator<T> traverse(TreeTraverseType type);
}
