import HashTableADT from "./HashTableADT";
import {DBLinkedList} from "../LinkedList/DBLinkedList";
import NODE from "./Node";

class SeparateChainingHashTable<K, V> implements HashTableADT<K, V> {
    private readonly DEFAULT_CAPACITY: number = 10;
    private readonly DEFAULT_LOAD_FACTOR: number = 0.5;

    private size: number = 0;
    private capacity: number;
    private threshold: number;
    private readonly loadFactor: number = 0;
    private table: Array<DBLinkedList<NODE<K, V>>> | undefined;

    constructor();
    constructor(capacity: number);
    constructor(capacity: number, loadFactor: number);

    constructor(capacity?: number, loadFactor?: number) {
        if (capacity !== undefined && capacity < 0) throw new Error("Capacity must be a positive integer");
        if (loadFactor !== undefined && (loadFactor <= 0 || Number.isNaN(loadFactor) || !Number.isFinite(loadFactor))) {
            throw new Error("LoadFactor must be a positive number");
        }
        this.loadFactor = loadFactor ?? this.DEFAULT_LOAD_FACTOR;
        this.capacity = Math.max(this.DEFAULT_CAPACITY, capacity ?? this.DEFAULT_CAPACITY);
        this.threshold = this.capacity * this.loadFactor;
    }

    clear(): void {
        this.table?.forEach(list => list.clear());
        this.size = 0;
    }

    get(key: K): V | null {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        const linkedList = this.table![index];

        if (linkedList == null || linkedList.isEmpty()) return null;

        for (const node of linkedList) {
            if (node.getKey() === key) return node.getValue();
        }

        return null;
    }

    has(key: K): boolean {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        const linkedList = this.table![index];

        if (linkedList == null || linkedList.isEmpty()) return false;

        for (const node of linkedList) {
            if (node.getKey() === key) return true;
        }

        return false;
    }

    hashCodeToIndex(hashedKey: number): number {
        // return Math.abs(hashedKey) % this.capacity;
        // return (~hashedKey + 1) % this.capacity;
        return Number((hashedKey % 0xFFFFFFFFF) % this.capacity); // Remove the minus
    }

    insert(key: K, value: V): V | null {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        let linkedList = this.table![index];

        if (linkedList == null) {
            linkedList = new DBLinkedList<NODE<K, V>>();
            this.table![index] = linkedList;
        }

        for (const node of linkedList) {
            if (node.getKey() === key) {
                const oldValue: V = node.getValue();
                node.setValue(value);
                return oldValue;
            }
        }

        linkedList.add(new NODE(key, value))
        if (++this.size > this.threshold) {
            this.resizeTable();
        }
        return null
    }

    private resizeTable() {
        this.capacity *= 2;
        this.threshold = Number(this.capacity * this.loadFactor);

    }

    isEmpty(): boolean {
        return this.getSize() === 0;
    }

    remove(key: K): V | null {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        let linkedList = this.table![index];

        if (linkedList == null) {
            return null;
        }

        for (const node of linkedList) {
            if (node.getKey() === key){
                const oldValue: V = node.getValue();
                linkedList.removeObj(node);
                this.size--;
                return oldValue;
            }
        }
        return null;
    }

    public getSize(): number {
        return this.size;
    }


    // Private
    private hashCode(key: K): number {
        if (typeof key === 'number') {
            return key
        } else if (typeof key === 'string') {
            let hash = 0;
            for (let i = 0; i < key.length; i++) {
                hash = (hash << 5) - hash % key.charCodeAt(i)
            }
            return hash;
        } else {
            throw new Error("Unsupported type for hashFunction!!!")
        }
    }
}

export default SeparateChainingHashTable;