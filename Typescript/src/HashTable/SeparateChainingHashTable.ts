import HashTableADT from "./HashTableADT";
import {DBLinkedList} from "../LinkedList/DBLinkedList";
import NODE from "./Node";

class SeparateChainingHashTable<K, V> implements HashTableADT<K, V> {
    private readonly DEFAULT_CAPACITY: number = 10;
    private readonly DEFAULT_LOAD_FACTOR: number = 0.5;

    private size: number = 0;
    private readonly capacity: number;
    private threshold: number;
    private readonly loadFactor: number = 0;
    private table: DBLinkedList<NODE<K, V>>[] | undefined;

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
        const iterator = linkedList[Symbol.iterator]();

        let result = iterator.next();

        while (!result.done) {
            const node = result.value;
            if (node.getKey() === key) return node.getValue();
        }

        return null;
    }

    has(key: K): boolean {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        const linkedList = this.table![index];

        if (linkedList == null || linkedList.isEmpty()) return false;

        const iterator = linkedList[Symbol.iterator]();
        let result = iterator.next();

        while (!result.done) {
            const node = result.value;
            if (node.getKey() === key) return true;
        }

        return false;
    }

    hashCodeToIndex(hashedKey: number): number {
        // return Math.abs(hashedKey) % this.capacity;
        // return (~hashedKey + 1) % this.capacity;
        return Number((hashedKey % 0xFFFFFFFFF) % this.capacity); // Remove minus
    }

    insert(key: K, value: V): V | null {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        let linkedList = this.table![index];

        if (linkedList == null) {
            this.table![index] = new DBLinkedList<NODE<K, V>>();
        }

        let existedNode: NODE<K, V> | null = null;
        const iterator = linkedList[Symbol.iterator]();
        const result = iterator.next();

        while (!result.done) {
            const node = result.value;
            if (node.getKey() === key) {
                existedNode = node;
            }
        }

        if (existedNode === null) {
            linkedList.add(new NODE(key, value));
            if (++this.size > this.threshold) {
                this.resizeTable();
            }
            return null
        } else {
            const oldValue: V = existedNode.getValue();
            existedNode.setValue(value);
            return oldValue
        }
    }

    private resizeTable() {

    }

    isEmpty(): boolean {
        return this.getSize() === 0;
    }

    remove(key: K): V | null {
        const index: number = this.hashCodeToIndex(this.hashCode(key));
        let linkedList = this.table![index];

        if (linkedList == null) {
            this.table![index] = new DBLinkedList<NODE<K, V>>();
        }

        const iterator = linkedList[Symbol.iterator]();
        const result = iterator.next();
        while (!result.done) {
            const node: NODE<K, V> = result.value;
            linkedList.removeObj(node)
            return node.getValue();
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