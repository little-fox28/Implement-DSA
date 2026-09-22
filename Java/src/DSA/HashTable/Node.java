package HashTable;

public class Node<K, V> {
    private int hash;
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public Node(K key, V value) {
        this.hash = key.hashCode();
        this.key = key;
        this.value = value;
    }

    public boolean equals(Node<K, V> other) {
        if (other.hash != hash) {
            return false;
        }
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return "key=" + key + ",value=" + value;
    }
}