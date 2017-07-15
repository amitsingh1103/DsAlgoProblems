package com.aks.dsandalgo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by singhami on 6/8/2017.
 */
public class ArraysExampleTest {

    private ArraysExample arraysExample = new ArraysExample();

    @Test
    public void testBinarySearchOfArray() {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        int key = 7;
        Assert.assertEquals(3, arraysExample.binarySearchOfArray(arr, 0, arr.length - 1, key));
    }

    @Test
    public void testFindSumPair() {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        int key = 8;
        arraysExample.findSumPair(arr, key);
    }

    @Test
    public void testFindMajorityElement() {
        int[] arr = new int[]{1, 2, 2, 2, 3, 2, 4 , 1, 2, 2};
        arraysExample.findMajorityElement(arr);
    }

    @Test
    public void testFindOddOccurrenceElement() {
        int[] arr = new int[]{1, 2, 3, 2, 3, 1, 3};
        arraysExample.findOddOccurrenceElement(arr);
    }

    @Test
    public void testFindLargestSumContiguousSubArray() {
        int[] arr = new int[]{-2, -3, 4, -1, -2, 1, 5, -3, -1, -1, -1, -24, 4, 6};
        arraysExample.findLargestSumContiguousSubArray(arr);
    }

    @Test
    public void tesFindMissingNumber() {
        int[] arr = new int[]{1,2,4,5,6};
        arraysExample.findMissingNumber(arr, 6);
    }

    @Test
    public void testSearchElementInRotatedArray() {
        int[] arr = new int[]{5, 6, 7, 8, 9, 10, 1, 2, 3};
        int key = 3;
        System.out.println(arraysExample.searchElementInRotatedArray(arr, key));
    }

    @Test
    public void testMergeTwoSortedArrays() {
        int NA = -1;
        int mPlusN[] = {2, 8, NA, NA, NA, 13, NA, 15, 20};
        int N[] = {5, 7, 9, 25};
        arraysExample.mergeTwoSortedArrays(mPlusN, N);
    }

    @Test
    public void testReverseArray() {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        arraysExample.reverseArray(arr);
    }

    @Test
    public void testFindMedianOfTwoSortedArrays() {
        int ar1[] = {1, 2, 3, 6};
        int ar2[] = {4, 6, 8, 10};
        System.out.println(arraysExample.findMedianOfTwoSortedArrays(ar1, ar2));
    }
}