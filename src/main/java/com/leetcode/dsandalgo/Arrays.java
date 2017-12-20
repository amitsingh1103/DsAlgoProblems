package com.leetcode.dsandalgo;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by amit on 6/8/17.
 */
public class Arrays {

    /**
     * Problem 1: Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * The digits are stored such that the most significant digit is at the head of the list.
     *
     * Solution: Iterate from last digit and iterate by 1. If value of digit is 10, then do the same increment for
     * next element else break. If at the end carry is still there then increase the sizeof array and store 1 at the
     * index 0.
     */
    public int[] plusOne(int[] digits) {
        int size = digits.length;
        int carry = 0;
        for (int i = size - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                break;
            }
        }

        if (carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            for (int i = 1; i < newDigits.length; i++) {
                newDigits[i] = digits[i - 1];
            }
            return newDigits;
        }
        return digits;
    }

    /*
      Problem 2: Given an index k, return the kth row of the Pascal's triangle.
      For example, given k = 3, return [1,3,3,1].

      Solution: C(line, i) = C(line, i-1) * [(line - i + 1) / i]
    */
    public List<Long> getRowInPascalTriangle(int row) {
        List<Long> result = new ArrayList<>();
        Long binCoeffVal = 1L;
        for (int i = 1; i <= row+1; i++) {
            result.add(binCoeffVal);
            binCoeffVal = (binCoeffVal * (row - (i - 1))) / i;
        }

        return result;
    }

    /*
    Problem 3: Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1),
     (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

     Solution: sort the list and take consecutive elements to create all pairs.
    */
    public int arrayPairSum(int[] nums) {
        java.util.Arrays.sort(nums);
        int sum = 0;
        if (nums.length % 2 == 0) {
            return sum;
        }

        for (int i = 0; i < nums.length; i++) {
            sum += Math.min(nums[i], nums[i+1]);
        }

        return sum;
    }

    /*
    Problem 4: Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Solution: Insert all elements in the list(O(n) to iterate and O(1) to insert) and then find the diff in the
    table(O(1) to serch). In case of dupes, check at the time of insertion only if double of value is the target.
    LinkedHashMap for the index order.
    */
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        Map<Integer, Integer> intMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!intMap.containsKey(nums[i])) {
                intMap.put(nums[i], i);
            } else if (2 * nums[i] == target) {
                ret[0] = intMap.get(nums[i]);
                ret[1] = i;
                return ret;
            }
        }

        for (Integer key : intMap.keySet()) {
            Integer probeElement = target - key;
            if (intMap.containsKey(probeElement) && probeElement != key) {
                ret[0] = intMap.get(key);
                ret[1] = intMap.get(probeElement);
                return ret;
            }
        }

        return ret;
    }

    /*
    Problem 5: Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and
    others appear once. Find all the elements of [1, n] inclusive that do not appear in this array.
    Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

    Solution: Negate element by using them as index of the array. viz. nums[nums[i] - 1] = -1 * nums[nums[i] - 1].
    Index on which elements are +ve will be the missing ones.
    */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -1 * nums[val];
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }

    /*
    Problem 6: Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
    return the maximum number. The time complexity must be in O(n).

    Solution: maintain three variables for O(n) runtime solution.
    */
    public int thirdMax(int[] nums) {
        int _1max = Integer.MIN_VALUE;
        int _2max = Integer.MIN_VALUE;
        int _3max = Integer.MIN_VALUE;

        int uniqueCount = 0;
        boolean intMinValCountRequired = true;
        for (int num : nums) {
            if (num > _1max) {
                _3max = _2max;
                _2max = _1max;
                _1max = num;
                uniqueCount++;
            } else if (num > _2max && num < _1max) {
                _3max = _2max;
                _2max = num;
                uniqueCount++;
            } else if (num > _3max && num < _2max) {
                _3max = num;
                uniqueCount++;
            }

            if (intMinValCountRequired && num == Integer.MIN_VALUE) {
                intMinValCountRequired = false;
                uniqueCount++;
            }
        }

        if (uniqueCount < 3) {
            _3max = _1max;
        }

        return _3max;
    }

    /*
    Problem 7: Given an integer array, find three numbers whose product is maximum and output the maximum product.

    Solution: sort and then compare multiplication of first two and last number AND last three numbers. O(logN)
    complexity.
    */
    public int maximumProduct(int[] nums) {
        java.util.Arrays.sort(nums);
        int lastIndex = nums.length - 1;
        int _1comp = nums[0] * nums[1] * nums[lastIndex];
        int _2comp = nums[lastIndex - 2] * nums[lastIndex - 1] * nums[lastIndex];

        return _1comp > _2comp ? _1comp : _2comp;
    }

    /**
     * Problem 8: Given an array of size n, find the majority element. The majority element is the element that appears
     * more than ⌊ n/2 ⌋ times. You may assume that the array is non-empty and the majority element always exist in the
     * array.
     *
     * Solution: Moores Voting Algorithm. Increment the count whenever the same element is found and decrement it
     * whenever the different element found. If count zero make the element as maj_ele.
     * At the end check for majority element.
     */
    public int majorityElement(int[] nums) {
        int maj_ele = nums[0];
        int count = 1;

        int i = 1;
        while (i < nums.length) {
            if (nums[i] == maj_ele) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                maj_ele = nums[i];
                count = 1;
            }
            i++;
        }

        return maj_ele;
    }

    /**
     * Problem 9: Given a sorted array, remove the duplicates in place such that each element appear only once and
     * return the new length.
       Do not allocate extra space for another array, you must do this in place with constant memory.
       For example,
       Given input array nums = [1,1,2],
       Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
       It doesn't matter what you leave beyond the new length.

       Solution: Since the array is sorted, remove the elements(shifting of array) if duplicates are found.
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }

        int last = nums[0];
        int lastUniqueElementIndex = 0;
        int uniqueCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (last != nums[i]) {
                uniqueCount++;
                nums[++lastUniqueElementIndex] = nums[i];
            }
            last = nums[i];
        }

        return uniqueCount;
    }

    /**
     * Problem 10: Given an array of integers that is already sorted in ascending order, find two numbers such that they
     * add up to a specific target number.
       The function twoSum should return indices of the two numbers such that they add up to the target, where index1
       must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
       You may assume that each input would have exactly one solution and you may not use the same element twice.

       Solution: Maintain two counters low and high and search for the sum.
     */
    public int[] twoSumSorted(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        int[] ret = new int[2];
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum == target) {
                ret[0] = low + 1;
                ret[1] = high + 1;
                break;
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }

        return ret;
    }

    /**
     * Problem 11: Given an array and a value, remove all instances of that value in place and return the new length.
       Do not allocate extra space for another array, you must do this in place with constant memory.
       The order of elements can be changed. It doesn't matter what you leave beyond the new length.

       Solution: Get the index for the first time and start shifting from the next element. Whenever the element to be
     removed will come just skip the element without doing anything.
     */
    public int removeElement(int[] nums, int val) {
        int newIndex = nums.length;
        boolean elementToBeRemovedAlreadyFound = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val && !elementToBeRemovedAlreadyFound) {
                elementToBeRemovedAlreadyFound = true;
                newIndex = i;
            }

            if (nums[i] != val && elementToBeRemovedAlreadyFound) {
                nums[newIndex++] = nums[i];
            }
        }

        return newIndex;
    }

    /**
     * Problem 12: Given an integer array, you need to find one continuous subarray that if you only sort this subarray
     * in ascending order, then the whole array will be sorted in ascending order, too.
     * You need to find the shortest such subarray and output its length.
     *
     * Solution:
     1) Find the candidate unsorted subarray
     a) Scan from left to right and find the first element which is greater than the next element. Let s be the index
     of such an element. In the above example 1, s is 3 (index of 30).
     b) Scan from right to left and find the first element (first in right to left order) which is smaller than the
     next element (next in right to left order). Let e be the index of such an element. In the above example 1, e is 7
     (index of 31).

     2) Check whether sorting the candidate unsorted subarray makes the complete array sorted or not. If not, then
     include more elements in the subarray.
     a) Find the minimum and maximum values in arr[s..e]. Let minimum and maximum values be min and max. min and max
     for [30, 25, 40, 32, 31] are 25 and 40 respectively.
     b) Find the first element (if there is any) in arr[0..s-1] which is greater than min, change s to index of this
     element. There is no such element in above example 1.
     c) Find the last element (if there is any) in arr[e+1..n-1] which is smaller than max, change e to index of this
     element. In the above example 1, e is changed to 8 (index of 35)

     3) Print s and e.
     */
    public int findShortestUnsortedContiguousSubArray(int[] nums) {
        int start = 0;
        int end = 0;
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                start = i;
                break;
            }
        }

        for (int i = length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                end = i;
                break;
            }
        }

        //get the maximum and minimum value.
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }

            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }

        // Get the actual start and end index.
        for (int i = 0; i < start; i++) {
            if (nums[i] > minVal) {
                start = i;
                break;
            }
        }

        for (int i = length - 1; i > end; i--) {
            if (nums[i] < maxVal) {
                end = i;
                break;
            }
        }

        if (end == 0 && start == 0) {
            return 0;
        }
        return end - start + 1;
    }

    /**
     * Problem 13: In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new
     * one with different size but keep its original data.
     * You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the
     * row number and column number of the wanted reshaped matrix, respectively.
     * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing
     * order as they were
     * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix;
     * Otherwise, output the original matrix.
     *
     * Solution: Check if resize possible; R*C == r*c, if yes store all elements in an array row-wise and then
     * iterate over the resultant array and insert the array values.
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if ((nums.length * nums[0].length) != (r * c)) {
            return nums;
        }

        int[][] ret = new int[r][c];
        int[] rowWiseArray = new int[r * c];
        int i = 0;
        for (int row = 0; row < nums.length; row++) {
            for (int col = 0; col < nums[0].length; col++) {
                rowWiseArray[i++] = nums[row][col];
            }
        }

        int j = 0;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                ret[row][col] = rowWiseArray[j++];
            }
        }

        return ret;
    }

    /**
     * Problem 14: Given a sorted array and a target value, return the index if the target is found. If not, return the
     * index where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     *
     * Solution: Apply binary search algorithm with the condition if low < high then low.
     */
    public int searchInsert(int[] nums, int target) {
        return binarySearchInsert(nums, 0, nums.length - 1, target);
    }

    private int binarySearchInsert(int[] nums, int low, int high, int key) {
        if (high < low) {
            return low;
        }

        int mid = (high + low) / 2;

        if (nums[mid] == key) {
            return mid;
        }

        if (key < nums[mid]) {
            return binarySearchInsert(nums, low, mid - 1, key);
        }
        return binarySearchInsert(nums, mid + 1, high, key);
    }

    /**
     * Problem 15: Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * Note:
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
     * from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     *
     * Solution: Remember here if nums1 is initialized by n then first n elements will have value and non-initialized
     * elements will have 0. Shift all the elements from numsLength to n. Then compare each element of nums1 and nums2
     * as in merge sort.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Size = nums1.length;

        int k = m - 1;
        for (int i = nums1Size - 1; i >= n; i--) {
            nums1[i] = nums1[k];
            k--;
        }

        int i = n;
        int j = 0;
        int p = 0;
        while (i < nums1Size && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums1[p] = nums1[i];
                i++;
                p++;
            } else {
                nums1[p] = nums2[j];
                j++;
                p++;
            }
        }

        if (i < nums1Size) {
            for (int q = i; q < nums1Size; q++) {
                nums1[p] = nums1[q];
                p++;
            }
        }

        if (j < n) {
            for (int q = j; q < n; q++) {
                nums1[p] = nums2[q];
                p++;
            }
        }
    }

    /**
     * Problem 16: Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother
     * to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding
     * cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
     *
     * Solution: Iterate over the combination of (-1, 0, +1) and (-1, 0, +1).
     */
    public int[][] imageSmoother(int[][] M) {
        int[][] ret = new int[M.length][M[0].length];

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                ret[i][j] = getAverageGrayScale(M, i, j);
            }
        }

        return ret;
    }

    private int getAverageGrayScale(int[][] M, int row, int col) {
        int divisor = 0;
        int sumIncItself = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i < 0 || col + j < 0 || row + i > M.length - 1 || col + j > M[0].length - 1) {
                    continue;
                }
                divisor++;
                sumIncItself += M[row + i][col + j];
            }
        }

        return (sumIncItself / divisor);
    }

    /**
     * Problem 17: Given an array with n integers, your task is to check if it could become non-decreasing by
     * modifying at most 1 element.
     *
     * Solution: Greedy Solution: If i < i-1 then do not increase i since it may create inversions for position
     * greater than i, so decrease i-1. If i-2 is also greater than i then either decrease i-2 and i-1 or increase i.
     * We will increase the value of i. Copy value of i to i-1 if i-2<0 ori-2 <= i ELSE copy i-1 to i.
     */
    public boolean checkPossibility(int[] nums) {
        int counter = 0;
        for (int i = 1, size = nums.length; i < size && counter <= 1; i++) {
            if (nums[i] < nums[i - 1]) {
                counter++;
                if (i - 2 < 0 || (nums[i - 2] <= nums[i])) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return counter <= 1;
    }

    /**
     * Problem 18: Given an array consisting of n integers, find the contiguous subarray of given length k that has
     * the maximum average value. And you need to output the maximum average value.
     *
     * Solution: Sliding window. Calculate first k elements sum. and then exclude first and include kth element. and
     * then get max everytime. O(n) solution.
     */
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        long max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(sum, max);
        }
        return max * 1.0/k;
    }

    /**
     * Problem 19: Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from
     * two different arrays (each array picks one) and calculate the distance. We define the distance between two
     * integers a and b to be their absolute difference |a-b|.
     *
     * Solution: In a single iteration, take first and last index and subtract it from min and max. Then store the
     * min and max out of those. At the same time seek for maxDistance. O(n)
     */
    public long findMaximumDistance(int[][] list) {
        int min = list[0][0];
        int max = list[list[0].length - 1][list[0].length - 1];

        long maxDistance = 0;
        for (int i = 1; i < list.length; i++) {
            int _1distance = Math.abs(min - list[i][list[i].length - 1]);
            int _2distance = Math.abs(max - list[i][0]);

            maxDistance = Math.max(Math.max(_1distance, _2distance), maxDistance);
            min = Math.min(min, list[i][0]);
            max = Math.max(max, list[i][list[i].length - 1]);
        }

        return maxDistance;
    }

    /**
     * Problem 20: Suppose you have a long flowerbed in which some of the plots are planted and some are not.
     * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
     * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and
     * a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
     *
     * Solution: Will be less than O(n). Look conditions for the solution.
     */
    public boolean canPlaceFlowers(int[] flowerBed, int n) {
        for (int i = 0, size = flowerBed.length; i < size &&  n != 0; i++) {
            // Conditions
            if (flowerBed[i] == 0) {
                if ((i - 1 < 0 && i + 1 >= size)
                        || (i - 1 < 0 && flowerBed[i + 1] == 0)
                        || (i + 1 >= size && flowerBed[i - 1] == 0)
                        || (i - 1 >= 0 && i + 1 <= size && flowerBed[i - 1] == 0) && flowerBed[i + 1] == 0)
                {
                    flowerBed[i] = 1;
                    n--;
                    i++;
                }
            }
        }

        return n == 0;
    }

    /**
     * Problem 21: Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in
     * the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the
     * array and their absolute difference is k.
     *
     * Solution: For duped elements we will search at the time of insertion to map. If we will be able to find out non
     * zero result value their only then we can return directly. Else if k <= 0, we are either searching for duped
     * elements or no element at all[absolute diff].
     * Else we can go for distinct element search.
     */
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> intMap = new HashMap<>();
        int uniquePairCount = 0;
        boolean skipFurther = false;
        for (int i = 0; i < nums.length; i++) {
            if (Objects.nonNull(intMap.putIfAbsent(nums[i], 1))) {
                intMap.put(nums[i], (intMap.get(nums[i]) + 1));
                if ((intMap.get(nums[i]) == 2) && (nums[i] - k == nums[i])) {
                    uniquePairCount++;
                    skipFurther = true;
                }
            }
        }

        // No need to search for distinct elements. Skipping.
        if (skipFurther || k <= 0) {
            return uniquePairCount;
        }
        for (Map.Entry<Integer, Integer> element : intMap.entrySet()) {
            int probe = element.getKey() + k;
            if (intMap.containsKey(probe)) {
                uniquePairCount++;
            }
        }

        return uniquePairCount;
    }

    /**
     * Problem 22: Given a binary array, find the maximum number of consecutive 1s in this array.
     *
     * Solution: Linear iteration. Increment counter with 1 till find 0 where make counter as 0. Store max everytime.
     */
    public int maxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }

            if (nums[i] == 0) {
                count = 0;
            }

            max = Math.max(count, max);
        }

        return max;
    }

    /**
     * Problem 23: Given an array nums, write a function to move all 0's to the end of it while maintaining the
     * relative order of the non-zero elements.
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     *
     * Solution: Linear iteration, with j = 0, copy non-zero element to j and increment j by 1. At the end assign 0
     * to all elements from j to nums.length
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * Problem 24: Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is
     * missing from the array.
     * For example, Given nums = [0, 1, 3] return 2.
     * Note: Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra
     * space complexity?
     *
     * Solution: n*(n+1)/2 - sum of all elements of the array
     */
    public int missingNumber(int[] nums) {
        int size = nums.length;
        int _nSum = (size * (size + 1)) / 2;
        int _arrSum = 0;
        for (int i = 0; i < size; i++) {
            _arrSum += nums[i];
        }

        return _nSum - _arrSum;
    }

    /**
     * Problem 25: Given a list of words and two words word1 and word2, return the shortest distance between these
     * two words in the list.
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * Given word1 = “coding”, word2 = “practice”, return 3.
     * Given word1 = "makes", word2 = "coding", return 1.
     *
     * Solution: If w1, w2 are the words then linearly iterate and whenever find the word then store the index and
     * get the minimum abs diff.
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int n = -1;
        int m = -1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                n = i;
                if (m != -1) {
                    min = Math.min(min, Math.abs(n - m));
                }
            } else if (words[i].equals(word2)) {
                m = i;
                if (n != -1) {
                    min = Math.min(min, Math.abs(n - m));
                }
            }
        }

        return min;
    }

    /**
     * Problem 26: Given an array of integers and an integer k, find out whether there are two distinct indices i and
     * j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     *
     * Solution: Variant of sliding window problem. Remember diff is <b>atmost</b> k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k <= 0) {
            return false;
        }

        Map<Integer, Integer> intMap  = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (Objects.nonNull(intMap.putIfAbsent(nums[i], i))) {
                if (Math.abs(i - intMap.get(nums[i])) <= k) {
                    return true;
                } else {
                    intMap.put(nums[i], i);
                }
            }
        }

        return false;
    }

    /**
     * Problem 27: Given an array of integers, find if the array contains any duplicates. Your function should return
     * true if any value appears at least twice in the array, and it should return false if every element is distinct.
     *
     * Solution: Use set but faster should be the use of map.
     */
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> intMap = new HashMap<>();
        for (Integer num : nums) {
            if (Objects.nonNull(intMap.putIfAbsent(num, 1))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Problem 28: Rotate an array of n elements to the right by k steps.
     * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
     * Note: Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
     *
     * Solution: Reverse from 0, n-1 then 0 to k-1 then k to n-1. K are steps not elements by which we need to rotate
     * to right so calculate mod: k = k%n
     */
    public void rotateArray(int[] nums, int k) {
        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * Problem 29: Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     *
     * Solution: first buy then sell. So iterate from left to right and store the min val encountered and maxDiff
     * generated.
     */
    public int maxPrices(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);

            int currProfit = prices[i] - min;
            maxProfit = Math.max(maxProfit, currProfit);
        }

        return maxProfit;
    }

    /**
     * Problem 30: Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the
     * same time (ie, you must sell the stock before you buy again).
     *
     * Solution: With peak and valley approach, find a peak just followed by valley(i > i-1) and get the diff. Do it
     * consecutively and additively, here the:
     * 1. idea of consecutive is i<j<k then [k-i = j-1 + k-j]
     * 2. idea of additive is Max peak and min valley will always be lower than and equals to the sum of the diff
     * of local peak and local valley.
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    /**
     * Problem 31: Given numRows, generate the first numRows of Pascal's triangle.
     *
     * Solution: Use the logic in [problem 2] to generate rows of pascal's triangle.
     */
    public List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> rowList = new ArrayList<>();
            Integer binomialCoefficient = 1;
            for (int j = 1; j <= i; j++) {
                rowList.add(binomialCoefficient);
                binomialCoefficient = (binomialCoefficient * (i - (j - 1))) / j;
            }
            rowList.add(binomialCoefficient);
            resultList.add(rowList);
        }

        return resultList;
    }

    /**
     * Problem 32: Given an unsorted array of integers, find the length of longest continuous increasing subsequence.
     *
     * Solution: Store the max_so_far till the lower value found.
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxCount = 1;
        int countSoFor = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                countSoFor = 0;
            }
            countSoFor++;
            maxCount = Math.max(maxCount, countSoFor);
        }
        return maxCount;
    }

    /**
     * Problem 33: Find the contiguous subarray within an array (containing at least one number) which has the
     * largest sum.
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     *
     * Solution: Use kadane's algorithm.
     * ALso just extended to store the indexes also.
     */
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currSum = nums[0];

        int lowIndex = 0;
        int currLowIndex = 0;
        int highIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            /*currSum = Math.max(nums[i], nums[i] + currSum);
            maxSoFar = Math.max(maxSoFar, currSum);*/
            if (nums[i] > (currSum + nums[i])) {
                currSum = nums[i];
                currLowIndex = i;
            } else {
                currSum = nums[i] + currSum;
            }

            if (currSum > maxSoFar) {
                maxSoFar = currSum;
                lowIndex = currLowIndex;
                highIndex = i;
            }
        }

        System.out.println("Low[" + lowIndex + "], High[" + highIndex + "]");
        return maxSoFar;
    }

    /**
     * Problem 34: Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
     * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded
     * by water. Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is
     * 0.)
     *
     * Solution: Using DFS on the grid. Iterate over each co-ordinate:-
     * 1. If it is 1 add 1 to it. Explore 4-directionally the coordinate in DFS manner.
     * 2. Return 0 if the element is 0 OR c < 0 or r < 0 or c >= length or r >= length OR it is already seen.
     * 3. We have to mark each co-ordinate as seen since we must not the explored co-ordinate again.
     * 4. Take maximum of these iterations.
     *
     * Explanation: We want to know the area of each connected shape in the grid, then take the maximum of these.
     * If we are on a land square and explore every square connected to it 4-directionally (and recursively squares
     * connected to those squares, and so on), then the total number of squares explored will be the area of that
     * connected shape.
     * To ensure we don't count squares in a shape more than once, let's use seen to keep track of squares we
     * haven't visited before. It will also prevent us from counting the same shape more than once.
     */
    private boolean[][] seen;
    public int maxAreaOfIsland(int[][] grid) {
        seen = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for (int i = 0; i  < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
               ans = Math.max(ans, area(i, j, grid));
            }
        }
        return ans;
    }

    private int area(int r, int c, int[][] grid) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || seen[r][c] || grid[r][c] == 0) {
            return 0;
        }

        seen[r][c] = true;
        return 1 + area(r + 1, c, grid) + area(r - 1, c, grid) + area(r, c + 1, grid) + area(r, c - 1, grid);
    }

    /**
     * Problem 35: Given a non-empty array of non-negative integers nums, the degree of this array is defined as the
     * maximum frequency of any one of its elements. Your task is to find the smallest possible length of a
     * (contiguous) subarray of nums, that has the same degree as nums.
     *
     * Solution: If d is the degree then there can be x elements that occur d times in the array. Maintain 3 map
     * one for the count and another for the left and right index of the each element in the array of its last
     * occurrence in the array.
     * In the next for loop try to get the maxCount for which length will be minimum.
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            int numCount = count.getOrDefault(nums[i], 0);
            count.put(nums[i], numCount + 1);
            left.putIfAbsent(nums[i], i);
            right.put(nums[i], i);
        }

        int maxCount = 0;
        int minLength = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > maxCount) {
                minLength = right.get(entry.getKey()) - left.get(entry.getKey()) + 1;
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                minLength = Math.min(minLength, right.get(entry.getKey()) - left.get(entry.getKey()) + 1);
            }
        }

        return minLength;
    }

    /**
     * Problem 36: We have two special characters. The first character can be represented by one bit 0. The second
     * character can be represented by two bits (10 or 11). Now given a string represented by several bits. Return
     * whether the last character must be a one-bit character or not. The given string will always end with a zero.
     *
     * Solution: If 1 bit is found in linear traversal of the array then make isOneBitCharacter as false and skip next
     * element. Else make isOneBitCharacter true;
     */
    public boolean isOneBitCharacter(int[] bits) {
        boolean isOneBitCharacter = true;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 1) {
                isOneBitCharacter = false;
                i++;
                continue;
            }
            isOneBitCharacter = true;
        }

        return isOneBitCharacter;
    }

    /**
     * Problem 37: Given an array of integers nums, write a method that returns the "pivot" index of this array. We
     * define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum
     * of the numbers to the right of the index.
     * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the
     * left-most pivot index.
     *
     * Solution: We need to quickly compute the sum of values to the left and the right of every index.
     * Let's say we knew S as the sum of the numbers, and we are at index i. If we knew the sum of numbers leftsum
     * that are to the left of index i, then the other sum to the right of the index would just be S - nums[i] -
     * leftsum.
     * As such, we only need to know about leftsum to check whether an index is a pivot index in constant time. Let's
     * do that: as we iterate through candidate indexes i, we will maintain the correct value of leftsum.
     */
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * Problem 38: Given a collection of intervals, merge all overlapping intervals.
     *
     * Solution: Sort the list of intervals by their start time. Compare the start time of second interval with the
     * end time of first interval and merge them.
     */
    private class Interval {
        private int start;
        private int end;

        public Interval(){
            start = 0;
            end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        intervals.sort(Comparator.comparing(interval -> interval.getStart()));

        if (intervals.isEmpty()) {
            return res;
        }
        res.add(intervals.get(0));
        for (Interval interval : intervals) {
            int size = res.size();
            if (interval.getStart() <= res.get(size - 1).getEnd()) {
                int endTime = Math.max(res.get(size - 1).getEnd(), interval.getEnd());
                res.get(size - 1).setEnd(endTime);
                continue;
            }
            res.add(interval);
        }
        return res;
    }

    /**
     * problem 39: Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and
     * others appear once. Find all the elements that appear twice in this array.
     * Could you do it without extra space and in O(n) runtime?
     *
     * Solution: Whenever find an element go to that index and negate the value. Whenever any element is accessed
     * with negative sign then it's a duplicate.
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                res.add(Math.abs(num));
            }
            nums[Math.abs(num) - 1] = -1 *  nums[Math.abs(num) - 1];
        }
        return res;
    }

    /**
     * Problem 40: Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all
     * unique triplets in the array which gives the sum of zero.
     * The solution set must not contain duplicate triplets.
     *
     * Solution: Sort the list so that we can get the result with two pointers logic[bidirectional 2sum sweep of the
     * list]. We need to skip the duplicate result by skipping those elements which are equal to previous element in
     * the sorted list.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1;
                int high = nums.length - 1;
                int sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        triplets.add(java.util.Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low + 1] == nums[low]) {
                                low++;
                        }
                        while (low < high && nums[high - 1] == nums[high]) {
                                high--;
                        }

                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return triplets;
    }

    /**
     * Problem 41: Given an array of non-negative integers, you are initially positioned at the first index of the
     * array. Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     *
     * Solution: It is a dynamic programming/backtracking problem if we need to find out the minimum jump but in
     * order to get if we can jump to last index, we can simply start from the last but one index. Find if maxJump
     * from that index is greater than current last index then it's a good index. At the end if we are able to get
     * last index value <= 0 then we can reach else not.
     */
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = last - 1; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        if (last == 0) {
            return true;
        }
        return false;
    }

    /**
     * Problem 42: Given an array S of n integers, find three integers in S such that the sum is closest to a given
     * number, target. Return the sum of the three integers. You may assume that each input would have exactly one
     * solution.
     *
     * Solution: Similar to problem 40. O(n^2) solution.
     */
    public int threeSumClosest(int[] nums, int target) {
        int closestValue = nums[0];
        int smallestDiff = Integer.MAX_VALUE;
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if (smallestDiff > Math.abs(target - (nums[low] + nums[high] + nums[i]))) {
                    smallestDiff = Math.abs(target - (nums[low] + nums[high] + nums[i]));
                    closestValue = nums[low] + nums[high] + nums[i];
                }

                if (nums[i] + nums[low] + nums[high] == target) {
                    return target;
                } else if (nums[i] + nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return closestValue;
    }

    /**
     * Problem 43: Design a data structure that supports all following operations in average O(1) time.
     * insert(val): Inserts an item val to the set if not already present.
     * remove(val): Removes an item val from the set if present.
     * getRandom: Returns a random element from current set of elements. Each element must have the same probability
     * of being returned.
     *
     * Solution: Use 2 hashmap and random function and a arrayList.
     */
    /** Initialize your data structure here. */
    Map<Integer, Integer> valToCountMap = new HashMap<>();
    Map<Integer, Integer> countToValueMap = new HashMap<>();
    int count;
    Random rand = new Random();

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valToCountMap.containsKey(val)) {
            return false;
        }
        valToCountMap.put(val, count);
        countToValueMap.put(count, val);
        count = valToCountMap.size();
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valToCountMap.containsKey(val)) {
            return false;
        }
        int valueCount = valToCountMap.get(val);
        valToCountMap.remove(val);
        if (valueCount != countToValueMap.size() - 1) {
            countToValueMap.put(valueCount, countToValueMap.get(countToValueMap.size() - 1));
            valToCountMap.put(countToValueMap.get(countToValueMap.size() - 1), valueCount);
            countToValueMap.remove(countToValueMap.size() - 1);
        } else {
            countToValueMap.remove(valueCount);
        }
        count = valToCountMap.size();
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return countToValueMap.get(rand.nextInt(count));
    }

    /**
     * Problem 44: Assume you have an array of length n initialized with all 0's and are given k update operations.
     * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of
     * subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
     * Return the modified array after all k operations were executed.
     *
     * Solution: Since 0 is an identity element so we can operate increment value to start index and decrement same
     * value from endIndex + 1. Then add i-1 to i in the original array. O(K + N)
     */
    public void updateArray(int length, int[][] updates) {
        int[] result = new int[length];
        for (int i = 0; i < updates.length; i++) {
            result[updates[i][0]] += updates[i][2];
            if (updates[i][1] < length - 1) {
                result[updates[i][1] + 1] -= updates[i][2];
            }
        }

        for (int i = 1; i < length - 1; i++) {
            result[i] += result[i - 1];
        }
    }

    /**
     * Problem 45: Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the
     * duplicate one.
     * Note:
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     *
     * Solution: Same algorithm as of cycle detection in linked list.
     */
    public int findDuplicate(int[] nums) {
        if (nums.length <= 1) {
            return -1;
        }

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (fast != slow)
        {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /**
     * Problem 46: According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular
     * automaton devised by the British mathematician John Horton Conway in 1970."
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
     * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
     * Wikipedia article):
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population.
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     *
     * Write a function to compute the next state (after one update) of the board given its current state.
     *
     * Follow up:
     * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update
     * some cells first and then use their updated values to update other cells.
     * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
     * cause problems when the active area encroaches the border of the array. How would you address these problems?
     *
     * Solution: Consider two bits as life 00 -> first bit as current state and second bit as next state. Initially
     * it will be 00 or 01 and following will be the transition:-
     * 00 -> 00
     * 00 -> 10
     * 01 -> 01
     * 01 -> 11
     *
     * To get the current state, '&' with 1
     * To get the next sate, '>>' by 1
     */
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int lives = getNeighbourLives(board, i, j, board.length, board[0].length);

                if ((board[i][j] & 1) == 1 && (lives == 2 || lives == 3)) {
                    board[i][j] = 3;
                }
                if ((board[i][j] & 1) == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int getNeighbourLives(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        for (int p = Math.max(i - 1, 0); p <= Math.min(i + 1, m - 1); p++) {
            for (int q = Math.max(j - 1, 0); q <= Math.min(j + 1, n - 1); q++) {
                lives += board[p][q] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }

    /**
     * Problem 47: Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     *
     * Solution: Put the conditions properly.
     */
    public int[][] generateMatrix(int n) {
        int R = n;
        int C = n;
        int i = 0;
        int val = 1;
        int[][] matrix = new int[n][n];
        while (R > 0 || C > 0) {
            for (int c = i; c < C; c++) {
                matrix[i][c] = val++;
            }

            for (int r = i + 1; r < R; r++) {
                matrix[r][C - 1] = val++;
            }

            for (int c = C - 2; c >= i; c--) {
                matrix[R - 1][c] = val++;
            }

            for (int r = R - 2; r > i; r--) {
                matrix[r][i] = val++;
            }
            i++;
            R -= 1;
            C -= 1;
        }

        return matrix;
    }

    /**
     * Problem 48: Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3].
     * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
     *
     * Solution: In linear time this can be solved. If below condition for n and n+1 is not hold then swap:-
     * A[even] <= A[odd]
     * A[odd] >= A[even]
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int prev, int next) {
        int temp = nums[prev];
        nums[prev] = nums[next];
        nums[next] = temp;
    }

    /**
     * Problem 49: A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * You may imagine that num[-1] = num[n] = -∞.
     * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     *
     * Solution: Do using binary traversal
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        return findPeakElementHelper(nums, 0, nums.length - 1);
    }

    private int findPeakElementHelper(int[] nums, int low, int high) {
        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return findPeakElementHelper(nums, low, mid);
        }
        return findPeakElementHelper(nums, mid + 1, high);
    }

    /**
     * Problem 50: Your are given an array of positive integers nums.
     * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is
     * less than k.
     *
     * Solution: Sliding Window[O(N)]: Get the product every time by inlcuding right element. If product >= K then
     * remove leftmost element from the product one by one. Keep adding right - left + 1 result with every right
     * element.
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        if (k <= 1) {
            return res;
        }
        int left = 0;
        int prod = 1;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left++];
            }
            res += right - left + 1;
        }
        return res;
    }

    /**
     * Problem 51: Implement a MyCalendar class to store your events. A new event can be added if adding the event
     * will not cause a double booking.
     * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half
     * open interval [start, end), the range of real numbers x such that start <= x < end.
     * A double booking happens when two events have some non-empty intersection (ie., there is some time that is
     * common to both events.)
     * For each call to the method MyCalendar.book, return true if the event can be added to the calendar
     * successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
     * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
     *
     * Solution: use TreeMap. TreeMap internally has RedBlack tree ds. Evry time get the floorKey and compare its
     * endTime with start AND then get ceilingKey and compare its startTime with end.
     * With n inputs, worst time can be O(nlgn). Getting floorKey, ceilingKey and put has O(lgn) time complexity.
     */
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    public boolean book(int start, int end) {
        Integer floorKey = treeMap.floorKey(start);
        if (floorKey != null && start < treeMap.get(floorKey)) {
            return false;
        }

        Integer ceilingKey = treeMap.ceilingKey(start);
        if (ceilingKey != null && end > ceilingKey) {
            return false;
        }

        treeMap.put(start, end);
        return true;
    }

    /**
     * Problem 52: Find the contiguous subarray within an array (containing at least one number) which has the
     * largest product.
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     *
     * Solution: Maintain max and min with every element. With each element get max of max*i, min*i and i. Likewise
     * get the min. O(n) complexity.
     * Holding minimum value as well since -ve element can make -ve minimum as maximum.
     */
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);

            result = Math.max(result, max);
        }
        return result;
    }

    /**
     * Problem 52: Given an array of n integers where n > 1, nums, return an array output such that output[i] is
     * equal to the product of all the elements of nums except nums[i].
     * Solve it without division and in O(n).
     * For example, given [1,2,3,4], return [24,12,8,6].
     *
     * Solution: Left side multiplication: Get multiplication of of each element left to it.
     * Right side multiplication: Get right side multiplication of each element.
     * Multiply both side multiplication.
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}



