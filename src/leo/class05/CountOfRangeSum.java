package leo.class05;

import leo.util.ArrayUtil;
import sun.rmi.runtime.Log;

/**
 * @author Leo
 * @ClassName CountOfRangeSum
 * @DATE 2020/11/25 2:54 下午
 * @Description
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 假设0-i整体累加和是x,求必须以i位置结尾的子数组,目标有多少个在[lower,upper]范围上
 * 等同于求在i之前的所有累加和中有多少个累加和在[x-upper,x-lower]上.
 * https://leetcode-cn.com/problems/count-of-range-sum/
 */
class CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, nums.length - 1, lower, upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);
    }

    private static int merge(long[] arr, int l, int m, int r, int lower, int upper) {
        int res = 0;
        int windowL = l;
        int windowR = l;
        //左右指针开始对比
        for (int i = m + 1; i <= r; i++) {
            long max = arr[i] - lower;
            long min = arr[i] - upper;
            //如果左侧的数小于等于右侧数减lower 代表在lower范围,指针前进
            while (windowR <= m && arr[windowR] <= max) {
                windowR++;
            }
            //如果左侧的数小于右侧书减upper,代表在upper范围之内
            //条件不是小于等于的原因是不知道有左侧的数有几个等于upper 等于也在upper范围内
            while (windowL <= m && arr[windowL] < min) {
                windowL++;
            }
            //左侧的两个指针相减,得到一共有个数lower和upper范围内
            res += windowR - windowL;
        }
        int p1 = l;
        int p2 = m + 1;
        long[] help = new long[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while(p2<=r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

}

class CountOfRangeSum1 {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, nums.length - 1, lower, upper);
    }

    private static int process(long[] sum, int l, int r,int lower,int upper) {


        if (l == r) {
            return sum[l] >= lower && sum[r] <= upper ? 1 : 0;
        }

        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);

    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {

        int windowR = l;
        int windowL = l;
        int res = 0;
        for (int i = m + 1; i <= r; i++) {

            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            res += windowR - windowL;
        }
        int p1 = l;
        int p2 = m + 1;
        long[] help = new long[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            help[i++] = sum[p1++];
        }
        while(p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;
    }

}


class CountOfRangeSum2 {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);

    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            return sum[l] >= lower & sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);
    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {
        int windowL = l;
        int windowR = l;
        int res = 0;
        for (int i = m + 1; i <= r; i++) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            res += windowR - windowL;
        }
        int p1 = l;
        int p2 = m + 1;
        long[] help = new long[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;
    }
}


class CountOfRangeSum3{

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l > r) {
            return 0;
        }
        if (l==r){
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);

        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);
    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {
        int windowL = l;
        int windowR = l;
        int res = 0;
        for (int i = m + 1; i <= r; i++) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            res += windowR - windowL;
        }


        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= m) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;
    }
}



class CountOfRangeSum4 {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);

    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {
        int res = 0;
        int windowR = l;
        int windowL = l;
        for (int i = m + 1; i <= r; i++) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            res += windowR - windowL;
        }

        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;


    }

}

class CountOfRangeSum5 {
    public static int countRangeSum(int[] num, int lower, int upper) {
        if (num.length == 0 || num == null) {
            return 0;
        }
        long[] sum = new long[num.length];
        sum[0] = num[0];
        for (int i = 1; i < num.length; i++) {
            sum[i] = sum[i - 1] + num[i];
        }
        return process(sum, 0, sum.length - 1,lower,upper);
    }

    public static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);
    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {

        int windowR = l;
        int windowL = l;
        int index = m + 1;
        int res = 0;
        while (index <= r) {
            long max = sum[index] - lower;
            long min = sum[index] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <=m && sum[windowL] < min) {
                windowL++;
            }
            index++;
            res += windowR - windowL;
        }
        int p1 = l;
        int p2 = m+1;
        long[] help = new long[r - l + 1];
        int i = 0;
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }

        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return res;
    }

}


class CountOfRangeSum6{
    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    public static int process(long[] arr, int l, int r, int lower, int upper) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(arr, l, m, lower, upper) + process(arr, m + 1, r, lower, upper) + merge(arr, l, m, r, lower, upper);
    }

    public static int merge(long[] arr,int l,int m,int r,int lower,int upper){
        int windowL = l;
        int windowR = l;
        int index = m + 1;
        int res = 0;
        while (index <= r) {
            long max = arr[index] - lower;
            long min = arr[index] - upper;
            while (windowR<=m&&arr[windowR] <= max) {
                windowR++;
            }
            while (windowL<=m&&arr[windowL] < min) {
                windowL++;
            }
            index++;
            res += windowR - windowL;
        }
        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=m){
            help[i++] = arr[p1++];
        }
        while(p2<=r){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;

    }
}

class CountOfRangeSum7 {
    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1,lower,upper);
    }

    private static int process(long[] sum, int l, int r,int lower,int upper) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(sum, l, m, lower, upper) + process(sum, m + 1, r, lower, upper) + merge(sum, l, m, r, lower, upper);
    }

    private static int merge(long[] sum, int l, int m, int r, int lower, int upper) {
        int windowL = l;
        int windowR = l;
        int i = m + 1;
        int ans = 0;
        while (i <= r) {
            long max = sum[i] - lower;
            long min = sum[i] - upper;
            while (windowR <= m && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= m && sum[windowL] < min) {
                windowL++;
            }
            i++;
            ans += windowR - windowL;
        }
        i = 0;
        int p1 = l;
        int p2 = m + 1;
        long[] help = new long[r - l + 1];
        while (p1 <= m && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= m) {
            help[i++] = sum[p1++];
        }

        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return ans;
    }
}

class MainTest{

    public static int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        for(int i=0;i<nums.length;i++){
            long sum = 0;
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                if(sum>=lower && sum<=upper) count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int testTime = 1000;
        int sizeMax = 5;
        int range = 50;
        System.out.println("start!");

        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomArray(sizeMax, range);
            int[] copyArray = ArrayUtil.copyArray(arr);
            int lower = (int) ((range * Math.random() + 1)-(range * Math.random() + 1));
            int upper;
            do {
                upper = (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
            } while (upper <= lower);
            int sumCount = CountOfRangeSum7.countRangeSum(arr, lower, upper);
            int testSumCount = countRangeSum(copyArray, lower, upper);
            if (sumCount != testSumCount) {
                System.out.println("sumCount :" + sumCount+" testSumCount : "+testSumCount);
                System.out.println("fuck!");
                break;
            }

        }
        System.out.println("end!");

    }



}


