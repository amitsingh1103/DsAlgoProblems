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
        int[] arr = new int[]{-2, -3, -4, -1, -2, -1, -5, -3};
        arraysExample.findLargestSumContiguousSubArray(arr);
    }

}