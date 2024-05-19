import { Comparable } from "./BinarySearchTree";
import TraverseSearchTree from "./TraverseSearchTree";
import NODE from "./Node";

export enum TreeTraverseType {
  PreOrder,
  InOrder,
  PostOrder,
  LevelOrder,
}

export interface TreeADT<T extends Comparable<T>> {
  isEmpty(): boolean;
  size(): number;
  height(): number;
  contains(element: T): boolean;
  add(element: T): boolean;
  remove(element: T): boolean;
  traverse(type: TreeTraverseType): TraverseSearchTree<T>;
}
