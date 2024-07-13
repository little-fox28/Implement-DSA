import TreeADT from "./TreeADT";
import NodeTree from "./NodeTree";
import node from "../LinkedList/Node";
import TreeOrderTraverseType from "./TreeOrderTraverseType";
import retryTimes = jest.retryTimes;
import {StackADT} from "../Stacks/StackADT";
import {LinkedListBaseStack} from "../Stacks/LinkedListBaseStack";
import NODE from "../LinkedList/Node";

type NodeTreeType = NodeTree | null;


class BinarySearchTree implements TreeADT {
    private nodeCount: number = 0;
    private root: NodeTreeType;

    constructor() {
        this.root = null;
    }

    height(): number {
        return this.Height(this.root);
    }

    isEmpty(): boolean {
        return this.size() === 0;
    }

    size(): number {
        return this.nodeCount;
    }

    contains(elem: number): boolean {
        if (elem === null) {
            return false;
        }
        return this.Contains(this.root, elem);
    }

    add(elem: number): boolean {
        if (this.contains(elem)) return false;
        this.root = this.Add(this.root, elem);
        this.nodeCount++
        return true;
    }


    remove(elem: number): boolean {
        if (!this.contains(elem)) return false;
        this.root = this.Remove(this.root, elem);
        this.nodeCount--
        return true;
    }

    traverse(type: TreeOrderTraverseType): number[] {
        switch (type) {
            case TreeOrderTraverseType.preOrderTraverse:
                return this.PreOrderTraverse();
            case TreeOrderTraverseType.inOrderTraverse:
                return this.InOrderTraverse();
            case TreeOrderTraverseType.levelOrderTraverse:
                return this.LevelOrderTraverse();
            case TreeOrderTraverseType.postOrderTraverse:
                return this.PostOrderTraverse();
            default:
                throw new Error("Invalid traversal type");
        }
    }

    // Private

    private PreOrderTraverse(): number[] {
        const result: number[] = [];
        const expectedCount = this.nodeCount;
        const stack: StackADT<NodeTree> = new LinkedListBaseStack<NodeTree>();

        if (this.root === null && stack.isEmpty()) {
            throw new Error("Tree Empty");
        }

        stack.push(this.root);

        while (!stack.isEmpty()) {
            if (expectedCount !== this.nodeCount) {
                throw new Error("Concurrent Modification Exception");
            }

            const node = stack.pop()!;
            result.push(node.getData()!);

            if (node.getRight() !== null) {
                stack.push(node.getRight()!);
            }

            if (node.getLeft() !== null) {
                stack.push(node.getLeft()!);
            }
        }

        return result;
    }

    public InOrderTraverse(): number[] {
        const result: number[] = [];
        const expectedCount = this.nodeCount;
        const stack: StackADT<NodeTree> = new LinkedListBaseStack();
        stack.push(this.root);

        if (this.root === null && stack.isEmpty()) {
            throw new Error("Tree Empty");
        }

        let travel = this.root;
        while (!stack.isEmpty()) {
            if (expectedCount !== this.nodeCount) {
                throw new Error("Concurrent Modification Exception");
            }

            while (travel !== null && travel?.getLeft() !== null) {
                stack.push(travel.getLeft()!);
                travel = travel.getLeft();
            }

            let node = stack.pop();

            if (node?.getRight() !== null) {
                stack.push(node!.getRight()!);
                travel = node!.getRight()!;
            }

            result.push(node!.getData()!);
        }
        return result;
    }

    private LevelOrderTraverse(): number[] {
        return [];

    }

    private PostOrderTraverse(): number[] {
        const result: number[] = [];
        const expectedCount = this.nodeCount;
        const stack: StackADT<NodeTree> = new LinkedListBaseStack()
        const postOrderStack: StackADT<NodeTree> = new LinkedListBaseStack()

        if (this.root === null) {
            throw new Error("Tree Empty");
        }

        stack.push(this.root);

        while (!stack.isEmpty()) {
            if (expectedCount !== this.nodeCount) {
                throw new Error("Concurrent Modification Exception");
            }

            const currentNode = stack.pop();
            postOrderStack.push(currentNode)

            if (currentNode!.getLeft() !== null) {
                stack.push(currentNode!.getLeft()!);
            }
            if (currentNode!.getRight() !== null) {
                stack.push(currentNode!.getRight()!);
            }
        }

        while (!postOrderStack.isEmpty()) {
            const nextNode = postOrderStack.pop();
            result.push(nextNode!.getData()!)
        }
        return result;
    }


    private Height(node: NodeTreeType): number {
        if (node === null) return 0;
        return 1 + Math.max(this.Height(node.getRight()), this.Height(node.getLeft()));
    }

    private Contains(node: NodeTreeType, elem: number): boolean {
        if (node === null) return false;

        if (elem < node.getData()!) {
            return this.Contains(node.getLeft(), elem);
        } else if (elem > node.getData()!) {
            return this.Contains(node.getRight(), elem);
        } else {
            return true
        }

    }

    private Add(node: NodeTreeType, elem: number): NodeTree {
        if (node === null) {
            return new NodeTree(elem)
        } else {
            if (elem < node.getData()!) {
                node.setLeft(this.Add(node.getLeft(), elem));
            } else {
                node.setRight(this.Add(node.getRight(), elem));
            }
        }
        return node;
    }

    private Remove(node: NodeTreeType, elem: number): NodeTreeType {
        if (node === null) return null;

        if (elem < node.getData()!) {
            node.setLeft(this.Remove(node.getLeft(), elem));
        } else if (elem > node.getData()!) {
            node.setRight(this.Remove(node.getRight(), elem));
        } else {
            if (node.getLeft() === null) {
                const nodeRight: NodeTreeType = node.getRight()
                node.setData(null)
                node = null
                return nodeRight
            } else if (node.getRight() === null) {
                const nodeLeft: NodeTreeType = node.getLeft()
                node.setData(null)
                node = null
                return nodeLeft
            } else {
                const tmp = this.maxLeft(node); // minRight(node)
                node.setData(tmp)
                node.setLeft(this.Remove(node.getLeft(), tmp))
            }
        }
        return node
    }

    private minRight(node: NodeTreeType): number {
        while (node!.getLeft() !== null) node = node!.getLeft()
        return node!.getData()!
    }

    private maxLeft(node: NodeTreeType): number {
        while (node!.getRight() !== null) node = node!.getRight()
        return node!.getData()!
    }
}

export default BinarySearchTree