interface TreeADT {
    isEmpty(): boolean;

    size(): number;

    height(): number;

    contains(elem: number): boolean;

    add(elem: number): boolean;

    remove(elem: number): boolean;

    // traverse(TreeTraverseType type);
}


export default TreeADT;