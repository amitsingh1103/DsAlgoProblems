package com.leetcode.dsandalgo;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * Created by amit on 6/8/17.
 */
public class ArraysTest {

    private Arrays arrays = new Arrays();

    @Test
    public void plusOne() {
        int[] arr = new int[]{0};
        int[] res = arrays.plusOne(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
    }

    @Test
    public void getRowInPasclaTriangle() {
        arrays.getRowInPascalTriangle(30);
    }

    @Test
    public void twoSum() {
        int[] arr = new int[]{2, 3, 4};
        int[] ret = arrays.twoSum(arr, 6);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }

    @Test
    public void maximumProduct() {
        int[] arr = new int[]{-4,-3,-2,-1,60};
        System.out.println(arrays.maximumProduct(arr));
    }

    @Test
    public void removeElement() {
        int[] arr = new int[]{1, 3, 2, 2, 3, 4};
        System.out.println(arrays.removeElement(arr, 3));
    }

    @Test
    public void findShortestUnsortedContiguousSubArray() {
        int[] arr = new int[]{2, 6, 4, 8, 10, 15};
        System.out.println(arrays.findShortestUnsortedContiguousSubArray(arr));
    }

    @Test
    public void matrixReshape() {
        int[][] arr = new int[][]{{1, 2}, {3, 4}};
        int r = 4;
        int c = 1;
        int[][] ret = arrays.matrixReshape(arr, r, c);
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
        System.out.println(arrays.searchInsert(arr, -1));
    }

    @Test
    public void merge() {
        int[] arr1 = new int[]{-1,0,0,3,3,3,0,0,0};
        int[] arr2 = new int[]{1,2,2};
        arrays.merge(arr1, 6, arr2, 3);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ",");
        }
    }

    @Test
    public void imageSmoother() {
        int[][] arr = new int[][]{{-1,-11,-1}, {1,0,1}, {111,111,1111}};
        int[][] ret = arrays.imageSmoother(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(ret[i][j] + ",");
            }
            System.out.println();
        }
    }

    @Test
    public void checkPossibility() {
        int[] arr = new int[]{-2, -1, -3};
        System.out.println(arrays.checkPossibility(arr));
    }

    @Test
    public void findMaxAverage() {
        int[] arr = new int[]{1,12,-5,-6,50,3};
        System.out.println(arrays.findMaxAverage(arr, 4));
    }

    @Test
    public void findMaximumDistance() {
        int[][] list = new int[][]{ {-1,2,3}, {4,5}, {1,2,3} };
        System.out.println(arrays.findMaximumDistance(list));
    }

    @Test
    public void canPlaceFlowers() {
        int[] arr = new int[]{1,0,0,0,1};
        System.out.println(arrays.canPlaceFlowers(arr, 1));
    }

    @Test
    public void findPairs() {
        int[] arr = new int[]{1,3,1,5,4};
        System.out.println(arrays.findPairs(arr, 0));
    }

    @Test
    public void shortestDistance() {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(arrays.shortestDistance(words, "coding", "practice"));
    }
    @Test
    public void containsNearbyDuplicate() {
        int[] arr = new int[]{1,-1,2,5,3,2};
        System.out.println(arrays.containsNearbyDuplicate(arr, 3));
    }

    @Test
    public void maxSubArray() {
        int[] arr = new int[]{1,2,3,4,-1,-2,-3,5,4,6,-2,-10,4,3};
        System.out.println("Max sum of contiguous subarray: " + arrays.maxSubArray(arr));
    }

    @Test
    public void pivotIndex() {
        int[] arr = new int[]{-1,0,0,0,-1,0};
        System.out.println(arrays.pivotIndex(arr));
    }

    @Test
    public void threeSum() {
        int[] arr = new int[]{-1,0,1,2,-1,-4};
        System.out.println(arrays.threeSum(arr).toString());
    }

    @Test
    public void canJump() {
        int[] arr = new int[]{0};
        System.out.println(arrays.canJump(arr));
    }

    @Test
    public void threeSumClosest() {
        int[] arr = new int[]{-1,2,1,-4};
        System.out.println(arrays.threeSumClosest(arr, 1));
    }

    @Test
    public void findDuplicates() {
        int[] arr = new int[]{2,5,9,6,9,3,8,9,7,1};
        System.out.println(arrays.findDuplicates(arr));
    }

    @Test
    public void gameOfLife() {
        int[][] arr = new int[][]{{1,1},{1,0}};
        arrays.gameOfLife(arr);
        for (int[] subArr : arr) {
            for (int i : subArr) {
                System.out.print(i + ", ");
            }
        }
    }

    @Test
    public void generateMatrix() {
        int[][] arr = arrays.generateMatrix(4);
        for (int[] subArr : arr) {
            for (int i : subArr) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

    @Test
    public void wiggleSort() {
        int[] arr = new int[]{3, 5, 2, 1, 6, 4};
        arrays.wiggleSort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }
}