# Phân Tích Nghiệp Vụ (BA) & Thiết Kế Kiểm Thử (QA) - Palindrome Number

Tài liệu đặc tả yêu cầu nghiệp vụ và chiến lược kiểm thử cho bài toán **LeetCode #9: Palindrome Number**, được quản lý trong gói `Leetcode.Problems.easy.PalindromeNumber`.

---

## 1. Tổng Quan Bài Toán (Problem Overview)

- **Mã bài toán:** LeetCode #9
- **Độ khó:** Easy
- **Tên bài toán:** Palindrome Number (Số đối xứng)
- **Định nghĩa nghiệp vụ:** 
  Một số nguyên được coi là **Palindrome** (đối xứng) nếu nó đọc xuôi (từ trái sang phải) và đọc ngược (từ phải sang trái) đều cho cùng một dãy ký tự số.
- **Mục tiêu hệ thống:** 
  Cung cấp hàm xác thực `isPalindrome(int x)` trả về kết quả `boolean` với hiệu năng thời gian tối ưu $\mathcal{O}(\log_{10} n)$ và bộ nhớ $\mathcal{O}(1)$.

---

## 2. Đặc Tả Yêu Cầu Nghiệp Vụ (Business Requirements Specification - BRD)

### 2.1. Yêu Cầu Chức Năng (Functional Requirements - FR)

| Mã Yêu Cầu | Tên Yêu Cầu | Chi Tiết Nghiệp Vụ | Tiêu Chí Nghiệm Thu (Acceptance Criteria) |
| :--- | :--- | :--- | :--- |
| **FR-01** | Xác thực số âm | Tất cả các số nguyên âm đều **không** phải là palindrome do có ký tự dấu trừ `-` đứng đầu (đọc ngược lại dấu trừ sẽ nằm ở cuối). | `x < 0` $\rightarrow$ Trả về `false`. Ví dụ: `-121` $\rightarrow$ ngược lại là `121-` $\ne$ `-121`. |
| **FR-02** | Xác thực số 0 | Số `0` đọc xuôi hay ngược đều là chính nó. | `x == 0` $\rightarrow$ Trả về `true`. |
| **FR-03** | Xác thực số tròn chục | Mọi số dương kết thúc bằng chữ số `0` (trừ chính số 0) đều **không** phải là palindrome (vì số nguyên chuẩn không có số 0 ở đầu). | `x > 0 && x % 10 == 0` $\rightarrow$ Trả về `false`. Ví dụ: `10` $\rightarrow$ đọc ngược là `01` = `1` $\ne$ `10`. |
| **FR-04** | Xác thực số 1 chữ số | Mọi số nguyên dương trong khoảng $[1, 9]$ đều là palindrome. | `1 <= x <= 9` $\rightarrow$ Trả về `true`. |
| **FR-05** | Xác thực số nhiều chữ số | Kiểm tra tính đối xứng của dãy số từ chữ số ngoài cùng vào tâm đối xứng. | Trả về `true` nếu toàn bộ cặp chữ số đối xứng trùng khớp, `false` nếu phát hiện bất kỳ cặp chữ số nào sai lệch. |

### 2.2. Yêu Cầu Phi Chức Năng (Non-Functional Requirements - NFR)

- **NFR-01 (Thời gian xử lý - Time Complexity):**
  - Thuật toán phải hoàn thành với độ phức tạp tối đa $\mathcal{O}(\log_{10}(n))$ tương ứng với số lượng chữ số của $x$ ($n \le 2^{31} - 1 \implies$ tối đa 10 chữ số).
- **NFR-02 (Bộ nhớ tối ưu - Space Complexity):**
  - Đạt $\mathcal{O}(1)$ bộ nhớ phụ, không cấp phát mảng, collection hoặc tạo đối tượng phụ trên heap.
- **NFR-03 (Tránh chuyển đổi chuỗi - Follow-up Requirement):**
  - **Không** sử dụng chuyển đổi sang chuỗi (`String.valueOf(x)` hoặc `Integer.toString(x)`).
  - *Lý do kỹ thuật:* Thao tác tạo `String` gây tốn bộ nhớ heap và tăng tải cho bộ thu dọn rác (Garbage Collector) khi thực thi tần suất cao trong hệ thống lớn.
