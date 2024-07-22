package HashTable;

import DLinkedList.DLinkedList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
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
        table = new DLinkedList[capacity];
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

        if (linkedList == null) {
            table[index] = linkedList = new DLinkedList<Node<K, V>>();
        }

        Iterator<Node<K, V>> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Node<K, V> node = iterator.next();
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
        }

        linkedList.add(new Node<>(key, value));
        if (++size > threshold) {
            resizeTable();
        }

        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (this.capacity * loadFactor);

        DLinkedList<Node<K, V>>[] newTable = new DLinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;

            Iterator<Node<K, V>> iterator = table[i].iterator();

            while (iterator.hasNext()) {
                Node<K, V> node = iterator.next();
                int index = hashCodeToIndex(node.getHash());

                DLinkedList<Node<K, V>> linkedList = newTable[index];

                if (linkedList == null) {
                    newTable[index] = linkedList = new DLinkedList<Node<K, V>>();
                }
                linkedList.add(node);
            }

            table[i].clear();
            table[i] = null;
        }
        table = newTable;
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
        int index = hashCodeToIndex(key.hashCode());
        DLinkedList<Node<K, V>> linkedList = table[index];

        if (linkedList == null) {
            return null;
        }

        Iterator<Node<K, V>> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Node<K, V> node = iterator.next();

            if (node.getKey().equals(key)) {
                linkedList.remove(node);
                --size;
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        int elementCount = size();

        return new Iterator<K>() {
            int index = 0;
            Iterator<Node<K, V>> bucketIterator = table[0] == null ? null : table[0].iterator();

            @Override
            public boolean hasNext() {
                if (elementCount != size())
                    throw new ConcurrentModificationException("Collection modified during iteration");

                if (bucketIterator == null || !bucketIterator.hasNext()) {
                    while (++index < capacity) {
                        if (table[index] != null || !table[index].isEmpty()) {
                            bucketIterator = table[index].iterator();
                            break;
                        }
                    }
                }
                return index < capacity;
            }

            @Override
            public K next() {
                return bucketIterator.next().getKey();
            }
        };
    }
}
