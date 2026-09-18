package Leetcode.Problems.Two_Sum;

import HashTable.SeparateChainingHashTable;

public class Solution {
        public int[] bruteForce(int[] nums, int target) {
            int[] result = new int[2];

            for (int i = 0; i < nums.length; i++) {

                int finder = target - nums[i];

                for (int j = i + 1; j < nums.length; j++) {
                    if(nums[j] == finder){
                        result[0] = i;
                        result[1] = j;
                    };
                };
            };
            return result;
        }

        public int[] hashMap (int[] nums, int target) {
            SeparateChainingHashTable<Integer, Integer> result = new SeparateChainingHashTable<>();

            for (int i = 0; i < nums.length; i++) {
                int finder = target - nums[i];

                if (result.has(finder)) {
                    return new int[] {result.get(finder), i};
                };

                result.insert(nums[i], i);
            }
            return new int[] {};
        }
}
