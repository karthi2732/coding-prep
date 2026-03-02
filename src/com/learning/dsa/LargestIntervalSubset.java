package com.learning.dsa;

import java.util.Arrays;

public class LargestIntervalSubset {

    /**
     * Problem:
     * Given with 2 array start[] and end[]
     * start[i] represent start of the event and end[i] represent end of the event
     * Goal is to find the largest subset of events, in which any one of the event overlaps with all other event in the subset.

     * Brute Force:
     * -------------------------------------------------------
     * For every pivot event in the array, iterate the array and find the count of events that overlaps with the pivot event.

     * Optimised Solution:
     * -------------------------------------------------------
     * Total Number of Overlapping Intervals = Total Number of Meetings - Total Number of non-overlapping intervals
     * Total Number of non-overlapping intervals = Number of Events with End Time before Start Time + Number of events with Start Time after End Time

     * Algorithm:
     * Get a Sorted Copy of Event Start Times and Event End Times.
     * For every event,
     *      Event with End Time strictly lesser than Event Start Time - Cannot Overlap - Binary Search over Sorted End Time to find Strictly Less value to Event Start Time
     *      Event with Start TIme strictly greater than Event End Time - Cannot Overlap - Binary Search over Sorted Start Time to find Strictly Great value to Event End Time

     * Solves the problem in O(n log n )
     *
     */


    public static int subsetCountByBruteForce(int[] start, int[] end) {
        int maxSubset = 0;

        for(int i=0; i<start.length; i++) {

            int currMaxSubset = 1;

            for(int j=0; j<start.length; j++) {
                if(i==j)
                    continue;

                if(start[i]<start[j]) {
                    if(end[i]>=start[j])
                        currMaxSubset++;
                } else {
                    if(end[j]>=start[i])
                        currMaxSubset++;
                }
            }

            if(currMaxSubset>maxSubset)
                maxSubset = currMaxSubset;
        }

        return maxSubset;
    }


    public static int getLastLesserIndex(int[] arr, int l, int r, int time) {
        int m = l + ((r-l)/2);
        if(l<=r) {
            if (arr[m] < time && ((m + 1)>r || arr[m + 1]>=time))
                return m;
            else if (m + 1 <= r && arr[m + 1] <= time)
                return getLastLesserIndex(arr, m + 1, r, time);
            else
                return getLastLesserIndex(arr, l, m - 1, time);
        }
        return -1;
    }

    public static int getFirstStrictlyGreater(int[] arr, int l, int r, int time) {
        while (l<r) {
            int m = l + (r-l)/2;
            if(arr[m]<=time)
                l = m+1;
            else
                r = m;
        }
        return l;
    }

    public static int endsBeforeCount(int[] sortedEnd, int startTime) {
        int lastLesserIdx = getLastLesserIndex(sortedEnd, 0, sortedEnd.length-1, startTime);
        return lastLesserIdx + 1;
    }

    public static int startsAfterCount(int[] sortedStart, int endTime) {
        int firstGreaterIdx = getFirstStrictlyGreater(sortedStart, 0, sortedStart.length-1, endTime);
        return sortedStart.length-firstGreaterIdx;
    }

    public static int subsetCountOptimised(int[] start, int[] end) {
        int maxSubset = 0;

        int currMaxSubset = 1;

        int[] sortedStart = Arrays.copyOf(start, start.length);
        int[] sortedEnd = Arrays.copyOf(end, end.length);
        Arrays.sort(sortedStart);
        Arrays.sort(sortedEnd);

        for(int i=0; i<start.length; i++) {
            int strictEndBefore = endsBeforeCount(sortedEnd, start[i]);
            int strictStartAfter = startsAfterCount(sortedStart, end[i]);

            System.out.printf("start: %d end: %d strictStartAfter: %d strictEndBefore: %d\n", start[i], end[i], strictStartAfter, strictEndBefore);

            currMaxSubset = start.length - (strictEndBefore + strictStartAfter);
            if(maxSubset<currMaxSubset)
                maxSubset = currMaxSubset;
        }

        return maxSubset;
    }


    public static void main(String[] args) {

        int[] start = {1,2,3,4};
        int[] end = {2,3,5,5};
        System.out.println(subsetCountByBruteForce(start, end));
        System.out.println(subsetCountOptimised(start, end));
    }

}
