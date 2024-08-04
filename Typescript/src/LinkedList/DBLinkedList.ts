import NODE from "./Node";
import { iDBLinkedList } from "./iDBLinkedList";

export class DBLinkedList<T> implements iDBLinkedList<T> {
    private size: number = 0;
    private head: NODE<T> | null = null;
    private tail: NODE<T> | null = null;

    public clear(): void {
        let currentNode = this.head;
        while (currentNode) {
            const nextNode = currentNode.getNext();
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
        const newNode = new NODE<T>(null, element, this.head);
        if (this.isEmpty()) {
            this.tail = newNode;
        } else {
            this.head?.setPrev(newNode);
        }
        this.head = newNode;
        this.size++;
        return element;
    }

    public addLast(element: T): T {
        const newNode = new NODE<T>(this.tail, element, null);
        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            this.tail?.setNext(newNode);
        }
        this.tail = newNode;
        this.size++;
        return element;
    }

    public peekFirst(): T | null {
        if (this.isEmpty()) {
            throw new Error("Empty node list!");
        }
        return this.head!.getData();
    }

    public peekLast(): T | null {
        if (this.isEmpty()) {
            throw new Error("Empty node list!");
        }
        return this.tail!.getData();
    }

    public removeFirst(): T | null {
        if (this.isEmpty()) {
            throw new Error("Empty node list!");
        }
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

    public removeLast(): T | null {
        if (this.isEmpty()) {
            throw new Error("Empty node list!");
        }
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

    public remove(node: NODE<T>): T | null {
        if (node.getPrev() === null) {
            return this.removeFirst();
        }
        if (node.getNext() === null) {
            return this.removeLast();
        }

        const data = node.getData();
        node.getPrev()?.setNext(node.getNext());
        node.getNext()?.setPrev(node.getPrev());

        node.setData(null);
        node.setPrev(null);
        node.setNext(null);

        this.size--;
        return data;
    }

    public removeObj(object: Object): boolean {
        let currentNode = this.head;
        while (currentNode) {
            if (currentNode.getData() === object) {
                this.remove(currentNode);
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public removeAt(index: number): T | null {
        if (index < 0 || index >= this.size) {
            throw new Error("Index out of bounds!");
        }

        let currentNode: NODE<T> | null;
        if (index < this.size / 2) {
            currentNode = this.head;
            for (let i = 0; i < index; i++) {
                currentNode = currentNode!.getNext();
            }
        } else {
            currentNode = this.tail;
            for (let i = this.size - 1; i > index; i--) {
                currentNode = currentNode!.getPrev();
            }
        }
        return this.remove(currentNode!);
    }

    public indexOf(object: Object): number {
        let index = 0;
        let currentNode = this.head;
        while (currentNode) {
            if (currentNode.getData() === object) {
                return index;
            }
            currentNode = currentNode.getNext();
            index++;
        }
        return -1;
    }

    public constrain(object: Object): boolean {
        return this.indexOf(object) !== -1;
    }

    public toString(): string {
        if (this.isEmpty()) return "null";

        const elements: string[] = [];
        let currentNode = this.head;
        while (currentNode) {
            elements.push(`[${currentNode.getData()}]`);
            currentNode = currentNode.getNext();
        }
        return elements.join("->");
    }

    [Symbol.iterator](): Iterator<T> {
        let currentNode = this.head;
        return {
            next: (): IteratorResult<T> => {
                if (!currentNode) {
                    return { done: true, value: undefined as any };
                }
                const value = currentNode.getData();
                currentNode = currentNode.getNext();
                return { done: false, value: value! };
            }
        };
    }
}
