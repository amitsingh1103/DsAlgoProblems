package com.leetcode.dsandalgo;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
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
    public int misingNumber(int[] nums) {
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
}

