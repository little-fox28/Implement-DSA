import { StringBuilder } from "../utils/StringBuilder";
import NODE from "./Node";
import { iDBLinkedList } from "./iDBLinkedList";

export class DBLinkedList<T> implements iDBLinkedList<T> {
  private size: number;
  private head: NODE<T> | null;
  private tail: NODE<T> | null;

  constructor() {
    this.size = 0;
    this.head = null;
    this.tail = null;
  }
  [Symbol.iterator](): Iterator<T, any, undefined> {
    let currentNode: NODE<T> | null = this.head;

    const hasNext = (): boolean => currentNode !== null;

    const next = (): IteratorResult<T, boolean> => {
      if (currentNode === null) {
        throw new Error("Empty node list!");
      }
      const data: T | any = currentNode.getData();
      currentNode = currentNode.getNext();
      return { value: data, done: false };
    };

    return {
      next,
      // hasNext,
    };
  }

  public clear(): void {
    let currentNode: NODE<T> | null = this.head;
    while (currentNode !== null) {
      let nextNode = currentNode.getNext();
      currentNode.setPrev(null);
      currentNode.setData(null);
      currentNode.setNext(null);
      currentNode = nextNode;
    }

    this.size = 0;
    this.head = null;
    this.tail = null;
  }

  public sizeList(): number {
    return this.size;
  }
  public isEmpty(): boolean {
    return this.size === 0;
  }

  public add(value: T): void {
    this.addLast(value);
  }

  public addFirst(value: T): void {
    if (this.isEmpty()) {
      this.head = this.tail = new NODE<T>(null, value, null);
    } else {
      let newNode = new NODE<T>(null, value, this.head);
      this.head?.setPrev(newNode);
      this.head = newNode;
    }
    this.size++;
  }

  public addLast(value: T): void {
    if (this.isEmpty()) {
      this.head = this.tail = new NODE<T>(null, value, null);
    } else {
      const newNode = new NODE<T>(this.tail, value, null);
      this.tail?.setNext(newNode);
      this.tail = newNode;
    }
    this.size++;
  }

  public peekFirst(): T | null {
    if (this.isEmpty()) {
      throw new Error(" Empty node list!");
    } else {
      return this.head!.getData();
    }
  }
  public peekLast(): T | null {
    if (this.isEmpty()) {
      throw new Error(" Empty node list!");
    } else {
      return this.tail!.getData();
    }
  }

  public removeFirst(): T | null {
    if (this.isEmpty()) {
      throw new Error(" Empty node list! ");
    } else {
      const data = this.head!.getData();
      this.head = this.head!.getNext();
      this.size--;
      if (this.isEmpty()) {
        this.tail = null;
      } else {
        this.head?.setPrev(null);
      }
      return data;
    }
  }

  public removeLast(): T | null {
    if (this.isEmpty()) {
      throw new Error(" Empty node list");
    } else {
      const data = this.tail!.getData();
      this.tail = this.tail!.getPrev();
      this.size--;
      if (this.isEmpty()) {
        this.head = null;
      } else {
        this.tail?.setNext(null);
      }
      return data;
    }
  }

  public remove(node: NODE<T> | null): T | null {
    if (node?.getPrev() === null) {
      return this.removeFirst();
    }
    if (node?.getNext() === null) {
      return this.removeLast();
    }

    const data = node!.getData();

    node?.getPrev()?.setNext(node.getNext());
    node?.getNext()?.setPrev(node.getPrev());

    node?.setData(null);
    node?.setPrev(null);
    node?.setNext(null);

    this.size--;
    return data;
  }

  public removeObj(object: Object): boolean {
    let currentNode = this.head;
    if (object == null) {
      while (currentNode != null) {
        if (currentNode.getData() === null) {
          this.remove(currentNode);
          return true;
        }
        currentNode = currentNode.getNext();
      }
    } else {
      while (currentNode != null) {
        if (currentNode.getData() === object) {
          this.remove(currentNode);
          return true;
        }
        currentNode = currentNode.getNext();
      }
    }
    return false;
  }

  public removeAt(index: number): T | null {
    if (index < 0 || index >= this.size) {
      throw new Error("Index out of bounds!");
    }

    let i: number;
    let currentNode: NODE<T> | null;

    if (index < this.size / 2) {
      i = 0;
      currentNode = this.head;
      while (i !== index) {
        currentNode = currentNode!.getNext();
        i++;
      }
    } else {
      i = this.size - 1;
      currentNode = this.tail;
      while (i !== index) {
        currentNode = currentNode!.getPrev();
        i--;
      }
    }

    return this.remove(currentNode);
  }

  public indexOf(object: Object): number {
    let index: number = 0;
    let currentNode = this.head;
    if (object === null) {
      while (currentNode !== null) {
        if (currentNode.getData() === null) {
          return index;
        }
        currentNode = currentNode.getNext();
        index++;
      }
    } else {
      while (currentNode !== null) {
        if (currentNode.getData() !== null) {
          if (currentNode !== null) {
            if (currentNode.getData() === object) {
              return index;
            }
          }
        }
        currentNode = currentNode!.getNext();
        index++;
      }
    }
    return -1;
  }

  public constrain(object: Object): boolean {
    return this.indexOf(object) !== -1;
  }

  public toString(): string {
    if (this.isEmpty()) return "[]";
    else {
      let sb: StringBuilder = new StringBuilder();
      let currentNode = this.head;

      sb.append("[");
      while (currentNode !== null) {
        sb.append(currentNode.getData());
        if (currentNode.getNext() !== null) {
          sb.append(",");
        }
        currentNode = currentNode.getNext();
      }
      sb.append("]");
      return sb.toString();
    }
  }
}
