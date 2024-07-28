package HashTable;

import java.util.Random;

public class TestHashTable {
    static final int NUMBER_OF_KEYS = 10000000;
    static final int MOD = 10000000;
    static int[] keys = new int[NUMBER_OF_KEYS];
    static int[] values = new int[NUMBER_OF_KEYS];

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < keys.length; i++) {
            keys[i] = random.nextInt() % MOD;
            values[i] = random.nextInt() % MOD;
        }

        testSeparateChainingHashTable();
    }

    private static void testSeparateChainingHashTable() {
        HashTableADT<Integer,Integer> hashTable = new SeparateChainingHashTable<>(MOD);

        long start = System.nanoTime();
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            hashTable.insert(keys[i],values[i]);
            int value = hashTable.get(keys[i]);
            if (value != values[i]) System.out.println("Your logical was wrong");
        }
        long end = System.nanoTime();

        System.out.println("SeparateChainingHashTable: " + (end - start) / 1e9);
    }
}
