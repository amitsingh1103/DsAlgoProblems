package com.aks.dsandalgo;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for ArraysExample.
 */
public class ArraysExampleTest {

    private ArraysExample arraysExample;

    @Before
    public void setUp() throws Exception {
        arraysExample = new ArraysExample();
    }

    @Test
    public void testHello() {
        arraysExample.print();
    }
}
