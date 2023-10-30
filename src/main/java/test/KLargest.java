package test;

public class KLargest {

    public static void main(String[] args) {
        int[] val = {3, 2, 1, 5, 6, 4};
        System.out.println("Largest value => " +findKthLargest(val, 3));
    }

    public static int findKthLargest(int[] nums, int k){

        if(k<1 || nums == null)
            return 0;
        return getKth(nums.length -k+1, nums, 0, nums.length -1);
    }

    /**
     *
     * @param k  -- to be found
     * @param nums -- values
     * @param start -- starting index
     * @param end -- final index
     * @return
     */
    public static int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];
        int left = start;
        int right = end;

        int w1 = 0;
        int w2 = 0;
        while(true){

            while(nums[left] < pivot && left < right){
                System.out.println("num[left] = "+nums[left]+" pivot = "+pivot+" left = "+left+" right = "+right
                );
                left++;
                w1++;
            }

            while(nums[right] >= pivot && right > left){
                System.out.println("num[left] = "+nums[left]+" pivot = "+pivot+" left = "+left+" right = "+right
                );
                right--;
                w2++;
            }
            if(left == right){
                System.out.println("Left = "+left+" Right = "+right);
                break;
            }
            swap(nums, left, right);
        }
        System.out.println("While loop count!!");
        System.out.println("First while => "+w1);
        System.out.println("Second while => "+w2);

        swap(nums, left, end);

        if(k== left +1){
            return pivot;
        } else if (k<left +1) {
            return getKth(k, nums, start, left -1);
        } else {
            return getKth(k, nums, left+1, end);
        }
    }

    public static void swap(int[] nums, int n1, int n2){
        int temp = nums[n1];
        nums[n1]= nums[n2];
        nums[n2] = temp;
    }
}
