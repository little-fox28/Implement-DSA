# Phân Tích Kỹ Thuật & Thiết Kế Kiểm Thử (QA) - Longest Common Prefix

Tài liệu đặc tả giải thuật và chiến lược kiểm thử tiêu chuẩn Senior Developer & Senior Tester cho bài toán **LeetCode #14: Longest Common Prefix**, thuộc package `Leetcode.easy.LongestCommonPrefix`.

---

## 1. Tổng Quan Bài Toán (Problem Overview)

- **Mã bài toán:** LeetCode #14
- **Độ khó:** Easy
- **Tên bài toán:** Longest Common Prefix (Tiền tố chung dài nhất)
- **Định nghĩa:**
  Cho một mảng các chuỗi `strs`. Tìm chuỗi tiền tố chung dài nhất bắt đầu từ vị trí index 0 của tất cả các chuỗi trong mảng.
  Nếu không có tiền tố chung, trả về chuỗi rỗng `""`.
- **Ràng buộc hệ thống (Constraints):**
  - $1 \le \text{strs.length} \le 200$
  - $0 \le \text{strs}[i].\text{length} \le 200$
  - `strs[i]` chỉ bao gồm các ký tự chữ cái tiếng Anh in thường (`a-z`) nếu không rỗng.

---

## 2. Đặc Tả Nghiệp Vụ & Yêu Cầu Kỹ Thuật (Specification)

### 2.1. Yêu Cầu Chức Năng (Functional Requirements - FR)

| Mã FR | Tên Yêu Cầu | Chi Tiết Nghiệp Vụ | Tiêu Chí Nghiệm Thu (Acceptance Criteria) |
| :--- | :--- | :--- | :--- |
| **FR-01** | Tiền tố khớp một phần | Khi tất cả chuỗi có $k$ ký tự đầu giống nhau. | Trả về chuỗi con độ dài $k$. Ví dụ: `["flower","flow","flight"]` $\rightarrow$ `"fl"`. |
| **FR-02** | Không có tiền tố chung | Ký tự đầu tiên giữa các chuỗi khác nhau hoặc có chuỗi rỗng `""`. | Trả về `""`. Ví dụ: `["dog","racecar","car"]` $\rightarrow$ `""`. |
| **FR-03** | Mảng chỉ có 1 phần tử | Khi `strs.length == 1`, tiền tố chung chính là chuỗi đó. | Trả về `strs[0]`. Ví dụ: `["flower"]` $\rightarrow$ `"flower"`, `[""]` $\rightarrow$ `""`. |
| **FR-04** | Một chuỗi là tiền tố của chuỗi khác | Khi có một chuỗi ngắn hơn là tiền tố hoàn toàn của các chuỗi dài hơn. | Trả về chuỗi ngắn nhất đó. Ví dụ: `["ab", "abc"]` $\rightarrow$ `"ab"`. |
| **FR-05** | Chuỗi rỗng trong mảng | Nếu bất kỳ chuỗi nào trong mảng là `""` (độ dài 0). | Trả về ngay lập tức `""`. |

### 2.2. Yêu Cầu Phi Chức Năng (Non-Functional Requirements - NFR)

- **NFR-01 (Thời gian xử lý - Time Complexity):**
  - Tối ưu $\mathcal{O}(S)$ trong trường hợp xấu nhất ($S$ là tổng số ký tự của tất cả các chuỗi).
  - Đạt $\mathcal{O}(N)$ thời gian tốt nhất khi phát hiện sai khác ngay ký tự đầu tiên (`index 0`).
- **NFR-02 (Bộ nhớ - Space Complexity):**
  - Bộ nhớ phụ $\mathcal{O}(1)$ ngoài chuỗi kết quả được trả về. Tránh cấp phát chuỗi trung gian liên tục gây áp lực lên GC (Garbage Collector).
- **NFR-03 (An toàn bộ nhớ & ngoại lệ - Exception Safety):**
  - Không bao giờ phát sinh `StringIndexOutOfBoundsException` hay `NullPointerException`.
  - Luôn kiểm tra biên độ dài của từng chuỗi trước khi truy xuất `charAt(i)`.

---

## 3. Phân Tích & Lựa Chọn Thuật Toán (Senior Developer Perspective)

