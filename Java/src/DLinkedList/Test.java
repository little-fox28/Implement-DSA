package DLinkedList;

public class Test {
    public static void main(String[] args) {
        DLinkedList dlinkedlist = new DLinkedList();

        for (int i = 1; i < 11; i++) {
            dlinkedlist.add(i * 2);
        }

        System.out.println("Size: " + dlinkedlist.size());
        System.out.println("Get first item: " + dlinkedlist.peekFirst());
        System.out.println("Get last item: " + dlinkedlist.peekLast());
        System.out.println("Head: " + dlinkedlist.toString());
        System.out.println("Remove First: " + dlinkedlist.removeFirst());
        System.out.println("Head: " + dlinkedlist.toString());
        System.out.println("Remove Last: " + dlinkedlist.removeLast());
        System.out.println("Head: " + dlinkedlist.toString());
        System.out.println("Remove object: " + dlinkedlist.remove(8));
        System.out.println("Head: " + dlinkedlist.toString());
        System.out.println("Remove at: " + dlinkedlist.removeAt(4));
        System.out.println("Head: " + dlinkedlist.toString());
        System.out.println("Index of: " + dlinkedlist.indexOf(10));
        System.out.println("Contain: " + dlinkedlist.contains(4));

    }
}
