package Leetcode.easy.RomanToInteger;

import java.util.Arrays;
import java.util.List;

/**
 * Senior Java Tester Test Suite for LeetCode #13: Roman to Integer.
 *
 * Test Strategy:
 * 1. Equivalence Partitioning (EP):
 *    - Group 1: LeetCode Official Examples ("III", "LVIII", "MCMXCIV")
 *    - Group 2: Base 7 Roman Symbols ("I", "V", "X", "L", "C", "D", "M")
 *    - Group 3: 6 Special Subtraction Instances ("IV", "IX", "XL", "XC", "CD", "CM")
 *    - Group 4: Pure Additive Numerals (Descending or Equal Left-to-Right)
 *    - Group 5: Mixed Additive & Subtractive Numerals (Complex Combinations)
 * 2. Boundary Value Analysis (BVA):
 *    - Minimum valid Roman numeral: "I" (1), min length = 1
 *    - Maximum valid standard Roman numeral: "MMMCMXCIX" (3999)
 *    - Maximum string length (15 characters): "MMMDCCCLXXXVIII" (3888)
 */
public class Test {

    /**
     * TestCase model holding input, expected output, and metadata.
     */
    static class TestCase {
        final int id;
        final String category;
        final String description;
        final String input;
        final int expected;

        public TestCase(int id, String category, String description, String input, int expected) {
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
            // =========================================================================
            // GROUP 1: LEETCODE STANDARD EXAMPLES
            // =========================================================================
            new TestCase(1, "LeetCode Examples", "Example 1: Cộng lặp cơ bản (III = 1+1+1)", "III", 3),
            new TestCase(2, "LeetCode Examples", "Example 2: Cộng kết hợp nhiều ký hiệu (L=50, V=5, III=3)", "LVIII", 58),
            new TestCase(3, "LeetCode Examples", "Example 3: Kết hợp nhiều phép trừ (M=1000, CM=900, XC=90, IV=4)", "MCMXCIV", 1994),

            // =========================================================================
            // GROUP 2: 7 BASE ROMAN SYMBOLS (EP: Single Symbol)
            // =========================================================================
            new TestCase(4, "Base Symbols", "Ký hiệu cơ sở I (1) - Biên dưới nhỏ nhất", "I", 1),
            new TestCase(5, "Base Symbols", "Ký hiệu cơ sở V (5)", "V", 5),
            new TestCase(6, "Base Symbols", "Ký hiệu cơ sở X (10)", "X", 10),
            new TestCase(7, "Base Symbols", "Ký hiệu cơ sở L (50)", "L", 50),
            new TestCase(8, "Base Symbols", "Ký hiệu cơ sở C (100)", "C", 100),
            new TestCase(9, "Base Symbols", "Ký hiệu cơ sở D (500)", "D", 500),
            new TestCase(10, "Base Symbols", "Ký hiệu cơ sở M (1000)", "M", 1000),

            // =========================================================================
            // GROUP 3: 6 SPECIAL SUBTRACTION INSTANCES (EP: Subtractive Pairs)
            // =========================================================================
            new TestCase(11, "Subtraction Rules", "I đứng trước V tạo thành 4 (5 - 1)", "IV", 4),
            new TestCase(12, "Subtraction Rules", "I đứng trước X tạo thành 9 (10 - 1)", "IX", 9),
            new TestCase(13, "Subtraction Rules", "X đứng trước L tạo thành 40 (50 - 10)", "XL", 40),
            new TestCase(14, "Subtraction Rules", "X đứng trước C tạo thành 90 (100 - 10)", "XC", 90),
            new TestCase(15, "Subtraction Rules", "C đứng trước D tạo thành 400 (500 - 100)", "CD", 400),
            new TestCase(16, "Subtraction Rules", "C đứng trước M tạo thành 900 (1000 - 100)", "CM", 900),

            // =========================================================================
            // GROUP 4: PURE ADDITIVE CASES (Lớn đến nhỏ từ trái sang phải)
            // =========================================================================
            new TestCase(17, "Pure Additive", "Hai ký tự giống nhau (II = 2)", "II", 2),
            new TestCase(18, "Pure Additive", "Mười hai (XII = 10 + 2)", "XII", 12),
            new TestCase(19, "Pure Additive", "Hai mươi bảy (XXVII = 20 + 5 + 2)", "XXVII", 27),
            new TestCase(20, "Pure Additive", "Hội tụ đủ 7 ký hiệu giảm dần (MDCLXVI = 1000+500+100+50+10+5+1)", "MDCLXVI", 1666),
            new TestCase(21, "Pure Additive", "Số hàng nghìn tròn (MMM = 3000)", "MMM", 3000),

            // =========================================================================
            // GROUP 5: MIXED ADDITIVE & SUBTRACTIVE (Tổ hợp phức tạp)
            // =========================================================================
            new TestCase(22, "Mixed Complex", "Mười bốn (XIV = 10 + 4) - Tránh nhầm lẫn cộng trước trừ sau", "XIV", 14),
            new TestCase(23, "Mixed Complex", "Hai mươi chín (XXIX = 20 + 9)", "XXIX", 29),
            new TestCase(24, "Mixed Complex", "Bốn mươi bốn (XLIV = 40 + 4)", "XLIV", 44),
            new TestCase(25, "Mixed Complex", "Chín mươi chín (XCIX = 90 + 9)", "XCIX", 99),
            new TestCase(26, "Mixed Complex", "Bốn trăm bốn mươi bốn (CDXLIV = 400 + 40 + 4)", "CDXLIV", 444),
            new TestCase(27, "Mixed Complex", "Chín trăm chín mươi chín (CMXCIX = 900 + 90 + 9)", "CMXCIX", 999),
            new TestCase(28, "Mixed Complex", "Năm lịch sử 2026 (MMXXVI = 2000 + 20 + 6)", "MMXXVI", 2026),

            // =========================================================================
            // GROUP 6: BOUNDARY VALUE ANALYSIS (BVA: Max Value & Max Length)
            // =========================================================================
            new TestCase(29, "Boundary (BVA)", "Chuỗi dài nhất chuẩn 15 ký tự (MMMDCCCLXXXVIII = 3888)", "MMMDCCCLXXXVIII", 3888),
            new TestCase(30, "Boundary (BVA)", "Giá trị lớn nhất chuẩn La Mã [Max=3999] (MMMCMXCIX)", "MMMCMXCIX", 3999)
        );

