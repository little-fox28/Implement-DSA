package Stack;

public class TestStack {
    public static void main(String[] args) {
        int numberOfOperations = 10000000;

        // Array based stack
        StackADT<Integer> arrayBaseStack = new ArrayBaseStack<Integer>(10);

        long startTime = System.nanoTime();
        for (int i = 0; i < numberOfOperations; i++) {
            arrayBaseStack.push(i * 2);
        }
        for (int i = 0; i < numberOfOperations; i++) {
            arrayBaseStack.pop();
        }
        long endTime = System.nanoTime();
        System.out.println("Array based stack took: " + (endTime - startTime) / 1_000_000_000.0 + "\n");

        // LinkedList based stack
        StackADT<Integer> linkedListBaseStack = new LinkedListBaseStack<>(numberOfOperations);

        startTime = System.nanoTime();
        for (int i = 0; i < numberOfOperations; i++) {
            linkedListBaseStack.push(i * 2);
        }
        for (int i = 0; i < numberOfOperations; i++) {
            linkedListBaseStack.pop();
        }
        endTime = System.nanoTime();
        System.out.println("LinkedList based stack took: " + (endTime - startTime) / 1_000_000_000.0 + "\n");
    }
}
