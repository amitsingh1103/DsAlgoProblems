package com.interviewbit.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicProgramming {

    /**
     * Problem 1: Given an array of integers, find the length of longest subsequence which is first increasing then
     * decreasing.
     */
    public int longestSubsequenceLength(final List<Integer> A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }

        int maxLength = Integer.MIN_VALUE;
        int[] lis = getAllLongestIncreasingSubSeqence(A);
        int[] lds = getAllLongestDecreasingSubSequence(A);

        for (int i = 0; i < A.size(); i++) {
            int currSum = lis[i] + lds[i] - 1;
            if (maxLength < currSum) {
                maxLength = currSum;
            }
        }
        return maxLength;
    }

    private int[] getAllLongestDecreasingSubSequence(List<Integer> A) {
        int[] lds = new int[A.size()];

        for (int i = 0; i < lds.length; i++) {
            lds[i] = 1;
        }

        for (int i = lds.length - 2; i >= 0; i--) {
            for (int j = lds.length - 1; j > i; j--) {
                if (A.get(j) < A.get(i) && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }
        return lds;
    }

    private int[] getAllLongestIncreasingSubSeqence(List<Integer> A) {
        int[] lis = new int[A.size()];

        for (int i = 0; i < lis.length;i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < lis.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A.get(j) < A.get(i) && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        return lis;
    }

    /**
     * Problem 2: A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     */
    public int numDecodings(String A) {
        if (A == null || "".equals(A) || A.charAt(0) == '0') {
            return 0;
        }

        char[] digits = A.toCharArray();
        int n = digits.length;
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (digits[i - 1] > '0') {
                count[i] = count[i - 1];
            }
            if (digits[i - 2] == '1' || (digits[i - 2] == '2' && digits[i - 1] < '7')) {
                count[i] += count[i - 2];
            }
        }

        return count[n];
    }

    /**
     * Problem 3: Given a binary grid i.e. a 2D grid only consisting of 0’s and 1’s, find the area of the largest
     * rectangle inside the grid such that all the cells inside the chosen rectangle should have 1 in them. You are
     * allowed to permutate the columns matrix i.e. you can arrange each of the column in any order in the final grid.
     */
    public int solve(ArrayList<ArrayList<Integer>> A) {
        if (A == null || A.isEmpty() || A.get(0) == null|| A.get(0).isEmpty()) {
            return 0;
        }

        addByColumn(A);
        sortByColumn(A);

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                maxArea = Math.max(maxArea, (j + 1) * A.get(i).get(j));
            }
        }
        return maxArea;
    }

    private void sortByColumn(ArrayList<ArrayList<Integer>> A) {
        for (List<Integer> integerList : A) {
            doCountingSort(integerList);
        }
    }

    private void doCountingSort(List<Integer> integerList) {
        integerList.sort(Comparator.reverseOrder());
    }

    private void addByColumn(ArrayList<ArrayList<Integer>> A) {
        for (int i = 0; i < A.get(0).size(); i++) {
            for (int j = 1; j < A.size(); j++) {
                int sum = 0;
                if (A.get(j).get(i) == 1) {
                    sum = A.get(j).get(i) + A.get(j - 1).get(i);
                }
                A.get(j).set(i, sum);
            }
        }
    }

    /**
     * Problem 4:
     */
    public int chordCnt(int A) {
        //int n = 2 * A;
        return 0;
    }

}
