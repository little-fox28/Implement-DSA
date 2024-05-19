import NODE from "./Node";
import { TreeADT, TreeTraverseType } from "./TreeADT";
import TraverseSearchTree from "./TraverseSearchTree";

export interface Comparable<T> {
  compareTo(other: T | null): number;
}

export class BinarySearchTree<T extends Comparable<T>> implements TreeADT<T> {
  public nodeCount: number = 0;
  public root: NODE<T | null> | null = null;

  isEmpty(): boolean {
    return this.size() === 0;
  }
  size(): number {
    return this.nodeCount;
  }
  height(): number {
    return this.Height(this.root);
  }
  contains(element: T): boolean {
    return this.Contains(this.root, element);
  }
  add(element: T): boolean {
    if (this.contains(element)) return false;

    this.root = this.Add(this.root, element);
    this.nodeCount++;

    return true;
  }
  remove(element: T): boolean {
    if (!this.contains(element)) {
      return false;
    }
    this.root = this.Remove(this.root, element);
    this.nodeCount--;
    return true;
  }
  traverse(type: TreeTraverseType) {
    return new TraverseSearchTree(type, this.root, this.nodeCount);
  }

  // PRIVATE

  private Height(node: NODE<T | null> | null): number {
    if (node === null) {
      return 0;
    }
    return (
      1 + Math.max(this.Height(node.getLeft()), this.Height(node.getRight()))
    );
  }
  private Contains(node: NODE<T | null> | null, element: T): boolean {
    if (node === null) return false;

    const result: number = element.compareTo(node.getData());

    if (result < 0) {
      return this.Contains(node.getLeft(), element);
    } else if (result > 0) {
      return this.Contains(node.getRight(), element);
    } else {
      return true;
    }
  }
  private Add(node: NODE<T | null> | null, element: T): NODE<T | null> {
    if (node === null) {
      return new NODE<T | null>(element, null, null);
    } else {
      if (element.compareTo(node.getData()) > 0) {
        node.setRight(this.Add(node.getRight(), element));
      } else {
        node.setLeft(this.Add(node.getLeft(), element));
      }
      return node;
    }
  }

  private minRight(node: NODE<T | null> | null): T | null {
    while (node?.getLeft() !== null) {
      node = node!.getLeft();
    }
    return node !== null ? node.getData() : null;
  }

  private maxLeft(node: NODE<T | null> | null): T | null {
    while (node?.getRight() !== null) {
      node = node!.getRight();
    }
    return node !== null ? node.getData() : null;
  }

  private Remove(
    node: NODE<T | null> | null,
    element: T
  ): NODE<T | null> | null {
    if (node === null) {
      return null;
    }

    const result: number = element.compareTo(node.getData());

    if (result > 0) {
      node.setRight(this.Remove(node.getRight(), element));
    } else if (result < 0) {
      node.setLeft(this.Remove(node.getLeft(), element));
    } else {
      if (node.getLeft() === null) {
        return node.getRight();
      } else if (node.getRight() === null) {
        return node.getLeft();
      } else {
        const tmp: T | null = this.minRight(node.getRight());
        if (tmp !== null) {
          node.setData(tmp);
          node.setRight(this.Remove(node.getRight(), tmp));
        }
      }
    }
    return node;
  }
}

export default BinarySearchTree;