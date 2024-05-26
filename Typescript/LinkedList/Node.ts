class NODE<T> {
    private data: T | null;
    private next: NODE<T> | null;
    private prev: NODE<T> | null;

    constructor(prev: NODE<T> | null, data: T, next: NODE<T> | null) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public getData(): T | null {
        return this.data;
    }

    public setData(data: T | null): void {
        this.data = data;
    }

    public getNext(): NODE<T> | null {
        return this.next;
    }

    public setNext(next: NODE<T> | null): void {
        this.next = next;
    }

    public getPrev(): NODE<T> | null {
        return this.prev;
    }

    public setPrev(prev: NODE<T> | null): void {
        this.prev = prev;
    }
}

export default NODE;
