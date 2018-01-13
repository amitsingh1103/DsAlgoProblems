package com.leetcode.dsandalgo;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgrammingPractice {

    /**
     * Problem 6: Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     *
     * Solution: Maximum profit for atmost 2 transactions will be p(i) - p(i-1) + maxProfit(i-2). It means we are
     * subtracting previous price from current price to get the current profit. In this we are trying to add
     * maxProfit till previous to previous price.
     * To achieve this, we are maintaining minimum of price - lastMaxProfit. And then we are subtracting this value
     * from next price.
     */
    public int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }

        int maxProfit2 = 0;
        int maxProfit1 = 0;
        int lowestBuyPrice2 = Integer.MAX_VALUE;
        int lowestBuyPrice1 = Integer.MAX_VALUE;
        for (int price : prices) {
            maxProfit2 = Math.max(maxProfit2, price - lowestBuyPrice2);
            lowestBuyPrice2 = Math.min(lowestBuyPrice2, price - maxProfit1);
            maxProfit1 = Math.max(maxProfit1, price - lowestBuyPrice1);
            lowestBuyPrice1 = Math.min(lowestBuyPrice1, price);
        }

        return maxProfit2;
    }

    /**
     * Problem 5: The thief has found himself a new place for his thievery again. There is only one entrance to this
     * area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the
     * smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the
     * police if two directly-linked houses were broken into on the same night.
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     *
     * Solution 1: At first glance, the problem exhibits the feature of “optimal substructure”: if we want to rob
     * maximum amount of money from current binary tree (rooted at root), we surely hope that we can do the same to
     * its left and right subtrees.
     * So going along this line, let’s define the function rob(root) which will return the maximum amount of money
     * that we can rob for the binary tree rooted at root; the key now is to construct the solution to the original
     * problem from solutions to its subproblems, i.e., how to get rob(root) from rob(root.left), rob(root.right),
     * ... etc.
     * Apparently the analyses above suggest a recursive solution. And for recursion, it’s always worthwhile figuring
     * out the following two properties:
     * Termination condition: when do we know the answer to rob(root) without any calculation? Of course when the
     * tree is empty – we’ve got nothing to rob so the amount of money is zero.
     * Recurrence relation: i.e., how to get rob(root) from rob(root.left), rob(root.right), ... etc. From the point of
     * view of the tree root, there are only two scenarios at the end: root is robbed or is not. If it is, due to the
     * constraint that “we cannot rob any two directly-linked houses”, the next level of subtrees that are available
     * would be the four “grandchild-subtrees” (root.left.left, root.left.right, root.right.left, root.right.right).
     * However if root is not robbed, the next level of available subtrees would just be the two “child-subtrees” (root
     * .left, root.right). We only need to choose the scenario which yields the larger amount of money.
     */
    public int robTreeWithRecursion(Tree.TreeNode root) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (root.left != null) {
            val += robTreeWithRecursion(root.left.left) + robTreeWithRecursion(root.left.right);
        }
        if (root.right != null) {
            val += robTreeWithRecursion(root.right.left) + robTreeWithRecursion(root.right.right);
        }

        return Math.max((root.val + val), (robTreeWithRecursion(root.left) + robTreeWithRecursion(root.right)));
    }

    /**
     * Solution 2: In step I, we only considered the aspect of “optimal substructure”, but think little about the
     * possibilities of overlapping of the subproblems. For example, to obtain rob(root), we need rob(root.left), rob
     * (root.right), rob(root.left.left), rob(root.left.right), rob(root.right.left), rob(root.right.right); but to
     * get rob(root.left), we also need rob(root.left.left), rob(root.left.right), similarly for rob(root.right). The
     * naive solution above computed these subproblems repeatedly, which resulted in bad time performance. Now if you
     * recall the two conditions for dynamic programming: “optimal substructure” + “overlapping of subproblems”, we
     * actually have a DP problem. A naive way to implement DP here is to use a hash map to record the results for
     * visited subtrees.
     */
    public int robTreeDP(Tree.TreeNode root) {
        return robTreeDPHelper(root, new HashMap<>());
    }

    private int robTreeDPHelper(Tree.TreeNode root, Map<Tree.TreeNode, Integer> dp) {
        if (root == null) {
            return 0;
        }

        if (dp.containsKey(root)) {
            return dp.get(root);
        }

        int val = 0;
        if (root.left != null) {
            val += robTreeDPHelper(root.left.left, dp) + robTreeDPHelper(root.left.right, dp);
        }
        if (root.right != null) {
            val += robTreeDPHelper(root.right.left, dp) + robTreeDPHelper(root.right.right, dp);
        }

        val = Math.max(root.val + val, (robTreeDPHelper(root.left, dp) + robTreeDPHelper(root.right, dp)));
        dp.put(root, val);

        return val;
    }

    /**
     * Solution 3: In step I, we defined our problem as rob(root), which will yield the maximum amount of money that
     * can be robbed of the binary tree rooted at root. This leads to the DP problem summarized in step II.
     * Now let’s take one step back and ask why we have overlapping subproblems. If you trace all the way back to the
     * beginning, you’ll find the answer lies in the way how we have defined rob(root). As I mentioned, for each tree
     * root, there are two scenarios: it is robbed or is not. rob(root) does not distinguish between these two cases,
     * so “information is lost as the recursion goes deeper and deeper”, which results in repeated subproblems.
     * If we were able to maintain the information about the two scenarios for each tree root, let’s see how it plays
     * out. Redefine rob(root) as a new function which will return an array of two elements, the first element of
     * which denotes the maximum amount of money that can be robbed if root is not robbed, while the second element
     * signifies the maximum amount of money robbed if it is robbed.
     * Let’s relate rob(root) to rob(root.left) and rob(root.right)..., etc. For the 1st element of rob(root), we
     * only need to sum up the larger elements of rob(root.left) and rob(root.right), respectively, since root is not
     * robbed and we are free to rob its left and right subtrees. For the 2nd element of rob(root), however, we only
     * need to add up the 1st elements of rob(root.left) and rob(root.right), respectively, plus the value robbed
     * from root itself, since in this case it’s guaranteed that we cannot rob the nodes of root.left and root.right.
     * As you can see, by keeping track of the information of both scenarios, we decoupled the subproblems and the
     * solution essentially boiled down to a greedy one.
     */
    public int robTreeGreedy(Tree.TreeNode root) {
        int[] res = robTreeGreedyHelper(root);

        return Math.max(res[0], res[1]);
    }

    private int[] robTreeGreedyHelper(Tree.TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] left = robTreeGreedyHelper(root.left);
        int[] right = robTreeGreedyHelper(root.right);

        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    /**
     * Problem 4: After robbing those houses on that street, the thief has found himself a new place for his thievery
     * so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That
     * means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain
     * the same as for those in the previous street.
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     *
     * Solution: Similar to problem 3 but run with 0 to length - 2 and 1 to length - 1.
     */
    public int robCircle(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robCircleHelper(nums, 0, nums.length - 2), robCircleHelper(nums, 1, nums.length - 1));
    }

    private int robCircleHelper(int[] nums, int low, int high) {
        int max1 = 0;
        int max2 = 0;
        int currentMax = 0;
        for (int i = low; i <= high; i++) {
            currentMax = Math.max(max2, (max1 + nums[i]));
            max1 = max2;
            max2 = currentMax;
        }

        return currentMax;
    }

    /**
     * Problem 3: You are a professional robber planning to rob houses along a street. Each house has a certain
     * amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
     * have security system connected and it will automatically contact the police if two adjacent houses were broken
     * into on the same night.
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     *
     * Solution: Get the max of current element + max1 OR max2(previous element) for each element.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max1 = 0;
        int max2 = 0;
        int currentMax = 0;
        for (int i = 0; i < nums.length; i++) {
            currentMax = Math.max(max2, (max1 + nums[i]));
            max1 = max2;
            max2 = currentMax;
        }

        return currentMax;
    }

    /**
     * Problem 2: You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Solution 1: Recursion with Memoization. We can recur(brute force approach) for sum of (i+1, i+2)[it takes 2^n
     * time to get the result] and at the same time memoize ith computed result to use it further.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int climbStairsWithRecursionAndMemoization(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] memo = new int[n];
        return climbStairsWithRecursionAndMemoizationHelper(0, n, memo);
    }

    private int climbStairsWithRecursionAndMemoizationHelper(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }

        if (i == n) {
            return 1;
        }

        if (memo[i] != 0) {
            return memo[i];
        }

        memo[i] = climbStairsWithRecursionAndMemoizationHelper(i + 1, n, memo) +
                climbStairsWithRecursionAndMemoizationHelper(i + 2, n, memo);
        return memo[i];
    }

    /**
     * Solution 2: With the above approach it is clear that there is an overlap. And we can easily break the solution
     * for i to i+1 and i+2. So the above easily be solved by dynamic programming. Ith step solution is the sum of
     * steps required to reach i - 1 and i - 2.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int climbStairsWithDP(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Solution 3: With the above approach this is clear a fibonacci series. And to reach nth step it will n element
     * in fibonacci series.
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int climbStairsWithFibonacciSeries(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int first = 1;
        int second = 2;
        int third = second;
        for (int i = 3; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }

        return third;
    }

    /**
     * With above approach it is clear that the problem can be solved by fibonacci series and hence we can simply use
     * fibonacci formula to get the solution for nth step.
     * F(n) = 1/sqrt5(((1 + sqrt5)/ 2)^n - (1 - sqrt5)/ 2)^n)
     *
     * Time Complexity: O(logn), since pow takes logn steps to get the result.
     * Space complexity: O(1)
     */
    public int climbStairWithFibonacciFormula(int n) {
        if (n <= 0) {
            return 0;
        }
        double sqrt5 = Math.sqrt(5);
        double fibDiff = Math.pow(((1 + sqrt5) / 2), n + 1) - Math.pow(((1 - sqrt5) / 2), n + 1);
        return (int) (fibDiff / sqrt5);
    }


    /**
     * Problem 1: On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of
     * the floor, and you can either start from the step with index 0, or the step with index 1.
     *
     * Solution: Get the effective cost of index i which will be cost[i] + Min(cost[i-1], cost[i-2]). At the end get
     * the minimum of last and second to last index cost.
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length <= 1) {
            return 0;
        }

        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 2], cost[i - 1]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
