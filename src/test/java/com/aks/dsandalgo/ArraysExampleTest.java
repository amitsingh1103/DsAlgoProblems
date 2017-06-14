package com.aks.dsandalgo;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by singhami on 6/8/2017.
 */
public class ArraysExampleTest extends TestCase {

    private ArraysExample arraysExample;

    public void setUp() throws Exception {
        arraysExample = new ArraysExample();
    }

    public void testBinarSeachOfArray() {
        int[] arr = new int[]{1, 3, 5, 7, 9};
        int key = 7;
        Assert.assertEquals(3, arraysExample.binarSeachOfArray(arr, 0, arr.length - 1, key));
    }
}