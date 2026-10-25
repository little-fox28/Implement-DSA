package Leetcode.easy.PalindromeNumber;

import java.util.Arrays;
import java.util.List;

/**
 * Senior Java Tester Test Suite for LeetCode #9: Palindrome Number.
 * 
 * Test Strategy:
 * 1. Equivalence Partitioning (EP):
 *    - Valid Palindromes: Single digit [0-9], odd length, even length.
 *    - Invalid Palindromes: Negative numbers, multiples of 10 (>0), asymmetric numbers.
 * 2. Boundary Value Analysis (BVA):
 *    - Lower limit: Integer.MIN_VALUE (-2^31 = -2,147,483,648)
 *    - Zero boundary: 0
 *    - Single digit boundaries: 0, 9
 *    - Largest 32-bit signed palindrome: 2,147,447,412
 *    - Upper limit: Integer.MAX_VALUE (2^31 - 1 = 2,147,483,647)
 * 3. Robustness & Overflow:
 *    - Numbers whose full reverse would exceed Integer.MAX_VALUE (e.g. 2,147,483,647 -> 7,463,847,412).
 */
public class Test {

    /**
     * TestCase data model representing an isolated test case with expected behavior.
     */
    static class TestCase {
        final int id;
        final String category;
        final String description;
        final int input;
        final boolean expected;

