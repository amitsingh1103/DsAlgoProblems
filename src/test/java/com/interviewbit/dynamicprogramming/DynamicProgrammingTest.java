package com.interviewbit.dynamicprogramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DynamicProgrammingTest {

    private DynamicProgramming dynamicProgramming = new DynamicProgramming();

    @Test
    public void longestSubsequenceLength() {
        List<Integer> list = Arrays.asList(1, 11, 2, 10, 4, 5, 2, 1);
        System.out.println(dynamicProgramming.longestSubsequenceLength(list));
    }

    @Test
    public void numDecodings() {
        String A =
                "07123";
        System.out.println(dynamicProgramming.numDecodings(A));
    }

    @Test
    public void solve() {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

    }
}