package Tree;

import java.util.Iterator;

public class TestBST {
    public static void main(String[] args) {
        TreeADT<Integer> bst = new BinarySearchTree<>();

        bst.add(20);
        bst.add(25);
        bst.add(10);
        bst.add(27);
        bst.add(11);
        bst.add(8);
        bst.add(29);
        bst.add(2);


        Iterator<Integer> traverse = bst.traverse(TreeTraverseType.LEVEL_ORDER);

        while (traverse.hasNext()){
            for (int i = 0; i < 5; i++) {
               System.out.println(traverse.next());
            }
        }




    }
}
