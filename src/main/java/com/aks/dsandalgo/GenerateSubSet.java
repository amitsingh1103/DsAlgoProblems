package com.aks.dsandalgo;

/**
 * Exhaustive Recursion. Two options at every decision point. Making a decision to explore each options available.
 *
 * Created by amit on 18/6/17.
 */
public class GenerateSubSet {

    // Order doesn't matters, ab is same as ba
    private void recSubsets(String soFar, String rest) {
        if ("".equals(rest)) {
            System.out.println(soFar);
            return;
        }

        // Include the character
        recSubsets(soFar + rest.charAt(0), rest.substring(1));
        // Exclude the character
        recSubsets(soFar, rest.substring(1));
    }

    public void generateSubsets(String input) {
        recSubsets("", input);
    }
}
