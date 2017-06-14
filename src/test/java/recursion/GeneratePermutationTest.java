package recursion;

import junit.framework.TestCase;

/**
 * Created by amit on 10/6/17.
 */
public class GeneratePermutationTest extends TestCase {

    private GeneratePermutation generatePermutation;
    public void setUp() throws Exception {
        generatePermutation = new GeneratePermutation();
    }

    public void testGeneratePermutations() throws Exception {
        generatePermutation.generatePermutations("kkiran");
    }

}