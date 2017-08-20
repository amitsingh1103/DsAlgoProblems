package com.leetcode.dsandalgo;

import org.junit.Test;

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
}