package com.aks.dsandalgo;

/**
 * Exhaustive Recursion. N options at every decision point. Making a decision to explore each options available.
 * Created by amit on 10/6/17.
 */
public class GeneratePermutation {

    /**
     * Permutations using com.aks.dsandalgo.recursion
     */
    private void permute(String soFar, String rest) {
        if (rest.equals("")) {
            System.out.println(soFar + ", ");
            return;
        }

        for (int i = 0; i < rest.length(); i++) {
            String next = soFar + rest.charAt(i);
            String remaining = rest.substring(0, i) + rest.substring(i + 1);
            permute(next, remaining);
        }
    }

    public void generatePermutations(String s) {
        permute("", s);
    }
}