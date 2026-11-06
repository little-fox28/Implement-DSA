package Leetcode.medium.AddTwoNumber;

public class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
//        ListNode dummyList = new ListNode(0);
//        ListNode temp = dummyList;
//        int carry = 0;
//
//        while (l1 != null || l2 != null || carry != 0) {
//            int x = l1 != null ? l1.val : 0;
//            int y = l2 != null ? l2.val : 0;
//
//            int sum = x + y + carry;
//            carry = sum / 10;
//
//            temp.next = new ListNode(sum % 10);
//            temp = temp.next;
//
//
//            if (l1 != null) l1 = l1.next;
//            if (l2 != null) l2 = l2.next;
//        }
//        return dummyList.next;
//    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyList = new ListNode();
        ListNode temp = dummyList;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }

        return dummyList.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = solution.addTwoNumbers(l1, l2);
        travlerList(result);
    }

    private static void travlerList(ListNode head) {
        if (head == null) {
            System.out.println("Result: null");
            return;
        }
        StringBuilder sb = new StringBuilder("Result: [");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        System.out.println(sb);
    }
}
