package com.learning.dsa;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {


    public static int minCoinsByBruteForce(int[] coins, int amount) {

        int coinsCnt = 0;

        if(amount==0) {
            coinsCnt = 0;
        } else if (amount<0) {
            coinsCnt = -1;
        } else {

            int minCoinsCount = -1;

            for(int i=0;i<coins.length; i++) {

                int remainingAmt = amount-coins[i];
                int currCoinsCnt = minCoinsByBruteForce(coins, remainingAmt);

                if(currCoinsCnt>-1) {
                    currCoinsCnt = currCoinsCnt + 1; // adding 1 for including a coin in current denomination
                    if(minCoinsCount==-1) {
                        minCoinsCount = currCoinsCnt;
                    } else {
                        minCoinsCount = Math.min(minCoinsCount, currCoinsCnt);
                    }
                }

            }

            coinsCnt = minCoinsCount;
        }

        return coinsCnt;

    }


    public static int minCoinsByMemoization(int[] coins, int amount, Map<Integer, Integer> amountCoinsCountMap) {

        int coinsCnt = 0;

        if (amount==0) {
            coinsCnt = 0;
        } else if (amount<0) {
            coinsCnt = -1;
        } else if(amountCoinsCountMap.containsKey(amount)) {
            return amountCoinsCountMap.get(amount);
        } else {

            int minCoinsCount = -1;

            for(int i=0;i<coins.length; i++) {

                int remainingAmt = amount-coins[i];
                int currCoinsCnt = minCoinsByMemoization(coins, remainingAmt, amountCoinsCountMap);

                if(currCoinsCnt>-1) {

                    currCoinsCnt = currCoinsCnt + 1; // adding 1 for including a coin in current denomination

                    if(minCoinsCount==-1) {
                        minCoinsCount = currCoinsCnt;
                    } else {
                        minCoinsCount = Math.min(minCoinsCount, currCoinsCnt);
                    }

                    if(amountCoinsCountMap.containsKey(amount)) {
                        amountCoinsCountMap.put(amount, Math.min(minCoinsCount, amountCoinsCountMap.get(amount)));
                    } else {
                        amountCoinsCountMap.put(amount, minCoinsCount);
                    }
                }

                System.out.printf("amount: %s | currentCoint: %s | Map: %s \n", amount, coins[i], amountCoinsCountMap);

            }

            coinsCnt = minCoinsCount;
        }

        return coinsCnt;
    }

    public static int nonNegativeMin(int a, int b) {
        if(a!=-1 && b!=-1) {
            return Math.min(a, b);
        } else if (a!=-1) {
            return a;
        } else if (b!=-1) {
            return b;
        }
        return -1;
    }

    public static int minCoinsByTabulation(int[] coins, int amount) {

        //Initialising DP State
        int[][] dpState = new int[coins.length+1][amount+1];

        for(int i=0; i<dpState.length; i++) {
            for(int j=0; j<dpState[i].length; j++) {
                dpState[i][j] = -1;
            }
        }

        // Filling base case
        for(int i=0; i<dpState.length; i++) {
            dpState[i][0] = 0;
        }

        for(int i=1; i< dpState.length; i++) {
            for(int j=1; j<dpState[i].length; j++) {
                int currCoin = coins[i-1];
                int amt = j;
                int residualAmt = amt - currCoin;

                int coinCntWithoutCurrCoin = dpState[i-1][amt];
                int coinCntWithCurrCoin = -1;
                if(residualAmt>=0) {
                    coinCntWithCurrCoin = nonNegativeMin(dpState[i][residualAmt], dpState[i-1][residualAmt]);
                    if(coinCntWithCurrCoin!=-1) {
                        coinCntWithCurrCoin++;
                    }
                }
                dpState[i][j] = nonNegativeMin(coinCntWithCurrCoin, coinCntWithoutCurrCoin);
            }
        }

        logDPState(dpState);

        return dpState[coins.length][amount];
    }

    public static void logDPState(int[][] dpState) {
        for(int i=0; i<dpState.length; i++) {
            for (int j=0; j<dpState[i].length; j++) {
                System.out.printf("%4d ", dpState[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

//        int[] coins = {186, 419, 83, 408};
//        int amount = 6249;

        int[] coins = {1,2,5};
        int amount = 11;

        System.out.println(minCoinsByBruteForce(coins, amount));

        Map<Integer, Integer> accCoinsMap = new HashMap<>();
        System.out.println(minCoinsByMemoization(coins, amount, accCoinsMap));

        System.out.println(minCoinsByTabulation(coins, amount));
    }

}
