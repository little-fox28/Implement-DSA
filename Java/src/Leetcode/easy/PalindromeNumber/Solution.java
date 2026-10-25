package Leetcode.easy.PalindromeNumber;

/**
 * LeetCode #9: Palindrome Number
 * Note: Aim to solve this problem without converting the integer to a string (Follow-up requirement).
 */
public class Solution {
    public boolean isPalindrome14ms(int x) {
        String str_nums = Integer.toString(x);
        int leng_num = str_nums.length() / 2;

        int i = 0;
        int j = str_nums.length() -1;

        while (leng_num > i) {
            if (str_nums.charAt(i) != str_nums.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome4ms(int x) {
        if (x < 0) {
            return false;
        }

        int reversed = 0;
        int y = x;

        while( y > 0){
            reversed = reversed*10 + y%10;
            y/=10;
        }
        return reversed == x;
    }

    public static void main(String[] args) {
        Solution numCheck = new Solution();
        numCheck.isPalindrome4ms(10022201);
    }
}
