package com.interviewbit.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayPracticeTest {

    private ArrayPractice arrayPractice = new ArrayPractice();

    @Test
    public void coverPoints() {
        List<Integer> A = Arrays.asList(-7, -13);
        List<Integer> B = Arrays.asList(1, -5);
        System.out.println(arrayPractice.coverPoints(A, B));
    }

    @Test
    public void plusOne() {
        List<Integer> A = Arrays.asList(1, 3, -1);
        System.out.println(arrayPractice.maxArr(A));
    }

    @Test
    public void flip() {
        String str = "011";
        System.out.println(arrayPractice.flip(str));
    }

    @Test
    public void generateMatrix() {
        arrayPractice.generateMatrix(5);
    }

}