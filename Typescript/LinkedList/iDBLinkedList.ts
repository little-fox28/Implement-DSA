import NODE from "./Node";

export interface iDBLinkedList<T> extends Iterable<T> {
  clear(): void;
  sizeList(): number;
  isEmpty(): boolean;
  add(value: T): void;
  addFirst(value: T): void;
  addLast(value: T): void;
  peekFirst(): T | null;
  peekLast(): T | null;
  removeFirst(): T | null;
  removeLast(): T | null;
  remove(node: NODE<T>): T | null;
  removeObj(object: Object): boolean;
  removeAt(index: number): T | null;
  indexOf(object: Object): number;
  constrain(object: Object): boolean;
  toString(): string;
}
