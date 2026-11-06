package Leetcode.medium.AddTwoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Senior Java Tester & Senior Developer Test Suite for LeetCode #2: Add Two Numbers.
 *
 * Test Design Methodology:
 * 1. Equivalence Partitioning (EP):
 *    - EP-1: Equal length lists without carry (e.g., [1,2,3] + [4,5,6] = [5,7,9]).
 *    - EP-2: Equal length lists with internal carry (e.g., [2,4,3] + [5,6,4] = [7,0,8]).
 *    - EP-3: Equal length lists producing a new MSB node (e.g., [5] + [5] = [0,1]).
 *    - EP-4: Asymmetric lengths (l1 longer than l2, and l1 shorter than l2).
 *    - EP-5: Identity addition with zero ([0] + [x,y,z] = [x,y,z]).
 *
 * 2. Boundary Value Analysis (BVA):
 *    - List length constraints (1 <= length <= 100):
 *      * Minimum length: 1 node + 1 node.
 *      * Extreme asymmetry: 1 node + 100 nodes.
 *      * Maximum length: 100 nodes + 100 nodes (producing 100 or 101 nodes).
 *    - Digit value constraints (0 <= Node.val <= 9):
 *      * Minimum digit: 0.
 *      * Maximum digit: 9 (maximizing carry generation: 9 + 9 + 1 = 19).
 *
 * 3. Pitfalls & Tricky Traps (Edge Cases):
 *    - Forgotten Final Carry Trap: Carry = 1 after both l1 and l2 become null (e.g., [9,9] + [1] -> [0,0,1]).
 *    - Ripple Carry Chain: Single carry cascading through multiple 9s (e.g., [1] + [9,9,9,9,9] -> [0,0,0,0,0,1]).
 *    - NullPointerException (NPE) Trap: Different list lengths when advancing l1.next / l2.next without null checks.
 *    - Integer / Long Overflow Trap: Lists with 11+ nodes (overflows 32-bit int) and 20+ nodes (overflows 64-bit long),
 *      defeating naive solutions that convert linked lists to primitive numbers.
 *    - Internal Zeros: Numbers like 1005 ([5,0,0,1]) containing mid-list zeros (valid, non-leading zeros).
 */
public class Test {

    private static final int MAX_SAFE_LIST_LENGTH = 105;

    /**
     * TestCase data model representing an isolated test scenario.
     */
    record TestCase(int id, String category, String description, int[] l1, int[] l2, int[] expected) {
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TestCase> testCases = buildTestCases();

        System.out.println("==========================================================================================================");
        System.out.println("                         TEST RUNNER: LEETCODE #2 - ADD TWO NUMBERS                                       ");
        System.out.println("==========================================================================================================\n");

        int passed = 0;
        int failed = 0;
        long totalTimeNs = 0;

        for (TestCase tc : testCases) {
            long start = System.nanoTime();
            boolean isPassed = executeTestCase(solution, tc);
            long elapsedNs = System.nanoTime() - start;
            totalTimeNs += elapsedNs;

            if (isPassed) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("==========================================================================================================");
        System.out.println("                                            TEST SUMMARY                                                  ");
        System.out.println("==========================================================================================================");
        System.out.printf("Total Test Cases : %d%n", testCases.size());
        System.out.printf("PASSED           : %d%n", passed);
        System.out.printf("FAILED           : %d%n", failed);
        System.out.printf("Pass Rate        : %.2f%%%n", (passed * 100.0) / testCases.size());
        System.out.printf("Total Exec Time  : %.3f ms%n", totalTimeNs / 1_000_000.0);
        System.out.println("----------------------------------------------------------------------------------------------------------");

        if (passed == testCases.size()) {
            System.out.println(">>> [SUCCESS] TAT CA TEST CASES DA PASS! THUAT TOAN DAT CHUAN SENIOR PRODUCTION READY!");
        } else {
            System.out.println(">>> [NOTICE] CO TEST CASE FAILED. Hay kiem tra lai thuat toan trong Solution.java!");
        }
        System.out.println("==========================================================================================================\n");
    }

