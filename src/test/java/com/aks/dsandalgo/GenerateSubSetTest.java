package com.aks.dsandalgo;

import junit.framework.TestCase;

/**
 * Created by amit on 18/6/17.
 */
public class GenerateSubSetTest extends TestCase {

    private GenerateSubSet generateSubSets = new GenerateSubSet();

    public void testGenerateSubsets() throws Exception {
        generateSubSets.generateSubsets("abcd");
    }

}