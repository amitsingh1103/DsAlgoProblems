package com.leetcode.dsandalgo;

import org.junit.Test;

import java.util.concurrent.Executor;

/**
 * Created by amit on 6/8/17.
 */
public class ArraysTest {

    private ArraysList arraysList = new ArraysList();

    @Test
    public void plusOne() {
        int[] arr = new int[]{0};
        int[] res = arraysList.plusOne(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
    }

    @Test
    public void getRowInPasclaTriangle() {
        arraysList.getRowInPascalTriangle(30);
    }

    @Test
    public void twoSum() {
        int[] arr = new int[]{2, 3, 4};
        int[] ret = arraysList.twoSum(arr, 6);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }

    @Test
    public void maximumProduct() {
        int[] arr = new int[]{-4,-3,-2,-1,60};
        System.out.println(arraysList.maximumProduct(arr));
    }

    @Test
    public void removeElement() {
        int[] arr = new int[]{1, 3, 2, 2, 3, 4};
        System.out.println(arraysList.removeElement(arr, 3));
    }

    @Test
    public void findShortestUnsortedContiguousSubArray() {
        int[] arr = new int[]{2, 6, 4, 8, 10, 15};
        System.out.println(arraysList.findShortestUnsortedContiguousSubArray(arr));
    }

    @Test
    public void matrixReshape() {
        int[][] arr = new int[][]{{1, 2}, {3, 4}};
        int r = 4;
        int c = 1;
        int[][] ret = arraysList.matrixReshape(arr, r, c);
        for (int i = 0; i < r; i++) {
            System.out.print("[");
            for (int j = 0; j < c; j++) {
                System.out.print(ret[i][j] + ",");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    @Test
    public void searchInsert() {
        int[] arr = new int[]{1,3,5,6};
        System.out.println(arraysList.searchInsert(arr, -1));
    }

    @Test
    public void merge() {
        int[] arr1 = new int[]{-1,0,0,3,3,3,0,0,0};
        int[] arr2 = new int[]{1,2,2};
        arraysList.merge(arr1, 6, arr2, 3);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ",");
        }
    }

    @Test
    public void imageSmoother() {
        int[][] arr = new int[][]{{-1,-11,-1}, {1,0,1}, {111,111,1111}};
        int[][] ret = arraysList.imageSmoother(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(ret[i][j] + ",");
            }
            System.out.println();
        }
    }
}