import { DBLinkedList } from "../LinkedList/DBLinkedList";
import { StackADT } from "./StackADT";

export class LinkedListBaseStack<T> implements StackADT<T> {
  private list: DBLinkedList<T> = new DBLinkedList<T>();

  public push(element: T): void {
    this.list.add(element);
  }
  public pop(): T | null {
    if (this.list.isEmpty()) throw new Error("Empty Stack!");
    return this.list.removeLast();
  }

  public top(): T | null {
    if (this.list.isEmpty()) throw new Error("Empty Stack!");
    return this.list.peekLast();
  }

  public isEmpty(): boolean {
    return this.list.sizeList() === 0;
  }

  public size(): number {
    return this.list.sizeList();
  }

  public [Symbol.iterator](): Iterator<T> {
    return this.list[Symbol.iterator]();
  }
}
