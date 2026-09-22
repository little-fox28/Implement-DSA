package DynamicArray;

public class TestDynamicArray {
    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<Integer>();

        for (int i = 1; i < 10; i++) {
            dynamicArray.add(i * 2);
        }


        System.out.println(dynamicArray.toString());
        System.out.println("Size: " + dynamicArray.size());
        System.out.println("Capacity: " + dynamicArray.capacity());
        System.out.println("Index: " + dynamicArray.indexOf(8));
        System.out.println("Contain: " + dynamicArray.contain(200));


        System.out.println("Get: " + dynamicArray.get(3));
        dynamicArray.set(3, 20);
        System.out.println("Set: " + dynamicArray.toString());


        dynamicArray.removeAt(3);
        System.out.println("Remove at: " + dynamicArray.toString());

        dynamicArray.remove(6);
        System.out.println("Remove: " + dynamicArray.toString());

        dynamicArray.clear();
        System.out.println("Clear: " + dynamicArray.toString());

    }
}