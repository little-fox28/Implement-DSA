import TreeADT from "./TreeADT";
import NodeTree from "./NodeTree";

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

    // Private
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