import { TreeTraverseType } from "./TreeADT";
import {LinkedListBaseStack} from "../Stacks/LinkedListBaseStack";
import NODE from "./Node";
import {Comparable} from "./BinarySearchTree";
import {StackADT} from "../Stacks/StackADT";


class TraverseSearchTree<T extends Comparable<T>>{
  private readonly node: NODE<T>;
  private readonly nodeCount: number;


  constructor(type: TreeTraverseType, node: NODE<T> | null, nodeCount: number) {
    this.node = node;
    this.nodeCount = nodeCount;

    switch (type) {
      case TreeTraverseType.PreOrder:
        this.preOrderTraverse();
        break;
      case TreeTraverseType.InOrder:
        this.inOrderTraverse();
        break;
      case TreeTraverseType.PostOrder:
        this.postOrderTraverse();
        break;
      case TreeTraverseType.LevelOrder:
        this.levelOrderTraverse();
        break;
      default:
        throw new Error("Invalid traversal type");
    }
  }

  private *inOrderTraverse(): Generator<T> {
    const expectedCount: number = this.nodeCount;
    const stack:StackADT<NODE<T>> = new LinkedListBaseStack();
    let travel: NODE<T> = this.node;

    if (stack.isEmpty()) throw new Error("Stack is Empty!")

    while (travel !== null && !stack.isEmpty()){
          while (travel !== null){
            stack.push(travel.getLeft());
            travel = travel.getLeft();
          }
          travel = stack.pop();
          yield travel.getData();
          travel = travel.getRight()
    }

    if (expectedCount !== this.nodeCount) {
      throw new Error("Tree modified during traversal!")
    }
  }

  private *preOrderTraverse(): Generator<T> {
    const expectedCount: number = this.nodeCount;
    const stack:StackADT<NODE<T>> = new LinkedListBaseStack();

    if (stack.isEmpty()) throw new Error("Stack is Empty!")
    stack.push(this.node)

    if (expectedCount !== this.nodeCount) {
      throw new Error("Tree modified during traversal!")
    }

    while (!stack.isEmpty()){
      const node: NODE<T> = stack.pop();
      yield node.getData()
      if (node.getRight() !== null) stack.push(node.getRight());
      if (node.getLeft() !== null) stack.push(node.getLeft());
    }
  }

  private levelOrderTraverse() {
    throw new Error("Method not implemented.");
  }
  private postOrderTraverse() {
    throw new Error("Method not implemented.");
  }
}

export default TraverseSearchTree;
