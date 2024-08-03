class NODE<K, V> {
    private hash: number;
    private key: K;
    private value: V;

    constructor(key: K, value: V) {
        this.hash = this.hashCode(key);
        this.key = key;
        this.value = value;
    }

    public getHash(): number {
        return this.hash;
    }

    public setHash(hash: number): void {
        this.hash = hash;
    }

    public getKey(): number {
        return this.hash;
    }

    public setKey(key: K): void {
        this.key = key;
    }

    public getValue(): V {
        return this.value;
    }

    public setValue(value: V): void {
        this.value = value;
    }

    public toString(): string {
        return `Hash:${this.hash} | Key: ${this.key} | Values: ${this.value}`;
    }


    // PRIVATE
    private hashCode(key: K): number {
        if (typeof key === 'number') {
            return key
        } else if (typeof key === 'string') {
            let hash = 0;
            for (let i = 0; i < key.length; i++) {
                hash = (hash << 5) - hash + key.charCodeAt(i);
                hash |= 0;
            }
            return hash;
        } else {
            throw new Error("Unsupported type for HashFunction");
        }
    }
}

export default NODE;