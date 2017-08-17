package com.aks.dsandalgo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testRotateArray() {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        arraysExample.rotateArray(arr, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

    @Test
    public void testMaxSumOfNonAdjacentEle() {
        int[] arr = new int[]{5, 5, 10, 100, 10, 5};
        System.out.println(arraysExample.maxSumOfNonAdjacentEle(arr));
    }

    @Test
    public void testGetLeaders() {
        int[] arr = new int[]{16, 17, 4, 3, 5, 2};
        arraysExample.getLeaders(arr);
    }

    @Test
    public void sortByFrequency() {
        List<Integer> integerList = Arrays.asList(2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8);
        arraysExample.sortByFrequency(integerList).stream().forEach(a -> System.out.print(a + ", "));
    }

    @Test
    public void sumClosestToZero() {
        List<Integer> integerList = Arrays.asList(1, 60, -10, 70, -80, 85);
        arraysExample.sumClosestToZero(integerList);
    }

    @Test
    public void smallestAndSecondSmallestEle() {
        int[] arr = new int[]{12, 13, 1, 10, 34, 1};
        arraysExample.smallestAndSecondSmallestEle(arr);
    }

    @Test
    public void calculateInversionCount() {
        int[] arr = new int[]{1, 20, 6, 4, 5};
        System.out.println(arraysExample.calculateInversionCount(arr));
    }

    @Test
    public void rotateArrayByOne() {
        int[] arr = new int[]{1, 20, 6, 4, 5};
        arraysExample.rotateArrayByOne(arr);
    }

    @Test
    public void findPairWithGivenSumInSortedAndRotatedArray() {
        int[] arr = new int[]{11, 15, 6, 8, 9, 10};
        arraysExample.findPairWithGivenSumInSortedAndRotatedArray(arr, 16);
    }

    @Test
    public void findMaxSumAmongAllRotationsOfArray() {
        int[] arr = new int[]{8, 3, 1, 2};
        System.out.println("Max sum: " + arraysExample.findMaxSumAmongAllRotationsOfArray(arr));
    }
}