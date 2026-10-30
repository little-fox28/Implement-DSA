package Leetcode.easy.LongestCommonPrefix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Senior Java Tester & Senior Developer Test Suite for LeetCode #14: Longest Common Prefix.
 *
 * Test Design Methodology:
 * 1. Equivalence Partitioning (EP):
 *    - EP-1: Partial common prefix matches (e.g., "fl", "inters", "c").
 *    - EP-2: Zero common prefix (disjoint strings, different first character).
 *    - EP-3: Full string matches (all strings identical, or one is complete prefix of others).
 *    - EP-4: Uniform repeated characters (e.g., "aaaa", "aa").
 *    - EP-5: Relative position of shortest string (first, middle, last).
 *
 * 2. Boundary Value Analysis (BVA):
 *    - Array size constraints (1 <= strs.length <= 200):
 *      * Minimum array size: strs.length = 1 (single element array).
 *      * Small array: strs.length = 2.
 *      * Upper boundary: strs.length = 200.
 *    - String length constraints (0 <= strs[i].length <= 200):
 *      * Minimum string length: strs[i].length = 0 (empty string "").
 *      * Single character length: strs[i].length = 1 ("a").
 *      * Maximum string length: strs[i].length = 200 characters.
 *
 * 3. Pitfalls & Tricky Traps (Edge Cases):
 *    - The "Substring vs Prefix" trap (e.g., ["reflower", "flow", "flight"] -> "").
 *    - Index out of bounds trap: first string longer than subsequent strings (e.g., ["ab", "a"]).
 *    - Trailing empty string after valid prefixes (e.g., ["flower", "flow", ""]).
 *    - Suffix matching but prefix mismatching (e.g., ["c", "acc", "ccc"]).
 */
public class Test {

    /**
         * TestCase data model representing an isolated test scenario.
         */
        record TestCase(int id, String category, String description, String[] input, String expected) {
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TestCase> testCases = buildTestCases();

        System.out.println("==========================================================================================================");
        System.out.println("                     TEST RUNNER: LEETCODE #14 - LONGEST COMMON PREFIX                                    ");
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
     * Executes a single test case with microsecond timing and failure diagnostics.
     */
    private static boolean executeTestCase(Solution solution, TestCase tc) {
        String actual;
        long start = System.nanoTime();

        try {
            actual = solution.longestCommonPrefix(tc.input);
        } catch (Exception e) {
            long elapsedUs = (System.nanoTime() - start) / 1000;
            System.out.printf("[FAIL] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input    : %s%n", formatInputForDisplay(tc.input));
            System.out.printf("       Exception: %s - %s%n", e.getClass().getSimpleName(), e.getMessage());
            System.out.printf("       Duration : %d us%n%n", elapsedUs);
            return false;
        }

        long elapsedUs = (System.nanoTime() - start) / 1000;
        boolean isPassed = (tc.expected == null && actual == null) || (tc.expected != null && tc.expected.equals(actual));

        if (isPassed) {
            System.out.printf("[PASS] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-25s | Expected: \"%s\" | Actual: \"%s\" | Time: %d us%n%n",
                    formatInputForDisplay(tc.input), tc.expected, actual, elapsedUs);
            return true;
        } else {
            System.out.printf("[FAIL] #%02d | %-20s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-25s | Expected: \"%s\" | Actual: \"%s\" | Time: %d us%n%n",
                    formatInputForDisplay(tc.input), tc.expected, actual, elapsedUs);
            return false;
        }
    }

