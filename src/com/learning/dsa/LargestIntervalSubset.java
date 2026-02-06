package com.learning.dsa;

public class LargestIntervalSubset {

    /**
     * Problem:
     * Given with 2 array start[] and end[]
     * start[i] represent start of the event and end[i] represent end of the event
     * Goal is to find the largest subset of events, in which any one of the event overlaps with all other event in the subset.
     *
     * Brute Force:
     * For every pivot event in the array, iterate the array and find the count of events that overlaps with the pivot event.
     *
     * Optimised Solution:
     * Uses Intersection of Intervals to find the count of subset.
     *
     * Algorithm:
     * Sort the start and end array independently.
     * Take the earliest ending time.
     * Iterate the start array and keep incrementing the count until we find a start time that is later than the earliest end time currently.
     * When the event start after this earliest ending, it doesn't overlap for sure.
     *
     * Now reset the subset counter to 1.
     *
     * Keep up the iteration until it reaches the end of the array.
     *
     * Every time, check if the current counter is greater than max. If so, store the current as max
     *
     * Return the max as result.
     *
     * Logic used here:
     * Earliest ending is used in evaluation.
     * All events that start before the earliest ending will definitely have `end[i] >= earliestEnd`
     * Earliest end is updated only after finding an event that have `start[i]>earliestEnd`
     *
     */

    public static void main(String[] args) {

    }

}
