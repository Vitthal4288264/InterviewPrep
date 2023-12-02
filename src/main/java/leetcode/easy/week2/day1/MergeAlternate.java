package leetcode.easy.week2.day1;

// 1768. Merge Strings Alternately
// You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other,
// append the additional letters onto the end of the merged string.
// Return the merged string.
public class MergeAlternate {

    public static void main(String[] args)  {

        System.out.println("Result :: " +mergeAlternate("abc", "poklaryud"));
    }

    public static String mergeAlternate(String word1, String word2){

        var wLen = word1.length() + word2.length();
        var sb = new StringBuilder();
        for(int i=0; i<wLen; i++){
            if(i < word1.length()){
                sb.append(word1.charAt(i));
            }
            if(i< word2.length()){
                sb.append(word2.charAt(i));
            }
        }

        return sb.toString();
    }
}
