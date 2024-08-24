package Tree;

import java.util.Iterator;

public class TestBST {
    public static void main(String[] args) {
        TreeADT<Integer> bst = new BinarySearchTree<>();

        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(3);
        bst.add(7);
        bst.add(12);
        bst.add(18);

        Iterator<Integer> traverse = bst.traverse(TreeTraverseType.LEVEL_ORDER);

        System.out.println("Level-order traversal:");
        while (traverse.hasNext()) {
            System.out.println(traverse.next());
        }
    }
}
