# So Sánh Các Phương Pháp Giải Quyết Bài Toán Two Sum

Tài liệu phân tích và so sánh chi tiết 2 phương pháp giải bài toán **Two Sum** được triển khai trong [`Solution.java`](Solution.java):
1. **Brute Force (Vét cạn)**
2. **Hash Map (Bảng băm sử dụng `SeparateChainingHashTable`)**

---

## 1. Tổng Quan Bài Toán (Problem Overview)

- **Đầu vào (Input):**
  - Mảng số nguyên `nums`.
  - Một số nguyên `target`.
- **Đầu ra (Output):**
  - Mảng 2 phần tử `[index1, index2]` chứa chỉ số của 2 số sao cho: `nums[index1] + nums[index2] == target`.
  - Mỗi phần tử chỉ được sử dụng một lần (`index1 != index2`).

---

## 2. Phương Pháp 1: Brute Force (Vét Cạn)

### 2.1. Ý Tưởng & Cơ Chế Hoạt Động
- Sử dụng 2 vòng lặp lồng nhau:
  - Vòng lặp ngoài cố định phần tử thứ nhất tại chỉ số `i` ($0 \le i < n$).
  - Vòng lặp trong duyệt qua tất cả các phần tử đứng sau `i` tại chỉ số `j` ($i + 1 \le j < n$).
  - Tính phần bù cần tìm: `finder = target - nums[i]`. Nếu `nums[j] == finder`, ghi nhận cặp chỉ số `[i, j]`.

### 2.2. Đoạn Mã Triển Khai
```java
public int[] bruteForce(int[] nums, int target) {
    int[] result = new int[2];

    for (int i = 0; i < nums.length; i++) {
        int finder = target - nums[i];

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] == finder) {
                result[0] = i;
                result[1] = j;
            }
        }
    }
    return result;
}
```

### 2.3. Đánh Giá Độ Phức Tạp
- **Độ phức tạp thời gian (Time Complexity):** $\mathcal{O}(n^2)$
  - Số phép so sánh trong trường hợp xấu nhất là $\frac{n(n - 1)}{2} \approx \frac{n^2}{2}$.
  - Khi $n = 10^5$, số phép tính xấp xỉ $5 \times 10^9$, chắc chắn dẫn đến **Time Limit Exceeded (TLE)**.
- **Độ phức tạp không gian (Space Complexity):** $\mathcal{O}(1)$
  - Chỉ sử dụng một vài biến cục bộ (`i`, `j`, `finder`, `result`), không tốn thêm bộ nhớ theo kích thước dữ liệu đầu vào.

---

## 3. Phương Pháp 2: Hash Map (Bảng Băm với `SeparateChainingHashTable`)

### 3.1. Ý Tưởng & Cơ Chế Hoạt Động
- Áp dụng kỹ thuật **đánh đổi không gian lấy thời gian (Time-Space Trade-off)**.
- Sử dụng cấu trúc dữ liệu bảng băm [`SeparateChainingHashTable<Integer, Integer>`](../../../DSA/HashTable/SeparateChainingHashTable.java) để lưu trữ:
  - **Key:** Giá trị phần tử (`nums[i]`).
  - **Value:** Chỉ số của phần tử trong mảng (`i`).
- Duyệt qua mảng đúng **1 lần duy nhất (One-pass)**:
  - Tại mỗi phần tử `nums[i]`, tính phần bù: `finder = target - nums[i]`.
  - Dùng `has(finder)` để kiểm tra phần bù đã xuất hiện trước đó chưa:
    - **Nếu có:** Lấy chỉ số đã lưu bằng `get(finder)` và trả về ngay kết quả `new int[] {result.get(finder), i}`.
    - **Nếu chưa:** Chèn phần tử hiện tại vào bảng bằng `insert(nums[i], i)`.

### 3.2. Đoạn Mã Triển Khai
```java
public int[] hashMap(int[] nums, int target) {
    SeparateChainingHashTable<Integer, Integer> result = new SeparateChainingHashTable<>();

    for (int i = 0; i < nums.length; i++) {
        int finder = target - nums[i];

        if (result.has(finder)) {
            return new int[] {result.get(finder), i};
        }

        result.insert(nums[i], i);
    }
    return new int[] {};
}
```

