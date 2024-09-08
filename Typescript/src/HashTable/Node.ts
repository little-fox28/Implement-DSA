class NODE<K extends number | string, V extends number | string> {
  private hash: number;
  private key: K;
  private value: V;

  constructor(key: K, value: V) {
    this.hash = this.generateHashCode(key);
    this.key = key;
    this.value = value;
  }

  public getHash(): number {
    return this.hash;
  }

  public setHash(hash: number): void {
    this.hash = hash;
  }

  public getKey(): K {
    return this.key;
  }

  public setKey(key: K): void {
    this.key = key;
  }

  public getValue(): V {
    return this.value;
  }

  public setValue(value: V): void {
    this.value = value;
    NODE<K, V>;
  }

  public equals(other: NODE<K, V>): boolean {
    if (other.hash !== this.hash) {
      return false;
    }
    return this.key === other.key;
  }

  public toString(): string {
    return `Node[Hash: ${this.hash} | Key: ${this.key}, Value: ${this.value}]`;
  }

  private generateHashCode(key: K): number {
    if (typeof key === "number") {
      return key;
    } else if (typeof key === "string") {
      let hash = 0;
      for (let i = 0; i < key.length; i++) {
        const char = key.charCodeAt(i);
        hash = (hash << 5) - hash + char;
        hash = hash & hash; // Convert to 32-bit integer
      }
      return hash;
    } else {
      throw new Error("Unsupported key type for hashing");
    }
  }
}

export default NODE;
