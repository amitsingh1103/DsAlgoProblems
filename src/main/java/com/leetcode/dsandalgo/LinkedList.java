package com.leetcode.dsandalgo;

import java.util.Objects;

public class LinkedList {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

    /**
     * Problem 1: Given a linked list, determine if it has a cycle in it.
     *
     * Solution: Modification of Floyd's Cycle Detection Algorithm: Shift two pointers, one by 1 node and second by 2
     * nodes. If the two node meets there is a cycle.
     *
     * To Remove: The point the two nodes meet, set the pointer of first to the first node and start shifting two
     * nodes by 1 node. The point they have same next node is the loop node. Set next of second pointer as null.
     */
    public boolean hasCycle(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return false;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (Objects.nonNull(fast) && Objects.nonNull(fast.next)) {
            if (fast == slow) {
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}
