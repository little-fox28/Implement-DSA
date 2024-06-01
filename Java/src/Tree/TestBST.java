package Tree;

import java.util.Iterator;

public class TestBST {
    public static void main(String[] args) {
        TreeADT<Integer> bst = new BinarySearchTree<Integer>();

        bst.add(5);
        bst.add(4);
        bst.add(6);
        bst.add(7);
        bst.add(3);
        bst.add(2);
        bst.add(10);


        Iterator<Integer> traverse = bst.traverse(TreeTraverseType.IN_ORDER);

        for (int i = 0; i < bst.size(); i++) {
            System.out.println(traverse.next());
        }

    }
}
