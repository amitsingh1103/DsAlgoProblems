package com.leetcode.dsandalgo;

import java.util.*;

public class Tree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Problem 1: Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original
     * BST is changed to the original key plus sum of all keys greater than the original key in BST.
     *
     * Solution: Recursive Solution: Get the sum of right subtree.
     * Add it to the value and get the new sum.
     * Get the sum again for left subtree.
     * Do this recursively.
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private void convert(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }

        convert(root.right);
        root.val += sum;
        sum = root.val;
        convert(root.left);
    }

    /**
     * problem 2: Given a non-empty binary search tree and a target value, find the value in the BST that is closest
     * to the target.
     *
     * Solution: Recursive Solution: Search BST and whenever gets the min diff store the node.
     */
    int result;
    int min = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, int target) {
        searchClosestValue(root, target);
        return result;
    }

    private void searchClosestValue(TreeNode root, int target) {
        if (Objects.isNull(root)) {
            return;
        }

        if (Math.abs(root.val - target) < min) {
            min = Math.abs(root.val - target);
            result = root.val;
        }

        if (target < root.val) {
            searchClosestValue(root.left, target);
        } else {
            searchClosestValue(root.right, target);
        }
    }

    /**
     * problem 3: Given a Binary Search Tree and a target number, return true if there exist two elements in the BST
     * such that their sum is equal to the given target.
     *
     * Solution:
     * 1. Breadth First Traversal and store elements in HashSet.
     *
     * 2. Inorder Traversal and search element in the sorted list(using two pointers)
     */
    List<Integer> keys = new ArrayList<>();
    public boolean findTarget(TreeNode root, int k) {
        inorderTraversal(root);
        int i = 0;
        int j = keys.size() - 1;
        while (i < j) {
            int sum = keys.get(i) + keys.get(j);
            if (sum == k) {
                return true;
            }

            if (sum < k) {
                i++;
            }

            if (sum > k) {
                j--;
            }
        }

        return false;
    }

    private void inorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }

        inorderTraversal(root.left);
        keys.add(root.val);
        inorderTraversal(root.right);
    }
}
