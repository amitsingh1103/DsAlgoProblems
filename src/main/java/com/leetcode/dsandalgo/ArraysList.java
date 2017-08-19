package com.leetcode.dsandalgo;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * Created by amit on 6/8/17.
 */
public class ArraysList {

    /**
     * Problem 1: Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public int[] plusOne(int[] digits) {
        int size = digits.length;
        int carry = 0;
        for (int i = size - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                break;
            }
        }

        if (carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 1; i < newDigits.length; i++) {
                newDigits[i] = digits[i - 1];
            }
            return newDigits;
        }
        return digits;
    }

    /*Given an index k, return the kth row of the Pascal's triangle.
      For example, given k = 3, return [1,3,3,1].
      Solution: C(line, i) = C(line, i-1) * [(line - i + 1) / i]
    */
    public List<Long> getRowInPascalTrianlgle(int row) {
        List<Long> result = new ArrayList<>();
        Long binCoeffVal = Long.valueOf(1);
        for (int i = 1; i <= row+1; i++) {
            result.add(binCoeffVal);
            binCoeffVal = (binCoeffVal * (row - (i - 1))) / i;
        }

        return result;
    }

    /*Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1),
     (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

     solution: sort the list and take consecutive elements to create all pairs.
    */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        if (nums.length % 2 == 0) {
            return sum;
        }

        for (int i = 0; i < nums.length; i++) {
            sum += Math.min(nums[i], nums[i+1]);
        }

        return sum;
    }

    /*Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Solution: Insert all elements in the list(O(n) to iterate and O(1) to insert) and then find the abs diff in the
    table(O(1) to serch). In case of dupes, check at the time of insertion only if double of value is the target.
    LinkedHashMap for the index order.
    */
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        Map<Integer, Integer> intMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!intMap.containsKey(nums[i])) {
                intMap.put(nums[i], i);
            } else if (2 * nums[i] == target) {
                ret[0] = intMap.get(nums[i]);
                ret[1] = i;
                return ret;
            }
        }

        for (Integer key : intMap.keySet()) {
            Integer probeElement = target - key;
            if (intMap.containsKey(probeElement) && probeElement != key) {
                ret[0] = intMap.get(key);
                ret[1] = intMap.get(probeElement);
                return ret;
            }
        }

        return ret;
    }

    /*Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
    Find all the elements of [1, n] inclusive that do not appear in this array.
    Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

    Solution: Negate element by using them as index of the array. viz. nums[nums[i] - 1] = -1 * nums[nums[i] - 1].
    Index on which elements are +ve will be the missing ones.
    */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -1 * nums[val];
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }
}
