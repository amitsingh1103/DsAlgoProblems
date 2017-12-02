package com.leetcode.dsandalgo;

import java.util.*;

public class Tree {

    TreeNode root;

    static class TreeNode {
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
    int maxFrequency = Integer.MIN_VALUE;
    public int longestUniValuePath(TreeNode root) {
        calculateLongestUniValuePath(root);
        return maxFrequency;
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

        maxFrequency = Math.max(maxFrequency, leftSum + rightSum);
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

    /**
     * Problem 10: Given a binary tree, return all root-to-leaf paths.
     *
     * Solution: Traverse recursively from root to leaf node. If right/left is not null, create stemp string from
     * pathSoFar and append the right/left value in it. Then call the method recursively.
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> allRootToLeafPaths = new ArrayList<>();

        if (root == null) {
            return allRootToLeafPaths;
        }
        findAllRootToLeafPaths(root, String.valueOf(root.val), allRootToLeafPaths);
        return allRootToLeafPaths;
    }

    private void findAllRootToLeafPaths(TreeNode root, String pathSoFar, List<String> allRootToLeafPaths) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            allRootToLeafPaths.add(pathSoFar);
            return;
        }

        if (root.left != null) {
            String pathLeft = pathSoFar;
            StringBuilder builder = new StringBuilder();
            pathLeft = builder.append(pathLeft).append("->").append(root.left.val).toString();
            findAllRootToLeafPaths(root.left, pathLeft, allRootToLeafPaths);
        }
        if (root.right != null) {
            String pathRight = pathSoFar;
            StringBuilder builder = new StringBuilder();
            pathRight = builder.append(pathRight).append("->").append(root.right.val).toString();
            findAllRootToLeafPaths(root.right, pathRight, allRootToLeafPaths);
        }
    }

    /**
     * Problem 11: Find the sum of all left leaves in a given binary tree.
     *
     * Solution: For each node, if left child is not null and it is a leaf node then add the value in total sum. Call
     * this method recursively for left and right child.
     */
    int totalLeftSum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            totalLeftSum += root.left.val;
        }

        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);

        return totalLeftSum;
    }

    /**
     * Problem 12: You are given a binary tree in which each node contains an integer value. Find the number of paths
     * that sum to a given value.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from
     * parent nodes to child nodes).
     *
     * Solution: do DFS on root node for all the possibilities(node and without node). O(n^2) time complexity.
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSumForNode(root,sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumForNode(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return (root.val == sum ? 1 : 0) + pathSumForNode(root.left, sum - root.val) + pathSumForNode(root.right, sum
                - root.val);
    }

    /**
     * Problem 13: Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently
     * occurred element) in the given BST.
     *
     * Solution: Inorder traversal on BST generates a sorted list. Compare prev value to the current value in order
     * to get the count of same value of the node.
     * Solution 1: One way to do is to use a list and convert it to array.
     *
     * Solution 2: But if we do not want to use a list then we have to double pass inorderHandler method. In first
     * pass we will get the total size of the array, it can be 1 or greater than 1. In next pass we will initialize
     * the array with the maxCount we got. Then we will make modeCount as 0 before going to second pass.
     *
     * Solution 3: If we do not want recursive solution then we can have morris algo to do inorder traversal of bt.
     */
    int maxFreq;
    int currCount;
    int currValue;
    int modeCount;
    int modes[];
    public int[] findMode(TreeNode root) {
        inorderHandler(root);
        modes = new int[modeCount];
        modeCount = 0;
        inorderHandler(root);
        return modes;
    }

    private void inorderHandler(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderHandler(root.left);
        if (currValue != root.val) {
            currValue = root.val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxFreq) {
            maxFreq = currCount;
            modeCount = 1;
        } else if (currCount == maxFreq) {
            if (modes != null) {
                modes[modeCount] = root.val;
            }
            modeCount++;
        }
        inorderHandler(root.right);
    }

    /**
     * Problem 14: Given a non-empty special binary tree consisting of nodes with the non-negative value, where each
     * node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is
     * the smaller value among its two sub-nodes.
     * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value
     * in the whole tree.
     * If no such second minimum value exists, output -1 instead.
     *
     * Solution: If a node cannot be a candidate for second minimum then return and do not call it's right or left
     * child. And if a node become a current secondMin value then also do not call left and right child of it.
     * Else always call left and right child.
     */
    int minValue;
    int secondMninValue = Integer.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return secondMninValue;
        }

        minValue = root.val;
        computeSecondMinValue(root);
        return secondMninValue == Integer.MAX_VALUE ? -1 : secondMninValue;
    }

    private void computeSecondMinValue(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val > secondMninValue) {
            return;
        }
        if (root.val > minValue && root.val < secondMninValue) {
            secondMninValue = root.val;
            return;
        }

        computeSecondMinValue(root.left);
        computeSecondMinValue(root.right);
    }

    /**
     * Problem 15: Given a binary tree, you need to compute the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may
     * or may not pass through the root.
     *
     * Solution: This problem is similar to finding LongestUniValuePath.
     */
    int diameterNotFromRoot = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max( getDiameter(root), diameterNotFromRoot);
    }

    private int getDiameter(TreeNode root) {
        int left = root.left == null ? 0 : getDiameter(root.left) + 1;
        int right = root.right == null ? 0 : getDiameter(root.right) + 1;
        diameterNotFromRoot = Math.max(diameterNotFromRoot, left + right);
        return Math.max(left, right);
    }

    /**
     * Problem 16: Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up
     * all the values along the path equals the given sum.
     *
     * Solution: Each time subtract the node value from the sum to get 0 till leaf node arrives. Return true. Or
     * condition between root.left and root.right. Recursive solution
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * Problem 17: Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
     * node.
     *
     * Solution: Solution is similar to get height of BT with a tweak that instead of getting max get min. Here we
     * need to handle the case where if either lheight or rHeight is 0, then we need to get the lheight/rHeight + 1.
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lHeight = minDepth(root.left);
        int rHeight = minDepth(root.right);

        return (lHeight == 0 || rHeight == 0) ? lHeight + rHeight + 1 : Math.min(lHeight, rHeight) + 1;
    }

    /**
     * Problem 18: Given a binary tree, determine if it is height-balanced.
     *
     * Solution: Bottom to up.
     * Solution 1: Iterating over each node and get the depth of each node and abs(lHeight - rHeight) <= 1 &&
     * isBalanced(root.left) && isBalanced(root.right)
     *
     * Solution 2: Using DFS get the height starting from left most node and if lheight == -1 or rHeight == -1 then
     * return -1 to its parent node.
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedByDfs(root) != -1;
    }

    private int isBalancedByDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lHeight = isBalancedByDfs(root.left);
        if (lHeight == -1) {
            return -1;
        }
        int rHeight = isBalancedByDfs(root.right);
        if (rHeight == -1) {
            return -1;
        }

        if (Math.abs(lHeight - rHeight) > 1) {
            return -1;
        }
        return Math.max(lHeight, rHeight) + 1;
    }
}
