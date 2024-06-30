export interface StackADT<T> {
  push(value: T | null): void;
  pop(): T | null;
  top(): T | null;
  size(): number;
  isEmpty(): boolean;
}
