import { Comparable } from "./BinarySearchTree";

class NODE<T extends Comparable<T> | null> {
  constructor(
    public data: T,
    public left: NODE<T> | null = null,
    public right: NODE<T> | null = null
  ) {}

  getData(): T | null {
    return this.data;
  }

  setData(data: T): void {
    this.data = data;
  }

  getRight(): NODE<T> | null {
    return this.right;
  }

  setRight(right: NODE<T> | null): void {
    this.right = right;
  }

  getLeft(): NODE<T> | null {
    return this.left;
  }

  setLeft(left: NODE<T> | null): void {
    this.left = left;
  }
}

export default NODE;
