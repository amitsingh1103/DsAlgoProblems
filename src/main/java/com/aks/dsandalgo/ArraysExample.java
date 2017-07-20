package com.aks.dsandalgo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by singhami on 6/8/2017.
 */
public class ArraysExample {

    /* Problem 19: Program to cyclically rotate an array by one
    Solution: Since it is fixed. We can shift the array elements bby 1 after storing the last element and then
    put it on the first index.
    */
    public void rotateArrayByOne(int[] arr) {
        int lastEle = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }

        arr[0] = lastEle;
        // print
        for (int e : arr) {
            System.out.print(e + ", ");
        }
    }

    /* Problem 18: Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array
    is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
    Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j

    Solution: Use modified merge sort.
    */
    public int calculateInversionCount(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        return _calculateInversionCountByMergeSort(arr, temp, 0, arr.length - 1);
    }

    private int _calculateInversionCountByMergeSort(int[] arr, int[] temp, int low, int high) {
        int inversionCount = 0;
        if (low >= high) {
            return inversionCount;
        }

        int mid = (low + high) / 2;
        inversionCount = _calculateInversionCountByMergeSort(arr, temp, low, mid);
        inversionCount += _calculateInversionCountByMergeSort(arr, temp, mid + 1, high);

        return inversionCount + merge(arr, temp, low, mid + 1, high);
    }

    private int merge(int[] arr, int[] temp, int low, int mid, int high) {
        int invCount = 0;
        int i = low;
        int j = mid;
        int p = low;
        while (i <= mid - 1 && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[p++] = arr[i++];
            } else {
                temp[p++] = arr[j++];
                invCount += (mid - i); // add invCount for all the elements that fall in right side. since arr is
                // sorted.
            }
        }

        if (i <= mid - 1) {
            while (i <= mid - 1) {
                temp[p++] = arr[i++];
            }
        }

        if (j <= high) {
            while (j <= high) {
                temp[p++] = arr[j++];
            }
        }

        for (int n = low; n <= high; n++) {
            arr[n] = temp[n];
        }

        return invCount;
    }


    /* Problem 17: find smallest and second smallest element in an array.
    Solution: Maintain two variables for smallest and second to samllest in single traversal.
    */
    public void smallestAndSecondSmallestEle(int[] arr) {
        int smallest = 99999;
        int secondSmallest = 99999;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
            if (arr[i] < secondSmallest && arr[i] != smallest) {
                secondSmallest = arr[i];
            }
        }

        System.out.println("Smallest: " + smallest + " SecondSmallest: " + secondSmallest);
    }

    /* Problem 16: An Array of integers is given, both +ve and -ve. You need to find the two elements such that their
    sum is closest to zero.

    Solution: Sort the array. Maintain two pointers: left and right. Maintain abs minimum_sum, if negative shift left
    if positive shift right pointers.
    */
    public void sumClosestToZero(List<Integer> integerList) {
        List<Integer> sortedList = integerList.stream().sorted().collect(Collectors.toList());
        int l = 0;
        int r = sortedList.size() - 1;
        int min_sum = 99999;
        int min_l = l;
        int min_r = r;
        while (l < r) {
            int sum = sortedList.get(l) + sortedList.get(r);
            if (Math.abs(sum) < min_sum) {
                min_sum = Math.abs(sum);
                min_l = l;
                min_r = r;
            }
            if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println("Sum: " + min_sum + " min_l: " + sortedList.get(min_l) + " min_r: " + sortedList.get
                (min_r));
    }

    /* Problem 15: Print the elements of an array in the decreasing frequency if 2 numbers have same frequency then print
    the one which came first.

    Soution: Maintain the count of occurence in a map keyed by element and then sort by count.
    */
    public List<Integer> sortByFrequency(List<Integer> integerList) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer e : integerList) {
            if(Objects.nonNull(countMap.putIfAbsent(e, 1))) {
                countMap.put(e, countMap.get(e) + 1);
            }
        }

        List<Integer> sortedListByFreq = new ArrayList<>();
        countMap.entrySet().stream().sorted((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return integerList.indexOf(o1.getKey()) < integerList.indexOf(o2.getKey()) ? -1 : 1;
            }
            return o2.getValue().compareTo(o1.getValue());
        }).forEach(a -> {
                    for (int i = 0; i < a.getValue(); i++) {
                        sortedListByFreq.add(a.getKey());
                    } });
        return sortedListByFreq;
    }


    /* Problem 14: Write a program to print all the LEADERS in the array. An element is leader if it is greater than all
    the elements to its right side. And the rightmost element is always a leader. For example int the array {16, 17,
            4, 3, 5, 2}, leaders are 17, 5 and 2.

    Solution: Traverse from right and compare with current leader to get all the leaders of the array.
    */
    public void getLeaders(int[] arr) {
        int prevLeader = -99999;
        for (int i = arr.length - 1; i > -1; i--) {
            if (arr[i] > prevLeader) {
                System.out.print(arr[i] + ", ");
                prevLeader = arr[i];
            }
        }
    }

    /* Problem 13: Given an array of positive numbers, find the maximum sum of a subsequence with the constraint that
    no 2 numbers in the sequence should be adjacent in the array. So 3 2 7 10 should return 13 (sum of 3 and 10)
    or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7). Answer the question in most efficient way.

    Solution: Take two variables: incl[inclusive sum of current element is curr + excl of previous element] and
    excl[exclusive sum of two elements is max of prev incl and prev excl]. At the end take max of incl and excl.
    */
    public int maxSumOfNonAdjacentEle(int[] arr) {
        int incl = 0;
        int excl = 0;
        for (int i = 0; i < arr.length; i++) {
            int inclSumWithCurr = arr[i] + excl;
            excl = Math.max(incl, excl);
            incl = inclSumWithCurr;
        }

        return Math.max(incl,excl);
    }

    /* Problem 12: Write a function rotate(ar[], d, n) that rotates arr[] of size n by d elements.

    Solution: Reverse the array. Then reverse the array from k + 1 to length.
    */
    public void rotateArray(int[] arr, int k) {
        reverseArrayFromTo(arr, 0, k - 1);
        reverseArrayFromTo(arr, k, arr.length - 1);
        reverseArrayFromTo(arr, 0, arr.length - 1);
    }

    private void reverseArrayFromTo(int[] arr, int from, int to) {
        int i = from;
        int j = to;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * Problem 11: There are 2 sorted arrays A and B of size n each. Write an algorithm to find the median of the
     * array obtained after merging the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n))
     *
     *
     * Median: In probability theory and statistics, a median is described as the number separating the higher half of
     * a sample, a population, or a probability distribution, from the lower half.
     * The median of a finite list of numbers can be found by arranging all the numbers from lowest value to highest
     * value and picking the middle one.
     *
     * For getting the median of input array { 12, 11, 15, 10, 20 }, first sort the array.
     * We get { 10, 11, 12, 15, 20 } after sorting. Median is the middle element of the sorted array which is 12.
     *
     * There are different conventions to take median of an array with even number of elements, one can take
     * the mean of the two middle values, or first middle value, or second middle value.
     *
     * Solution 1: Merging the two sorted arrays and taking the mean middle two elements.Time complexity O(n).
     * Solution 2: By comparing median of two sub-arrays.
     * 1) Calculate the medians m1 and m2 of the input arrays ar1[]
     * and ar2[] respectively.
     2) If m1 and m2 both are equal then we are done.
     return m1 (or m2)
     3) If m1 is greater than m2, then median is present in one
     of the below two subarrays.
     a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
     b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
     4) If m2 is greater than m1, then median is present in one
     of the below two subarrays.
     a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
     b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
     5) Repeat the above process until size of both the subarrays
     becomes 2.
     6) If size of the two arrays is 2 then use below formula to get
     the median.
     Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2and ar2[] respectively.
     */
    public int findMedianOfTwoSortedArrays(int[] ar1, int[] ar2) {
        if (ar1.length != ar2.length) {
            throw new IllegalArgumentException("Array length must be same");
        }

        return getMedian(ar1, ar2, 0, ar1.length - 1, 0, ar2.length - 1, ar1.length);
    }

    private int getMedian(int[] ar1, int[] ar2, int low1, int high1, int low2, int high2, int length) {
        if (length <= 0) {
            return -1;
        }

        if (length == 1) {
            return (ar1[low1] + ar2[low2]) / 2;
        }

        if (length == 2) {
            return (Math.max(ar1[low1], ar2[low2]) + Math.min(ar1[high1], ar2[high2])) / 2;
        }

        // calculate median.
        int m1 = median(ar1, length, low1);
        int m2 = median(ar2, length, low2);

        if (m1 == m2) {
            return m1;
        }

        if (m1 < m2) {
            return getMedian(ar1, ar2, (length / 2), length - 1, 0, (length / 2), (length - length / 2));
        }

        return getMedian(ar1, ar2, 0, (length / 2), (length / 2), length - 1, (length - length / 2));
    }

    private int median(int[] ar, int n, int low) {
        if (n % 2 == 0) {
            return (ar[low + (n/2 - 1)] + ar[low + n/2]) / 2;
        }
        return ar[low + n/2];
    }


    /**
     * Problem 10: Write a program to reverse an array or string
     * Time Complexity: O(n)
     */
    public void reverseArray(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }

        for (int e : arr) {
            System.out.print(e + ", ");
        }
    }

    /**
     * Problem 9: There are two sorted arrays. First one is of size m+n containing only m elements.
     * Another one is of size n and contains n elements. Merge these two arrays into the first array of
     * size m+n such that the output is sorted.
     * Solution: Merge arrays as in merge algorithm. Shift elements of mPlusN to right and then compare each array
     * element by element using two counters.
     *
     * Time Complexity: O(n)
     */
    public void mergeTwoSortedArrays(int[] mPlusN, int[] N) {
        // shift the elements in mPlusN to right.
        int j = mPlusN.length - 1;
        int i;
        for (i = mPlusN.length - 1; i >= 0; i--) {
            if (mPlusN[i] != -1) {
                mPlusN[j] = mPlusN[i];
                j--;
            }
        }

        // Merge two arrays.
        // reuse counters i, j
        i = N.length;
        j = 0;
        int p = 0;
        while (i < mPlusN.length && j < N.length) {
            if (N[j] < mPlusN[i]) {
                mPlusN[p] = N[j];
                j++;
            } else {
                mPlusN[p] = mPlusN[i];
                i++;
            }
            p++;
        }

        if (j < N.length) {
            // reuse counter i
            for (i = j; i < N.length; i++, p++) {
                mPlusN[p] = N[i];
            }
        }

        // reuse counter i
        for (i = 0; i < mPlusN.length; i++) {
            System.out.print(mPlusN[i] + ", ");
        }
    }

    /**
     * Problem 8: Search an element in a sorted and rotated array. Elements are unique.
     * Solution: Find the pivot element. Then find key in the subarray.
     */
    public int searchElementInRotatedArray(int[] arr, int key) {
        // Get the pivot element.
        int pivot = findPivot(arr, 0, arr.length - 1);
        if (pivot == -1) {
            return -1;
        }

        if (key == arr[pivot]) {
            return pivot;
        }

        if (key >= arr[0]) {
            return binarySearchOfArray(arr, 0, pivot - 1, key);
        }

        return binarySearchOfArray(arr, pivot + 1, arr.length - 1, key);
    }

    // Given list in sorted in non-decreasing order
    private int findPivot(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }

        if (low == high) {
            return low;
        }

        int mid = (low + high) / 2;
        if (mid > low && arr[mid] < arr[mid - 1]) {
            return mid - 1;
        }

        if (mid < high && arr[mid] > arr[mid + 1]) {
            return mid;
        }

        if (arr[low] > arr[mid]) {
            return findPivot(arr, low, mid - 1);
        }

        return findPivot(arr, mid + 1, high);
    }


    /**
     * Problem 7: You are given a list of n-1 integers and these integers are in the range of 1 to n. There are no
     * duplicates in list. One of the integers is missing in the list. Write an efficient code to find the missing
     * integer.
     * Solution: Calculate the sum of n numbers and then subtract this with the sum of elements.
     */
    public void findMissingNumber(int[] arr, int n) {
        int sumN = (n * (n + 1)) / 2;
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArr += arr[i];
        }

        System.out.println("Missing element: " + (sumN - sumArr));
    }

    /**
     * Problem 6: Find the sum of contiguous subarray within a one-dimensional array of numbers which has the
     * largest sum. Also print positions.
     * Solution 1: Kadane's Algorithm-> Taking current maximum and maximum so far.
     */
    public void findLargestSumContiguousSubArray(int[] arr) {
        int currentMax = arr[0];
        int maxSoFar = arr[0];
        int low = 0;
        int lowTemp = 0;
        int high = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > (currentMax + arr[i])) {
                currentMax = arr[i];
                lowTemp = i;
            } else {
                currentMax += arr[i];
            }

            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                high = i;
                low = lowTemp;
            }
        }

        System.out.println("Largest sum: " + maxSoFar + " .. Positions: [" + low + ", " + high + "]");
    }

    /**
     * Problem 5: Given an array of positive integers. All numbers occur even number of times except one number
     * which occurs odd number of times. Find the number in O(n) time & constant space.
     * Solution 1: Do XOR of each element. 2 XOR 2 = 0 ... 2 XOR 0 = 2
     * 0 is the identity element in XOR
     * Even occurrence elements will return 0 and the only odd occurrence element will be remaining in the output.
     */
    public void findOddOccurrenceElement(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }

        System.out.println("Odd occurrence element is: " + result);
    }


    /**
     * Problem 4: Find the majority element. x is the majority element in a list if the occurrences of x
     * in the list is greater than n/2.
     * Solution 1: Brute force: O(n^2)
     *
     * Solution 2: Moore's voting algorithm: O(n)
     * step 1: Whenever get the same element increment the count by 1 else decrement it by 1. When count is 0
     * make the  current element as majority element. At the end whatever be the element remaining may be the
     * majority element.
     * step 2: verify the above result if it is a majority element.
     *
     * Solution 3: Using HashMap
     */
    public void findMajorityElement(int[] arr) {
        int count = 1;
        int maj_index = 0;
        // Voting for the maj element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[maj_index]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                count = 1;
                maj_index = i;
            }
        }

        int votedElementCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr[maj_index]) {
                votedElementCount++;
            }
        }

        if (votedElementCount > (arr.length / 2)) {
            System.out.println("Majority Element: " + arr[maj_index]);
            return;
        }

        System.out.println("No majority element present!");
    }

    /**
     * Problem 3: Given an array A[] and a number x, check for pair in A[] with sum as x
     * Solution:
     * 1. Sort the array and use two indexes at low and high. Move left index if
     * key > sum else right index. return if key == sum or fail if left = right.
     * 2. Use hashmap
     */
    public void findSumPair(int[] arr, int key) {
        Map<Integer, Integer> integerMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            integerMap.putIfAbsent(arr[i], 1);
        }

        Iterator<Integer> itr = integerMap.keySet().iterator();
        while (itr.hasNext()) {
            Integer k1 = itr.next();
            if (integerMap.containsKey(Math.abs(key - k1))) {
                System.out.println("Pair: [" + k1 + ", " + Math.abs(key - k1) + "]");
            }
            itr.remove();
        }
    }

    /**
     * Problem 2: Insert, Delete and Search in sorted array
     * Solution: Insert takes O(n) time, need to sort again
     * Delete takes O(n)time, need to shift elemnts
     * Search takes O(log n) time, can do binary search
     */
    public int binarySearchOfArray(int[] arr, int low, int high, int key) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (key == arr[mid]) {
            return mid;
        }

        if (key > arr[mid]) {
            return binarySearchOfArray(arr, mid + 1, high, key);
        }

        return binarySearchOfArray(arr, low, mid - 1, key);
    }

    /**
     * Problem 1:Search, Insert and Delete in unsorted array
     * Solution: Insert takes O(1),
     * Delete takes O(n) time, need to shift elements
     * Search will be linear, O(n)
     */
}
