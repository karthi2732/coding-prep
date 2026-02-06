package com.learning.dsa;

import java.util.Arrays;

public class TrappingRainWater {

    public static int maxHeight(int[] heights, int start, int end) {
        if(end<start) {
            return -1;
        }

        int max = heights[start];
        for (int i = start+1; i <= end; i++) {
            if(heights[i]>max) {
                max = heights[i];
            }
        }
        return max;
    }

    /**
     * water held on top of a height is dependent on the smallest wall at either side
     * this approach repeated re-computes the max height at either side
     * uses the smallest height as wall and calculates the water quantity that can be persisted every height that is smaller than the wall
     */
    public static int maxWaterTrappedByBruteForce(int[] heights) {
        int trappedWater = 0;

        for(int i=1; i<heights.length-1; i++) {

            int leftMax = maxHeight(heights, 0, i-1);
            int rightMax = maxHeight(heights, i+1, heights.length-1);

            int minWallHeight = Math.min(leftMax, rightMax);

            if(heights[i]<minWallHeight) {
                trappedWater = trappedWater + minWallHeight - heights[i];
            }

        }
        return trappedWater;
    }


    /**
     * brute force approach repeated re-computes the max height at either side
     * pre-computing and persisting this max heights saves n iteration everytime and consumes O(n) space
     */

    public static int maxwaterTrappedByDP(int[] heights) {

        int[] leftWallHeight = new int[heights.length];
        int[] rightWallHeight = new int[heights.length];

        int maxWaterTrapped = 0;

        // pre-computing left and right wall heights for each index

        for(int i=0; i<heights.length; i++) {
            leftWallHeight[i] = maxHeight(heights, 0, i);
            rightWallHeight[i] = maxHeight(heights, i, heights.length-1);
        }

        for(int i=1; i<heights.length-1; i++) {
            int minWallHeight = Math.min(leftWallHeight[i-1], rightWallHeight[i+1]);
            if(heights[i]<minWallHeight) {
//                System.out.printf("water at current height: %s | min wall of index %s: %s | water log possible: %s\n", heights[i], i, minWallHeight, (minWallHeight - heights[i]) );
                maxWaterTrapped = maxWaterTrapped + ( minWallHeight - heights[i] );
            }

        }

        return maxWaterTrapped;
    }

    public static int maxWaterTrappedByTwoPointers(int[] heights) {
        int maxWaterTrapped = 0;

        int leftWall = heights[0];
        int rightWall = heights[heights.length-1];

        int leftIdx = 1;
        int rightIdx = heights.length-2;

        while (leftIdx<=rightIdx) {
            if(leftWall<rightWall) {
                if(leftWall>heights[leftIdx]) {
                    maxWaterTrapped = maxWaterTrapped + (leftWall-heights[leftIdx]);
                } else {
                    leftWall = heights[leftIdx];
                }
                leftIdx++;
            } else {
                if(rightWall>heights[rightIdx]) {
                    maxWaterTrapped = maxWaterTrapped + (rightWall-heights[rightIdx]);
                } else {
                    rightWall = heights[rightIdx];
                }
                rightIdx--;
            }
        }

        return maxWaterTrapped;
    }


    public static void main(String[] args) {

//        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] heights = {4,2,0,3,2,5};

        System.out.println(maxWaterTrappedByBruteForce(heights));
        System.out.println(maxwaterTrappedByDP(heights));
        System.out.println(maxWaterTrappedByTwoPointers(heights));

    }

}
