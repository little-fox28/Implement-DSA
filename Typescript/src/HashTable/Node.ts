class NODE<K,V>{
    private hash: number;
    private key: K;
    private value: V;

    constructor(hash: number, key: K, value: V) {
        this.hash = hash;
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
}

export default NODE;