Có 4 hướng tiếp cận phổ biến cho bài toán này:

| Giải thuật | Time Complexity (Worst) | Time Complexity (Best) | Space | Ưu / Nhược điểm trong thực tế |
| :--- | :---: | :---: | :---: | :--- |
| **1. Horizontal Scanning** | $\mathcal{O}(S)$ | $\mathcal{O}(S)$ | $\mathcal{O}(1)$ | Dễ viết (`indexOf`), nhưng phải so sánh lần lượt từng cặp, sinh nhiều chuỗi rác (`substring`) nếu tiền tố thu hẹp dần. |
| **2. Vertical Scanning** *(Khuyên dùng)* | $\mathcal{O}(S)$ | $\mathcal{O}(N)$ | $\mathcal{O}(1)$ | **Tối ưu nhất**: So sánh từng cột ký tự. Dừng ngay lập tức khi phát hiện lệch ký tự hoặc chạm độ dài chuỗi ngắn nhất. |
| **3. Divide and Conquer** | $\mathcal{O}(S)$ | $\mathcal{O}(S)$ | $\mathcal{O}(m \log N)$ | Chia để trị tốn bộ nhớ ngăn xếp đệ quy $\mathcal{O}(\log N)$, không cần thiết với $N \le 200$. |
| **4. Binary Search** | $\mathcal{O}(S \cdot \log(\min L))$ | $\mathcal{O}(S \cdot \log(\min L))$ | $\mathcal{O}(1)$ | Tìm kiếm nhị phân trên độ dài tiền tố. Phức tạp hóa bài toán và chậm hơn Vertical Scanning khi có mismatch sớm. |

> **Quyết định kiến trúc:** Sử dụng **Vertical Scanning (Quét dọc theo cột)** vì tính gọn gàng, hiệu quả bộ nhớ tối đa và khả năng ngắt sớm (Early Exit) ngay tại cột đầu tiên.

---

## 4. Chiến Lược Thiết Kế Test Case (Senior Tester Perspective)

Bộ kiểm thử áp dụng các phương pháp Black-box Testing chuẩn ISTQB:

### 4.1. Equivalence Partitioning (Phân Vùng Tương Đương - EP)
1. **Khớp tiền tố một phần thông thường:** Độ dài tiền tố từ 1 đến $L-1$.
2. **Khớp toàn phần (Full Match):** Tất cả các chuỗi giống hệt nhau hoặc chuỗi ngắn nhất là prefix của tất cả các chuỗi còn lại.
3. **Hoàn toàn không khớp (Zero Match):** Ký tự đầu tiên khác nhau hoàn toàn.
4. **Vị trí chuỗi ngắn nhất:** Nằm ở đầu mảng (`index 0`), giữa mảng, hoặc cuối mảng.
5. **Chuỗi lặp lại đồng nhất ký tự:** Chuỗi chỉ gồm 1 ký tự lặp lại nhiều lần với độ dài khác nhau.

### 4.2. Boundary Value Analysis (Phân Tích Giá Trị Biên - BVA)
- **Biên kích thước mảng (`strs.length`):**
  - Biên dưới cực tiểu: `strs.length = 1`.
  - Biên nhỏ: `strs.length = 2`.
  - Biên trên cực đại: `strs.length = 200`.
- **Biên độ dài chuỗi (`strs[i].length`):**
  - Biên dưới cực tiểu: Chuỗi rỗng `""` (độ dài 0) ở vị trí đầu, giữa, cuối.
  - Chuỗi đơn ký tự: Độ dài 1 (`"a"`, `"c"`).
  - Biên trên cực đại: Chuỗi dài tối đa 200 ký tự.

### 4.3. Error Guessing & Traps (Bẫy Kỹ Thuật Cần Đề Phòng)
- **Bẫy Substring:** Chuỗi con nằm ở giữa chứ không phải ở đầu (ví dụ: `"flow"` nằm trong `"reflower"`, nhưng tiền tố phải bắt đầu từ `index 0`).
- **Bẫy IndexOutOfBounds:** Chuỗi `strs[0]` dài hơn các chuỗi đằng sau (ví dụ: `["ab", "a"]`).
- **Bẫy thu hẹp tiền tố:** Hai chuỗi đầu khớp rất dài, nhưng chuỗi thứ ba chỉ khớp 2 ký tự (ví dụ: `["throne", "throne", "th"]`).

