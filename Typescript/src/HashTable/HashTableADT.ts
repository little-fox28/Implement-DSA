export interface HashTableADT<K extends string | number, V>
  extends Iterable<K> {
  size(): number;

  isEmpty(): boolean;

  hashCodeToIndex(hashedKey: number): number;

  clear(): void;

  has(key: K): boolean;

  insert(key: K, value: V): V;

  get(key: K): V | undefined;

  remove(key: K): V | undefined;
}
