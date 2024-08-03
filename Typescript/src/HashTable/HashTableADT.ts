interface HashTableADT<K,V> {
    getSize(): number;
    isEmpty(): boolean;
    hashCodeToIndex(hashedKey: number): number;
    clear(): void;
    has(key: K): boolean;
    insert(key: K, value: V): V | null;
    get(key: K): V | null;
    remove(key: K): V | null;
};

export default HashTableADT;