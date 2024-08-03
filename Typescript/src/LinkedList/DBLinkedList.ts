import {StringBuilder} from "../../utils/StringBuilder";
import NODE from "./Node";
import {iDBLinkedList} from "./iDBLinkedList";

export class DBLinkedList<T> implements iDBLinkedList<T> {
    private size: number;
    private head: NODE<T> | null;
    private tail: NODE<T> | null;

    constructor() {
        this.size = 0;
        this.head = null;
        this.tail = null;
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

    public getHead(): NODE<T> | null {
        return this.head;
    }

    public getTail(): NODE<T> | null {
        return this.tail;
    }

    public isEmpty(): boolean {
        return this.size === 0;
    }

    public add(element: T): void {
        this.addLast(element);

    }

    public addFirst(element: T): T {
        if (this.isEmpty()) {
            this.head = this.tail = new NODE<T>(null, element, null);
        } else {
            let newNode = new NODE<T>(null, element, this.head);
            this.head?.setPrev(newNode);
            this.head = newNode;
        }
        this.size++;

        return element;
    }

    public addLast(element: T): T {
        if (this.isEmpty()) {
            this.head = this.tail = new NODE<T>(null, element, null);
        } else {
            const newNode = new NODE<T>(this.tail, element, null);
            this.tail?.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;

        return element;
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
        if (this.isEmpty()) return "null";
        else {
            let sb: StringBuilder = new StringBuilder();
            let currentNode = this.head;

            while (currentNode !== null) {
                sb.append("[");
                sb.append(currentNode.getData());
                sb.append("]");
                if (currentNode.getNext() !== null) {
                    sb.append("->");
                }
                currentNode = currentNode.getNext();
            }
            return sb.toString();
        }
    }

    [Symbol.iterator](): Iterator<T> {
        let currentNode = this.head;
        return {
            next: (): IteratorResult<T> => {
                if (currentNode === null) {
                    return { done: true, value: undefined as any };
                } else {
                    const value = currentNode.getData();
                    currentNode = currentNode.getNext();
                    return { done: false, value: value! };
                }
            }
        };
    }
}