    /**
     * Formats the input array for clean console printing, truncating very long lists.
     */
    private static String formatInputForDisplay(String[] input) {
        if (input == null) return "null";
        if (input.length <= 4) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < input.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append("\"").append(truncateString(input[i], 15)).append("\"");
            }
            sb.append("]");
            return sb.toString();
        } else {
            return String.format("[\"%s\", \"%s\", ... %d items total]",
                    truncateString(input[0], 12), truncateString(input[1], 12), input.length);
        }
    }

    private static String truncateString(String s, int maxLen) {
        if (s == null) return "null";
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen) + "...";
    }

    /**
     * Constructs comprehensive test cases categorized across all quality dimensions.
     */
    private static List<TestCase> buildTestCases() {
        List<TestCase> list = new ArrayList<>();

        // =========================================================================
        // GROUP 1: LEETCODE STANDARD EXAMPLES
        // =========================================================================
        list.add(new TestCase(1, "LeetCode Examples", "Example 1: Khớp tiền tố 2 ký tự ('fl')",
                new String[]{"flower", "flow", "flight"}, "fl"));
        list.add(new TestCase(2, "LeetCode Examples", "Example 2: Không có tiền tố chung ('')",
                new String[]{"dog", "racecar", "car"}, ""));

        // =========================================================================
        // GROUP 2: BOUNDARY VALUE ANALYSIS (Array Length: strs.length)
        // =========================================================================
        list.add(new TestCase(3, "BVA: Array Length", "strs.length = 1: Mảng có đúng 1 phần tử ký tự đơn ('a')",
                new String[]{"a"}, "a"));
        list.add(new TestCase(4, "BVA: Array Length", "strs.length = 1: Mảng có 1 phần tử chuỗi dài ('developer')",
                new String[]{"developer"}, "developer"));
        list.add(new TestCase(5, "BVA: Array Length", "strs.length = 1: Mảng có 1 phần tử là chuỗi rỗng ('')",
                new String[]{""}, ""));
        list.add(new TestCase(6, "BVA: Array Length", "strs.length = 2: Hai chuỗi khớp hoàn toàn ('apple', 'apple')",
                new String[]{"apple", "apple"}, "apple"));
        list.add(new TestCase(7, "BVA: Array Length", "strs.length = 2: Hai chuỗi khớp 1 phần ('interspecies', 'interstellar')",
                new String[]{"interspecies", "interstellar"}, "inters"));
        list.add(new TestCase(8, "BVA: Array Length", "strs.length = 2: Hai chuỗi không khớp ký tự đầu ('alpha', 'beta')",
                new String[]{"alpha", "beta"}, ""));

        // =========================================================================
        // GROUP 3: BOUNDARY VALUE ANALYSIS (String Length: strs[i].length)
        // =========================================================================
        list.add(new TestCase(9, "BVA: String Length", "Chuỗi rỗng ở vị trí đầu tiên (strs[0] = '')",
                new String[]{"", "b"}, ""));
        list.add(new TestCase(10, "BVA: String Length", "Chuỗi rỗng ở vị trí giữa mảng",
                new String[]{"flower", "", "flight"}, ""));
        list.add(new TestCase(11, "BVA: String Length", "Chuỗi rỗng ở vị trí cuối cùng",
                new String[]{"flower", "flow", ""}, ""));
        list.add(new TestCase(12, "BVA: String Length", "Tất cả các phần tử đều là chuỗi rỗng",
                new String[]{"", "", ""}, ""));
        list.add(new TestCase(13, "BVA: String Length", "Độ dài 1 ký tự: Tất cả phần tử đều giống nhau ('c')",
                new String[]{"c", "c", "c"}, "c"));
        list.add(new TestCase(14, "BVA: String Length", "Độ dài 1 ký tự: Các phần tử khác nhau hoàn toàn",
                new String[]{"a", "b", "c"}, ""));
        list.add(new TestCase(15, "BVA: String Length", "Ký tự đơn khớp với tiền tố của các chuỗi dài hơn ('a', 'ab', 'abc')",
                new String[]{"a", "ab", "abc"}, "a"));
        list.add(new TestCase(16, "BVA: String Length", "Ký tự đơn khác biệt hoàn toàn với chuỗi dài ('z', 'abc', 'abd')",
                new String[]{"z", "abc", "abd"}, ""));

        // =========================================================================
        // GROUP 4: SUBSTRING VS PREFIX TRAPS & PITFALLS
        // =========================================================================
        list.add(new TestCase(17, "Traps & Edge Cases", "Bẫy Substring: 'flow' là con của 'reflower' nhưng không phải prefix",
                new String[]{"reflower", "flow", "flight"}, ""));
        list.add(new TestCase(18, "Traps & Edge Cases", "Bẫy IndexOutOfBounds: Chuỗi đầu tiên dài hơn chuỗi thứ hai ('ab', 'a')",
                new String[]{"ab", "a"}, "a"));
        list.add(new TestCase(19, "Traps & Edge Cases", "Bẫy IndexOutOfBounds: Chuỗi giảm dần độ dài ('abcd', 'abc', 'ab', 'a')",
                new String[]{"abcd", "abc", "ab", "a"}, "a"));
        list.add(new TestCase(20, "Traps & Edge Cases", "Bẫy thu hẹp tiền tố ở phần tử thứ 3 ('throne', 'throne', 'th')",
                new String[]{"throne", "throne", "th"}, "th"));
        list.add(new TestCase(21, "Traps & Edge Cases", "Bẫy vị trí ký tự: 'c' ở index 0 của strs[0] nhưng ở index khác ở strs[1]",
                new String[]{"c", "acc", "ccc"}, ""));
        list.add(new TestCase(22, "Traps & Edge Cases", "2 phần tử đầu khớp dài, phần tử thứ 3 khác ngay ký tự đầu",
                new String[]{"interspecies", "interstellar", "galaxy"}, ""));

        // =========================================================================
        // GROUP 5: EQUIVALENCE PARTITIONING (Ordering & Patterns)
        // =========================================================================
        list.add(new TestCase(23, "Equivalence Partition", "Chuỗi ngắn nhất nằm ở đầu danh sách",
                new String[]{"sun", "sunny", "sunlight"}, "sun"));
        list.add(new TestCase(24, "Equivalence Partition", "Chuỗi ngắn nhất nằm ở cuối danh sách",
                new String[]{"sunlight", "sunny", "sun"}, "sun"));
        list.add(new TestCase(25, "Equivalence Partition", "Chuỗi ngắn nhất nằm ở giữa danh sách",
                new String[]{"sunny", "sun", "sunlight"}, "sun"));
        list.add(new TestCase(26, "Equivalence Partition", "Tất cả các chuỗi trong mảng hoàn toàn giống hệt nhau",
                new String[]{"system", "system", "system"}, "system"));
        list.add(new TestCase(27, "Equivalence Partition", "Tiền tố chỉ dài đúng 1 ký tự duy nhất ('c')",
                new String[]{"cat", "car", "cow", "cup"}, "c"));
        list.add(new TestCase(28, "Equivalence Partition", "Chuỗi lặp lại cùng một ký tự với độ dài khác nhau ('aa')",
                new String[]{"aaaa", "aa", "aaaaa"}, "aa"));
        list.add(new TestCase(29, "Equivalence Partition", "Tiền tố dài gần bằng toàn bộ chuỗi ('project')",
                new String[]{"projector", "projection", "projects"}, "project"));

        // =========================================================================
        // GROUP 6: CONSTRAINTS & STRESS TESTING (Upper Boundaries: 200 items, 200 chars)
        // =========================================================================
        // 30. Max string length (200 characters) - Khớp 200 ký tự hoàn hảo
        char[] chars200 = new char[200];
        Arrays.fill(chars200, 'x');
        String str200 = new String(chars200);
        list.add(new TestCase(30, "Stress & Constraints", "Hai chuỗi độ dài cực đại (200 chars) khớp nhau 100%",
                new String[]{str200, str200}, str200));

        // 31. Max string length (200 characters) - Khớp 199 ký tự, lệch ở ký tự cuối cùng
        char[] chars200Diff = new char[200];
        Arrays.fill(chars200Diff, 'x');
        chars200Diff[199] = 'y';
        String str200Diff = new String(chars200Diff);
        list.add(new TestCase(31, "Stress & Constraints", "Hai chuỗi 200 chars lệch ở ký tự thứ 200 (kết quả khớp 199 chars)",
                new String[]{str200, str200Diff}, str200.substring(0, 199)));

        // 32. Max array length (200 elements) - 200 chuỗi giống hệt nhau
        String[] maxArray = new String[200];
        Arrays.fill(maxArray, "algorithm");
        list.add(new TestCase(32, "Stress & Constraints", "Mảng kích thước cực đại (200 phần tử) cùng chung tiền tố 'algorithm'",
                maxArray, "algorithm"));

        // 33. Max array length (200 elements) - Phần tử thứ 200 là chuỗi rỗng
        String[] maxArrayWithEmpty = new String[200];
        Arrays.fill(maxArrayWithEmpty, "prefix");
        maxArrayWithEmpty[199] = "";
        list.add(new TestCase(33, "Stress & Constraints", "Mảng 200 phần tử nhưng phần tử cuối cùng là chuỗi rỗng ('')",
                maxArrayWithEmpty, ""));

        // 34. Max array length (200 elements) - Phần tử thứ 200 khác ký tự đầu tiên
        String[] maxArrayWithMismatch = new String[200];
        Arrays.fill(maxArrayWithMismatch, "constant");
        maxArrayWithMismatch[199] = "variable";
        list.add(new TestCase(34, "Stress & Constraints", "Mảng 200 phần tử nhưng phần tử cuối cùng lệch tiền tố ('')",
                maxArrayWithMismatch, ""));

        return list;
    }
}
