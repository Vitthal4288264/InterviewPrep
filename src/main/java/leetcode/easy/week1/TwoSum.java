package leetcode.easy.week1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] result = twoSum(arr, 9);
        Arrays.stream(result).forEach(System.out::println);
    }

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                return new int[] { map.get(complement), i};
            }
            map.put(nums[i],i);
        }

        return null;
    }
}