    /**
     * Executes a single test case with microsecond timing, cycle detection, and failure diagnostics.
     */
    private static boolean executeTestCase(Solution solution, TestCase tc) {
        ListNode head1 = toListNode(tc.l1);
        ListNode head2 = toListNode(tc.l2);

        ListNode actualNode;
        long start = System.nanoTime();

        try {
            actualNode = solution.addTwoNumbers(head1, head2);
        } catch (Exception e) {
            long elapsedUs = (System.nanoTime() - start) / 1000;
            System.out.printf("[FAIL] #%02d | %-22s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input    : l1 = %s, l2 = %s%n", formatArray(tc.l1), formatArray(tc.l2));
            System.out.printf("       Exception: %s - %s%n", e.getClass().getSimpleName(), e.getMessage());
            System.out.printf("       Duration : %d us%n%n", elapsedUs);
            return false;
        }

        long elapsedUs = (System.nanoTime() - start) / 1000;

        int[] actual;
        try {
            actual = toArraySafe(actualNode);
        } catch (IllegalStateException cycleEx) {
            System.out.printf("[FAIL] #%02d | %-22s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input    : l1 = %s, l2 = %s%n", formatArray(tc.l1), formatArray(tc.l2));
            System.out.printf("       Error    : %s%n", cycleEx.getMessage());
            System.out.printf("       Duration : %d us%n%n", elapsedUs);
            return false;
        }

        boolean isPassed = Arrays.equals(tc.expected, actual);

        if (isPassed) {
            System.out.printf("[PASS] #%02d | %-22s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input   : l1 = %-22s | l2 = %-22s%n", formatArray(tc.l1), formatArray(tc.l2));
            System.out.printf("       Expected: %-27s | Actual: %-22s | Time: %d us%n%n",
                    formatArray(tc.expected), formatArray(actual), elapsedUs);
            return true;
        } else {
            System.out.printf("[FAIL] #%02d | %-22s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input   : l1 = %-22s | l2 = %-22s%n", formatArray(tc.l1), formatArray(tc.l2));
            System.out.printf("       Expected: %-27s | Actual: %-22s | Time: %d us%n%n",
                    formatArray(tc.expected), formatArray(actual), elapsedUs);
            return false;
        }
    }

