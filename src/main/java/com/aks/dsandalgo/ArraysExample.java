package com.aks.dsandalgo;

/**
 * Created by singhami on 6/8/2017.
 */
public class ArraysExample {

    /**
     * Problem 3: Given an array A[] and a number x, check for pair in A[] with sum as x
     * Solution:
     * 1. Sort the array and use two indexes at low and high. Move left index if
     * key > sum else right index. return if key == sum or fail if left = right.
     * 2. Use hashmap
     */
    //public void


    /**
     * Problem 2: Insert, Delete and Search in sorted array
     * Solution: Insert takes O(n) time, need to sort again
     * Delete takes O(n)time, need to shift elemnts
     * Search takes O(log n) time, can do binary search
     */
    public int binarSeachOfArray(int[] arr, int low, int high, int key) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (key == arr[mid]) {
            return mid;
        }

        if (key > arr[mid]) {
            return binarSeachOfArray(arr, mid + 1, high, key);
        }

        return binarSeachOfArray(arr, low, mid - 1, key);
    }

    /**
     * Problem 1:Search, Insert and Delete in unsorted array
     * Solution: Insert takes O(1),
     * Delete takes O(n) time, need to shift elements
     * Search will be linear, O(n)
     */
}
