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
     * <p>
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
     * <p>
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
     * <p>
     * Solution:
     * 1. Breadth First Traversal and store elements in HashSet.
     * <p>
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

    /**
     * Problem 4: Given a binary tree, find the length of the longest path where each node in the path has the same
     * value. This path may or may not pass through the root.
     * <p>
     * Solution: Trick i:-
     * 1. if a node value is not equal to its left or right child then this will not lead to a path
     * to its ancestors.
     * 2. Secondly we need to get the maximum to get the path without/with root.
     */
    int maxValue = Integer.MIN_VALUE;

    public int longestUniValuePath(TreeNode root) {
        calculateLongestUniValuePath(root);
        return maxValue;
    }

    private int calculateLongestUniValuePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = calculateLongestUniValuePath(root.left);
        int right = calculateLongestUniValuePath(root.right);

        int leftSum = 0;
        int rightSum = 0;
        if (root.left != null && root.left.val == root.val) {
            leftSum = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightSum = right + 1;
        }

        maxValue = Math.max(maxValue, leftSum + rightSum);
        return Math.max(leftSum, rightSum);
    }

    /**
     * Problem 5: Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that
     * all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should
     * return the new root of the trimmed binary search tree.
     * <p>
     * Solution:
     * Case 1: If value is < L then call recursively with right child.
     * Case 2: If value is > R then call recursively with lift child.
     * Else do not do anything.
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }

        // Case 1
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        // Case 2
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    /**
     * Problem 6: Given a non-empty binary tree, return the average value of the nodes on each level in the form of
     * an array.
     * <p>
     * Solution: Level order traversal
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> avgRes = new ArrayList<>();

        if (root == null) {
            return avgRes;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int currLevel = 1;
        int currNodeCount = currLevel;
        int nextLevel = 0;
        double currSum = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                q.add(node.right);
                nextLevel++;
            }

            currLevel--;
            currSum += root.val;
            if (currLevel == 0) {
                Double avg = currSum / currNodeCount;
                avgRes.add(avg);
                currLevel = nextLevel;
                currNodeCount = nextLevel;
                nextLevel = 0;
                currSum = 0;
            }
        }

        return avgRes;
    }

    /**
     * Problem 7: Given two binary trees, write a function to check if they are the same or not.
     *
     * Solution: Two binary trees are considered the same if they are structurally identical and the nodes have the
     * same value.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * Problem 8: Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from
     * left to right, level by level from leaf to root).
     *
     * Solution: Level order traversal and use stack.
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> traversalRes = new ArrayList<>();
        if (root == null) {
            return traversalRes;
        }
        Stack<List<Integer>> s = new Stack<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int currLevel = 1;
        int nextLevel = 0;
        List<Integer> levelTraversalRes = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.add(node.left);
                nextLevel++;
            }
            if (node.right != null) {
                q.add(node.right);
                nextLevel++;
            }

            currLevel--;
            levelTraversalRes.add(node.val);
            if (currLevel == 0) {
                s.push(levelTraversalRes);
                currLevel = nextLevel;
                nextLevel = 0;
                    levelTraversalRes = new ArrayList<>();
            }
        }

        while (!s.empty()) {
            traversalRes.add(s.pop());
        }

        return traversalRes;
    }

    /**
     * Problem 9: Invert a binary tree.
     *
     * Solution: Level order traversal
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }
}
