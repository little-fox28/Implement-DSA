import NODE from "./Node";

export interface iDBLinkedList<T> {
    clear(): void;

    sizeList(): number;

    isEmpty(): boolean;

    add(element: T): void;

    addFirst(element: T): T;

    addLast(element: T): T;

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
