package HashTable;

public interface HashTableADT<K, V> extends Iterable<K> {
    int size();

    boolean isEmpty();

    int hashCodeToIndex(int hashedKey);

    void clear();

    boolean has(K key);

    V insert(K key);

    V get(K key);

    V remove(K key);
}