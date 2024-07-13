import { QueueADT } from "./QueueADT";

export class ArrayBaseQueue<T> implements QueueADT<T> {
  private readonly array: T[];
  private front: number;
  private end: number;
  private readonly size: number;

  constructor(maxSize: number) {
    this.front = this.end = 0;
    this.size = maxSize + 1;
    this.array = new Array(this.size);
  }

  enQueue(element: T): void {
    this.array[this.end] = element;
    if (++this.end == this.size) this.end = 0;
    if (this.end == this.front) throw new Error("Queue is full");
  }

  deQueue(): T | null {
    if (this.isEmpty()) throw new Error("Queue is empty");
    let deQueueElement: T | null = this.array[this.front];
    deQueueElement = null;
    if (++this.front == this.size) this.front = 0;
    return deQueueElement;
  }
  peek(): T | null {
    if (this.isEmpty()) throw new Error("Queue is empty");
    return this.array[this.front];
  }
  capacity(): number {
    if (this.front > this.end) return this.end + this.size - this.front;
    return this.end - this.front;
  }
  isEmpty(): boolean {
    return this.front == this.end;
  }
}
