package com.interviewbit.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayPractice {

    public int coverPoints(List<Integer> A, List<Integer> B) {
        int numberOfSteps = 0;
        if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
            return numberOfSteps;
        }
        if (A.size() != B.size()) {
            return numberOfSteps;
        }

        int currRow = A.get(0);
        int currCol = B.get(0);
        for (int i = 1; i < A.size(); i++) {
            int rowSteps = Math.abs(currRow - A.get(i));
            int colSteps = Math.abs(currCol - B.get(i));
            currRow = A.get(i);
            currCol = B.get(i);
            numberOfSteps += Math.max(rowSteps, colSteps);
        }

        return numberOfSteps;
    }

    public List<Integer> maxset(List<Integer> A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            return res;
        }

        int low = 0;
        int high = -1;
        int currLow = 0;
        long maxSum = 0;
        long currSum = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) < 0) {
                currSum = 0;
                currLow = i + 1;
                continue;
            }
            currSum += A.get(i);
            if (currSum > maxSum || currSum == maxSum && (i - currLow) > (high - low)) {
                low = currLow;
                high = i;
                maxSum = currSum;
            }
        }

        for (int i = low; i <= high; i++) {
            res.add(A.get(i));
        }
        return res;
    }

    public List<Integer> plusOne(List<Integer> A) {
        if (A == null || A.isEmpty()) {
            return A;
        }

        List<Integer> res = new ArrayList<>(A);
        for (Integer digit : A) {
            if (digit > 0) {
                break;
            }
            res.remove(digit);
        }

        boolean isCarry = true;
        for (int i = res.size() - 1; i >= 0; i--) {
            if (isCarry) {
                if (res.get(i) == 9) {
                    res.set(i, 0);
                    isCarry = true;
                } else {
                    res.set(i, res.get(i) + 1);
                    isCarry = false;
                }
            }
        }

        if (isCarry) {
            res.add(0, 1);
        }

        return res;
    }

    public int maxSubArray(final List<Integer> A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }

        int maxSum = A.get(0);
        int currSum = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            currSum = Math.max(A.get(i) + currSum, currSum);
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }

    public int maxArr(List<Integer> A) {
        if (A == null || A.isEmpty()) {
            return 0;
        }

        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < A.size(); i++) {
            max1 = Math.max(max1, A.get(i) + i);
            min1 = Math.min(min1, A.get(i) + i);
            max2 = Math.max(max2, A.get(i) - i);
            min2 = Math.min(min2, A.get(i) - i);
        }

        return Math.max(max1 - min1, max2 - min2);
    }

    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            return (ArrayList<Integer>) res;
        }

        long n = A.size();
        double N = (n * (n + 1)) / 2;
        double M = (n * (n + 1) * (2 * n + 1)) / 6;
        long S = 0;
        long P = 0;
        for (int a : A) {
            S += a;
            P += (a * a);
        }

        double sumDiff = N - S;
        double C = ((M - P) / sumDiff);
        res.add(Integer.valueOf((int) ((C - sumDiff) / 2)));
        res.add(Integer.valueOf((int) ((C + sumDiff)) / 2));
        return (ArrayList<Integer>) res;
    }

    public ArrayList<Integer> flip(String A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.isEmpty()) {
            return (ArrayList<Integer>) res;
        }

        int start = 0;
        int end = -1;
        int currStart = 0;
        int currSum = 0;
        int maxSum = 0;
        int[] arr = new int[A.length()];
        int index = 0;
        for (char c : A.toCharArray()) {
            if (c == '0') {
                arr[index++] = 0;
            } else {
                arr[index++] = 1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                currSum++;
            } else {
                currSum--;
            }

            if (currSum < 0) {
                currSum = 0;
                currStart = i + 1;
            }

            if (currSum > maxSum) {
                maxSum = currSum;
                start = currStart;
                end = i;
            }
        }

        if (start <= end) {
            res.add(start + 1);
            res.add(end + 1);
        }

        return (ArrayList<Integer>) res;
    }

    public ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        if (A == 0) {
            return new ArrayList<>();
        }

        int row = 0;
        int col = 0;
        int value = 1;
        int size = A;
        int[][] res = new int[A][A];
        while (A > 0) {
            for (int i = col; i < A; i++) {
                res[row][i] = value++;
            }

            for (int i = row + 1; i < A; i++) {
                res[i][A - 1] = value++;
            }

            for (int i = A - 2; i >= col; i--) {
                res[A - 1][i] = value++;
            }

            for (int i = A - 2; i > row; i--) {
                res[i][col] = value++;
            }

            A -= 1;
            row++;
            col++;
        }

        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> rowRes = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                rowRes.add(res[i][j]);
            }
            resList.add(rowRes);
        }

        return resList;
    }
}
