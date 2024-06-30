class NodeTree {
    private data: number | null;
    private left: NodeTree | null;
    private right: NodeTree | null;

    constructor(data: number) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    getData(): number | null {
        return this.data;
    }

    getLeft(): NodeTree | null {
        return this.left;
    }

    getRight(): NodeTree | null {
        return this.right;
    }

    setData(data: number | null): void {
        this.data = data;
    }

    setLeft(left: NodeTree | null): void {
        this.left = left;
    }

    setRight(right: NodeTree | null): void {
        this.right = right;
    }
}

export default NodeTree