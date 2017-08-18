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
        arraysList.getRowInPascalTrianlgle(30);
    }

}