        public TestCase(int id, String category, String description, int input, boolean expected) {
            this.id = id;
            this.category = category;
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<TestCase> testCases = Arrays.asList(
            // ==========================================
            // GROUP 1: BASE CASES & SINGLE DIGIT (EP: [0..9])
            // ==========================================
            new TestCase(1, "Base / Single Digit", "Số 0 là số đối xứng cơ sở", 0, true),
            new TestCase(2, "Base / Single Digit", "Số dương đơn lẻ (1 chữ số: 7)", 7, true),
            new TestCase(3, "Base / Single Digit", "Số dương đơn lẻ lớn nhất (9)", 9, true),

            // ==========================================
            // GROUP 2: NEGATIVE NUMBERS (EP: x < 0)
            // ==========================================
            new TestCase(4, "Negative Numbers", "Số âm cơ bản (-1)", -1, false),
            new TestCase(5, "Negative Numbers", "Số âm đối xứng dạng số nhưng có dấu '-' (-121)", -121, false),
            new TestCase(6, "Negative Numbers", "Số âm tròn chục (-10)", -10, false),
            new TestCase(7, "Negative Numbers", "Giá trị nhỏ nhất của kiểu int 32-bit (Integer.MIN_VALUE)", Integer.MIN_VALUE, false),

            // ==========================================
            // GROUP 3: TRAILING ZEROS (EP: x > 0 && x % 10 == 0)
            // ==========================================
            new TestCase(8, "Trailing Zeros", "Số tròn chục nhỏ nhất (10)", 10, false),
            new TestCase(9, "Trailing Zeros", "Số tròn trăm (100)", 100, false),
            new TestCase(10, "Trailing Zeros", "Số nhiều số 0 ở cuối (10000)", 10000, false),
            new TestCase(11, "Trailing Zeros", "Thân số đối xứng nhưng có số 0 ở đuôi (21120)", 21120, false),

            // ==========================================
            // GROUP 4: ODD-LENGTH PALINDROMES
            // ==========================================
            new TestCase(12, "Odd Palindromes", "Số đối xứng 3 chữ số cơ bản (121)", 121, true),
            new TestCase(13, "Odd Palindromes", "Số đối xứng 3 chữ số khác (313)", 313, true),
            new TestCase(14, "Odd Palindromes", "Số đối xứng 5 chữ số có tâm (12321)", 12321, true),
            new TestCase(15, "Odd Palindromes", "Số đối xứng 7 chữ số (9876789)", 9876789, true),

            // ==========================================
            // GROUP 5: EVEN-LENGTH PALINDROMES
            // ==========================================
            new TestCase(16, "Even Palindromes", "Số đối xứng 2 chữ số nhỏ nhất (11)", 11, true),
            new TestCase(17, "Even Palindromes", "Số đối xứng 2 chữ số (88)", 88, true),
            new TestCase(18, "Even Palindromes", "Số đối xứng 4 chữ số (1221)", 1221, true),
            new TestCase(19, "Even Palindromes", "Số đối xứng 6 chữ số (123321)", 123321, true),
            new TestCase(20, "Even Palindromes", "Số toàn chữ số 9 chẵn (999999)", 999999, true),

            // ==========================================
            // GROUP 6: NON-PALINDROMES (ASYMMETRIC)
            // ==========================================
            new TestCase(21, "Non-Palindromes", "Số 2 chữ số không đối xứng (12)", 12, false),
            new TestCase(22, "Non-Palindromes", "Số 3 chữ số không đối xứng (123)", 123, false),
            new TestCase(23, "Non-Palindromes", "Lệch chữ số ở giữa (1231)", 1231, false),
            new TestCase(24, "Non-Palindromes", "Số 4 chữ số khác nhau hoàn toàn (1234)", 1234, false),
            new TestCase(25, "Non-Palindromes", "Đầu và cuối giống nhau nhưng ruột khác (1000021)", 1000021, false),
            new TestCase(26, "Non-Palindromes", "Gần đối xứng chỉ lệch 1 chữ số cuối (123322)", 123322, false),

            // ==========================================
            // GROUP 7: BOUNDARY VALUE ANALYSIS & OVERFLOW TRAPS
            // ==========================================
            new TestCase(27, "BVA & Overflow Trap", "Integer.MAX_VALUE (2147483647) - đảo ngược sẽ tràn 32-bit", Integer.MAX_VALUE, false),
            new TestCase(28, "BVA & Overflow Trap", "Số đối xứng lớn nhất vừa khít 32-bit signed int (2147447412)", 2147447412, true),
            new TestCase(29, "BVA & Overflow Trap", "Cận trên gần MAX_VALUE không đối xứng (2147483646)", 2147483646, false),
            new TestCase(30, "BVA & Overflow Trap", "Số lớn có đảo ngược vượt MAX_VALUE (1534236469)", 1534236469, false)
        );

        System.out.println("==========================================================================================");
        System.out.println("                   TEST RUNNER: LEETCODE #9 - PALINDROME NUMBER                           ");
        System.out.println("==========================================================================================\n");

        int passed = 0;
        int failed = 0;
        long totalExecutionTimeNs = 0;

        for (TestCase tc : testCases) {
            long startTime = System.nanoTime();
            boolean isPassed = executeTestCase(solution, tc);
            long durationNs = System.nanoTime() - startTime;
            totalExecutionTimeNs += durationNs;

            if (isPassed) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("==========================================================================================");
        System.out.println("                                      TEST SUMMARY                                        ");
        System.out.println("==========================================================================================");
        System.out.printf("Total Test Cases : %d%n", testCases.size());
        System.out.printf("PASSED           : %d%n", passed);
        System.out.printf("FAILED           : %d%n", failed);
        System.out.printf("Pass Rate        : %.2f%%%n", (passed * 100.0) / testCases.size());
        System.out.printf("Total Exec Time  : %.3f ms%n", totalExecutionTimeNs / 1_000_000.0);
        System.out.println("------------------------------------------------------------------------------------------");
    }

    /**
     * Executes a single test case safely with exception handling and performance capture.
     */
    private static boolean executeTestCase(Solution solution, TestCase tc) {
        boolean actual;
        long start = System.nanoTime();

        try {
            actual = solution.isPalindrome4ms(tc.input);
        } catch (Exception e) {
            long elapsedUs = (System.nanoTime() - start) / 1000;
            System.out.printf("[FAIL] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input    : %d%n", tc.input);
            System.out.printf("       Exception: %s - %s%n", e.getClass().getSimpleName(), e.getMessage());
            System.out.printf("       Duration : %d us%n%n", elapsedUs);
            return false;
        }

        long elapsedUs = (System.nanoTime() - start) / 1000;
        boolean matches = (actual == tc.expected);

        if (matches) {
            System.out.printf("[PASS] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-12d | Expected: %-5b | Actual: %-5b | Time: %d us%n%n",
                    tc.input, tc.expected, actual, elapsedUs);
            return true;
        } else {
            System.out.printf("[FAIL] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-12d | Expected: %-5b | Actual: %-5b | Time: %d us%n%n",
                    tc.input, tc.expected, actual, elapsedUs);
            return false;
        }
    }
}
