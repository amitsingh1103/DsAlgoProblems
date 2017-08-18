package com.leetcode.dsandalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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



}
