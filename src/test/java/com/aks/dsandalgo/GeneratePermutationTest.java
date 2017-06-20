package com.aks.dsandalgo;

import org.junit.Test;

/**
 * Created by amit on 10/6/17.
 */
public class GeneratePermutationTest {

    private GeneratePermutation generatePermutation = new GeneratePermutation();

    @Test
    public void testGeneratePermutations() throws Exception {
        generatePermutation.generatePermutations("apple");
    }

}