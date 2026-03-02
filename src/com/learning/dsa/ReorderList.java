package com.learning.dsa;

public class ReorderList {

    // https://leetcode.com/problems/reorder-list/?envType=problem-list-v2&envId=oizxjoit

    /**
     * Split the list into 2 using Fast Pointer & Slow Pointer

     * Now Slow Pointer points to the mid-point
     * Node next to Fast Pointer is the Second List.

     * Reverse Second List

     * Pick one from first and then pick from second.
     *      Hence the sequence in the new List becomes -> N[0] -> N[n] -> N[1] -> N[n-1] ...

     * Repeat till the 2nd array exhaust.
     *
     */

    public static void main(String[] args) {

    }

}
