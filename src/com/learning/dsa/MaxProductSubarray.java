package com.learning.dsa;

public class MaxProductSubarray {

    public static int maxProductByBruteForce(int[] arr) {
        int res = arr[0];

        for(int i=0; i<arr.length; i++) {
            int currProduct = 1;
            for(int j=i; j<arr.length; j++) {
                currProduct = currProduct * arr[j];
                if (currProduct>res){
                    res = currProduct;
                }
            }
        }

        return res;
    }


    public static int maxProductByKadanesAlgorithm(int[] arr) {

        int maxProduct = arr[0];

        int currMaxProduct = arr[0];
        int currMinProduct = arr[0];

        for(int i=1; i<arr.length; i++) {
            int prevMaxProduct = currMaxProduct;
            currMaxProduct = Math.max(arr[i], Math.max(prevMaxProduct*arr[i], currMinProduct*arr[i]));
            currMinProduct = Math.min(arr[i], Math.min(prevMaxProduct*arr[i], currMinProduct*arr[i]));

            maxProduct = Math.max(maxProduct, currMaxProduct);
        }

        return maxProduct;
    }

    public static int maxProductByRightLeftApproach(int[] arr) {
        int n = arr.length;
        int maxProd = arr[0];

        // leftToRight to store product from left to Right
        int leftToRight = 1;

        // rightToLeft to store product from right to left
        int rightToLeft = 1;

        for (int i = 0; i < n; i++) {

            if (leftToRight == 0)
                leftToRight = 1;

            if (rightToLeft == 0)
                rightToLeft = 1;

            // calculate product from index left to right
            leftToRight *= arr[i];

            // calculate product from index right to left
            int j = n - i - 1;
            rightToLeft *= arr[j];

            maxProd = Math.max(leftToRight, Math.max(rightToLeft, maxProd));

//            System.out.printf("arr[%s]: %s | leftToRight: %s | rightToLeft: %s | maxProduct: %s\n", i, arr[i], leftToRight, rightToLeft, maxProd);

        }
        return maxProd;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 6, 3, 0, 0};
        System.out.println(maxProductByBruteForce(arr));
        System.out.println(maxProductByKadanesAlgorithm(arr));
        System.out.println((maxProductByRightLeftApproach(arr)));
    }

}
