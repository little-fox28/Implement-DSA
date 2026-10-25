package Leetcode.easy.TwoSum;

import java.util.Arrays;
import java.util.List;

public class Test {

    /**
     * Lớp dữ liệu TestCase độc lập, chứa input và expected output chuẩn.
     */
    static class TestCase {
        final int id;
        final String description;
        final int[] nums;
        final int target;
        final int[] expected;

        public TestCase(int id, String description, int[] nums, int target, int[] expected) {
            this.id = id;
            this.description = description;
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Danh sách các Test Case độc lập với dữ liệu đầu vào và kết quả mong đợi chuẩn
        List<TestCase> testCases = Arrays.asList(
            new TestCase(1, "Ví dụ cơ bản 1", new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
            new TestCase(2, "Các phần tử nằm giữa và cuối", new int[]{3, 2, 4}, 6, new int[]{1, 2}),
            new TestCase(3, "Hai phần tử bằng nhau", new int[]{3, 3}, 6, new int[]{0, 1}),
            new TestCase(4, "Mảng chứa số âm", new int[]{-1, -2, -3, -4, -5}, -8, new int[]{2, 4}),
            new TestCase(5, "Mảng kết hợp số âm và dương", new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}),
            new TestCase(6, "Mảng chứa số 0", new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}),
            new TestCase(7, "Mảng tối thiểu 2 phần tử", new int[]{1, 5}, 6, new int[]{0, 1}),
            new TestCase(8, "Giá trị phần tử lớn", new int[]{1000000000, 500, 1000000000}, 2000000000, new int[]{0, 2})
        );

        System.out.println("========== BẮT ĐẦU CHẠY TEST CASE TWO SUM ==========\n");

        int passed = 0;
        int total = testCases.size();

        for (TestCase tc : testCases) {
            if (executeTestCase(solution, tc)) {
                passed++;
            }
        }

        System.out.println("===================================================");
        System.out.printf("TỔNG KẾT: %d/%d test cases PASSED\n", passed, total);
        if (passed == total) {
            System.out.println("-> TẤT CẢ TEST CASES ĐÃ VƯỢT QUA THÀNH CÔNG!");
        } else {
            System.out.println("-> CÓ TEST CASE BỊ LỖI, HÃY KIỂM TRA LẠI THUẬT TOÁN!");
        }
    }

    /**
     * Chạy độc lập từng test case, so sánh trực tiếp kết quả với Expected Output.
     */
    private static boolean executeTestCase(Solution solution, TestCase tc) {
        int[] inputCopy = tc.nums.clone(); // Tránh bị ảnh hưởng nếu thuật toán thay đổi mảng gốc
        int[] actual;

        try {
            actual = solution.hashMap(inputCopy, tc.target);
        } catch (Exception e) {
            System.out.printf("[TEST %d - FAILED] %s\n", tc.id, tc.description);
            System.out.printf("  Input    : nums = %s, target = %d\n", Arrays.toString(tc.nums), tc.target);
            System.out.printf("  Lỗi      : Ném ra ngoại lệ (%s: %s)\n\n", e.getClass().getSimpleName(), e.getMessage());
            return false;
        }

        // Kiểm tra độc lập hoàn toàn dựa trên Expected Output chuẩn
        boolean isPassed = isResultMatching(actual, tc.expected);

        if (isPassed) {
            System.out.printf("[TEST %d - PASSED] %s\n", tc.id, tc.description);
            System.out.printf("  Input    : nums = %s, target = %d\n", Arrays.toString(tc.nums), tc.target);
            System.out.printf("  Expected : %s\n", Arrays.toString(tc.expected));
            System.out.printf("  Actual   : %s\n\n", Arrays.toString(actual));
            return true;
        } else {
            System.out.printf("[TEST %d - FAILED] %s\n", tc.id, tc.description);
            System.out.printf("  Input    : nums = %s, target = %d\n", Arrays.toString(tc.nums), tc.target);
            System.out.printf("  Expected : %s\n", Arrays.toString(tc.expected));
            System.out.printf("  Actual   : %s\n\n", Arrays.toString(actual));
            return false;
        }
    }

    /**
     * So sánh actual với expected chuẩn (chấp nhận cả [i, j] hoặc [j, i]).
     */
    private static boolean isResultMatching(int[] actual, int[] expected) {
        if (actual == null || expected == null || actual.length != 2 || expected.length != 2) {
            return false;
        }
        return (actual[0] == expected[0] && actual[1] == expected[1]) ||
               (actual[0] == expected[1] && actual[1] == expected[0]);
    }
}

