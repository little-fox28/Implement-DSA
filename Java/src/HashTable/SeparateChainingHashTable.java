package HashTable;

import DLinkedList.DLinkedList;

import java.util.Arrays;
import java.util.Iterator;

public class SeparateChainingHashTable<K, V> implements HashTableADT<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private final double loadFactor;
    private int size = 0, capacity, threshold;

    private DLinkedList<Node<K, V>>[] table;

    public SeparateChainingHashTable() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public SeparateChainingHashTable(int capacity) {
        this(DEFAULT_LOAD_FACTOR, capacity);
    }

    public SeparateChainingHashTable(double loadFactor, int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative: " + capacity);
        if (loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor)) {
            throw new IllegalArgumentException("loadFactor cannot be negative");
        }

        this.loadFactor = loadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.threshold = (int) (this.capacity * loadFactor);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int hashCodeToIndex(int hashedKey) {
//        return (int) ((hashedKey & 0xFFFFFFFFFL) % capacity);
        return Math.abs(hashedKey % capacity);
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public boolean has(K key) {
        int index = hashCodeToIndex(key.hashCode());
        DLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null || linkedList.isEmpty()) return false;
        Iterator<Node<K, V>> iterator = linkedList.iterator();

        while (iterator.hasNext()) {
            Node<K, V> node = iterator.next();

            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V insert(K key, V value) {
        int index = hashCodeToIndex(key.hashCode());
        DLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null) table[index] = linkedList = new DLinkedList<Node<K, V>>();

        Node<K, V> existedNode = null;
        Iterator<Node<K, V>> iterator = linkedList.iterator();

        while (iterator.hasNext()) {
            Node<K, V> node = iterator.next();
            if (node.getKey().equals(key)) existedNode = node;
            if (existedNode == null) {
                linkedList.add(new Node<>(key, value));
                if (++size > threshold) {
                    resizeTable();
                }
                return null;
            } else {
                V oldValue = existedNode.getValue();
                existedNode.setValue(value);
                return oldValue;
            }
        }
        return null;
    }

    private void resizeTable() {

    }

    @Override
    public V get(K key) {
        int index = hashCodeToIndex(key.hashCode());
        DLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null || linkedList.isEmpty()) return null;
        Iterator<Node<K, V>> iterator = linkedList.iterator();

        while (iterator.hasNext()) {
            Node<K, V> node = iterator.next();

            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
