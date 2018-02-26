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
     * Problem 6: Get middle node
     *
     * Solution: Two pointers. One fast: fast.next.next and another slow: slow.next
     */
    public static int getMiddleNode(ListNode head) {
        if (head == null) {
            return -1;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.val;

    }

    /**
     * Problem 5: Remove all elements from a linked list of integers that have value val.
     *
     * Solution: Logic similar to remove duplicates.
     */
    public ListNode removeElements(ListNode head, int val) {
        while (Objects.nonNull(head)) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }

        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode prevNode = head;
        ListNode current = prevNode.next;
        while(Objects.nonNull(current)) {
            int currValue = current.val;
            if (currValue != val) {
                prevNode.next = current;
                prevNode = current;
            }
            current = current.next;
        }
        prevNode.next = null;
        return head;
    }

    /**
     * Problem 4: Write a program to find the node at which the intersection of two singly linked lists begins.
     *
     * Solution: 1. count the nodes of two linked lists. Get the difference of two linked lists, say, d. Traverse the
     * larger linked list by d. Then traverse both linked list in equal speed and match the nodes.
     *
     * 2. Traverse first linked list and create a loop. Detect the loop node from second list.
     *
     * 3. Use hashing.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode intersectionNode = null;
        if (Objects.isNull(headA) || Objects.isNull(headB)) {
            return intersectionNode;
        }

        int lengthA = 0;
        ListNode current = headA;
        while (Objects.nonNull(current)) {
            current = current.next;
            ++lengthA;
        }

        int lengthB = 0;
        current = headB;
        while (Objects.nonNull(current)) {
            current = current.next;
            ++lengthB;
        }

        int lengthDiff = Math.abs(lengthA - lengthB);
        ListNode largerListHead;
        ListNode shorterListHead;
        if (lengthA > lengthB) {
            largerListHead = headA;
            shorterListHead = headB;
        } else {
            largerListHead = headB;
            shorterListHead = headA;
        }

        int displacement = 0;
        while (Objects.isNull(largerListHead) || displacement < lengthDiff) {
            largerListHead = largerListHead.next;
            ++displacement;
        }


        while (Objects.nonNull(largerListHead) && Objects.nonNull(shorterListHead)) {
            if (largerListHead == shorterListHead) {
                intersectionNode = largerListHead;
                break;
            }
            largerListHead = largerListHead.next;
            shorterListHead = shorterListHead.next;
        }

        return intersectionNode;
    }

    /**
     * Problem 3: Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Solution: Traverse linearly and change the reference of the current node to next different node.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (Objects.isNull(head)) {
            return head;
        }

        ListNode previousUnq = head;
        int prevUnqValue = previousUnq.val;
        ListNode current = previousUnq.next;
        while (Objects.nonNull(current)) {
            int currValue = current.val;
            if (currValue != prevUnqValue) {
                previousUnq.next = current;
                previousUnq = current;
                prevUnqValue = currValue;
            }
            current = current.next;
        }
        previousUnq.next = null;
        return head;
    }

    /**
     * Problem 2: Write a function to delete a node (except the tail) in a singly linked list, given only access to
     * that node.
     * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list
     * should become 1 -> 2 -> 4 after calling your function.
     *
     * Solution: Copy the value from the next node and make the next node as null.
     */
    public void deleteNode(ListNode node) {
        if (Objects.isNull(node) || Objects.isNull(node.next)) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
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
