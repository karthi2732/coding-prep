package com.learning.dsa;

import java.util.HashMap;
import java.util.Map;

public class MaxSubstringWithoutRepChars {

    public static void main(String[] args) {

        String s = "fdsgwertnwerewnrtu";

        int idx = 0, maxNonRepSubString = 0, nonRepSubString = 0;
        Map<Character, Integer> characterIdxMap = new HashMap<>();
        for(char c:s.toCharArray()) {

            if(characterIdxMap.containsKey(c)) {
                int lastIdxOfC = characterIdxMap.get(c);
                nonRepSubString = idx - lastIdxOfC;
            } else {
                characterIdxMap.put(c, idx);
                nonRepSubString++;
            }
            if (nonRepSubString>maxNonRepSubString){
                maxNonRepSubString = nonRepSubString;
            }
            characterIdxMap.put(c, idx);
            idx++;

        }
    }

}
