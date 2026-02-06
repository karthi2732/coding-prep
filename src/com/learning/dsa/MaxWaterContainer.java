package com.learning.dsa;

public class MaxWaterContainer {

    public static int maxWaterContainerByBruteForce(int[] height) {
        int maxWater = 0;

        for(int i=0; i<height.length; i++) {

            int currMaxWater = 0;

            for (int j=i+1; j<height.length; j++) {
                int containerHeight = Math.min(height[i], height[j]);
                int currWater = (j-i)*containerHeight;
                if(currMaxWater<currWater) {
                    currMaxWater = currWater;
                }
            }

            if(currMaxWater>maxWater) {
                maxWater = currMaxWater;
            }
        }

        return maxWater;
    }


    public static int maxWaterContainerByTwoPointer(int[] height) {
        int maxWater = 0, currMaxWater = 0;

        int left = 0, right = height.length-1;

        while (left<right) {
            if(height[left] < height[right]) {
                currMaxWater = (right-left)*height[left];
                left++;
            } else {
                currMaxWater = (right-left)*height[right];
                right--;
            }
            if(currMaxWater>maxWater) {
                maxWater = currMaxWater;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {

    }

}
