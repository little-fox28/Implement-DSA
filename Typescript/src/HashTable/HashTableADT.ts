interface HashTableADT<K,V> {
    size(): number;
    isEmpty(): boolean;
    hashCodeToIndex(hashedKey: number): number;
    clear(): void;
    has(key: K): boolean;
    insert(key: K, value: V): V;
    get(key: K): V;
    remove(key: K): V;
}

export default HashTableADT;