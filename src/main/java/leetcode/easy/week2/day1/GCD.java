package leetcode.easy.week2.day1;

// 1071. Greatest Common Divisor of Strings
public class GCD {

    public static void main(String[] args) {
        System.out.println("GCD of String ->"+ gcdOfString("ABAB","ABABAB"));
    }

    public static String gcdOfString(String str1, String str2){
        if(!(str1+str2).equals(str2+str1)) return "";
        int gcdVal = gcd(str1.length(), str2.length());
        return str2.substring(0, gcdVal);
    }

    public static int gcd(int x, int y){
        if(y == 0) return x;
        else return gcd(y, x%y);
    }
}
