import { DBLinkedList } from "../LinkedList/DBLinkedList";
import { HashTableADT } from "./HashTableADT";
import NODE from "./Node";

export class SeparateChainingHashTable<
  K extends number | string,
  V extends number | string
> implements HashTableADT<K, V>
{
  private static readonly DEFAULT_CAPACITY = 10;
  private static readonly DEFAULT_LOAD_FACTOR = 0.5;

  private loadFactor: number;
  private size: number;
  private capacity: number;
  private threshold: number;
  private table: DBLinkedList<NODE<K, V>>[];

  constructor(
    loadFactor: number = SeparateChainingHashTable.DEFAULT_LOAD_FACTOR,
    capacity: number = SeparateChainingHashTable.DEFAULT_CAPACITY
  ) {
    if (capacity < 0) {
      throw new Error("Capacity must not be negative");
    }
    if (
      loadFactor <= 0 ||
      !Number.isFinite(loadFactor) ||
      Number.isNaN(loadFactor)
    ) {
      throw new Error("Load factor must be a positive number");
    }

    this.loadFactor = loadFactor;
    this.capacity = Math.max(
      SeparateChainingHashTable.DEFAULT_CAPACITY,
      capacity
    );
    this.threshold = Math.floor(this.capacity * this.loadFactor);
    this.table = new Array(this.capacity)
      .fill(null)
      .map(() => new DBLinkedList<NODE<K, V>>());
  }

  size(): number {
    throw new Error("Method not implemented.");
  }
  isEmpty(): boolean {
    throw new Error("Method not implemented.");
  }
  hashCodeToIndex(hashedKey: number): number {
    throw new Error("Method not implemented.");
  }
  clear(): void {
    throw new Error("Method not implemented.");
  }
  has(key: K): boolean {
    throw new Error("Method not implemented.");
  }
  insert(key: K, value: V): V {
    throw new Error("Method not implemented.");
  }
  get(key: K): V | undefined {
    throw new Error("Method not implemented.");
  }
  remove(key: K): V | undefined {
    throw new Error("Method not implemented.");
  }
  [Symbol.iterator](): Iterator<K, any, undefined> {
    throw new Error("Method not implemented.");
  }
}
