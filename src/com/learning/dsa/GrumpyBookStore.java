package com.learning.dsa;

public class GrumpyBookStore {

    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        // solve it independently, so that guaranteed customers count remains calculated
        // core problem is finding the window in which he is grumpy where he receives max customers.

        int guaranteedCustomersCount = 0, leveragedCustomersCount = 0, maxLeveragedCustomerCount = 0;

        for(int i=0; i<customers.length; i++) {

            if(grumpy[i]==0) {
                guaranteedCustomersCount = guaranteedCustomersCount + customers[i];
            } else {
                leveragedCustomersCount = leveragedCustomersCount + customers[i];
            }

            if(i>=minutes && customers[i-minutes]==1) {
                leveragedCustomersCount = leveragedCustomersCount - customers[i-minutes];
            }

            if(maxLeveragedCustomerCount<leveragedCustomersCount) {
                maxLeveragedCustomerCount = leveragedCustomersCount;
            }

            // System.out.printf("i: %s | leveragedCustomersCount: %s | guaranteedCustomersCount: %s | maxLeveragedCustomerCount: %s \n", i, leveragedCustomersCount, guaranteedCustomersCount, maxLeveragedCustomerCount);

        }

        return maxLeveragedCustomerCount + guaranteedCustomersCount;

    }

    public static void main(String[] args) {

        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3;

        System.out.println(maxSatisfied(customers, grumpy, minutes));

    }

}
