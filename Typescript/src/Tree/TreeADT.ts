import TreeOrderTraverseType from "./TreeOrderTraverseType";

interface TreeADT {
    isEmpty(): boolean;

    size(): number;

    height(): number;

    contains(elem: number): boolean;

    add(elem: number): boolean;

    remove(elem: number): boolean;

    traverse(type: TreeOrderTraverseType): any[];
}


export default TreeADT;