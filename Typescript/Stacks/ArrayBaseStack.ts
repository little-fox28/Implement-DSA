import { DynamicArray } from "../DynamicArray/DynamicArray";
import { StackADT } from "./StackADT";

export class ArrayBaseStack<T> implements StackADT<T> {
  private array: DynamicArray<T>;
  private index: number = -1;

  constructor(initSize: number) {
    this.array = new DynamicArray<T>(initSize);
  }

  public push(value: T): void {
    this.index++;
    this.array.add(value);
  }

  public pop(): T | null {
    if (this.array.isEmpty()) {
      throw new Error("Empty Stack!");
    }
    return this.array.removeAtWithoutMoving(this.index--);
    // The stack only interacts with the first element because of the FIFO mechanism
    // The index -1 because of array starting from the 0
  }

  public top(): T | null {
    return this.array.getAt(this.index);
  }

  public size(): number {
    return this.array.length();
  }

  public isEmpty(): boolean {
    return this.array.isEmpty();
  }
}