- **NFR-04 (An toàn tràn số - Overflow Safety):**
  - Thuật toán không được gây lỗi tràn số nguyên 32-bit có dấu (`Integer.MAX_VALUE = 2,147,483,647`).

---

## 3. Câu Hỏi Mở & Điểm Cần Làm Rõ (Open Questions - BA Perspective)

Dưới góc nhìn của Senior Business Analyst, đây là các câu hỏi mở cần thảo luận với Product Owner, Architect hoặc Khách hàng khi tích hợp giải thuật này vào hệ thống thực tế:

| STT | Câu Hỏi Mở (Open Question) | Ngữ Cảnh Kỹ Thuật & Tác Động | Quyết Định Đề Xuất (Recommendation) |
| :---: | :--- | :--- | :--- |
| **1** | **Hệ số (Radix/Base) có cố định là cơ số 10 (Decimal) không?** | Trong các bài toán tài chính, viễn thông hoặc mật mã học, palindrome có thể cần kiểm tra trên hệ nhị phân (Binary - Base 2), bát phân (Base 8), hoặc thập lục phân (Hex - Base 16). | Hiện tại cố định cơ số 10 theo đề bài LeetCode. Thiết kế kiến trúc dạng interface mở nếu cần hỗ trợ `isPalindrome(int x, int radix)`. |
| **2** | **Cách xử lý khi đảo ngược toàn bộ số gây tràn số nguyên (Integer Overflow)?** | Nếu đảo ngược toàn bộ số `x` (ví dụ `x = 2,147,483,647` $\implies$ đảo ngược là `7,463,847,412` vượt quá `2^31 - 1`), biến chứa kết quả sẽ bị tràn số nếu dùng kiểu `int`. | **Đề xuất kỹ thuật:** Chỉ đảo ngược một nửa số (Revert Half of the Number). Khi phần số đảo ngược lớn hơn hoặc bằng phần còn lại, dừng lại và so sánh. Cách này triệt tiêu hoàn toàn rủi ro tràn số. |
| **3** | **Tại sao các số kết thúc bằng 0 như `10`, `100` không được xem là đối xứng?** | Về mặt số học chuẩn (Canonical form), không tồn tại số nguyên dạng `01` hay `001`. Do đó `10` đảo ngược là `01` $\equiv$ `1` $\ne$ `10`. | Định nghĩa chuẩn xác nhận: Ngoại trừ chính số `0`, mọi số chia hết cho `10` đều là `false`. |
| **4** | **Phạm vi kiểu dữ liệu đầu vào có mở rộng lên `long` (64-bit) hay `BigInteger` không?** | Nếu hệ thống tiếp nhận mã định danh (ID) hoặc mã vạch lớn hơn 10 chữ số, kiểu `int` sẽ không đủ sức chứa. | Phiên bản hiện tại phục vụ kiểu `int` (32-bit signed). Đề xuất làm rõ với PO để tạo overload method `isPalindrome(long x)` nếu quy mô dữ liệu mở rộng. |

---

## 4. Chiến Lược & Ma Trận Kiểm Thử (Senior Java Tester Perspective)

Chiến lược kiểm thử áp dụng kết hợp 2 kỹ thuật Black-box Testing tiêu chuẩn quốc tế:
1. **Phân Vùng Tương Đương (Equivalence Partitioning - EP)**
2. **Phân Tích Giá Trị Biên (Boundary Value Analysis - BVA)**

### 4.1. Bảng Phân Vùng Tương Đương (Equivalence Partitioning)

