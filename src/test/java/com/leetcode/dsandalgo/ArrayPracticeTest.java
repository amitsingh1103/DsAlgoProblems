package com.leetcode.dsandalgo;

import org.junit.Test;

/**
 * Created by amit on 6/8/17.
 */
public class ArrayPracticeTest {

    private ArrayPractice arrayPractice = new ArrayPractice();

    @Test
    public void plusOne() {
        int[] arr = new int[]{0};
        int[] res = arrayPractice.plusOne(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
    }

    @Test
    public void getRowInPasclaTriangle() {
        arrayPractice.getRowInPascalTriangle(30);
    }

    @Test
    public void twoSum() {
        int[] arr = new int[]{2, 3, 4};
        int[] ret = arrayPractice.twoSum(arr, 6);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }

    @Test
    public void maximumProduct() {
        int[] arr = new int[]{-4,-3,-2,-1,60};
        System.out.println(arrayPractice.maximumProduct(arr));
    }

    @Test
    public void removeElement() {
        int[] arr = new int[]{1, 3, 2, 2, 3, 4};
        System.out.println(arrayPractice.removeElement(arr, 3));
    }

    @Test
    public void findShortestUnsortedContiguousSubArray() {
        int[] arr = new int[]{2, 6, 4, 8, 10, 15};
        System.out.println(arrayPractice.findShortestUnsortedContiguousSubArray(arr));
    }

    @Test
    public void matrixReshape() {
        int[][] arr = new int[][]{{1, 2}, {3, 4}};
        int r = 4;
        int c = 1;
        int[][] ret = arrayPractice.matrixReshape(arr, r, c);
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
        System.out.println(arrayPractice.searchInsert(arr, -1));
    }

    @Test
    public void merge() {
        int[] arr1 = new int[]{-1,0,0,3,3,3,0,0,0};
        int[] arr2 = new int[]{1,2,2};
        arrayPractice.merge(arr1, 6, arr2, 3);
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + ",");
        }
    }

    @Test
    public void imageSmoother() {
        int[][] arr = new int[][]{{-1,-11,-1}, {1,0,1}, {111,111,1111}};
        int[][] ret = arrayPractice.imageSmoother(arr);
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
        System.out.println(arrayPractice.checkPossibility(arr));
    }

    @Test
    public void findMaxAverage() {
        int[] arr = new int[]{1,12,-5,-6,50,3};
        System.out.println(arrayPractice.findMaxAverage(arr, 4));
    }

    @Test
    public void findMaximumDistance() {
        int[][] list = new int[][]{ {-1,2,3}, {4,5}, {1,2,3} };
        System.out.println(arrayPractice.findMaximumDistance(list));
    }

    @Test
    public void canPlaceFlowers() {
        int[] arr = new int[]{1,0,0,0,1};
        System.out.println(arrayPractice.canPlaceFlowers(arr, 1));
    }

    @Test
    public void findPairs() {
        int[] arr = new int[]{1,3,1,5,4};
        System.out.println(arrayPractice.findPairs(arr, 0));
    }

    @Test
    public void shortestDistance() {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(arrayPractice.shortestDistance(words, "coding", "practice"));
    }
    @Test
    public void containsNearbyDuplicate() {
        int[] arr = new int[]{1,-1,2,5,3,2};
        System.out.println(arrayPractice.containsNearbyDuplicate(arr, 3));
    }

    @Test
    public void maxSubArray() {
        int[] arr = new int[]{1,2,3,4,-1,-2,-3,5,4,6,-2,-10,4,3};
        System.out.println("Max sum of contiguous subarray: " + arrayPractice.maxSubArray(arr));
    }

    @Test
    public void pivotIndex() {
        int[] arr = new int[]{-1,0,0,0,-1,0};
        System.out.println(arrayPractice.pivotIndex(arr));
    }

    @Test
    public void threeSum() {
        int[] arr = new int[]{-1,0,1,2,-1,-4};
        System.out.println(arrayPractice.threeSum(arr).toString());
    }

    @Test
    public void canJump() {
        int[] arr = new int[]{0};
        System.out.println(arrayPractice.canJump(arr));
    }

    @Test
    public void threeSumClosest() {
        int[] arr = new int[]{-1,2,1,-4};
        System.out.println(arrayPractice.threeSumClosest(arr, 1));
    }

    @Test
    public void findDuplicates() {
        int[] arr = new int[]{2,5,9,6,9,3,8,9,7,1};
        System.out.println(arrayPractice.findDuplicates(arr));
    }

    @Test
    public void gameOfLife() {
        int[][] arr = new int[][]{{1,1},{1,0}};
        arrayPractice.gameOfLife(arr);
        for (int[] subArr : arr) {
            for (int i : subArr) {
                System.out.print(i + ", ");
            }
        }
    }

    @Test
    public void generateMatrix() {
        int[][] arr = arrayPractice.generateMatrix(4);
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
        arrayPractice.wiggleSort(arr);
        System.out.println(java.util.Arrays.toString(arr));
    }

    @Test
    public void book2() {
        int[] s = new int[]{10, 10, 10};
        int[] e = new int[]{20, 20, 20};
        for (int i = 0; i < s.length; i++) {
            System.out.println(arrayPractice.book2(s[i], e[i]));
        }
    }

    @Test
    public void book3() {
        int[] s = new int[]{10, 10, 10};
        int[] e = new int[]{20, 20, 20};
        for (int i = 0; i < s.length; i++) {
            System.out.println(arrayPractice.book3(s[i], e[i]));
        }
    }

    @Test
    public void searchInRotatedSortedArray() {
        int[] arr = new int[]{4,5,1,2,3};
        System.out.println(arrayPractice.searchInRotatedSortedArray(arr, 3));
    }

    @Test
    public void findMin2() {
        int[] arr = new int[]{1,3,1,1,1};
        System.out.println(arrayPractice.findMin2(arr));
    }

    @Test
    public void subsetsWithDup() {
        int[] arr = new int[]{1,1,3};
        System.out.println(arrayPractice.subsetsWithDup(arr));
    }

    @Test
    public void leastInterval() {
        char[] arr = new char[]{'G','C','A','H','A','G','G','F','G','J','H','C','A','G','E','A','H','E','F','D','B','D','H','H','E','G','F','B','C','G','F','H','J','F','A','C','G','D','I','J','A','G','D','F','B','F','H','I','G','J','G','H','F','E','H','J','C','E','H','F','C','E','F','H','H','I','G','A','G','D','C','B','I','D','B','C','J','I','B','G','C','H','D','I','A','B','A','J','C','E','B','F','B','J','J','D','D','H','I','I','B','A','E','H','J','J','A','J','E','H','G','B','F','C','H','C','B','J','B','A','H','B','D','I','F','A','E','J','H','C','E','G','F','G','B','G','C','G','A','H','E','F','H','F','C','G','B','I','E','B','J','D','B','B','G','C','A','J','B','J','J','F','J','C','A','G','J','E','G','J','C','D','D','A','I','A','J','F','H','J','D','D','D','C','E','D','D','F','B','A','J','D','I','H','B','A','F','E','B','J','A','H','D','E','I','B','H','C','C','C','G','C','B','E','A','G','H','H','A','I','A','B','A','D','A','I','E','C','C','D','A','B','H','D','E','C','A','H','B','I','A','B','E','H','C','B','A','D','H','E','J','B','J','A','B','G','J','J','F','F','H','I','A','H','F','C','H','D','H','C','C','E','I','G','J','H','D','E','I','J','C','C','H','J','C','G','I','E','D','E','H','J','A','H','D','A','B','F','I','F','J','J','H','D','I','C','G','J','C','C','D','B','E','B','E','B','G','B','A','C','F','E','H','B','D','C','H','F','A','I','A','E','J','F','A','E','B','I','G','H','D','B','F','D','B','I','B','E','D','I','D','F','A','E','H','B','I','G','F','D','E','B','E','C','C','C','J','J','C','H','I','B','H','F','H','F','D','J','D','D','H','H','C','D','A','J','D','F','D','G','B','I','F','J','J','C','C','I','F','G','F','C','E','G','E','F','D','A','I','I','H','G','H','H','A','J','D','J','G','F','G','E','E','A','H','B','G','A','J','J','E','I','H','A','G','E','C','D','I','B','E','A','G','A','C','E','B','J','C','B','A','D','J','E','J','I','F','F','C','B','I','H','C','F','B','C','G','D','A','A','B','F','C','D','B','I','I','H','H','J','A','F','J','F','J','F','H','G','F','D','J','G','I','E','B','C','G','I','F','F','J','H','H','G','A','A','J','C','G','F','B','A','A','E','E','A','E','I','G','F','D','B','I','F','A','B','J','F','F','J','B','F','J','F','J','F','I','E','J','H','D','G','G','D','F','G','B','J','F','J','A','J','E','G','H','I','E','G','D','I','B','D','J','A','A','G','A','I','I','A','A','I','I','H','E','C','A','G','I','F','F','C','D','J','J','I','A','A','F','C','J','G','C','C','H','E','A','H','F','B','J','G','I','A','A','H','G','B','E','G','D','I','C','G','J','C','C','I','H','B','D','J','H','B','J','H','B','F','J','E','J','A','G','H','B','E','H','B','F','F','H','E','B','E','G','H','J','G','J','B','H','C','H','A','A','B','E','I','H','B','I','D','J','J','C','D','G','I','J','G','J','D','F','J','E','F','D','E','B','D','B','C','B','B','C','C','I','F','D','E','I','G','G','I','B','H','G','J','A','A','H','I','I','H','A','I','F','C','D','A','C','G','E','G','E','E','H','D','C','G','D','I','A','G','G','D','A','H','H','I','F','E','I','A','D','H','B','B','G','I','C','G','B','I','I','D','F','F','C','C','A','I','E','A','E','J','A','H','C','D','A','C','B','G','H','G','J','G','I','H','B','A','C','H','I','D','D','C','F','G','B','H','E','B','B','H','C','B','G','G','C','F','B','E','J','B','B','I','D','H','D','I','I','A','A','H','G','F','B','J','F','D','E','G','F','A','G','G','D','A','B','B','B','J','A','F','H','H','D','C','J','I','A','H','G','C','J','I','F','J','C','A','E','C','H','J','H','H','F','G','E','A','C','F','J','H','D','G','G','D','D','C','B','H','B','C','E','F','B','D','J','H','J','J','J','A','F','F','D','E','F','C','I','B','H','H','D','E','A','I','A','B','F','G','F','F','I','E','E','G','A','I','D','F','C','H','E','C','G','H','F','F','H','J','H','G','A','E','H','B','G','G','D','D','D','F','I','A','F','F','D','E','H','J','E','D','D','A','J','F','E','E','E','F','I','D','A','F','F','J','E','I','J','D','D','G','A','C','G','G','I','E','G','E','H','E','D','E','J','B','G','I','J','C','H','C','C','A','A','B','C','G','B','D','I','D','E','H','J','J','B','F','E','J','H','H','I','G','B','D'};
        System.out.println(arrayPractice.leastInterval(arr, 1));
    }

    @Test
    public void sortColors() {
        int[] arr = new int[]{0,0,0};
        arrayPractice.sortColors(arr);
        java.util.Arrays.stream(arr).forEach(a -> System.out.print(a + ", "));
    }

    @Test
    public void findLongestChain() {
        int[][] arr = new int[][]{{3,4},{2,3},{1,2}};
        System.out.println(arrayPractice.findLongestChain(arr));
    }

    @Test
    public void checkSubarraySum() {
        int[] arr = new int[]{0,0,0};
        System.out.println(arrayPractice.checkSubarraySum(arr, 0));
    }

    @Test
    public void maxArea() {
        int[] arr = new int[]{2,3,4,5,18,17,6};
        System.out.println(arrayPractice.maxArea(arr));
    }

    @Test
    public void trapRainWaterMatrix() {
        int[][] arr = new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        System.out.println(arrayPractice.trapRainWaterMatrix(arr));
    }

    @Test
    public void maximumSwap() {
        int num = 9937;
        System.out.println(arrayPractice.maximumSwap(num));
    }
}