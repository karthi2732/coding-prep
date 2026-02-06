package com.learning.dsa;

import java.util.Arrays;
import java.util.List;

public class RegexPatternMatch {


    public static boolean recursiveBruteForce(String pattern, String word, int patternIdx, int wordIdx) {

        if(patternIdx>=pattern.length() && wordIdx>=(word.length())) {
            return true;
        } else if (patternIdx>= pattern.length()) {
            return false;
        }

        if((patternIdx+1)<pattern.length() && pattern.charAt(patternIdx+1)=='*') {
            if(pattern.charAt(patternIdx)=='.' || (wordIdx<word.length() && word.charAt(wordIdx)==pattern.charAt(patternIdx))) {
                return recursiveBruteForce(pattern, word, patternIdx, wordIdx+1) || recursiveBruteForce(pattern, word, patternIdx+2, wordIdx+1) || recursiveBruteForce(pattern, word, patternIdx+2, wordIdx);
            } else {
                return recursiveBruteForce(pattern, word, patternIdx+2, wordIdx);
            }
        } else if(wordIdx<word.length() && (pattern.charAt(patternIdx)=='.' || pattern.charAt(patternIdx)==word.charAt(wordIdx))) {
            return recursiveBruteForce(pattern, word, patternIdx+1, wordIdx+1);
        }
        return false;
    }


    public static boolean dpSolution(String pattern, String word) {

        // Init state for DP Solution
        boolean[][] dp = new boolean[word.length()+1][pattern.length()+1];

        // Filling Base case solutions in DP State to avoid re-computation
        dp[0][0] = true;
        for(int i=1; i<pattern.length();i++) {
            if(pattern.charAt(i-1)=='*')
                dp[0][i] = dp[0][i-2];
        }

        // computing solution bottom up approach
        for(int i=1; i<word.length()+1; i++) {
            for(int j=1; j<pattern.length()+1; j++) {
                if(pattern.charAt(j-1)=='*') {
                    // check if zero occurrence of pattern character matches
                    // if not then, check if pattern character matches current string character && if this is the first or n'th occurrence
                    dp[i][j] = dp[i][j-2] || ( (pattern.charAt(j-2)=='.' || pattern.charAt(j-2)==word.charAt(i-1)) && (dp[i-1][j] || dp[i][j-1]) );
                } else if (pattern.charAt(j-1)=='.' || pattern.charAt(j-1)==word.charAt(i-1)) {
                    // if it is exact match, then check the previous indexed substring of the pattern and actual string
                    dp[i][j] = dp[i-1][j-1];
                }
            }
//            System.out.println(word.substring(0, i-1) + " " + pattern);
//            logDPState(dp);
        }

        return dp[word.length()][pattern.length()];
    }

    private static void logDPState(boolean[][] state) {
        for(int i=0; i<state.length; i++) {
            for (int j=0; j<state[i].length; j++) {
                char c = 'F';
                if(state[i][j]) {
                    c = 'T';
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        List<String> inputStringList = Arrays.asList("aab", "mississippi");
        List<String> inputPatternList = Arrays.asList("c*a*b", "mis*is*p*.");

        for(int i=0; i<inputStringList.size(); i++) {
            String str = inputStringList.get(i);
            String pattern = inputPatternList.get(i);
            System.out.printf("String: %s Pattern: %s Recursive Solution: %s\n", str, pattern, recursiveBruteForce(pattern, str, 0, 0));
            System.out.printf("String: %s Pattern: %s DP Solution: %s\n", str, pattern, dpSolution(pattern, str));
            System.out.println("-----------------------------------------------------------------------------------------");
        }

    }

}
