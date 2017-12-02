package com.leetcode.dsandalgo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    private Tree tree;

    @Before
    public void createTree() {
        tree = new Tree();
        tree.root = new Tree.TreeNode(2);
        tree.root.left = new Tree.TreeNode(2);
        tree.root.right = new Tree.TreeNode(2);
        tree.root.left.left = new Tree.TreeNode(3);
        tree.root.left.right = new Tree.TreeNode(4);
        tree.root.left.left.left = new Tree.TreeNode(0);
        tree.root.right.left = new Tree.TreeNode(5);
        tree.root.right.right = new Tree.TreeNode(7);
        tree.root.right.right.left = new Tree.TreeNode(7);
    }

    @Test
    public void pathSum() {
        System.out.println(tree.pathSum(tree.root, -1));
    }

    @Test
    public void findMode() {
        for (int val : tree.findMode(tree.root)) {
            System.out.println(val);
        }
    }

    @Test
    public void findSecondMinimumValue() {
        System.out.println(tree.findSecondMinimumValue(tree.root));
    }

    @Test
    public void diameterOfBinaryTree() {
        System.out.println(tree.diameterOfBinaryTree(tree.root));
    }

    @Test
    public void hasPathSum() {
        System.out.println(tree.hasPathSum(tree.root, 9));
    }

    @Test
    public void minDepth() {
        System.out.println(tree.minDepth(tree.root));
    }

    @Test
    public void isBalanced() {
        System.out.println(tree.isBalanced(tree.root));
    }
}