        System.out.println("==================================================================================================");
        System.out.println("                        TEST RUNNER: LEETCODE #13 - ROMAN TO INTEGER                              ");
        System.out.println("==================================================================================================\n");

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

        System.out.println("==================================================================================================");
        System.out.println("                                          TEST SUMMARY                                            ");
        System.out.println("==================================================================================================");
        System.out.printf("Total Test Cases : %d%n", testCases.size());
        System.out.printf("PASSED           : %d%n", passed);
        System.out.printf("FAILED           : %d%n", failed);
        System.out.printf("Pass Rate        : %.2f%%%n", (passed * 100.0) / testCases.size());
        System.out.printf("Total Exec Time  : %.3f ms%n", totalTimeNs / 1_000_000.0);
        System.out.println("--------------------------------------------------------------------------------------------------");

        if (passed == testCases.size()) {
            System.out.println(">>> [SUCCESS] TẤT CẢ 30/30 TEST CASES ĐÃ PASS! THUẬT TOÁN HOÀN HẢO!");
        } else {
            System.out.println(">>> [NOTICE] CÒN TEST CASE FAILED. Hãy hoàn thiện hàm romanToInt(String s) trong Solution.java!");
        }
        System.out.println("==================================================================================================\n");
    }

    /**
     * Executes a single test case and prints detailed comparison result.
     */
    private static boolean executeTestCase(Solution solution, TestCase tc) {
        int actual;
        long start = System.nanoTime();

        try {
            actual = solution.romanToInt(tc.input);
        } catch (Exception e) {
            long elapsedUs = (System.nanoTime() - start) / 1000;
            System.out.printf("[FAIL] #%02d | %-18s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input    : \"%s\"%n", tc.input);
            System.out.printf("       Exception: %s - %s%n", e.getClass().getSimpleName(), e.getMessage());
            System.out.printf("       Duration : %d us%n%n", elapsedUs);
            return false;
        }

        long elapsedUs = (System.nanoTime() - start) / 1000;
        boolean isPassed = (actual == tc.expected);

        if (isPassed) {
            System.out.printf("[PASS] #%02d | %-18s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-17s | Expected: %-6d | Actual: %-6d | Time: %d us%n%n",
                    "\"" + tc.input + "\"", tc.expected, actual, elapsedUs);
            return true;
        } else {
            System.out.printf("[FAIL] #%02d | %-18s | %s%n", tc.id, tc.category, tc.description);
            System.out.printf("       Input: %-17s | Expected: %-6d | Actual: %-6d | Time: %d us%n%n",
                    "\"" + tc.input + "\"", tc.expected, actual, elapsedUs);
            return false;
        }
    }
}