---

## 5. Ma Trận Test Cases Chi Tiết (34 Test Cases)

| ID | Nhóm Kiểm Thử | Tên Kịch Bản / Mô Tả | Input | Expected Output | Mục Đích Kiểm Thử |
| :---: | :--- | :--- | :--- | :---: | :--- |
| **01** | LeetCode Examples | Khớp tiền tố 2 ký tự | `["flower","flow","flight"]` | `"fl"` | Kiểm thử ví dụ 1 chuẩn LeetCode. |
| **02** | LeetCode Examples | Không có tiền tố chung | `["dog","racecar","car"]` | `""` | Kiểm thử ví dụ 2 chuẩn LeetCode. |
| **03** | BVA: Array Length | Mảng 1 phần tử ký tự đơn | `["a"]` | `"a"` | Kiểm tra biên dưới `strs.length = 1`. |
| **04** | BVA: Array Length | Mảng 1 phần tử chuỗi dài | `["developer"]` | `"developer"` | Trả về chính chuỗi đó khi mảng chỉ có 1 phần tử. |
| **05** | BVA: Array Length | Mảng 1 phần tử là chuỗi rỗng | `[""]` | `""` | Biên kết hợp `strs.length = 1` và `length = 0`. |
| **06** | BVA: Array Length | Mảng 2 phần tử giống nhau | `["apple", "apple"]` | `"apple"` | Kiểm tra mảng nhỏ 2 phần tử khớp 100%. |
| **07** | BVA: Array Length | Mảng 2 phần tử khớp 1 phần | `["interspecies", "interstellar"]` | `"inters"` | Tiền tố chung giữa 2 chuỗi dài. |
| **08** | BVA: Array Length | Mảng 2 phần tử không khớp | `["alpha", "beta"]` | `""` | Trả về rỗng ngay tại ký tự đầu tiên. |
| **09** | BVA: String Length | Chuỗi rỗng ở vị trí đầu | `["", "b"]` | `""` | `strs[0]` là chuỗi rỗng $\implies$ dừng ngay lập tức. |
| **10** | BVA: String Length | Chuỗi rỗng ở vị trí giữa | `["flower", "", "flight"]` | `""` | Ngăn ngừa ngoại lệ khi duyệt qua chuỗi rỗng. |
| **11** | BVA: String Length | Chuỗi rỗng ở vị trí cuối | `["flower", "flow", ""]` | `""` | Xóa bỏ tiền tố tạm thời về `""` do phần tử cuối rỗng. |
| **12** | BVA: String Length | Toàn bộ mảng là chuỗi rỗng | `["", "", ""]` | `""` | Mảng chứa nhiều chuỗi rỗng. |
| **13** | BVA: String Length | Các phần tử đều là 1 ký tự giống nhau | `["c", "c", "c"]` | `"c"` | Độ dài 1 ký tự khớp hoàn toàn. |
| **14** | BVA: String Length | Các phần tử đều là 1 ký tự khác nhau | `["a", "b", "c"]` | `""` | Độ dài 1 ký tự không khớp. |
| **15** | BVA: String Length | Ký tự đơn là tiền tố chuỗi dài | `["a", "ab", "abc"]` | `"a"` | Chuỗi ngắn nhất có độ dài 1. |
| **16** | BVA: String Length | Ký tự đơn khác biệt hoàn toàn | `["z", "abc", "abd"]` | `""` | Chuỗi đầu có độ dài 1 và không khớp. |
| **17** | Traps & Edge Cases | Bẫy chuỗi con (Substring Trap) | `["reflower", "flow", "flight"]` | `""` | Phải kiểm tra từ index 0, không được nhầm với contains. |
| **18** | Traps & Edge Cases | Bẫy IndexOutOfBounds (strs[0] dài hơn) | `["ab", "a"]` | `"a"` | Chuỗi sau ngắn hơn chuỗi trước. |
| **19** | Traps & Edge Cases | Chuỗi giảm dần độ dài | `["abcd", "abc", "ab", "a"]` | `"a"` | Kiểm tra vòng lặp khi độ dài giảm dần liên tục. |
| **20** | Traps & Edge Cases | Bẫy co hẹp tiền tố ở phần tử thứ 3 | `["throne", "throne", "th"]` | `"th"` | Hai phần tử đầu giống hệt, phần tử 3 ngắn hơn. |
| **21** | Traps & Edge Cases | Ký tự xuất hiện ở vị trí khác index 0 | `["c", "acc", "ccc"]` | `""` | Ký tự 'c' có trong tất cả các chuỗi nhưng không ở index 0 của 'acc'. |
| **22** | Traps & Edge Cases | 2 chuỗi đầu khớp dài, chuỗi 3 khác đầu | `["interspecies", "interstellar", "galaxy"]` | `""` | Reset prefix về `""` khi gặp mismatch ở đầu. |
| **23** | Equivalence Partition | Chuỗi ngắn nhất nằm ở đầu danh sách | `["sun", "sunny", "sunlight"]` | `"sun"` | Prefix chính là `strs[0]`. |
| **24** | Equivalence Partition | Chuỗi ngắn nhất nằm ở cuối danh sách | `["sunlight", "sunny", "sun"]` | `"sun"` | Prefix chính là phần tử cuối. |
| **25** | Equivalence Partition | Chuỗi ngắn nhất nằm ở giữa danh sách | `["sunny", "sun", "sunlight"]` | `"sun"` | Prefix chính là phần tử giữa. |
| **26** | Equivalence Partition | Tất cả các chuỗi giống hệt nhau | `["system", "system", "system"]` | `"system"` | Kết quả là toàn bộ chuỗi. |
| **27** | Equivalence Partition | Tiền tố chỉ dài đúng 1 ký tự | `["cat", "car", "cow", "cup"]` | `"c"` | Khớp 1 ký tự đầu qua nhiều phần tử. |
| **28** | Equivalence Partition | Chuỗi lặp lại cùng một ký tự | `["aaaa", "aa", "aaaaa"]` | `"aa"` | Mẫu ký tự lặp đồng nhất. |
| **29** | Equivalence Partition | Tiền tố dài gần bằng toàn bộ từ | `["projector", "projection", "projects"]` | `"project"` | Khớp 7 ký tự trên từ 8-10 ký tự. |
| **30** | Stress & Constraints | Khớp 200 ký tự hoàn hảo | `[200 'x', 200 'x']` | `200 'x'` | Kiểm tra giới hạn chuỗi lớn nhất theo đề bài. |
| **31** | Stress & Constraints | 2 chuỗi 200 ký tự lệch ở ký tự thứ 200 | `[200 'x', 199 'x' + 'y']` | `199 'x'` | Kiểm tra tính chính xác tuyệt đối ở vị trí biên cuối cùng. |
| **32** | Stress & Constraints | Mảng 200 phần tử cùng tiền tố | `200 x ["algorithm"]` | `"algorithm"` | Kiểm tra giới hạn mảng lớn nhất theo đề bài. |
| **33** | Stress & Constraints | Mảng 200 phần tử, phần tử 200 là `""` | `[199 x "prefix", ""]` | `""` | Kiểm tra tính chịu tải khi phần tử cuối cùng phá hủy tiền tố. |
| **34** | Stress & Constraints | Mảng 200 phần tử, phần tử 200 lệch đầu | `[199 x "constant", "variable"]` | `""` | Đảm bảo xử lý chính xác khi phần tử cuối cùng gây lệch. |

---

## 6. Hướng Dẫn Biên Dịch & Chạy Kiểm Thử

### Cách 1: Chạy trực tiếp qua Terminal (Console Test Runner)
```bash
# Biên dịch Solution và Test Suite
javac -encoding UTF-8 -d Java/bin Java/src/Leetcode/easy/LongestCommonPrefix/Solution.java Java/src/Leetcode/easy/LongestCommonPrefix/Test.java

# Chạy Test Runner
java -cp Java/bin Leetcode.easy.LongestCommonPrefix.Test
```

### Cách 2: Chạy trong IntelliJ IDEA / Eclipse
- Mở file `Java/src/Leetcode/easy/LongestCommonPrefix/Test.java`.
- Nhấn tổ hợp phím `Shift + F10` hoặc nhấn chuột phải chọn **Run 'Test.main()'**.
