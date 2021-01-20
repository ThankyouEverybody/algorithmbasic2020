package leo.class23;

/**
 * @author Leo
 * @ClassName SplitSumClosed
 * @DATE 2021/1/19 8:18 下午
 * @Description
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class SplitSumClosed {

    static class Recursion{
        public static int minSum(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }
            int sum = 0;
            for (int i : arr) {
                sum += i;
            }
            return process(arr, 0, sum >> 1);
        }

        private static int process(int[] arr, int i, int rest) {
            if (i == arr.length) {
                return 0;
            }
            int p1 = process(arr, i + 1, rest);
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = process(arr, i + 1, rest - arr[i]);
            }
            return Math.max(p1, p2);
        }
    }

    static class Dp {
        public static int minSum(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }
            int sum = 0;
            for (int i : arr) {
                sum += i;
            }
            sum /= 2;
            int n = arr.length;
            int[][] dp = new int[n + 1][sum + 1];
            for (int i = n - 1; i >= 0; i--) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[i + 1][rest];
                    int p2 = 0;
                    if (arr[i] <= rest) {
                        p2 = dp[i + 1][rest - arr[i]];
                    }
                    dp[i][rest] = Math.max(p1, p2);
                }
            }
            return dp[0][sum];
        }
    }


    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = Recursion.minSum(arr);
            int ans2 = Dp.minSum(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
