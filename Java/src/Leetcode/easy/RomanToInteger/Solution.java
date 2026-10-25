package Leetcode.easy.RomanToInteger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * LeetCode #13: Roman to Integer
 *
 * TODO: Implement the romanToInt method.
 */
public class Solution {

    /**
     * Converts a Roman numeral string to an integer.
     *
     * @param s a valid Roman numeral in the range [1, 3999]
     * @return the integer value of the Roman numeral
     */
    public int romanToInt7ms(String s) {
        Map<Character, Integer> conversionTable = Map.of(
                'I',1,
                'V',5,
                'X',10,
                'L',50,
                'C',100,
                'D',500,
                'M',1000
        );

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentVal = conversionTable.get(s.charAt(i));
            if (i + 1 < s.length() && currentVal < conversionTable.get(s.charAt(i + 1))) {
                result -= currentVal;
            } else {
                result += currentVal;
            }
        }

        return result;
    }

    public int romanToInt(String s) {
        int total = 0;
        int prevVal = 0;

        for (int i = s.length() - 1; i >= 0 ; i--) {
            int currentVal = getInt(s.charAt(i));

            if (currentVal < prevVal) {
                total -= currentVal;
            } else {
                total += currentVal;
            }

            prevVal = currentVal;
        }

        return total;
    }

    private int getInt(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Solution converter = new Solution();
        int result = converter.romanToInt("MCMXCIV");
        System.out.println(result);
    }
}