### 3.3. Đánh Giá Độ Phức Tạp
- **Độ phức tạp thời gian (Time Complexity):** $\mathcal{O}(n)$
  - Duyệt mảng 1 vòng lặp từ $0$ đến $n - 1$.
  - Thao tác `has()`, `get()`, `insert()` trong bảng băm đạt thời gian trung bình $\mathcal{O}(1)$.
  - Tổng thời gian thực thi: $\mathcal{O}(n)$.
- **Độ phức tạp không gian (Space Complexity):** $\mathcal{O}(n)$
  - Cần bộ nhớ phụ để lưu tối đa $n$ phần tử vào các bucket của bảng băm.

---

## 4. Bảng So Sánh Chi Tiết

| Tiêu Chí | Brute Force (`bruteForce`) | Hash Map (`hashMap`) |
| :--- | :--- | :--- |
| **Độ phức tạp thời gian (TB)** | $\mathcal{O}(n^2)$ | **$\mathcal{O}(n)$** (vượt trội) |
| **Độ phức tạp thời gian (Xấu nhất)** | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ *(rất hiếm, chỉ khi tất cả key bị trùng mã băm)* |
| **Độ phức tạp không gian (RAM)** | **$\mathcal{O}(1)$** (Tối ưu tuyệt đối) | $\mathcal{O}(n)$ (Cần bộ nhớ cho bảng băm) |
| **Số lần duyệt mảng** | $\frac{n(n - 1)}{2}$ bước so sánh | **1 lần duy nhất** (One-pass) |
| **Cấu trúc dữ liệu sử dụng** | Không cần (chỉ dùng mảng kết quả) | [`SeparateChainingHashTable`](../../../DSA/HashTable/SeparateChainingHashTable.java) |
| **Khả năng mở rộng (Scalability)** | Kém khi dữ liệu lớn ($n > 10^3$) | Rất tốt với dữ liệu lớn ($n \ge 10^5$) |
| **Cài đặt & Độ phức tạp code** | Đơn giản, ngắn gọn | Cần import và phụ thuộc cấu trúc bảng băm |

---

## 5. Ưu Điểm và Nhược Điểm

### 5.1. Brute Force
- **Ưu điểm:**
  - Logic đơn giản, dễ đọc, không cần cấu trúc dữ liệu bổ trợ.
  - Không tốn thêm bất kỳ tài nguyên RAM nào ($\mathcal{O}(1)$).
- **Nhược điểm:**
  - Hiệu năng rất chậm khi $n$ tăng cao.
  - Vòng lặp không dừng ngay khi tìm thấy kết quả nếu không đặt câu lệnh `return` hoặc `break` sớm.

### 5.2. Hash Map
- **Ưu điểm:**
  - Tối ưu hóa thời gian chạy đạt chuẩn $\mathcal{O}(n)$.
  - Trả về kết quả ngay lập tức khi tìm thấy cặp số thỏa mãn.
  - Dễ dàng vượt qua các test cases lớn trên LeetCode.
- **Nhược điểm:**
  - Tiêu tốn thêm bộ nhớ $\mathcal{O}(n)$.
  - Bảng băm tự viết (`SeparateChainingHashTable`) sử dụng `DLinkedList` (danh sách liên kết đôi) nên có overhead bộ nhớ cho các con trỏ `prev` và `next` trong từng node.

---

## 6. Khi Nào Nên Dùng Phương Pháp Nào?

1. **Sử dụng Brute Force khi:**
   - Tập dữ liệu $n$ rất nhỏ ($n \le 50$).
   - Môi trường thực thi bị giới hạn ngặt nghèo về RAM (hệ thống nhúng, vi điều khiển).
   - Yêu cầu viết nhanh lời giải tạm thời không phụ thuộc thư viện/cấu trúc dữ liệu ngoài.

2. **Sử dụng Hash Map khi:**
   - Tập dữ liệu từ vừa đến lớn ($n > 100$ hoặc lên tới hàng triệu phần tử).
   - Phỏng vấn kỹ thuật hoặc nộp bài trên các nền tảng OJ (LeetCode, Codeforces, HackerRank).
   - Hệ thống có đủ bộ nhớ và cần tối ưu tối đa về thời gian phản hồi (Latency).