| Phân Vùng (Partition) | Mô Tả Phân Vùng | Kỳ Vọng (Expected) | Giá Trị Đại Diện Kiểm Thử |
| :--- | :--- | :---: | :--- |
| **EP-01 (Valid)** | Số 0 (Zero base case) | `true` | `0` |
| **EP-02 (Valid)** | Số dương 1 chữ số $[1..9]$ | `true` | `7`, `9` |
| **EP-03 (Invalid)** | Số nguyên âm có dấu `'-'` | `false` | `-1`, `-121`, `-10`, `Integer.MIN_VALUE` |
| **EP-04 (Invalid)** | Số dương chia hết cho 10 ($x > 0$ và $x \pmod{10} = 0$) | `false` | `10`, `100`, `10000`, `21120` |
| **EP-05 (Valid)** | Số đối xứng độ dài lẻ (3, 5, 7 chữ số) | `true` | `121`, `313`, `12321`, `9876789` |
| **EP-06 (Valid)** | Số đối xứng độ dài chẵn (2, 4, 6 chữ số) | `true` | `11`, `88`, `1221`, `123321`, `999999` |
| **EP-07 (Invalid)** | Số không đối xứng (lệch chữ số biên hoặc ruột) | `false` | `12`, `123`, `1231`, `1234`, `1000021`, `123322` |
| **EP-08 (Invalid)** | Cận trên tràn số khi đảo ngược | `false` | `2147483647` (`Integer.MAX_VALUE`), `1534236469` |

### 4.2. Bảng Phân Tích Giá Trị Biên (Boundary Value Analysis)

| Điểm Biên | Giá Trị Cụ Thể | Kết Quả Mong Đợi | Ý Nghĩa Kiểm Thử |
| :--- | :--- | :---: | :--- |
| Biên dưới cùng kiểu `int` | `Integer.MIN_VALUE` ($-2^{31} = -2,147,483,648$) | `false` | Kiểm tra giới hạn âm tuyệt đối, không được ném Exception |
| Biên số âm gần 0 nhất | `-1` | `false` | Điểm chuyển giao giữa số âm và số 0 |
| Điểm gốc (Origin) | `0` | `true` | Điểm đối xứng tối thiểu |
| Biên số dương 1 chữ số | `1`, `9` | `true` | Cận dưới và cận trên của số dương 1 chữ số |
| Biên chuyển tiếp sang 2 chữ số | `10` | `false` | Số 2 chữ số đầu tiên (kết thúc bằng 0) |
| Số đối xứng 2 chữ số nhỏ nhất | `11` | `true` | Số đối xứng 2 chữ số đầu tiên |
| Số đối xứng lớn nhất trong 32-bit | `2,147,447,412` | `true` | Số đối xứng lớn nhất vẫn nằm trong phạm vi signed 32-bit int |
| Cận trên cùng kiểu `int` | `Integer.MAX_VALUE` ($2^{31} - 1 = 2,147,483,647$) | `false` | Bẫy tràn số: Nếu thuật toán đảo ngược nguyên số sẽ tràn thành số âm |

---

## 5. Hướng Dẫn Thực Thi Kiểm Thử (Test Execution & TDD Guide)

Bộ test case đã được hiện thực hoàn chỉnh tại [`../TwoSum/Test.java`](Test.java).

### Cách Biên Dịch & Chạy Test:

Mở terminal tại thư mục gốc của dự án (`Implement-DSA`):

```bash
# 1. Biên dịch source code
javac -d "Java/bin" "Java/src/Leetcode/Problems/easy/PalindromeNumber/Solution.java" "Java/src/Leetcode/Problems/easy/PalindromeNumber/Test.java"

# 2. Chạy Test Runner
java -cp "Java/bin" PalindromeNumber.Leetcode.easy.Test
```

### Quy Trình TDD Đề Xuất Cho Lập Trình Viên:
1. **Bước 1 (Red):** Chạy `../TwoSum/Test.java` hiện tại $\rightarrow$ 13 test cases đối xứng sẽ FAILED (do `../TwoSum/Solution.java` đang trả về mặc định `false`).
2. **Bước 2 (Green):** Mở [`../TwoSum/Solution.java`](Solution.java) và cài đặt giải thuật (khuyến nghị giải thuật Revert Half Number để đạt $\mathcal{O} (\log_{10} n)$ và $\mathcal{O} (1)$ space).
3. **Bước 3 (Refactor & Verify):** Chạy lại `../TwoSum/Test.java` đến khi đạt tỷ lệ **100% Passed (30/30 Test Cases)** mà không vi phạm quy tắc ép chuỗi.
