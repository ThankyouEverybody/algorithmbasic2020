package leo.class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Leo
 * @ClassName KM
 * @DATE 2020/11/15 6:48 下午
 * @Description arr中只有一种数出现了K次,其他数出了M次,k<m,找出出现K次的数.
 * & 遇0则0; | 遇1则1;
 */
public class KM {

    public static int testForOnlyKTimes(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }

        return -1;
    }

    public static int onlyKTimes(int[] arr, int k, int m) {

        //准备32位的数组,用于记录arr中所有出现数的每一位出现1的次数
        int[] t = new int[32];

        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            //如果t的某一位取模m不等0;说明出现k次数的位上有1
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;

    }


    public static int onlyKTimes1(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static int onlyKTimes2(int[] arr, int k, int m) {
        int[] t = new int[32];
        for(int num:arr){
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static int onlyKTimes3(int[]arr,int k,int m){
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int j = 0; j < t.length; j++) {
            if (t[j] % m != 0) {
                ans |= 1 << j;

            }
        }
        return ans;
    }

    public static int onlyKTime4(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }


    public static int onlyKTime5(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << i);
                }else{
                    return -1;
                }
            }
        }

        if (ans == 0) {
            int count = 0;
            for (int num : arr) {

                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }

        return ans;
    }

    public static int onlyKTime6(int[] arr,int k,int m){
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << i);
                }else{
                    return -1;
                }
            }
        }

        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count == k) {
                return ans;
            } else {
                return  -1;
            }
        }


        return ans;

    }

    public static int onlyKTime7(int[] arr, int k, int m) {
        int[]  t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i]%m!=0){
                if (t[i] % m == k) {
                    res |= (1 << i);
                } else{
                    return -1;
                }
            }
        }

        if (res == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count == k) {
                return res;
            }else{
                return -1;
            }
        }
        return res;

    }

    public static int onlyKTime8(int[] arr, int k, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[] t = new int[32];
        for (int num : arr) {
            for (int i = 0; i < t.length; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << i);
                } else {
                    return -1;
                }
            }
        }
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }

            }
            if (count == k) {
                return 0;
            }else {
                return -1;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int maxKinds = 20;
        int range = 50;
        int testTimes = 10000;
        int max = 9;
        System.out.println("开始");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (max * Math.random() + 1);
            int b = (int) (max * Math.random() + 1);
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(maxKinds, range, k, m);

            int ans = onlyKTime8(arr, k, m);
            int ans2 = testForOnlyKTimes(arr, k, m);
            if (ans != ans2) {
                System.out.println(ans);
                System.out.println(ans2);
                System.out.println("出错了!!");
//                break;
            }
        }
        System.out.println("结束");
    }


    private static int[] randomArray1(int maxKinds, int range, int k, int m) {
        //k
        int kNumber = randomInt(range);
        //随机生成共有几种数
        int kinds = (int) (maxKinds * Math.random() + 1);
        int arrLength = k + (kinds - 1) * m;
        int[] arr = new int[arrLength];

        int index = 0;
        HashSet<Integer> set = new HashSet<>();
        for (; index < k; index++) {
            arr[index] = kNumber;
        }
        kinds--;
        set.add(kNumber);
        while (kinds != 0) {
            int curNum = 0;
            do {
                curNum = randomInt(range);
            } while (set.contains(curNum));
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
            kinds--;
        }
        return arr;
    }

    /**
     * 功能描述 :
     * @author Leo
     * @date 2020/11/15 8:01 下午
     * @param maxKinds 最多几种数
     * @param range 范围
     * @param k k出现的次数
     * @param m m出现的次数
     * @throw
     * @return int[]
     */
    private static int[] randomArray(int maxKinds, int range, int k, int m) {
        //一共几种数,必须大于2
        int numKinds = (int) (maxKinds * Math.random() + 2);
        //出现k的数
        int kValue = randomInt(range);
        int Ktime = Math.random() < 0.5 ? k : (int) ((Math.random() * (m - 1)) + 1);
        //arr长度
        int[] arr = new int[Ktime + (numKinds - 1) * m];

        int index = 0;
        for (; index < Ktime; index++) {
            arr[index] = kValue;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(kValue);
        while (numKinds != 0) {
            int curNum;
            do {
                curNum = randomInt(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }

        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (arr.length * Math.random());
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    private static int[] randomArray2(int maxKinds, int range, int k, int m) {
        int kind = (int) (maxKinds * Math.random() + 1);
        int arrLength = k + (kind - 1) * m;
        int kNumber = randomInt(range);
        int[] arr = new int[arrLength];
        int index = 0;
        HashSet<Integer> set = new HashSet<>();
        for (; index < k; index++) {
            arr[index] = kNumber;
        }
        set.add(kNumber);
        kind--;
        while (kind != 0) {
            int curNum;
            do {
                curNum = randomInt(range);
            } while (set.contains(curNum));
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
            set.add(curNum);
            kind--;
        }
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (arrLength * Math.random());
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;

    }


    private static int randomInt(int range) {
        return (int) ((range * Math.random() + 1) - (range * Math.random() + 1));
    }


}
