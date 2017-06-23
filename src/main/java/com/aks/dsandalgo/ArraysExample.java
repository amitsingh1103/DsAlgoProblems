package com.aks.dsandalgo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by singhami on 6/8/2017.
 */
public class ArraysExample {

    /**
     * Problem 8: Search an element in a sorted and rotated array. Elements are unique.
     * Solution: Find the pivot element. Then find key in the subarray.
     */
    

    /**
     * Problem 7: You are given a list of n-1 integers and these integers are in the range of 1 to n. There are no
     * duplicates in list. One of the integers is missing in the list. Write an efficient code to find the missing
     * integer.
     * Solution: Calculate the sum of n numbers and then subtract this with the sum of elements.
     */
    public void findMissingNumber(int[] arr, int n) {
        int sumN = (n * (n + 1)) / 2;
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArr += arr[i];
        }

        System.out.println("Missing element: " + (sumN - sumArr));
    }

    /**
     * Problem 6: Find the sum of contiguous subarray within a one-dimensional array of numbers which has the
     * largest sum. Also print positions.
     * Solution 1: Kadane's Algorithm-> Taking current maximum and maximum so far.
     */
    public void findLargestSumContiguousSubArray(int[] arr) {
        int currentMax = arr[0];
        int maxSoFar = arr[0];
        int low = 0;
        int lowTemp = 0;
        int high = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > (currentMax + arr[i])) {
                currentMax = arr[i];
                lowTemp = i;
            } else {
                currentMax += arr[i];
            }

            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                high = i;
                low = lowTemp;
            }
        }

        System.out.println("Largest sum: " + maxSoFar + " .. Positions: [" + low + ", " + high + "]");
    }

    /**
     * Problem 5: Given an array of positive integers. All numbers occur even number of times except one number
     * which occurs odd number of times. Find the number in O(n) time & constant space.
     * Solution 1: Do XOR of each element. 2 XOR 2 = 0 ... 2 XOR 0 = 2
     * 0 is the identity element in XOR
     * Even occurrence elements will return 0 and the only odd occurrence element will be remaining in the output.
     */
    public void findOddOccurrenceElement(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }

        System.out.println("Odd occurrence element is: " + result);
    }


    /**
     * Problem 4: Find the majority element. x is the majority element in a list if the occurrences of x
     * in the list is greater than n/2.
     * Solution 1: Brute force: O(n^2)
     *
     * Solution 2: Moore's voting algorithm: O(n)
     * step 1: Whenever get the same element increment the count by 1 else decrement it by 1. When count is 0
     * make the  current element as majority element. At the end whatever be the element remaining may be the
     * majority element.
     * step 2: verify the above result if it is a majority element.
     *
     * Solution 3: Using HashMap
     */
    public void findMajorityElement(int[] arr) {
        int count = 1;
        int maj_index = 0;
        // Voting for the maj element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[maj_index]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                count = 1;
                maj_index = i;
            }
        }

        int votedElementCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr[maj_index]) {
                votedElementCount++;
            }
        }

        if (votedElementCount > (arr.length / 2)) {
            System.out.println("Majority Element: " + arr[maj_index]);
            return;
        }

        System.out.println("No majority element present!");
    }

    /**
     * Problem 3: Given an array A[] and a number x, check for pair in A[] with sum as x
     * Solution:
     * 1. Sort the array and use two indexes at low and high. Move left index if
     * key > sum else right index. return if key == sum or fail if left = right.
     * 2. Use hashmap
     */
    public void findSumPair(int[] arr, int key) {
        Map<Integer, Integer> integerMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            integerMap.putIfAbsent(arr[i], 1);
        }

        Iterator<Integer> itr = integerMap.keySet().iterator();
        while (itr.hasNext()) {
            Integer k1 = itr.next();
            if (integerMap.containsKey(Math.abs(key - k1))) {
                System.out.println("Pair: [" + k1 + ", " + Math.abs(key - k1) + "]");
            }
            itr.remove();
        }
    }


    /**
     * Problem 2: Insert, Delete and Search in sorted array
     * Solution: Insert takes O(n) time, need to sort again
     * Delete takes O(n)time, need to shift elemnts
     * Search takes O(log n) time, can do binary search
     */
    public int binarySearchOfArray(int[] arr, int low, int high, int key) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (key == arr[mid]) {
            return mid;
        }

        if (key > arr[mid]) {
            return binarySearchOfArray(arr, mid + 1, high, key);
        }

        return binarySearchOfArray(arr, low, mid - 1, key);
    }

    /**
     * Problem 1:Search, Insert and Delete in unsorted array
     * Solution: Insert takes O(1),
     * Delete takes O(n) time, need to shift elements
     * Search will be linear, O(n)
     */
}
