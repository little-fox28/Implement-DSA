import { DBLinkedList } from "./../LinkedList/DBLinkedList";
import { QueueADT } from "./QueueADT";

export class LinkedListBaseQueue<T> implements QueueADT<T> {
  private readonly linkedList: DBLinkedList<T> = new DBLinkedList<T>();

  constructor(firstElement: T) {
    this.enQueue(firstElement);
  }

  enQueue(element: T): T {
    return this.linkedList.addLast(element);
  }
  deQueue(): T | null {
    if (this.isEmpty()) throw new Error("Queue is empty!");
    return this.linkedList.removeFirst();
  }
  peek(): T | null {
    if (this.isEmpty()) throw new Error("Queue is empty!");
    return this.linkedList.peekFirst();
  }
  capacity(): number {
    return this.linkedList.sizeList();
  }
  isEmpty(): boolean {
    return this.linkedList.isEmpty();
  }
}
