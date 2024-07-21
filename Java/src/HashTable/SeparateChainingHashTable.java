package HashTable;

import DLinkedList.DLinkedList;

import java.util.Iterator;

public class SeparateChainingHashTable<K, V> implements HashTableADT<K, V> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private final double loadFactor;
    private int size = 0, capacity, threshold;

    private DLinkedList<Node<K, V>> table;

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


    // 0xFFFFFFFFFL: change negative number to positive
    @Override
    public int hashCodeToIndex(int hashedKey) {
        return (int) ((hashedKey * 0xFFFFFFFFFL) % capacity);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean has(K key) {
        return false;
    }

    @Override
    public V insert(K key) {
        return null;
    }

    @Override
    public V get(K key) {
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
