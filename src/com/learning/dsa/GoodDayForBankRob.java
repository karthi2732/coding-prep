package com.learning.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodDayForBankRob {


    public static List<Integer> goodDayToRobBank(int[] security, int time) {

        List<Integer> res = new ArrayList<>();

        int[] increasingSecurityCnt = new int[security.length];
        int[] decreasingSecurityCnt = new int[security.length];

        int lastDayIdx = security.length-1;

        for (int i = 0; i <= lastDayIdx; i++) {

            if((i+1)<security.length && security[i+1]<=security[i]) {
                decreasingSecurityCnt[i+1] = decreasingSecurityCnt[i] + 1;
            }

            if((lastDayIdx-i-1)>=0 && security[lastDayIdx-i-1]<=security[lastDayIdx-i]) {
                increasingSecurityCnt[lastDayIdx-i-1] = increasingSecurityCnt[lastDayIdx-i]+1;
            }

        }

        System.out.println(Arrays.toString(increasingSecurityCnt));
        System.out.println(Arrays.toString(decreasingSecurityCnt));

        for (int i=time; i<security.length-1-time; i++) {
            if(increasingSecurityCnt[i]>=time && decreasingSecurityCnt[i]>=time) {
                res.add(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] security = {5,3,3,3,5,6,2};
        int time = 2;
        System.out.println((goodDayToRobBank(security, time)));
    }

}
