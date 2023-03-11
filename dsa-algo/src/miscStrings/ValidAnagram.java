package miscStrings;

import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram va = new ValidAnagram();
        System.out.println(va.isAnagram("cat","tac"));
        System.out.println(va.isAnagram("catcat","tac"));
        System.out.println(va.isAnagram("cat","rat"));
        System.out.println(va.isAnagram("tar","rat"));
    }
    public boolean isAnagram(String s, String t) {
        // return bruteforce(s,t);
        // return  optimizedSpace(s,t);
        return usingSorting(s,t);
    }

    //O(N LOG N) time O(1) space
    private boolean usingSorting(String s, String t){
        //mismathcing lengh
        if(s.length() != t.length())
            return false;

        char arr1[] = s.toCharArray();
        char arr2[] = t.toCharArray();
        //Sort array
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        // Compare, if any mismatch in alphbet, strings are not Anagram
        for(int i = 0;i< arr1.length;i++){
            if(arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    //O(N) O(1) space
    private boolean optimizedSpace(String s, String t){
        //mismathcing lengh
        if(s.length() != t.length())
            return false;

        int[] chars = new int[26];

        for(int i = 0; i< s.length();i++){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            // first string will increase the char count, second will decrease, if the count is 0 in end, both strings ar anagram
            chars[ch1-'a']++;
            chars[ch2-'a']--;
        }

        for(int i = 0;i<chars.length;i++)
            if(chars[i] != 0)
                return false;

        return true;
    }

    //O(N) Time O(1) /constant space
    private boolean bruteforce(String s, String t){
        //mismathcing lengh
        if(s.length() != t.length())
            return false;

        // count freq of alfabets in s1 and s2
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        //As both string of same size, we can count rfreq is same loop freq of first / second string
        for(int i = 0; i< s.length();i++ ){
            c1[s.charAt(i)-'a']++;
            c2[t.charAt(i)-'a']++;
        }

        //compare both have same freq for each alphabet
        for(int i = 0; i< c1.length;i++){
            if(c1[i] != c2[i])
                return false;
        }

        return true;
    }


}
