export interface QueueADT<T> {
  enQueue(element: T): void;
  deQueue(): T | null;
  peek(): T | null;
  capacity(): number;
  isEmpty(): boolean;
}