    /**
     * Helper: Converts an int[] array of digits into a singly-linked ListNode chain.
     */
    private static ListNode toListNode(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int d : digits) {
            curr.next = new ListNode(d);
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * Helper: Converts a ListNode chain back to int[] with cycle/infinite-loop guard.
     */
    private static int[] toArraySafe(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> values = new ArrayList<>();
        ListNode curr = head;
        int steps = 0;
        while (curr != null) {
            if (steps > MAX_SAFE_LIST_LENGTH) {
                throw new IllegalStateException(
                        "Infinite loop or cycle detected in returned ListNode (> " + MAX_SAFE_LIST_LENGTH + " nodes)!");
            }
            values.add(curr.val);
            curr = curr.next;
            steps++;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Formats an int[] array for clean console display, truncating overly long arrays.
     */
    private static String formatArray(int[] arr) {
        if (arr == null) {
            return "null";
        }
        if (arr.length <= 8) {
            return Arrays.toString(arr);
        }
        return String.format("[%d, %d, %d, ... %d, %d] (len=%d)",
                arr[0], arr[1], arr[2], arr[arr.length - 2], arr[arr.length - 1], arr.length);
    }

    /**
     * Constructs comprehensive test cases categorized across all quality dimensions.
     */
    private static List<TestCase> buildTestCases() {
        List<TestCase> list = new ArrayList<>();

        // =========================================================================
        // GROUP 1: LEETCODE STANDARD EXAMPLES
        // =========================================================================
        list.add(new TestCase(1, "LeetCode Examples", "Example 1: 342 + 465 = 807 (Có nhớ ở hàng chục)",
                new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8}));

        list.add(new TestCase(2, "LeetCode Examples", "Example 2: 0 + 0 = 0 (Biên nhỏ nhất: 1 node chứa số 0)",
                new int[]{0}, new int[]{0}, new int[]{0}));

        list.add(new TestCase(3, "LeetCode Examples", "Example 3: 9999999 + 9999 = 10009998 (Lệch độ dài + nhớ dây chuyền)",
                new int[]{9, 9, 9, 9, 9, 9, 9}, new int[]{9, 9, 9, 9}, new int[]{8, 9, 9, 9, 0, 0, 0, 1}));

        // =========================================================================
        // GROUP 2: BOUNDARY VALUE ANALYSIS (Single Node & Identity Zero)
        // =========================================================================
        list.add(new TestCase(4, "BVA: Single Node", "1 node + 1 node không nhớ: 3 + 4 = 7",
                new int[]{3}, new int[]{4}, new int[]{7}));

        list.add(new TestCase(5, "BVA: Single Node", "1 node + 1 node chạm ngưỡng 9: 1 + 8 = 9",
                new int[]{1}, new int[]{8}, new int[]{9}));

        list.add(new TestCase(6, "BVA: Single Node", "1 node + 1 node sinh node mới: 5 + 5 = 10",
                new int[]{5}, new int[]{5}, new int[]{0, 1}));

        list.add(new TestCase(7, "BVA: Single Node", "1 node + 1 node cực đại: 9 + 9 = 18",
                new int[]{9}, new int[]{9}, new int[]{8, 1}));

        list.add(new TestCase(8, "BVA: Identity Zero", "Cộng 0 vào số nhiều chữ số (l1 = [0]): 0 + 987 = 987",
                new int[]{0}, new int[]{7, 8, 9}, new int[]{7, 8, 9}));

        list.add(new TestCase(9, "BVA: Identity Zero", "Cộng 0 vào số nhiều chữ số (l2 = [0]): 987 + 0 = 987",
                new int[]{7, 8, 9}, new int[]{0}, new int[]{7, 8, 9}));

        // =========================================================================
        // GROUP 3: EQUIVALENCE PARTITIONING (Equal & Asymmetric Lengths)
        // =========================================================================
        list.add(new TestCase(10, "EP: Equal Length", "Cùng độ dài, hoàn toàn không có nhớ: 321 + 654 = 975",
                new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{5, 7, 9}));

        list.add(new TestCase(11, "EP: Equal Length", "Cùng độ dài, sinh thêm 1 node ở cuối: 654 + 446 = 1100",
                new int[]{4, 5, 6}, new int[]{6, 4, 4}, new int[]{0, 0, 1, 1}));

        list.add(new TestCase(12, "EP: Equal Length", "Cùng độ dài toàn số 9: 999 + 999 = 1998",
                new int[]{9, 9, 9}, new int[]{9, 9, 9}, new int[]{8, 9, 9, 1}));

        list.add(new TestCase(13, "EP: Asymmetric", "l1 ngắn hơn l2, không nhớ: 12 + 345 = 357",
                new int[]{2, 1}, new int[]{5, 4, 3}, new int[]{7, 5, 3}));

        list.add(new TestCase(14, "EP: Asymmetric", "l1 dài hơn l2, không nhớ: 345 + 12 = 357",
                new int[]{5, 4, 3}, new int[]{2, 1}, new int[]{7, 5, 3}));

        list.add(new TestCase(15, "EP: Asymmetric", "l1 ngắn hơn l2, có nhớ ở giữa: 89 + 1234 = 1323",
                new int[]{9, 8}, new int[]{4, 3, 2, 1}, new int[]{3, 2, 3, 1}));

        // =========================================================================
        // GROUP 4: TRAPS & EDGE CASES (Ripple Carry, Internal Zeros, Forgotten Carry)
        // =========================================================================
        list.add(new TestCase(16, "Traps: Ripple Carry", "1 cộng chuỗi 9 dài (l1 ngắn): 1 + 99999 = 100000",
                new int[]{1}, new int[]{9, 9, 9, 9, 9}, new int[]{0, 0, 0, 0, 0, 1}));

        list.add(new TestCase(17, "Traps: Ripple Carry", "Chuỗi 9 dài cộng 1 (l2 ngắn): 99999 + 1 = 100000",
                new int[]{9, 9, 9, 9, 9}, new int[]{1}, new int[]{0, 0, 0, 0, 0, 1}));

        list.add(new TestCase(18, "Traps: Ripple Stop", "Nhớ dây chuyền dừng ở giữa danh sách: 1 + 2999 = 3000",
                new int[]{1}, new int[]{9, 9, 9, 2}, new int[]{0, 0, 0, 3}));

        list.add(new TestCase(19, "Traps: Internal Zero", "Số có nhiều chữ số 0 ở giữa: 1005 + 2005 = 3010",
                new int[]{5, 0, 0, 1}, new int[]{5, 0, 0, 2}, new int[]{0, 1, 0, 3}));

        list.add(new TestCase(20, "Traps: Internal Zero", "Số tròn chục nghìn cộng nhau: 1000 + 9000 = 10000",
                new int[]{0, 0, 0, 1}, new int[]{0, 0, 0, 9}, new int[]{0, 0, 0, 0, 1}));

        list.add(new TestCase(21, "Traps: Alternating", "Xen kẽ có nhớ và không nhớ: 90909 + 10101 = 101010",
                new int[]{9, 0, 9, 0, 9}, new int[]{1, 0, 1, 0, 1}, new int[]{0, 1, 0, 1, 0, 1}));

        // =========================================================================
        // GROUP 5: PRIMITIVE OVERFLOW TRAPS (Defeating int / long conversion)
        // =========================================================================
        // 12 digits > Integer.MAX_VALUE (2,147,483,647)
        list.add(new TestCase(22, "Traps: Int Overflow", "Bẫy tràn số 32-bit int (12 chữ số): 999,999,999,999 + 1",
                new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                new int[]{1},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}));

        // 21 digits > Long.MAX_VALUE (9,223,372,036,854,775,807)
        int[] twentyOne9s = new int[21];
        Arrays.fill(twentyOne9s, 9);
        int[] twentyOneExpected = new int[22];
        twentyOneExpected[21] = 1;
        list.add(new TestCase(23, "Traps: Long Overflow", "Bẫy tràn số 64-bit long (21 chữ số): 10^21 - 1 + 1 = 10^21",
                twentyOne9s,
                new int[]{1},
                twentyOneExpected));

        // =========================================================================
        // GROUP 6: STRESS & CONSTRAINTS (Max Length = 100 Nodes)
        // =========================================================================
        // 24. Max length (100 nodes) + 1 node without carry
        int[] hundredOnes = new int[100];
        Arrays.fill(hundredOnes, 1);
        int[] hundredOnesPlusTwo = Arrays.copyOf(hundredOnes, 100);
        hundredOnesPlusTwo[0] = 3;
        list.add(new TestCase(24, "Stress: Max Length", "Biên trên 100 nodes + 1 node không nhớ",
                hundredOnes,
                new int[]{2},
                hundredOnesPlusTwo));

        // 25. Max length (100 nodes of 9) + [1] -> 101 nodes (100 zeros followed by 1)
        int[] hundredNines = new int[100];
        Arrays.fill(hundredNines, 9);
        int[] hundredOneNodesResult = new int[101];
        hundredOneNodesResult[100] = 1;
        list.add(new TestCase(25, "Stress: Max Length", "Biên trên 100 nodes toàn số 9 + [1] -> Sinh ra 101 nodes",
                hundredNines,
                new int[]{1},
                hundredOneNodesResult));

        // 26. Max length (100 nodes of 9) + Max length (100 nodes of 9) -> 101 nodes ([8, 9, 9, ..., 9, 1])
        int[] maxPlusMaxExpected = new int[101];
        Arrays.fill(maxPlusMaxExpected, 9);
        maxPlusMaxExpected[0] = 8;
        maxPlusMaxExpected[100] = 1;
        list.add(new TestCase(26, "Stress: Max Length", "Cực đại 100 nodes toàn 9 + 100 nodes toàn 9 -> 101 nodes",
                hundredNines,
                hundredNines,
                maxPlusMaxExpected));

        // 27. Max length (100 nodes of 5) + Max length (100 nodes of 4) -> 100 nodes of 9 (no extra node)
        int[] hundredFives = new int[100];
        Arrays.fill(hundredFives, 5);
        int[] hundredFours = new int[100];
        Arrays.fill(hundredFours, 4);
        list.add(new TestCase(27, "Stress: Max Length", "100 nodes toàn 5 + 100 nodes toàn 4 -> 100 nodes toàn 9 (Không dư nhớ)",
                hundredFives,
                hundredFours,
                hundredNines));

        return list;
    }
}
