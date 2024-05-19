import { StringBuilder } from "../utils/StringBuilder";

export class DynamicArray<T> implements Iterable<T> {
  private array: T | Array<T | null>;
  private capacity: number;
  private size: number = 0;

  constructor(capacity: number = 10) {
    if (capacity < 0) {
      throw new Error(`Capacity cannot be negative, got ${capacity}`);
    }
    this.array = [];
    this.capacity = capacity;
    this.array = new Array(capacity);
  }

  public length(): number {
    return this.size;
  }

  public volume(): number {
    return this.capacity;
  }

  public isEmpty(): boolean {
    return this.length() === 0;
  }

  public getAt(index: number): T | null {
    return this.array[index];
  }

  public setAt(index: number, value: T): void {
    this.array[index] = value;
  }

  public cleanUp(): void {
    for (let i = 0; i < this.size; i++) {
      this.array[i] = null;
    }
    this.size = 0;
  }

  public add(value: T): void {
    if (this.size === this.capacity) {
      this.capacity = this.capacity === 0 ? 1 : (this.capacity *= 2);
      const newArray = new Array(this.capacity);
      for (let i = 0; i < this.size; i++) {
        newArray[i] = this.array[i];
      }
      this.array = newArray;
    }
    this.array[this.size] = value;
    this.size++;
  }

  public removeAt(removeIndex: number): void {
    if (removeIndex < 0 || removeIndex >= this.size) {
      throw new Error("Index out of bounds");
    }

    const newArray = new Array(this.size - 1);
    for (
      let oldArrayIndex = 0, newArrayIndex = 0;
      oldArrayIndex < this.size;
      oldArrayIndex++
    ) {
      if (oldArrayIndex === removeIndex) {
        continue;
      }
      newArray[newArrayIndex++] = this.array[oldArrayIndex];
    }

    this.array = newArray;
    this.size--;
  }

  public removeAtWithoutMoving(removeIndex: number): T | null {
    if (removeIndex >= this.size || removeIndex < 0) {
      throw new Error("Index out of bounds!");
    }
    const item: T = this.array[removeIndex];
    this.array[removeIndex] = null;
    this.size--;
    return item;
  }

  public remove(object: Object): void {
    const removeIndex = this.indexOf(object);
    this.removeAt(removeIndex);
  }
  public indexOf(object: Object): number {
    for (let i = 0; i < this.size; i++) {
      if (object === null) {
        if (this.array[i] === null) {
          return i;
        }
      } else {
        if (object === this.array[i]) {
          return i;
        }
      }
    }
    return -1;
  }
  public contains(object: Object): boolean {
    return this.indexOf(object) !== -1;
  }

  public toString(): string {
    if (this.isEmpty()) {
      return "[]";
    } else {
      const sb: StringBuilder = new StringBuilder();
      sb.append("[");
      for (let i = 0; i < this.size - 1; i++) {
        sb.append(this.array[i]);
        sb.append(", ");
      }
      sb.append(this.array[this.size - 1]);
      sb.append("]");
      return sb.toString();
    }
  }

  public [Symbol.iterator](): Iterator<T> {
    let index = 0;

    return {
      next: (): IteratorResult<T> => {
        if (index < this.size) {
          return { value: this.array[index++], done: false };
        } else {
          return { value: undefined, done: true };
        }
      },
    };
  }
}
